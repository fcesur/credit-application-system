package com.fcesur.creditapplicationsystem.service;

import com.fcesur.creditapplicationsystem.request.ClientCreateRequest;
import com.fcesur.creditapplicationsystem.request.ClientUpdateRequest;
import com.fcesur.creditapplicationsystem.response.ClientResponse;


import java.util.List;

public interface ClientService {

    ClientResponse findById(Long id);

    List<ClientResponse> findAll();

    ClientResponse save(ClientCreateRequest request);

    ClientResponse updateById(Long id, ClientUpdateRequest request);

    void deleteById(Long id);

}
