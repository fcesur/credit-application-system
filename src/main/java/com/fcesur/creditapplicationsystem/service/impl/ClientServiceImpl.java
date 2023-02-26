package com.fcesur.creditapplicationsystem.service.impl;


import com.fcesur.creditapplicationsystem.converter.ClientConverter;
import com.fcesur.creditapplicationsystem.entity.Client;
import com.fcesur.creditapplicationsystem.exception.ResourceNotFoundException;
import com.fcesur.creditapplicationsystem.repository.ClientRepository;
import com.fcesur.creditapplicationsystem.request.ClientCreateRequest;
import com.fcesur.creditapplicationsystem.request.ClientUpdateRequest;
import com.fcesur.creditapplicationsystem.response.ClientResponse;
import com.fcesur.creditapplicationsystem.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientConverter converter;

    @Override
    public ClientResponse findById(Long id) {
        Client client = clientRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id : " + id));
        return converter.entityToResponse(client);
    }

    @Override
    public List<ClientResponse> findAll() {
        return clientRepository
                .findAll()
                .stream()
                .map(converter::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponse save(ClientCreateRequest request) {

        Client client = converter.requestToEntity(request);

        Client savedClient = clientRepository.save(client);

        return converter.entityToResponse(savedClient);

    }

    @Override
    public ClientResponse updateById(Long id, ClientUpdateRequest request) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id : " + id));

        client.setBirthday(request.getBirthday());
        client.setName(request.getName());
        client.setIncome(request.getIncome());
        client.setIdentificationNumber(request.getIdentificationNumber());
        client.setPhone(request.getPhone());

        return converter.entityToResponse(clientRepository.save(client));
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
