package com.fcesur.creditapplicationsystem.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCreditScoreRequest {
    private Long clientId;

    private Integer creditScore;
}
