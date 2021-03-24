package com.moeny.ussd.rest.request;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

@Data
@GroupSequence(value = {Nullable.class})
public class UssdRequestResource {

    private String sessionId;
    @NotNull@NotNull(message = "msisdn may not be empty", groups = Nullable.class)
    private String msisdn;
    private String userEntry;

}
