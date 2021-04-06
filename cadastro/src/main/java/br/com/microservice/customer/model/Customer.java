package br.com.microservice.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Transient
	@NotEmpty(message = "password.not.blank")
    private String password;
	
    @Column(unique=true, nullable = false)
    @Email(message = "email.not.valid")
    @NotEmpty(message = "email.not.blank")
    private String  email;

    private Date createdDate;
    @Column(unique=true, nullable = false)
    private String document;

    private String name;
    private String companyName;
    private Date birthday;
    private String motherName;
    private String fatherName;
    private int maritalStatus;
    private String pinCard;
    private String maritalStatusDescription;
    private String profession;
    private String gender;
    private String type;

    @Transient
    private TypeAccount typeAccount;

    @OneToOne(cascade=CascadeType.PERSIST)
    private CurrentAccount currentAccount;

    @OneToOne(cascade=CascadeType.PERSIST)
    private SavingsAccount savingsAccount;

    @OneToOne(cascade=CascadeType.PERSIST)
    private Address address;

    @PrePersist
    protected void createdDate() {
        createdDate = new Date();
    }
}

