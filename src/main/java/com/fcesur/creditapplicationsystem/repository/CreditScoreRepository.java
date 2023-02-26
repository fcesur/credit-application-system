package com.fcesur.creditapplicationsystem.repository;

import com.fcesur.creditapplicationsystem.entity.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditScoreRepository extends JpaRepository<CreditScore, Long> {
    CreditScore findByClientIdentificationNumber(String identificationNumber);
}
