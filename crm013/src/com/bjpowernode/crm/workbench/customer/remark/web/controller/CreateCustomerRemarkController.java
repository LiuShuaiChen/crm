package com.bjpowernode.crm.workbench.customer.remark.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.utils.DateUtils;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.UUIDutils;
import com.bjpowernode.crm.workbench.customer.remark.domain.CustomerRemark;
import com.bjpowernode.crm.workbench.customer.remark.service.CustomerRemarkService;
import com.bjpowernode.crm.workbench.customer.remark.service.impl.CustomerRemarkServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 添加 客户备注
 * @author LauShuaichen
 * Servlet implementation class CreateCustomerRemarkController
 */
@WebServlet("/workbench/customer/remark/createCustomerRemark.do")
public class CreateCustomerRemarkController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.customer.remark.web.controller.CreateCustomerRemarkController");
		
		//获取表单
		String noteContent = request.getParameter("noteContent");
		String customerId = request.getParameter("customerId");
		
		//创建客户备注对象 封装参数
		CustomerRemark customerRemark = new CustomerRemark();
		customerRemark.setId(UUIDutils.getUUid());
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		customerRemark.setEditFlag(0);
		customerRemark.setNotePerson(user.getId());
		customerRemark.setNoteTime(DateUtils.getDate());
		customerRemark.setNoteContent(noteContent);
		
		//调用service 持久化对象
		CustomerRemarkService customerRemarkService = (CustomerRemarkService) ServiceFactory.getService(new CustomerRemarkServiceImpl());
		int ret = customerRemarkService.createCustomerRemark(customerRemark);
		
		Map<String, Object>map = new HashMap<String,Object>();
		
		if (ret > 0) {
			map.put("success", true);
		}else {
			map.put("success", false);
		}
		
		String json = new ObjectMapper().writeValueAsString(map);
		
		System.out.println(json);
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
