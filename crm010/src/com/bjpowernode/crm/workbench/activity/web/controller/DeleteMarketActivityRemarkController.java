package com.bjpowernode.crm.workbench.activity.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityRemarkService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityRemarkServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class DeleteMarketActivityRemarkController
 */
@WebServlet("/workbench/activity/detail/remark/deleteMarketActivityRemark")
public class DeleteMarketActivityRemarkController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.activity.web.controller.DeleteMarketActivityRemarkController");
		// 获取表单
		String id = request.getParameter("id");

		MarketActivityRemarkService marketActivityRemarkService = (MarketActivityRemarkService) ServiceFactory.getService(new MarketActivityRemarkServiceImpl());
		
		int ret = marketActivityRemarkService.deleteMarketActivityRemarkById(id);

		Map<String, Object> map = new HashMap<String, Object>();

		if (ret > 0) {
			map.put("success", true);
		} else {
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
		doGet(request, response);
	}

}
