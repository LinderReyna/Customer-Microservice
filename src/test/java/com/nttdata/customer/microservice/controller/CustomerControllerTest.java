package com.nttdata.customer.microservice.controller;

import com.nttdata.customer.microservice.mapper.CustomerMapper;
import com.nttdata.customer.microservice.model.Customer;
import com.nttdata.customer.microservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.ZoneOffset;
import java.util.Date;

@WebFluxTest(CustomerController.class)
class CustomerControllerTest {
    private static final String ENDPOINT_URL = "/LINDERREYNAE/Customer-Microservice/1.0.0/customer";
    @MockBean
    CustomerService customerService;
    @Autowired
    WebTestClient webTestClient;
    @MockBean
    CustomerMapper customerMapper;
    @Test
    void addCustomer() {
        Customer request = new Customer();
        request.setDni("70000000");
        request.setNames("Linder Jossemar");
        request.setSurnames("Reyna Esquivel");
        request.setEmail("linder_reynae@hotmail.com");
        request.setPhone("+51940000000");
        request.setEmployer("NTTDATA");
        com.nttdata.customer.microservice.document.Customer customer = new com.nttdata.customer.microservice.document.Customer();
        customer.setId("63615ea219091d2c16969136");
        customer.setDni("70000000");
        customer.setNames("Linder Jossemar");
        customer.setSurnames("Reyna Esquivel");
        customer.setEmail("linder_reynae@hotmail.com");
        customer.setPhone("+51940000000");
        customer.setEmployer("NTTDATA");
        customer.setCreatedAt(new Date());
        Customer response = new Customer();
        response.setId("63615ea219091d2c16969136");
        response.setDni("70000000");
        response.setNames("Linder Jossemar");
        response.setSurnames("Reyna Esquivel");
        response.setEmail("linder_reynae@hotmail.com");
        response.setPhone("+51940000000");
        response.setEmployer("NTTDATA");
        response.setCreatedAt(customer.getCreatedAt().toInstant().atOffset(ZoneOffset.UTC));
        Mockito.when(customerService.save(ArgumentMatchers.any())).thenReturn(Mono.just(customer));
        Mockito.when(customerMapper.toDocument(ArgumentMatchers.any(Customer.class))).thenReturn(customer);
        Mockito.when(customerMapper.toModel(ArgumentMatchers.any(com.nttdata.customer.microservice.document.Customer.class))).thenReturn(response);
        webTestClient.post().uri(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), Customer.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody();
    }

    @Test
    void deleteCustomer() {
        com.nttdata.customer.microservice.document.Customer customer = new com.nttdata.customer.microservice.document.Customer();
        customer.setId("63615ea219091d2c16969136");
        customer.setDni("70000000");
        customer.setNames("Linder Jossemar");
        customer.setSurnames("Reyna Esquivel");
        customer.setEmail("linder_reynae@hotmail.com");
        customer.setPhone("+51940000000");
        customer.setEmployer("NTTDATA");
        customer.setCreatedAt(new Date());
        Mockito.when(customerService.findById(ArgumentMatchers.anyString())).thenReturn(Mono.just(customer));
        Mockito.when(customerService.delete(ArgumentMatchers.any(com.nttdata.customer.microservice.document.Customer.class))).thenReturn(Mono.empty());
        webTestClient.delete().uri(ENDPOINT_URL + "/63615ea219091d2c16969136")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void getCustomerByDNI() {
        com.nttdata.customer.microservice.document.Customer customer = new com.nttdata.customer.microservice.document.Customer();
        customer.setId("63615ea219091d2c16969136");
        customer.setDni("70000000");
        customer.setNames("Linder Jossemar");
        customer.setSurnames("Reyna Esquivel");
        customer.setEmail("linder_reynae@hotmail.com");
        customer.setPhone("+51940000000");
        customer.setEmployer("NTTDATA");
        customer.setCreatedAt(new Date());
        Customer response = new Customer();
        response.setId("63615ea219091d2c16969136");
        response.setDni("70000000");
        response.setNames("Linder Jossemar");
        response.setSurnames("Reyna Esquivel");
        response.setEmail("linder_reynae@hotmail.com");
        response.setPhone("+51940000000");
        response.setEmployer("NTTDATA");
        response.setCreatedAt(customer.getCreatedAt().toInstant().atOffset(ZoneOffset.UTC));
        Mockito.when(customerService.findByDni(ArgumentMatchers.anyString())).thenReturn(Mono.just(customer));
        Mockito.when(customerMapper.toModel(ArgumentMatchers.any(com.nttdata.customer.microservice.document.Customer.class))).thenReturn(response);
        webTestClient.get().uri(ENDPOINT_URL + "/dni/70000000")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    void getCustomerById() {
        com.nttdata.customer.microservice.document.Customer customer = new com.nttdata.customer.microservice.document.Customer();
        customer.setId("63615ea219091d2c16969136");
        customer.setDni("70000000");
        customer.setNames("Linder Jossemar");
        customer.setSurnames("Reyna Esquivel");
        customer.setEmail("linder_reynae@hotmail.com");
        customer.setPhone("+51940000000");
        customer.setEmployer("NTTDATA");
        customer.setCreatedAt(new Date());
        Customer response = new Customer();
        response.setId("63615ea219091d2c16969136");
        response.setDni("70000000");
        response.setNames("Linder Jossemar");
        response.setSurnames("Reyna Esquivel");
        response.setEmail("linder_reynae@hotmail.com");
        response.setPhone("+51940000000");
        response.setEmployer("NTTDATA");
        response.setCreatedAt(customer.getCreatedAt().toInstant().atOffset(ZoneOffset.UTC));
        Mockito.when(customerService.findById(ArgumentMatchers.anyString())).thenReturn(Mono.just(customer));
        Mockito.when(customerMapper.toModel(ArgumentMatchers.any(com.nttdata.customer.microservice.document.Customer.class))).thenReturn(response);
        webTestClient.get().uri(ENDPOINT_URL + "/63615ea219091d2c16969136")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    void updateCustomer() {
        Customer request = new Customer();
        request.setDni("70000000");
        request.setNames("Linder Jossemar");
        request.setSurnames("Reyna Esquivel");
        request.setEmail("linder_reynae@hotmail.com");
        request.setPhone("+51940000001");
        request.setEmployer("NTTDATA");
        com.nttdata.customer.microservice.document.Customer customer = new com.nttdata.customer.microservice.document.Customer();
        customer.setId("63615ea219091d2c16969136");
        customer.setDni("70000000");
        customer.setNames("Linder Jossemar");
        customer.setSurnames("Reyna Esquivel");
        customer.setEmail("linder_reynae@hotmail.com");
        customer.setPhone("+51940000001");
        customer.setEmployer("NTTDATA");
        customer.setCreatedAt(new Date());
        Customer response = new Customer();
        response.setId("63615ea219091d2c16969136");
        response.setDni("70000000");
        response.setNames("Linder Jossemar");
        response.setSurnames("Reyna Esquivel");
        response.setEmail("linder_reynae@hotmail.com");
        response.setPhone("+51940000001");
        response.setEmployer("NTTDATA");
        response.setCreatedAt(customer.getCreatedAt().toInstant().atOffset(ZoneOffset.UTC));
        Mockito.when(customerService.update(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(Mono.just(customer));
        Mockito.when(customerMapper.toDocument(ArgumentMatchers.any(Customer.class))).thenReturn(customer);
        Mockito.when(customerMapper.toModel(ArgumentMatchers.any(com.nttdata.customer.microservice.document.Customer.class))).thenReturn(response);
        webTestClient.put().uri(ENDPOINT_URL + "/63615ea219091d2c16969136")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), Customer.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }
}