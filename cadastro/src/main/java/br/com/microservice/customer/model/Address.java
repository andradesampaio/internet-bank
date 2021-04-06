package br.com.microservice.customer.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String country;
    private String city;
    private String state;
}
