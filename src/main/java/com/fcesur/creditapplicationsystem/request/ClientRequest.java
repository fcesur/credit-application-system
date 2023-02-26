package com.fcesur.creditapplicationsystem.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ClientRequest {
    private Long id;
    private String identificationNumber;
    private String name;
    private Double income;
    private String phone;
    private LocalDate birthday;
}
