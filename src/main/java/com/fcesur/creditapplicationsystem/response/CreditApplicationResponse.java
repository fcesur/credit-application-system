package com.fcesur.creditapplicationsystem.response;


import com.fcesur.creditapplicationsystem.enums.CreditApplicationStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditApplicationResponse {
    @Enumerated(EnumType.STRING)
    private CreditApplicationStatusEnum status;
    private CreditLimitResponse credit;
    private ClientResponse client;
}
