package com.bjpowernode.crm.workbench.customer.remark.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.customer.remark.service.CustomerRemarkService;
import com.bjpowernode.crm.workbench.customer.remark.service.impl.CustomerRemarkServiceImpl;

/**
 * 删除客户备注
 * @author LauShuaichen
 * Servlet implementation class DeleteCustomerRemarkController
 */
@WebServlet("/workbench/customer/remark/deleteCustomerRemark.do")
public class DeleteCustomerRemarkController extends HttpServlet {


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.customer.remark.web.controller.DeleteCustomerRemarkController");
		
		//获取表单
		String id = request.getParameter("id");
		
		//调用service
		CustomerRemarkService customerRemarkService = (CustomerRemarkService) ServiceFactory.getService(new CustomerRemarkServiceImpl());
		int ret = customerRemarkService.deleteCustomerRemark(id);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
