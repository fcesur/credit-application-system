package com.fcesur.creditapplicationsystem.converter;


import com.fcesur.creditapplicationsystem.entity.Credit;
import com.fcesur.creditapplicationsystem.response.ClientResponse;
import com.fcesur.creditapplicationsystem.response.CreditResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CreditConverter {

    public CreditResponse entityToResponse(Credit entity) {

        CreditResponse response = new CreditResponse();

        BeanUtils.copyProperties(entity, response);

        if (entity.getClient() != null) {
            ClientResponse clientResponse = new ClientResponse();
            BeanUtils.copyProperties(entity.getClient(), clientResponse);
            response.setClient(clientResponse);
        }

        return response;
    }
}
