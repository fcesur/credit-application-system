package com.fcesur.creditapplicationsystem.service;

import com.fcesur.creditapplicationsystem.request.CreditApplicationCreateRequest;
import com.fcesur.creditapplicationsystem.response.CreditApplicationResponse;

import java.util.List;

public interface CreditApplicationService {
    CreditApplicationResponse findById(Long id);

    List<CreditApplicationResponse> findAll();

    CreditApplicationResponse save(CreditApplicationCreateRequest request);

    void deleteById(Long id);
}
