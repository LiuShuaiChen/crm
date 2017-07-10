package com.bjpowernode.crm.workbench.customer.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.customer.domain.Customer;
import com.bjpowernode.crm.workbench.customer.remark.domain.CustomerRemark;
import com.bjpowernode.crm.workbench.customer.remark.service.CustomerRemarkService;
import com.bjpowernode.crm.workbench.customer.remark.service.impl.CustomerRemarkServiceImpl;
import com.bjpowernode.crm.workbench.customer.service.CustomerService;
import com.bjpowernode.crm.workbench.customer.service.impl.CustomerServiceImpl;

/**
 * 跳转到详细页中 显示该客户的明细页 and 备注
 * 
 * @author LauShuaichen Servlet implementation class
 *         LookCustomerDetailController
 */
@WebServlet("/workbench/customer/detail/lookCustomerDetail.do")
public class LookCustomerDetailController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.customer.web.controller.LookCustomerDetailController");

		// 获取 客户的id
		String id = request.getParameter("id");

		// 调用service 查看该客户
		CustomerService customerService = (CustomerService) ServiceFactory.getService(new CustomerServiceImpl());
		Customer customer = customerService.lookCustomerDetail(id);

		// 根据id 查询该客户的备注
		CustomerRemarkService customerRemarkService = (CustomerRemarkService) ServiceFactory
				.getService(new CustomerRemarkServiceImpl());
		List<CustomerRemark> remark = customerRemarkService.lookCustomerRemarkForCustomer(id);

		Map<String, Object> map = new HashMap<String, Object>();

		if (customer != null) {
			map.put("success", true);
			map.put("customer", customer);
			map.put("remark", remark);
		} else {
			map.put("success", false);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
