package com.fcesur.creditapplicationsystem.request;

import com.fcesur.creditapplicationsystem.entity.Client;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreditScoreCreateRequest {
    private Integer creditScore;
}
