package com.nttdata.customer.microservice.service;

import com.nttdata.customer.microservice.domain.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> save(Mono<Customer> customer);
    Mono<Customer> findById(String id);
    Mono<Customer> findByDni(String dni);
    Mono<Customer> update(String id, Mono<Customer> customer);
    Mono<Void> delete(Customer customer);
}
