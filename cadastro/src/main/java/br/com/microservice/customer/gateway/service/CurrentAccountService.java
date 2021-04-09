package br.com.microservice.customer.gateway.service;

import br.com.microservice.customer.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Slf4j
@Service
public class CurrentAccountService {

    private VisaService visaService;

    @Autowired
    public CurrentAccountService(VisaService visaService) {
        this.visaService = visaService;
    }

    public Customer generateCurrentAccount(Customer customer){
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setCheckDigit(currentAccount.getCheckDigit());
        currentAccount.setAccountNumber(currentAccount.generateAccountNumber());
        currentAccount.setCurrentBalance(BigDecimal.ZERO);
         return checkTypeAccount(customer, currentAccount);
    }

    private Customer checkTypeAccount(Customer customer, CurrentAccount currentAccount) {
        if (customer.getTypeAccount().equals(TypeAccount.CURRENT_ACCOUNT)) {
            currentAccount.setLimitCurrent(BigDecimal.valueOf(1000));
            currentAccount.setStatus(true);
            currentAccount.setCreditCard(visaService.setVisa(customer));
            customer.setCurrentAccount(currentAccount);
            return customer;
        }
        currentAccount.setStatus(false);
        customer.setCurrentAccount(currentAccount);
        return customer;
    }
}
