package br.com.home.demo2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.home.demo2.model.Customer;
import br.com.home.demo2.repository.CustomerRepository;
import br.com.home.demo2.repository.MyTableRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class CustomerController {

	@Autowired
	CustomerRepository repository;

	@Autowired
	MyTableRepository tableRepository;

	@GetMapping("/health")
	public ResponseEntity<String> getHealth() {
		System.out.println("The server is...");

		List<Customer> customers = new ArrayList<>();
		repository.findAll().forEach(customers::add);

		if (customers.isEmpty()) {
			System.out.println("Injeção de dependência - NOK ");
			System.out.println("Banco - NOK!");
		}

		return new ResponseEntity<>("OK", HttpStatus.OK);

	}

	@GetMapping(value = "/in/{in}")
	public ResponseEntity<String> getIn(@PathVariable(name = "in") String inParam1) {
		System.out.println("Get in...");

		tableRepository.inOnlyTest(inParam1);

		return new ResponseEntity<>("Procedimento executado com sucesso", HttpStatus.OK);
	}

	@GetMapping(value = "/out/{in}")
	public String getOut(@PathVariable(name = "in") String inParam1) {
		System.out.println("Get out...");

		return tableRepository.inAndOutTest(inParam1);
	}

	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		System.out.println("Get all Customers...");

		List<Customer> customers = new ArrayList<>();
		repository.findAll().forEach(customers::add);

		return customers;
	}

	@PostMapping(value = "/customers/create")
	public ResponseEntity<Customer> postCustomer(@RequestBody(required = false) Customer customer) {

		Customer _customer = repository.save(new Customer(customer.getName(), customer.getAge()));

		return new ResponseEntity<>(_customer, HttpStatus.CREATED);
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/customers/delete")
	public ResponseEntity<String> deleteAllCustomers() {
		System.out.println("Delete All Customers...");

		repository.deleteAll();

		return new ResponseEntity<>("All customers have been deleted!", HttpStatus.OK);
	}

	@GetMapping(value = "customers/age/{age}")
	public List<Customer> findByAge(@PathVariable int age) {

		List<Customer> customers = repository.findByAge(age);
		return customers;
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
		System.out.println("Update Customer with ID = " + id + "...");

		Optional<Customer> customerData = repository.findById(id);

		if (customerData.isPresent()) {
			Customer _customer = customerData.get();
			_customer.setName(customer.getName());
			_customer.setAge(customer.getAge());
			_customer.setActive(customer.isActive());
			return new ResponseEntity<>(repository.save(_customer), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}