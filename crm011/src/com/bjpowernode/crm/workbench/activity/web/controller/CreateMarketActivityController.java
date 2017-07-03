package com.bjpowernode.crm.workbench.activity.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.DateUtils;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.UUIDutils;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 添加市场活动
 * @author LauShuaichen
 * Servlet implementation class CreateMarketActivityController
 */	
@WebServlet("/workbench/activity/createMarketActivity.do")
public class CreateMarketActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("/workbench/activity/market/create.do");
		
		
		UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
		List<User> userList =  userService.queryAllUsers();
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(userList);
		
		request.setAttribute("data", json);
		request.getRequestDispatcher("/data.jsp").forward(request, response);

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
