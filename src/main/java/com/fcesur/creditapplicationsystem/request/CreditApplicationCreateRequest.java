package com.fcesur.creditapplicationsystem.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreditApplicationCreateRequest {
    private ClientRequest client;

    private Double collateral;
}
