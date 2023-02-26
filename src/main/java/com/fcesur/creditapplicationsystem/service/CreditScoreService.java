package com.fcesur.creditapplicationsystem.service;

import com.fcesur.creditapplicationsystem.response.CreditScoreResponse;

public interface CreditScoreService {

    CreditScoreResponse findCreditScoreByClientIdentificationNumber(String identificationNumber);

    CreditScoreResponse setCreditScoreById(Long id, Integer creditScore);
}
