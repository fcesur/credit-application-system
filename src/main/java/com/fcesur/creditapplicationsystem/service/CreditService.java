package com.fcesur.creditapplicationsystem.service;

import com.fcesur.creditapplicationsystem.entity.Credit;
import com.fcesur.creditapplicationsystem.response.CreditResponse;

public interface CreditService {
    CreditResponse findById(Long id);
    CreditResponse findCreditByClientIdentificationNumber(String identificationNumber);
    Credit save(Credit credit);
    void deleteById(Long id);
    Credit evaluateCredit(Integer creditScore, Double income, Double collateral);
}
