package com.bjpowernode.crm.workbench.customer.service.impl;

import com.bjpowernode.crm.utils.SqlSessionutils;
import com.bjpowernode.crm.workbench.customer.dao.CustomerDao;
import com.bjpowernode.crm.workbench.customer.domain.Customer;
import com.bjpowernode.crm.workbench.customer.service.CustomerService;

/**
 * 
 * @author LauShuaichen
 *
 */
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao = SqlSessionutils.getSession().getMapper(CustomerDao.class);

	/**
	 * 创建新的客户
	 */
	@Override
	public int SaveCustomer(Customer customer) {
		return customerDao.SaveCustomer(customer);
	}

	/**
	 * 根据id 获取 客户
	 */
	@Override
	public Customer editCustomerById(String id) {
		return customerDao.editCustomerById(id);
	}

}
