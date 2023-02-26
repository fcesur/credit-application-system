package com.fcesur.creditapplicationsystem.repository;

import com.fcesur.creditapplicationsystem.entity.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {
}
