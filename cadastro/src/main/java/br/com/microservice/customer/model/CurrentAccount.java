package br.com.microservice.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "current_account")
public class CurrentAccount{

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String branch = "0900";
    private Long  accountNumber;
    private int  checkDigit;
    private Boolean status;
    private BigDecimal currentBalance = new BigDecimal(0);
    private static Long NEXTACCOUNTNUMBER = 11223145L;
    private BigDecimal limitCurrent = new BigDecimal(0);

    @OneToOne(cascade=CascadeType.PERSIST)
    private CreditCard creditCard;

    public Long getAccountNumber() {
        return ++NEXTACCOUNTNUMBER;
    }

    public int getCheckDigit() {
        return (int) ((Math.random() * (9 - 0) + 1) + 0);
    }
}
