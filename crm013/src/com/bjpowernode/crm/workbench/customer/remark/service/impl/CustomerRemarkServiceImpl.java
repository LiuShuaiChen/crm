package com.bjpowernode.crm.workbench.customer.remark.service.impl;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.commons.vo.PaginationVO;
import com.bjpowernode.crm.utils.SqlSessionutils;
import com.bjpowernode.crm.workbench.contacts.remark.domain.ContactsRemark;
import com.bjpowernode.crm.workbench.customer.remark.dao.CustomerRemarkDao;
import com.bjpowernode.crm.workbench.customer.remark.domain.CustomerRemark;
import com.bjpowernode.crm.workbench.customer.remark.service.CustomerRemarkService;

/**
 * 处理 客户备注 业务实现类
 * @author LauShuaichen
 *
 */
public class CustomerRemarkServiceImpl implements CustomerRemarkService {
	
	private CustomerRemarkDao customerRemarkDao = SqlSessionutils.getSession().getMapper(CustomerRemarkDao.class);

	/**
	 * 添加客户备注
	 */
	@Override
	public int createCustomerRemark(CustomerRemark customerRemark) {
		return customerRemarkDao.createCustomerRemark(customerRemark);
	}

	/**
	 * 获取客户备注信息 进行准备修改
	 */
	@Override
	public int editCustomerRemark(String id) {
		return customerRemarkDao.editCustomerRemark(id);
	}

	/**
	 * 更新 客户备注信息
	 */
	@Override
	public int updateCustomerRemark(CustomerRemark customerRemark) {
		return customerRemarkDao.updateCustomerRemark(customerRemark);
	}

	/**
	 * 根据客户备注id 删除客户备注
	 */
	@Override
	public int deleteCustomerRemark(String id) {
		return customerRemarkDao.deleteCustomerRemark(id);
	}

	/**
	 * 根据当前id 获取所有关联的备注
	 */
	@Override
	public List<CustomerRemark> lookCustomerRemarkForCustomer(String id) {
		return customerRemarkDao.lookCustomerRemarkForCustomer(id);
	}

	
	

}
