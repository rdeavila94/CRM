package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to the inject the session
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		//get the result list from the query
		
		List<Customer> customers = theQuery.getResultList();
		//return the list of customers retrieved
		
		
		
		return customers;
	}


	@Override
	public void saveCustomer(Customer customer) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		//decide whether to save or update
		currentSession.saveOrUpdate(customer);
	}


	@Override
	public Customer getCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();

		Customer theCustomer =  currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}
	
	public void deleteCustomer(int theId) {
		//create session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create query
		Query query = currentSession.createQuery("delete from Customer where id =:theCustomerId");
		
		//pass the value to the parameter
		query.setParameter("theCustomerId", theId);
		
		//execute the operation
		query.executeUpdate();
	}
 
}
