package com.fcesur.creditapplicationsystem.controller;

import com.fcesur.creditapplicationsystem.request.ClientCreateRequest;
import com.fcesur.creditapplicationsystem.request.ClientUpdateRequest;
import com.fcesur.creditapplicationsystem.response.ClientResponse;
import com.fcesur.creditapplicationsystem.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientCreateRequest request) {
        return ResponseEntity.ok(clientService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> updateClient(@RequestBody ClientUpdateRequest clientUpdateRequest, @PathVariable Long id) {

        return ResponseEntity.ok(clientService.updateById(id, clientUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteClient(Long id) {
        clientService.deleteById(id);
    }
}
