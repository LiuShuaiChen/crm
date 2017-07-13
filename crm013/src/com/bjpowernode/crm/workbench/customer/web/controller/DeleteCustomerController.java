package com.bjpowernode.crm.workbench.customer.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.customer.service.CustomerService;
import com.bjpowernode.crm.workbench.customer.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 删除客户信息 根据id
 * @author LauShuaichen
 * Servlet implementation class DeleteCustomerController
 */
@WebServlet("/workbench/customer/deleteCustomer.do")
public class DeleteCustomerController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.customer.web.controller.DeleteCustomerController");
		
		String id = request.getParameter("id");
		
		//调用service 删除
		CustomerService customerService = (CustomerService) ServiceFactory.getService(new CustomerServiceImpl());
		int ret = customerService.deleteCustomer(id);
		
		Map<String, Object>map = new HashMap<String,Object>();
		if (ret > 0) {
			map.put("success", true);
		}else {
			map.put("success", false);
		}
		
		String json = new ObjectMapper().writeValueAsString(map);
		
		request.setAttribute("data", json);
		request.getRequestDispatcher("/data.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
