package com.bjpowernode.crm.workbench.customer.dao;

import java.util.List;
import java.util.Map;

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

	/**
	 * 更新客户对象 
	 * @param customer
	 * @return
	 */
	int updateCustomer(Customer customer);

	/**
	 * 删除客户对象 根据id
	 * @param id
	 * @return
	 */
	int deleteCustomer(String id);

	
	/**
	 * 分页查询总记录
	 * @param map
	 * @return
	 */
	List<Customer> queryCustomerForPageByCondition(Map<String, Object> map);

	/**
	 * 分页查询记录总数
	 * @param map
	 * @return
	 */
	long queryTotalCountOfCustomerForPageByCondition(Map<String, Object> map);

	/**
	 * 根据id查询该客户明细
	 */
	Customer lookCustomerDetail(String id);

}
