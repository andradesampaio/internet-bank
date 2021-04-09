package br.com.microservice.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "savings_account")
public class SavingsAccount{

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
    private BigDecimal yieldCurrent = new BigDecimal(0);
    private static Long NEXTACCOUNTNUMBER = 11223145L;

    public Long generateAccountNumber() {
        return ++NEXTACCOUNTNUMBER;
    }

    public int getCheckDigit() {
        return (int) ((Math.random() * (9 - 0) + 1) + 0);
    }


}
