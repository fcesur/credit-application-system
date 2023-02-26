package com.fcesur.creditapplicationsystem.converter;

import com.fcesur.creditapplicationsystem.entity.Client;
import com.fcesur.creditapplicationsystem.request.ClientCreateRequest;
import com.fcesur.creditapplicationsystem.request.ClientRequest;
import com.fcesur.creditapplicationsystem.response.ClientResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter {

    public Client requestToEntity(ClientCreateRequest request) {
        Client entity = new Client();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }

//    public Client requestToEntity(ClientRequest request) {
//        Client entity = new Client();
//        BeanUtils.copyProperties(request, entity);
//        return entity;
//    }

    public ClientResponse entityToResponse(Client entity) {
        ClientResponse response = new ClientResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public Client responseToEntity(ClientResponse response) {
        Client entity = new Client();
        BeanUtils.copyProperties(response, entity);
        return entity;
    }

}
