package com.bjpowernode.crm.workbench.customer.service.impl;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.commons.vo.PaginationVO;
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

	/**
	 * 更新 客户 对象
	 */
	@Override
	public int updateCustomer(Customer customer) {
		return customerDao.updateCustomer(customer);
	}

	/**
	 * 删除客户 根据id
	 */
	@Override
	public int deleteCustomer(String id) {
		return customerDao.deleteCustomer(id);
	}

	/**
	 * 分页查询客户列表页
	 */
	@Override
	public PaginationVO<Customer> queryCustomerForPageByCondition(Map<String, Object> map) {
		// 调用dao 查询记录列表
		List<Customer> customerList = customerDao.queryCustomerForPageByCondition(map);
		// 调用dao 查询记录总数
		long totalCount = customerDao.queryTotalCountOfCustomerForPageByCondition(map);

		PaginationVO<Customer> vo = new PaginationVO<>();
		vo.setDataList(customerList);
		vo.setTotalCount(totalCount);

		return vo;
	}

	@Override
	public Customer lookCustomerDetail(String id) {
		return customerDao.lookCustomerDetail(id);
	}

}
