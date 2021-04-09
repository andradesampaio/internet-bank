package br.com.microservice.customer.gateway.service;

import br.com.microservice.customer.model.Customer;
import br.com.microservice.customer.model.SavingsAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class SavingsAccountService {

    public Customer generateSavingsAccount(Customer customer) {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setCheckDigit(savingsAccount.getCheckDigit());
        savingsAccount.setAccountNumber(savingsAccount.generateAccountNumber());
        savingsAccount.setStatus(true);
        savingsAccount.setCurrentBalance(BigDecimal.ZERO);
        savingsAccount.setYieldCurrent(BigDecimal.valueOf(0));
        customer.setSavingsAccount(savingsAccount);
        return customer;
    }

}
