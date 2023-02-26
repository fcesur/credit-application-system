package com.fcesur.creditapplicationsystem.converter;

import com.fcesur.creditapplicationsystem.entity.CreditScore;
import com.fcesur.creditapplicationsystem.request.CreditScoreCreateRequest;
import com.fcesur.creditapplicationsystem.request.CreditScoreRequest;
import com.fcesur.creditapplicationsystem.response.ClientResponse;
import com.fcesur.creditapplicationsystem.response.CreditScoreResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CreditScoreConverter {

    public CreditScoreResponse entityToResponse(CreditScore entity) {
        CreditScoreResponse response = new CreditScoreResponse();

        if (entity.getClient() != null) {
            ClientResponse clientResponse = new ClientResponse();
            BeanUtils.copyProperties(entity.getClient(), clientResponse);
            response.setClient(clientResponse);
        }

        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public CreditScore requestToEntity(CreditScoreRequest request) {
        CreditScore entity = new CreditScore();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }

    public CreditScore requestToEntity(CreditScoreCreateRequest request) {
        CreditScore entity = new CreditScore();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }

    public CreditScore responseToEntity(CreditScoreResponse response) {
        CreditScore entity = new CreditScore();
        BeanUtils.copyProperties(response, entity);
        return entity;
    }
}
