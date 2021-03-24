package com.moeny.ussd.dao;

import com.moeny.ussd.util.ProcessFlag;
import lombok.Data;

@Data
public class UssdResponseDto {

    private String sessionId;
    private String message;
    private String country;
    private String msisdn;
    private String amount;
    private ProcessFlag processFlag;
}
