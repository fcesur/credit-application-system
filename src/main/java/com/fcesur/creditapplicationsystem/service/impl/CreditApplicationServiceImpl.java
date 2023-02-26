package com.fcesur.creditapplicationsystem.service.impl;

import com.fcesur.creditapplicationsystem.converter.ClientConverter;
import com.fcesur.creditapplicationsystem.converter.CreditApplicationConverter;
import com.fcesur.creditapplicationsystem.entity.Client;
import com.fcesur.creditapplicationsystem.entity.Credit;
import com.fcesur.creditapplicationsystem.entity.CreditApplication;
import com.fcesur.creditapplicationsystem.enums.CreditApplicationStatusEnum;
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
    private final CreditApplicationRepository creditApplicationRepository;

    private final CreditService creditService;
    private final CreditScoreService creditScoreService;
    private final ClientService clientService;

    private final CreditApplicationConverter creditApplicationConverter;
    private final ClientConverter clientConverter;

    @Override
    public CreditApplicationResponse findById(Long id) {
        CreditApplication creditApplication = creditApplicationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Credit Application not found with id: " + id));

        return creditApplicationConverter.entityToResponse(creditApplication);
    }

    @Override
    public List<CreditApplicationResponse> findAll() {
        return creditApplicationRepository.findAll()
                .stream()
                .map(creditApplicationConverter::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CreditApplicationResponse save(CreditApplicationCreateRequest request) {

        Client client = clientConverter.responseToEntity(clientService.findById(request.getClient().getId()));

        Integer clientCreditScore = creditScoreService
                .findCreditScoreByClientIdentificationNumber(client.getIdentificationNumber())
                .getCreditScore();

        CreditApplication creditApplication = CreditApplication.builder()
                .client(client)
                .status(evaluateCreditStatusByCreditScore(clientCreditScore))
                .collateral(request.getCollateral())
                .build();

        if (creditApplication.getStatus() == APPROVED) {
            Credit credit = creditService.evaluateCredit(clientCreditScore, client.getIncome(), request.getCollateral());
            credit.setClient(client);
            credit.setCreditApplication(creditApplication);
            creditApplication.setCredit(credit);
            creditService.save(credit);
        }

        CreditApplication savedCreditApplication = creditApplicationRepository.save(creditApplication);

        return creditApplicationConverter.entityToResponse(savedCreditApplication);
    }


    private CreditApplicationStatusEnum evaluateCreditStatusByCreditScore(Integer clientCreditScore) {
        return clientCreditScore < 500 ? REJECTED : APPROVED;
    }

    @Override
    public void deleteById(Long id) {
        creditApplicationRepository.deleteById(id);
    }

}
