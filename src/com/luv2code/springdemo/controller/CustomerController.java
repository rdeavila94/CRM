package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	/*Spring is able to autowire this because you have one CustomerDAOImpl
	that is of type @Repository, which inherits from @Component, and remember
	that @Component registers objects/classes with Spring. This autowired will
	find that CustomerDAOImpl in the "Bean Factory" and link it*/
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		//get the customers from the DAO
		List<Customer> customers = customerService.getCustomers();
		
		//add it to the Model
		theModel.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Customer customer = new Customer();
		theModel.addAttribute("customer", customer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute ("customer") Customer theCustomer) {
		System.out.println("Customer ID: " + theCustomer.getId());
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
			Model theModel) {
		//get customer from service
		Customer theCustomer = customerService.getCustomer(theId);
		
		//Populate the model with customer info
		theModel.addAttribute("customer", theCustomer);
		
		//send over to our form
		System.out.println("CustomerID in showFormForUpdate: " + theCustomer.getId());
		return "customer-form";
		
		
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		//delete the customer
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}
}
