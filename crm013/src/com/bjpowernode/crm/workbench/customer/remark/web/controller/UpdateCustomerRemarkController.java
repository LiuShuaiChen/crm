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
import com.bjpowernode.crm.workbench.customer.remark.domain.CustomerRemark;
import com.bjpowernode.crm.workbench.customer.remark.service.CustomerRemarkService;
import com.bjpowernode.crm.workbench.customer.remark.service.impl.CustomerRemarkServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 更新 客户备注 信息
 * @author LauShuaichen
 * 
 * Servlet implementation class UpdateCustomerRemarkController
 */
@WebServlet("/workbench/customer/remark/updateCustomerRemark.do")
public class UpdateCustomerRemarkController extends HttpServlet {


	/**
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("com.bjpowernode.crm.workbench.customer.remark.web.controller.UpdateCustomerRemarkController");
		
		//获取参数
		String id = request.getParameter("id");
		String noteContent = request.getParameter("noteContent");
		String customerId = request.getParameter("customerId");
		
		//封装对象
		CustomerRemark customerRemark = new CustomerRemark();
		customerRemark.setId(id);
		customerRemark.setNoteContent(noteContent);
		customerRemark.setCustomerId(customerId);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		customerRemark.setEditFlag(1);
		customerRemark.setEditPerson(user.getId());
		customerRemark.setEditTime(DateUtils.getDate());
		
		
		CustomerRemarkService customerRemarkService = (CustomerRemarkService) ServiceFactory.getService(new CustomerRemarkServiceImpl());
		int ret = customerRemarkService.updateCustomerRemark(customerRemark);
		
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
