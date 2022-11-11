package com.nttdata.customer.microservice.controller;

import com.nttdata.customer.microservice.api.CustomerApi;
import com.nttdata.customer.microservice.mapper.CustomerMapper;
import com.nttdata.customer.microservice.model.Customer;
import com.nttdata.customer.microservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@RestController
public class CustomerController implements CustomerApi{

    private static final String TIMESTAMP = "timestamp";

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> addCustomer(Mono<Customer> customer, ServerWebExchange exchange) {
        Map<String, Object> response = new HashMap<>();
        return customerService.save(customer.map(customerMapper::toDocument))
                .map(customerMapper::toModel)
                .map(c -> {
                    response.put("customer", c);
                    response.put("message", "Cliente guardado con éxito");
                    response.put(TIMESTAMP, new Date());
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                })
                .onErrorResume(WebExchangeBindException.class, getThrowableMonoFunction(response))
                .onErrorResume(DuplicateKeyException.class, getThrowableDuplicate(response));
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteCustomer(String id, ServerWebExchange exchange) {
        return customerService.findById(id)
                .flatMap(c -> customerService.delete(c)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<ResponseEntity<Customer>> getCustomerByDNI(String dni, ServerWebExchange exchange) {
        return customerService.findByDni(dni)
                .map(customerMapper::toModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Customer>> getCustomerById(String id, ServerWebExchange exchange) {
        return customerService.findById(id)
                .map(customerMapper::toModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> updateCustomer(String id, Mono<Customer> customer, ServerWebExchange exchange) {
        Map<String, Object> response = new HashMap<>();
        return customerService.update(id, customer.map(customerMapper::toDocument))
                .map(customerMapper::toModel)
                .map(c -> {
                    response.put("customer", c);
                    response.put("message", "Cliente guardado con éxito");
                    response.put(TIMESTAMP, new Date());
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                })
                .onErrorResume(WebExchangeBindException.class, getThrowableMonoFunction(response))
                .onErrorResume(DuplicateKeyException.class, getThrowableDuplicate(response))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    private static Function<Throwable, Mono<? extends ResponseEntity<Map<String, Object>>>> getThrowableMonoFunction(Map<String, Object> response){
        return t -> Mono.just(t).cast(WebExchangeBindException.class)
                .flatMap(e -> Mono.just(e.getFieldErrors()))
                .flatMapMany(Flux::fromIterable)
                .map(fieldError -> "Campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collectList()
                .flatMap(l -> {
                    response.put(TIMESTAMP, new Date());
                    response.put("status", HttpStatus.BAD_REQUEST.value());
                    response.put("errors", l);
                    return Mono.just(ResponseEntity.badRequest().body(response));
                });
    }
    private static Function<Throwable, Mono<? extends ResponseEntity<Map<String, Object>>>> getThrowableDuplicate(Map<String, Object> response){
        return t -> Mono.just(t).cast(DuplicateKeyException.class)
                .flatMap(l -> {
                    response.put(TIMESTAMP, new Date());
                    response.put("status", HttpStatus.BAD_REQUEST.value());
                    response.put("errors", l.getMessage());
                    return Mono.just(ResponseEntity.badRequest().body(response));
                });
    }
}
