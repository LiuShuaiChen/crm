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

import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class EditMarketActivityDetailByIdController
 */
@WebServlet("/workbench/activity/detail/editMarketActivityDetailById.do")
public class EditMarketActivityDetailByIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(
				"com.bjpowernode.crm.workbench.activity.web.controller.EditMarketActivityDetailByIdController");

		// 获取参数
		String id = request.getParameter("id");

		// 调用 service 查询市场活动
		MarketActivityService marketActivityService = (MarketActivityService) ServiceFactory
				.getService(new MarketActivityServiceImpl());
		MarketActivity marketActivity = marketActivityService.queryMarketActivityForDetailById(id);
		
		UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
		List<User> userList = userService.queryAllUsers();

		Map<String, Object> map = new HashMap<String, Object>();

		if (marketActivity != null) {
			map.put("success", true);
			map.put("marketActivity", marketActivity);
			map.put("userList", userList);
		}else {
			map.put("success", false);
		}
		
		String json = new ObjectMapper().writeValueAsString(map);
		
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
