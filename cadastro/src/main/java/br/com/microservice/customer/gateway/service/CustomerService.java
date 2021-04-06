package br.com.microservice.customer.gateway.service;

import br.com.microservice.customer.exception.CustomerBusinessException;
import br.com.microservice.customer.gateway.client.impl.LoginClientImpl;
import br.com.microservice.customer.gateway.http.request.RequestLogin;
import br.com.microservice.customer.gateway.repository.CustomerRepository;
import br.com.microservice.customer.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {

	private CustomerRepository customerRepository;
	private ModelMapper modelMapper;
	private BankAccountService bankAccountService;
	private LoginClientImpl loginClientImpl;

	@Autowired
	public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper, BankAccountService bankAccountService, LoginClientImpl loginClientImpl) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
		this.bankAccountService = bankAccountService;
		this.loginClientImpl = loginClientImpl;
	}

	public ResponseDto findByDocument(String document) {
		Customer newCustomer = customerRepository.findAllByDocument(document)
				.orElseThrow(() -> new CustomerBusinessException("Customer not found", HttpStatus.SC_NOT_FOUND));
		 return modelMapper.map(newCustomer, ResponseDto.class);

	}

	public ResponseDto save(CustomerDto customerDto){
		Customer newCustomer = new Customer();
		RequestLogin requestLogin = convertCustomerToLoginRequest(customerDto);
		newCustomer = modelMapper.map(customerDto, Customer.class);
		newCustomer = bankAccountService.generateBankAccount(newCustomer);

		try {
			loginClientImpl.addLogin(requestLogin);
			log.info("Customer save document number: "+ "***"+customerDto.getDocument().substring(3,6)+"***");
			return modelMapper.map(customerRepository.save(newCustomer), ResponseDto.class);
		}catch (CustomerBusinessException e){
			log.error("Fail request to Api Login:");
			throw new CustomerBusinessException("Fail request to Api Login", HttpStatus.SC_BAD_REQUEST);
		}catch (DataAccessException e){
			log.error("Customer already registered:");
			throw new CustomerBusinessException("Customer already registered", HttpStatus.SC_BAD_REQUEST);
		}
	}

	private RequestLogin convertCustomerToLoginRequest(CustomerDto customer){
		RequestLogin requestLogin = new RequestLogin();
		requestLogin.setUsername(customer.getName());
		requestLogin.setPassword(customer.getPassword());
		requestLogin.setEmail(customer.getEmail());
		requestLogin.setActive("1");
		return requestLogin;
	}

}