package com.moeny.ussd.rest;

import com.moeny.ussd.dao.UssdDtoMapper;
import com.moeny.ussd.dao.UssdRequestDto;
import com.moeny.ussd.dao.UssdResponseDto;
import com.moeny.ussd.dao.UssdSession;
import com.moeny.ussd.rest.request.UssdRequestResource;
import com.moeny.ussd.rest.request.UssdResponseResource;
import com.moeny.ussd.service.UssdService;
import com.moeny.ussd.util.ProcessFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
public class USSDController {

    private UssdService ussdService;
    private UssdDtoMapper mapper;

    @Autowired
    private USSDController (UssdService ussdService) {

        this.ussdService = ussdService;
        mapper = new UssdDtoMapper();
    }

    @PostMapping(
        value = "ussd",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UssdResponseResource> requestUssd(@Valid @RequestBody UssdRequestResource requestResource,
                                                            HttpServletRequest request) {

        Map<String, UssdSession> sessions = (Map<String, UssdSession>) request.getSession().getAttribute(requestResource.getMsisdn());

        if (sessions == null) {
            sessions = new HashMap<>();
        }

        UssdRequestDto dto = mapper.mapFrom(requestResource);

        UssdSession session = sessions.get(requestResource.getMsisdn());
        dto.setProcessFlag(session != null ? session.getProcessFlag() : ProcessFlag.INITIAL);

        UssdResponseDto responseDto = ussdService.processUssd(dto, sessions.get(requestResource.getMsisdn()));

        if (ProcessFlag.ACCEPTED.equals(responseDto.getProcessFlag())) {
            request.getSession().invalidate();
        }

        sessions.put(requestResource.getMsisdn(), mapper.mapFrom(responseDto));
        request.getSession().setAttribute(requestResource.getMsisdn(), sessions);

        UssdResponseResource resource = mapper.mapTo(responseDto);

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
