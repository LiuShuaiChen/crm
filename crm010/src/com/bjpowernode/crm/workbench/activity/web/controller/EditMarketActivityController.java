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

import com.bjpowernode.crm.settings.qx.user.dao.UserDao;
import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.SqlSessionutils;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class EditMarketActivityController
 */
@WebServlet("/workbench/activity/editMarketActivity.do")
public class EditMarketActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.activity.web.controller.EditMarketActivityController");
		//获取表单
		String id = request.getParameter("id");
		
		MarketActivityService marketActivityService = (MarketActivityService) ServiceFactory.getService(new MarketActivityServiceImpl());
		MarketActivity marketActivity = marketActivityService.queryMarketActivityById(id);
		
		UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
		//UserDao userDao = SqlSessionutils.getSession().getMapper(UserDao.class);
		List<User> userList =  userService.queryAllUsers();
		
		
		//把marketActivity 和 userList 转成成json 返回客户端
		Map<String, Object> map = new HashMap<String,Object>();
		if (marketActivity != null) {
			map.put("success", true);
			map.put("marketActivity", marketActivity);
			map.put("userList", userList);
		}else {
			map.put("success", false);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
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
