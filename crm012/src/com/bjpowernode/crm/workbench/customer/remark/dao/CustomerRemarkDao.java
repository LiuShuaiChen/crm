package com.bjpowernode.crm.workbench.customer.remark.dao;

import java.util.List;

import com.bjpowernode.crm.workbench.customer.remark.domain.CustomerRemark;

public interface CustomerRemarkDao {

	int createCustomerRemark(CustomerRemark customerRemark);

	int editCustomerRemark(String id);

	int updateCustomerRemark(CustomerRemark customerRemark);

	int deleteCustomerRemark(String id);

	List<CustomerRemark> lookCustomerRemarkForCustomer(String id);



}
