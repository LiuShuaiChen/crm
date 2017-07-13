package com.bjpowernode.crm.settings.qx.user.web.controller;

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
 * 获取所有者 下拉菜单
 * Servlet implementation class GetUserOwnerController
 */
@WebServlet("/settings/qx/user/GetUserOwner.do")
public class GetUserOwnerController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.settings.qx.user.web.controller.GetUserOwnerController");
		
		//调用service 
		UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
		List<User> userList =  userService.queryAllUsers();
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(userList);
		
		request.setAttribute("data", json);
		request.getRequestDispatcher("/data.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
