package com.nttdata.customer.microservice.service;

import com.nttdata.customer.microservice.domain.Customer;
import com.nttdata.customer.microservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<Customer> save(Mono<Customer> customer) {
        return customer.flatMap(customerRepository::insert);
    }

    @Override
    public Mono<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public Mono<Customer> findByDni(String dni) {
        return customerRepository.findByDni(dni);
    }

    @Override
    public Mono<Customer> update(String id, Mono<Customer> customer) {
        return customerRepository.findById(id)
                .flatMap(c -> customer)
                .doOnNext(e -> e.setId(id))
                .flatMap(customerRepository::save);
    }

    @Override
    public Mono<Void> delete(Customer customer) {
        return customerRepository.delete(customer);
    }
}
