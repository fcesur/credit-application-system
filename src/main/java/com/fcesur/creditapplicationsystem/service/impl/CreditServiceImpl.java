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
}
