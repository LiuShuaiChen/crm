package com.bjpowernode.crm.workbench.customer.dao;

import com.bjpowernode.crm.workbench.customer.domain.Customer;

public interface CustomerDao {

	/**
	 * 创建新的客户
	 * @param customer
	 * @return
	 */
	int SaveCustomer(Customer customer);

	/**
	 * 根据id 获取客户
	 * @param id
	 * @return
	 */
	Customer editCustomerById(String id);

}
