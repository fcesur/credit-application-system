package com.fcesur.creditapplicationsystem.response;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreditScoreResponse implements Serializable {

    private ClientResponse client;
    private Integer creditScore;
}
