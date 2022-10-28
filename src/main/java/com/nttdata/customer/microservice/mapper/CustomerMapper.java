package com.nttdata.customer.microservice.mapper;

import com.nttdata.customer.microservice.model.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.Date;
@Component
public class CustomerMapper implements EntityMapper<Customer, com.nttdata.customer.microservice.document.Customer>{
    @Override
    public com.nttdata.customer.microservice.document.Customer toDocument(Customer model) {
        com.nttdata.customer.microservice.document.Customer customer = new com.nttdata.customer.microservice.document.Customer();
        BeanUtils.copyProperties(model, customer);
        customer.setCreatedAt(new Date());
        return customer;
    }

    @Override
    public Customer toModel(com.nttdata.customer.microservice.document.Customer document) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(document, customer);
        customer.setCreatedAt(document.getCreatedAt().toInstant().atOffset(ZoneOffset.UTC));
        return customer;
    }
}
