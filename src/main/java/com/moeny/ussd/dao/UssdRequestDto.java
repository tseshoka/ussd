package com.moeny.ussd.dao;

import com.moeny.ussd.util.ProcessFlag;
import lombok.Data;

@Data
public class UssdRequestDto {

    private String sessionId;
    private String msisdn;
    private String userEntry;
    private ProcessFlag processFlag;
}
