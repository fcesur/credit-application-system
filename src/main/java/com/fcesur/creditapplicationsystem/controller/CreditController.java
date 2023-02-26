package com.fcesur.creditapplicationsystem.controller;


import com.fcesur.creditapplicationsystem.response.CreditResponse;
import com.fcesur.creditapplicationsystem.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credit")
public class CreditController {

    private final CreditService creditService;

    @GetMapping("/{id}")
    public ResponseEntity<CreditResponse> getCreditById(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.findById(id));
    }

    @GetMapping
    public ResponseEntity<CreditResponse> getCreditByClientIdentificationNumber(@RequestBody String identificationNumber) {
        return ResponseEntity.ok(creditService.findCreditByClientIdentificationNumber(identificationNumber));
    }
}
