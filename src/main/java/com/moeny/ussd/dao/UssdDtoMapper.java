package com.moeny.ussd.dao;

import com.moeny.ussd.rest.request.UssdRequestResource;
import com.moeny.ussd.rest.request.UssdResponseResource;

public class UssdDtoMapper {

    public UssdRequestDto mapFrom(UssdRequestResource resource) {

        UssdRequestDto dto = null;
        if(resource != null) {
            dto = new UssdRequestDto();
            dto.setMsisdn(resource.getMsisdn());
            dto.setSessionId(resource.getSessionId());
            dto.setUserEntry(resource.getUserEntry());
        }

        return dto;
    }

    public UssdSession mapFrom (UssdResponseDto dto) {

        UssdSession session = null;
        if (dto != null) {
            session = new UssdSession();
            session.setSessionId(dto.getMsisdn());
            session.setCountry(dto.getCountry());
            session.setAmount(dto.getAmount());
            session.setMsisdn(dto.getMsisdn());
            session.setProcessFlag(dto.getProcessFlag());
        }

        return session;

    }

    public UssdResponseResource mapTo (UssdResponseDto dto) {

        UssdResponseResource resource = null;
        if (dto != null) {
            resource = new UssdResponseResource();
            resource.setSessionId(dto.getMsisdn());
            resource.setMessage(dto.getMessage());
        }
        return resource;
    }
}
