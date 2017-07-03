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

import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivityRemark;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityRemarkService;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityRemarkServiceImpl;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author LauShuaichen
 * 查看市场活动明细页
 * Servlet implementation class DetailMarketActivityController
 */
@WebServlet("/workbench/activity/detailMarketActivity.do")
public class DetailMarketActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.activity.web.controller.DetailMarketActivityController");
		
		//获取表单 id  
		String id = request.getParameter("id");
		
		//获取市场活动对象
		MarketActivityService marketActivityService = (MarketActivityService) ServiceFactory.getService(new MarketActivityServiceImpl());
		MarketActivity marketActivity = marketActivityService.queryMarketActivityForDetailById(id);
		
		
		//获取 市场活动 备注
		MarketActivityRemarkService marketActivityRemarkService = (MarketActivityRemarkService) ServiceFactory.getService(new MarketActivityRemarkServiceImpl());
	 	List<MarketActivityRemark> remarkList = marketActivityRemarkService.queryMarketActivityRemarkByActivityId(id);
	 	

	 	
	 	request.setAttribute("marketActivity", marketActivity);
	 	request.setAttribute("remarkList", remarkList);
	 	
	 	request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
