package com.bjpowernode.crm.workbench.customer.remark.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.customer.remark.service.CustomerRemarkService;
import com.bjpowernode.crm.workbench.customer.remark.service.impl.CustomerRemarkServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 获取客户备注id  准备修改
 * @author LauShuaichen
 * Servlet implementation class EditCustomerRemarkController
 */
@WebServlet("/workbench/customer/remark/editCustomerRemark.do")
public class EditCustomerRemarkController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.customer.remark.web.controller.EditCustomerRemarkController");
		
		String id = request.getParameter("id");
		
		//调用service
		CustomerRemarkService customerRemarkService = (CustomerRemarkService) ServiceFactory.getService(new CustomerRemarkServiceImpl());
		int ret = customerRemarkService.editCustomerRemark(id);
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		if (ret > 0) {
			map.put("success", true);
		}else {
			map.put("success", false);
		}
		
		String json = new ObjectMapper().writeValueAsString(map);
		
		request.setAttribute("data", json);
		request.getRequestDispatcher("/workbench/customer/detail.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
