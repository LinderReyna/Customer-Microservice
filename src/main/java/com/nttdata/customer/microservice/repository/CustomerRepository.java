package com.nttdata.customer.microservice.repository;

import com.nttdata.customer.microservice.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository  extends ReactiveMongoRepository<Customer, String> {
    Mono<Customer> findByDni(String dni);
}
