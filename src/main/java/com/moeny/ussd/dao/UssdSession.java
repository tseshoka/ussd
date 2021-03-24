package com.moeny.ussd.dao;

import com.moeny.ussd.util.ProcessFlag;
import lombok.Data;

import java.io.Serializable;

@Data
public class UssdSession implements Serializable {

    private String sessionId;
    private String country;
    private String msisdn;
    private String amount;
    private ProcessFlag processFlag;
}
