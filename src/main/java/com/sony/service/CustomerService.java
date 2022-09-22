package com.sony.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sony.entity.Customer;
import com.sony.repository.CustomerRepository;


@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repo;
	
	public List<Customer> getAllCustomers(String id){
		//Pageable pageable=PageRequest.of(pageNum - 1, pageSize,Sort.by(sortBy));
		return ( repo.findAll());
	}
	public Customer getCustomerById(String id) {
		Optional<Customer> op = repo.findById(id);
		
		return op.isEmpty() ? null : op.get();
	}
	
	public Customer addNewCustomer(Customer customer) {
		customer.setCustomerId(customer.getCustomerId());
		return repo.save(customer);
		
	}
	
	public Customer updateCustomer(String id,Customer customer) {
		Customer c1= this.getCustomerById(id);
		if(c1==null)
		{
			throw new ServiceException("cutomer id does not exist");
		}
		return repo.save(customer);
	}
}
