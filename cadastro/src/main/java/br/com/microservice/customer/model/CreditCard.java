package br.com.microservice.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_card")
@Table(name = "creditcard")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCard {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cardNumber;
    private String holderName;
    private int securityCode;
    private String validThru;
    private String password;
    private BigDecimal accountBalance = new BigDecimal(0);
    private BigDecimal limitCurrent = new BigDecimal(0);
}
