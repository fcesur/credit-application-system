package com.fcesur.creditapplicationsystem.repository;

import com.fcesur.creditapplicationsystem.entity.Credit;
import com.fcesur.creditapplicationsystem.response.CreditResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    Credit findCreditByClientIdentificationNumber(String identificationNumber);
}
