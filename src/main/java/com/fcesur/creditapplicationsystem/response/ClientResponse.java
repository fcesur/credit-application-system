package com.fcesur.creditapplicationsystem.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
public class ClientResponse implements Serializable {
    private Long id;
    private String identificationNumber;
    private String name;
    private Double income;
    private String phone;
    private LocalDate birthday;
}
