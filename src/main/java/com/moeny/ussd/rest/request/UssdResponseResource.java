package com.moeny.ussd.rest.request;

import lombok.Data;

@Data
public class UssdResponseResource {

    private String sessionId;
    private String message;
}
