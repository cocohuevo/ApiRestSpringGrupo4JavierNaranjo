package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomerDTO;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class RestCustomer {

	@Autowired
	@Qualifier("customerServiceImpl")
	private CustomerService customerService;

//	@GetMapping("/customers")
//	public List<CustomerDTO> getCustomers(){
//		return customerService.listAllCustomer();
//	}

	@GetMapping("/listCustomers")
	public ResponseEntity<?> getListCustomers() {
		List<CustomerDTO> list = customerService.listAllCustomer();
		if (list.isEmpty())
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.ok(list);
	}

//	@GetMapping("/customers/{customerId}")
//	public CustomerDTO getCustomer(@PathVariable int customerId)
//	{
//		return customerService.findCustomerByIdModel(customerId);
//	}

	@GetMapping("/listCustomers/{customerId}")
	public ResponseEntity<?> getCustomerResp(@PathVariable int customerId) {
		CustomerDTO customer = customerService.findCustomerByIdModel(customerId);
		if (customer == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(customer);
	}

//	@PostMapping("/customers")
//	public CustomerDTO addCustomer(@RequestBody CustomerDTO customer)
//	{
//		customerService.addCustomer(customer);
//		return customer;
//	}

	@PostMapping("/customersNew")
	public ResponseEntity<?> insertCustomerNew(@RequestBody CustomerDTO customer) {
		customerService.addCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}

//	@PutMapping("/customers")
//	public CustomerDTO updateCustomer(@RequestBody CustomerDTO customer)
//	{
//		customerService.addCustomer(customer);
//		return customer;
//	}

	@PutMapping("/customersNew")
	public ResponseEntity<?> updateCustomerNew(@RequestBody CustomerDTO customer) {
		return ResponseEntity.ok(customerService.addCustomer(customer));
	}

//	@DeleteMapping("/customers/{customerId}")
//	public void deleteCustomer(@PathVariable int customerId)
//	{
//		customerService.removeCustomer(customerId);
//	}

	@DeleteMapping("/customersDelete/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) {
		boolean existe = customerService.removeCustomer(customerId);
		if (existe)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.notFound().build();
	}

}
