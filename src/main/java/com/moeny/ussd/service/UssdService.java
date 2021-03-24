package com.moeny.ussd.service;

import com.moeny.ussd.dao.UssdRequestDto;
import com.moeny.ussd.dao.UssdResponseDto;
import com.moeny.ussd.dao.UssdSession;

public interface UssdService {

    UssdResponseDto processUssd (UssdRequestDto dto, UssdSession session);
}
