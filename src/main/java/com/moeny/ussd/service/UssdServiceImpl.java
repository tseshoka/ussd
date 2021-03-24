package com.moeny.ussd.service;

import com.moeny.ussd.dao.UssdRequestDto;
import com.moeny.ussd.dao.UssdResponseDto;
import com.moeny.ussd.dao.UssdSession;
import com.moeny.ussd.util.ProcessFlag;
import com.moeny.ussd.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class UssdServiceImpl implements  UssdService {

    @Autowired
    private Environment environment;

    @Override
    public UssdResponseDto processUssd(UssdRequestDto dto, UssdSession session) {

        UssdResponseDto ussdResponseDto = new UssdResponseDto();
        if (dto == null || session == null) {
            return ussdResponseDto;
        }

        if (dto.getSessionId() == null ) {

            String message = environment.getProperty("prompt.message.welcome");
            ussdResponseDto = setUssdResponseDto(ussdResponseDto, dto, message);
            ussdResponseDto.setProcessFlag(ProcessFlag.INITIAL);

        } else if (ProcessFlag.INITIAL.equals(dto.getProcessFlag())) {

           String country = Util.getCountry(dto.getUserEntry());
           if(country != null) {
               String message = environment.getProperty("prompt.message.amount") + country + "?";
               ussdResponseDto = setUssdResponseDto(ussdResponseDto, dto, message);

               ussdResponseDto.setCountry(country);
               ussdResponseDto.setProcessFlag(ProcessFlag.COUNTRY);
           }
       } else if (ProcessFlag.COUNTRY.equals(dto.getProcessFlag())) {

            String amount = Util.convertAmount(session.getCountry(), Double.parseDouble(dto.getUserEntry()));
            String message = environment.getProperty("prompt.message.confirmation") + amount;
            ussdResponseDto = setUssdResponseDto(ussdResponseDto, dto, message);

            ussdResponseDto.setCountry(session.getCountry());
            ussdResponseDto.setProcessFlag(ProcessFlag.AMOUNT);
            ussdResponseDto.setAmount(amount);
            session.setAmount(dto.getUserEntry());
        } else if (ProcessFlag.AMOUNT.equals(dto.getProcessFlag())) {

            String message = environment.getProperty("prompt.message.done");
            ussdResponseDto = setUssdResponseDto(ussdResponseDto, dto,message);
            ussdResponseDto.setCountry(session.getCountry());
            ussdResponseDto.setProcessFlag(ProcessFlag.ACCEPTED);
        }

        return ussdResponseDto;
    }

    private UssdResponseDto setUssdResponseDto (UssdResponseDto ussdResponseDto, UssdRequestDto dto, String message) {

        ussdResponseDto.setSessionId(dto.getSessionId());
        ussdResponseDto.setMsisdn(dto.getMsisdn());
        ussdResponseDto.setMessage(message);
        return  ussdResponseDto;
    }
}
