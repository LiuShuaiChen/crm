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

import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.customer.domain.Customer;
import com.bjpowernode.crm.workbench.customer.service.CustomerService;
import com.bjpowernode.crm.workbench.customer.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 根据id 获取客户  进行编辑
 * @author LauShuaichen
 * Servlet implementation class EditCustomerController
 */
@WebServlet("/workbench/customer/editCustomer.do")
public class EditCustomerController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.customer.web.controller.EditCustomerController");
		
		//获取表单
		String id = request.getParameter("id");
		
		//调用service 获取客户对象
		CustomerService customerService = (CustomerService) ServiceFactory.getService(new CustomerServiceImpl());
		Customer customer = customerService.editCustomerById(id);
		
		
		UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
		List<User>userList = userService.queryAllUsers();
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		if (customer != null) {
			map.put("success", true);
			map.put("customer", customer);
			map.put("userList", userList);
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
