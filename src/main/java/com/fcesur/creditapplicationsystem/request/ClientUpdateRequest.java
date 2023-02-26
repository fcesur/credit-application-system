package com.fcesur.creditapplicationsystem.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class ClientUpdateRequest {
    private String identificationNumber;
    private String name;
    private Double income;
    private String phone;
    private LocalDate birthday;
}
