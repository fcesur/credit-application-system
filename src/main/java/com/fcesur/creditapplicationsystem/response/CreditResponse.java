package com.fcesur.creditapplicationsystem.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreditResponse {
    private ClientResponse client;
    private Double creditLimit;
}
