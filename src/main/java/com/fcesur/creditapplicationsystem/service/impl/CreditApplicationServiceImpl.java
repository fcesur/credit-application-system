package com.fcesur.creditapplicationsystem.service.impl;

import com.fcesur.creditapplicationsystem.converter.ClientConverter;
import com.fcesur.creditapplicationsystem.converter.CreditApplicationConverter;
import com.fcesur.creditapplicationsystem.converter.CreditScoreConverter;
import com.fcesur.creditapplicationsystem.entity.Client;
import com.fcesur.creditapplicationsystem.entity.Credit;
import com.fcesur.creditapplicationsystem.entity.CreditApplication;
import com.fcesur.creditapplicationsystem.entity.CreditScore;
import com.fcesur.creditapplicationsystem.exception.ResourceNotFoundException;
import com.fcesur.creditapplicationsystem.repository.CreditApplicationRepository;
import com.fcesur.creditapplicationsystem.request.CreditApplicationCreateRequest;
import com.fcesur.creditapplicationsystem.response.CreditApplicationResponse;
import com.fcesur.creditapplicationsystem.service.ClientService;
import com.fcesur.creditapplicationsystem.service.CreditApplicationService;
import com.fcesur.creditapplicationsystem.service.CreditScoreService;
import com.fcesur.creditapplicationsystem.service.CreditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.fcesur.creditapplicationsystem.enums.CreditApplicationStatusEnum.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditApplicationServiceImpl implements CreditApplicationService {

    private static final Float CREDIT_LIMIT_MULTIPLIER = 4F;


    private final CreditApplicationRepository creditApplicationRepository;
    private final CreditService creditService;
    private final CreditScoreService creditScoreService;
    private final ClientService clientService;

    private final CreditApplicationConverter creditApplicationConverter;
    private final ClientConverter clientConverter;
    private final CreditScoreConverter creditScoreConverter;


    @Override
    public CreditApplicationResponse findById(Long id) {
        CreditApplication creditApplication = creditApplicationRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit Application not found with id: " + id));

        return creditApplicationConverter.entityToResponse(creditApplication);
    }

    @Override
    public List<CreditApplicationResponse> findAll() {
        return creditApplicationRepository
                .findAll()
                .stream()
                .map(creditApplicationConverter::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CreditApplicationResponse save(CreditApplicationCreateRequest request) {

        CreditApplication creditApplication = new CreditApplication();
        Credit credit = null;


        Client client = clientConverter.responseToEntity(clientService.findById(request.getClient().getId()));

        CreditScore creditScore = creditScoreConverter.responseToEntity(
                creditScoreService.findCreditScoreByClientIdentificationNumber(
                        client.getIdentificationNumber()
                )
        );

        Integer clientCreditScore = creditScore.getCreditScore();

        if (clientCreditScore < 500) {
            creditApplication.setStatus(REJECTED);
        }
        if (clientCreditScore >= 500 && clientCreditScore < 1000) {
            credit = calculateCreditByIncome(client.getIncome(), request.getCollateral());
            creditApplication.setStatus(APPROVED);

        } else {
            credit = calculateCreditByIncome(client.getIncome(), creditApplication.getCollateral());
            creditApplication.setStatus(APPROVED);
        }

        if (credit != null) {
            creditApplication.setCredit(creditService.save(credit));
        }

        creditApplication.setClient(client);

        CreditApplication savedCreditApplication = creditApplicationRepository.save(creditApplication);

        return creditApplicationConverter.entityToResponse(savedCreditApplication);
    }

    @Override
    public void deleteById(Long id) {
        creditApplicationRepository.deleteById(id);
    }


    private Credit calculateCreditByIncome(Double income, Double collateral) {
        Credit credit = new Credit();
        Integer collateralAddPercentage = 0;

        if (income < 5000) {
            credit.setCreditLimit(10000D);
            collateralAddPercentage = 10;
        }
        if (income >= 5000 && income < 10000) {
            credit.setCreditLimit(20000D);
            collateralAddPercentage = 20;
        }
        if (income > 10000) {
            credit.setCreditLimit(income * CREDIT_LIMIT_MULTIPLIER / 2);
            collateralAddPercentage = 25;
        }
        if (collateral > 0)
            credit = addCreditCollateral(credit, collateral, collateralAddPercentage);

        return credit;
    }

    private Credit addCreditCollateral(Credit credit, Double collateral, Integer percentage) {
        credit.setCreditLimit(credit.getCreditLimit() + collateral * percentage / 100);
        return credit;
    }



}
