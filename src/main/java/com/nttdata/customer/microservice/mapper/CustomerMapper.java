package com.nttdata.customer.microservice.mapper;

import com.nttdata.customer.microservice.model.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements EntityMapper<Customer, com.nttdata.customer.microservice.domain.Customer>{
    @Override
    public com.nttdata.customer.microservice.domain.Customer toDomain(Customer model) {
        com.nttdata.customer.microservice.domain.Customer customer = new com.nttdata.customer.microservice.domain.Customer();
        BeanUtils.copyProperties(model, customer);
        return customer;
    }

    @Override
    public Customer toModel(com.nttdata.customer.microservice.domain.Customer domain) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(domain, customer);
        return customer;
    }
}
