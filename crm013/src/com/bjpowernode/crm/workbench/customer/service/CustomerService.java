package com.bjpowernode.crm.workbench.customer.service;

import java.util.Map;

import com.bjpowernode.crm.commons.vo.PaginationVO;
import com.bjpowernode.crm.workbench.customer.domain.Customer;

/**
 * @author LauShuaichen
 */
public interface CustomerService {

	int SaveCustomer(Customer customer);

	Customer editCustomerById(String id);

	int updateCustomer(Customer customer);

	int deleteCustomer(String id);

	PaginationVO<Customer> queryCustomerForPageByCondition(Map<String, Object> map);

	Customer lookCustomerDetail(String id);

}
