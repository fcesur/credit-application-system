package com.fcesur.creditapplicationsystem.service.impl;

import com.fcesur.creditapplicationsystem.converter.CreditConverter;
import com.fcesur.creditapplicationsystem.entity.Credit;
import com.fcesur.creditapplicationsystem.exception.ResourceNotFoundException;
import com.fcesur.creditapplicationsystem.repository.CreditRepository;
import com.fcesur.creditapplicationsystem.response.CreditResponse;
import com.fcesur.creditapplicationsystem.service.CreditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private static final Float CREDIT_LIMIT_MULTIPLIER = 4F;
    private final CreditRepository creditRepository;
    private final CreditConverter converter;

    @Override
    public CreditResponse findById(Long id) {
        return converter.entityToResponse(
                creditRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Credit not found with id : " + id)
                        )
        );
    }

    @Override
    public CreditResponse findCreditByClientIdentificationNumber(String identificationNumber) {
        return converter.entityToResponse(
                creditRepository.findCreditByClientIdentificationNumber(identificationNumber)
        );
    }

    @Override
    public Credit save(Credit credit) {
        return creditRepository.save(credit);
    }

    @Override
    public void deleteById(Long id) {
        creditRepository.deleteById(id);
    }

    @Override
    public Credit evaluateCredit(Integer creditScore, Double income, Double collateral) {
        Credit credit = new Credit();

        if (creditScore >= 500 && creditScore < 1000) {
            credit.setCreditLimit(evaluateStandardCreditLimit(income, collateral));
        } else {
            credit.setCreditLimit(evaluateExclusiveCreditLimit(income, collateral));
        }

        return credit;
    }

    private Double evaluateStandardCreditLimit(Double income, Double collateral) {
        Double creditLimit;
        Double collateralPercentage;

        if (income < 5000) {
            creditLimit = 10000D;
            collateralPercentage = 10D;
        } else if (income >= 5000 && income < 10000) {
            creditLimit = 20000D;
            collateralPercentage = 20D;
        } else {
            creditLimit = income * CREDIT_LIMIT_MULTIPLIER / 2;
            collateralPercentage = 25D;
        }

        if (collateral > 0) {
            creditLimit += addCreditCollateral(collateral, collateralPercentage);
        }

        return creditLimit;
    }

    private Double evaluateExclusiveCreditLimit(Double income, Double collateral) {
        Double creditLimit = income * CREDIT_LIMIT_MULTIPLIER;
        Double collateralPercentage = 50D;

        if (collateral > 0) {
            creditLimit += addCreditCollateral(collateral, collateralPercentage);
        }

        return creditLimit;
    }

    private Double addCreditCollateral(Double collateral, Double collateralPercentage) {
        return (collateral * collateralPercentage) / 100D;
    }

}
