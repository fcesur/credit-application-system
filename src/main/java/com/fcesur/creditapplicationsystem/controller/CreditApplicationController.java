package com.fcesur.creditapplicationsystem.controller;


import com.fcesur.creditapplicationsystem.request.CreditApplicationCreateRequest;
import com.fcesur.creditapplicationsystem.response.CreditApplicationResponse;
import com.fcesur.creditapplicationsystem.service.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credit-application")
public class CreditApplicationController {

    private final CreditApplicationService creditApplicationService;

    @PostMapping
    public ResponseEntity<CreditApplicationResponse> createCreditApplication(@RequestBody CreditApplicationCreateRequest creditApplicationCreateRequest) {
        return ResponseEntity.ok(creditApplicationService.save(creditApplicationCreateRequest));
    }

}
