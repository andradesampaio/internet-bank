package br.com.microservice.customer.gateway.http;


import br.com.microservice.customer.model.CustomerDto;
import br.com.microservice.customer.model.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(value = "Customer Controller", description="Operations pertaining to customer in Online")
@RestController
@RequestMapping("/v1")
public class CustomerController{

	@Autowired
	private br.com.microservice.customer.gateway.service.CustomerService customerService;

    @ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Save new customer", response = ResponseDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Successfully save customer"),
			@ApiResponse(code = 400, message = "Customer already registered")
	})
	@ResponseBody
	@PostMapping("/customers")
	public ResponseDto saveCustomer(@RequestBody CustomerDto customer) {
		log.info("Customers is created");
		return customerService.save(customer);
	}

	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "View customer" , response = ResponseDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved customer"),
			@ApiResponse(code = 404, message = "Customer not found")})
	@ResponseBody
	@GetMapping("/customers/{document}")
	public ResponseDto findByDocumentCustomer(@PathVariable String document) {
		log.info("Customers find");
		return customerService.findByDocument(document);
	}

}
