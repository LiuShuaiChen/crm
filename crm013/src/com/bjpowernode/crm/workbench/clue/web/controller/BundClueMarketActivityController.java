package com.bjpowernode.crm.workbench.clue.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 市场活动关联
 * @author LauShuaichen
 * Servlet implementation class BundClueMarketActivityController
 */
@WebServlet("/workbench/clue/detail/bundClueMarketActivity.do")
public class BundClueMarketActivityController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.clue.web.controller.BundClueMarketActivityController");
		
		//获取参数
		String clueId = request.getParameter("clueId");
		String name = request.getParameter("name");
		
		//封装参数
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("clueId", clueId);
		map.put("name", name);
		
		//调用service  查询市场活动
		MarketActivityService marketActivityService = (MarketActivityService) ServiceFactory.getService(new MarketActivityServiceImpl());
		List<MarketActivity> activityList = marketActivityService.bundClueMarketActivity(map);
		
		//把activityList转换成json 返回客户端
		String json = new ObjectMapper().writeValueAsString(activityList);
		
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
