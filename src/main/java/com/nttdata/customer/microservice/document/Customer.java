package com.nttdata.customer.microservice.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Getter
@Setter
@Document(collection = "customer")
public class Customer {
    @Id
    private String id;
    @Indexed(unique = true)
    private String dni;
    private String names;
    private String surnames;
    @Indexed(unique = true)
    private String email;
    private String phone;
    private String employer;
    @CreatedDate
    private Date createdAt;
}
