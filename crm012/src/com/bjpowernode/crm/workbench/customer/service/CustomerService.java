package com.bjpowernode.crm.workbench.customer.service;

import com.bjpowernode.crm.workbench.customer.domain.Customer;

/**
 * @author LauShuaichen
 */
public interface CustomerService {

	int SaveCustomer(Customer customer);

	Customer editCustomerById(String id);

}
