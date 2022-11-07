package com.nttdata.customer.microservice.service;

import com.nttdata.customer.microservice.document.Customer;
import com.nttdata.customer.microservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;
@WebFluxTest(CustomerServiceImpl.class)
class CustomerServiceImplTest {
    @MockBean
    CustomerRepository customerRepository;
    @Autowired
    CustomerServiceImpl customerService;

    @Test
    void save() {
        Customer request = new Customer();
        request.setDni("70000000");
        request.setNames("Linder Jossemar");
        request.setSurnames("Reyna Esquivel");
        request.setEmail("linder_reynae@hotmail.com");
        request.setPhone("+51940000000");
        request.setEmployer("NTTDATA");
        Customer response = new Customer();
        response.setId("63615ea219091d2c16969136");
        response.setDni("70000000");
        response.setNames("Linder Jossemar");
        response.setSurnames("Reyna Esquivel");
        response.setEmail("linder_reynae@hotmail.com");
        response.setPhone("+51940000000");
        response.setEmployer("NTTDATA");
        response.setCreatedAt(new Date());
        Mockito.when(customerRepository.insert(ArgumentMatchers.any(Customer.class))).thenReturn(Mono.just(response));
        StepVerifier.create(customerService.save(Mono.just(request))).expectNextCount(1).verifyComplete();
    }

    @Test
    void findById() {
        Customer response = new Customer();
        response.setId("63615ea219091d2c16969136");
        response.setDni("70000000");
        response.setNames("Linder Jossemar");
        response.setSurnames("Reyna Esquivel");
        response.setEmail("linder_reynae@hotmail.com");
        response.setPhone("+51940000000");
        response.setEmployer("NTTDATA");
        response.setCreatedAt(new Date());
        Mockito.when(customerRepository.findById(ArgumentMatchers.anyString())).thenReturn(Mono.just(response));
        StepVerifier.create(customerService.findById("63615ea219091d2c16969136")).expectNextCount(1).verifyComplete();
    }

    @Test
    void findByDni() {
        Customer response = new Customer();
        response.setId("63615ea219091d2c16969136");
        response.setDni("70000000");
        response.setNames("Linder Jossemar");
        response.setSurnames("Reyna Esquivel");
        response.setEmail("linder_reynae@hotmail.com");
        response.setPhone("+51940000000");
        response.setEmployer("NTTDATA");
        response.setCreatedAt(new Date());
        Mockito.when(customerRepository.findByDni(ArgumentMatchers.anyString())).thenReturn(Mono.just(response));
        StepVerifier.create(customerService.findByDni("70000000")).expectNextCount(1).verifyComplete();
    }

    @Test
    void update() {
        Customer request = new Customer();
        request.setDni("70000000");
        request.setNames("Linder Jossemar");
        request.setSurnames("Reyna Esquivel");
        request.setEmail("linder_reynae@hotmail.com");
        request.setPhone("+51940000001");
        request.setEmployer("NTTDATA");
        Customer response = new Customer();
        response.setId("63615ea219091d2c16969136");
        response.setDni("70000000");
        response.setNames("Linder Jossemar");
        response.setSurnames("Reyna Esquivel");
        response.setEmail("linder_reynae@hotmail.com");
        response.setPhone("+51940000001");
        response.setEmployer("NTTDATA");
        response.setCreatedAt(new Date());
        Mockito.when(customerRepository.findById(ArgumentMatchers.anyString())).thenReturn(Mono.just(response));
        Mockito.when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(Mono.just(response));
        StepVerifier.create(customerService.update("63615ea219091d2c16969136", Mono.just(request))).expectNextCount(1).verifyComplete();
    }

    @Test
    void delete() {
        Customer customer = new Customer();
        customer.setId("63615ea219091d2c16969136");
        customer.setDni("70000000");
        customer.setNames("Linder Jossemar");
        customer.setSurnames("Reyna Esquivel");
        customer.setEmail("linder_reynae@hotmail.com");
        customer.setPhone("+51940000001");
        customer.setEmployer("NTTDATA");
        customer.setCreatedAt(new Date());
        Mockito.when(customerRepository.delete(ArgumentMatchers.any(Customer.class))).thenReturn(Mono.empty());
        StepVerifier.create(customerService.delete(customer)).expectNextCount(0).verifyComplete();
    }
}