package com.bjpowernode.crm.workbench.customer.remark.service;

import java.util.List;

import com.bjpowernode.crm.workbench.contacts.remark.domain.ContactsRemark;
import com.bjpowernode.crm.workbench.customer.remark.domain.CustomerRemark;

public interface CustomerRemarkService {

	int createCustomerRemark(CustomerRemark customerRemark);

	int editCustomerRemark(String id);

	int updateCustomerRemark(CustomerRemark customerRemark);

	int deleteCustomerRemark(String id);

	List<CustomerRemark> lookCustomerRemarkForCustomer(String id);



}
