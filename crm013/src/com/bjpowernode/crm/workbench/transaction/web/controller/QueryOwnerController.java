package com.bjpowernode.crm.workbench.transaction.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 创建用户 需要查询所有者 
 * @author LauShuaichen
 * Servlet implementation class QueryOwnerController
 */
@WebServlet("/workbench/transaction/queryOwner.do")
public class QueryOwnerController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.transaction.web.controller.QueryOwnerController");

		//调用service 查询所有者
		UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
		List<User> userList = userService.queryAllUsers();

		String json = new ObjectMapper().writeValueAsString(userList);

		request.setAttribute("data", json);
		request.getRequestDispatcher("/data.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
