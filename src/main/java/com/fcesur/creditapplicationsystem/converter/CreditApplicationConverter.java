package com.fcesur.creditapplicationsystem.converter;

import com.fcesur.creditapplicationsystem.entity.CreditApplication;
import com.fcesur.creditapplicationsystem.request.CreditApplicationCreateRequest;
import com.fcesur.creditapplicationsystem.response.CreditApplicationResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CreditApplicationConverter {

    public CreditApplication requestToEntity(CreditApplicationCreateRequest request) {
        CreditApplication entity = new CreditApplication();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }

    public CreditApplicationResponse entityToResponse(CreditApplication entity) {
        CreditApplicationResponse response = new CreditApplicationResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
