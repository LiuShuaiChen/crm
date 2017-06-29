package com.bjpowernode.crm.workbench.activity.web.controller;

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
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class MarketActivitySaveController
 */
@WebServlet("/workbench/activity/market/save.do")
public class MarketActivitySaveController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("/workbench/activity/saveCreateMarketActivity.do");
		
//		String id = request.getParameter("id");
		String owner = request.getParameter("owner");	//所有者
		String type = request.getParameter("type");		//类型
		String name = request.getParameter("name");		//名称
		String state = request.getParameter("state");	//状态
		String startDate = request.getParameter("startDate");	//开始日期
		String endDate = request.getParameter("endDate");		//结束日期
		String budgetCost = request.getParameter("budgetCost");	//实际成本
		String actualCost = request.getParameter("actualCost");	//预算成本
		String description = request.getParameter("description");	//描述
		
//		String createBy = request.getParameter("createBy");	//登录用户
//		String createTime = request.getParameter("createTime");	//获取系统当前时间
		
		MarketActivity marketActivity = new MarketActivity();
		
		if (actualCost != null && actualCost.trim().length() > 0) {
			marketActivity.setActualCost(Long.valueOf(actualCost));
		}
		
		if (budgetCost != null && budgetCost.trim().length() >0 ) {
			marketActivity.setBudgetCost(Long.valueOf(budgetCost));
		}
		
		marketActivity.setId(UUIDutils.getUUid());
		marketActivity.setOwner(owner);
		marketActivity.setType(type);
		marketActivity.setName(name);
		marketActivity.setState(state);
		marketActivity.setStartDate(startDate);
		marketActivity.setEndDate(endDate);
		marketActivity.setDescription(description);
		
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		
		marketActivity.setCreateBy(user.getId());
		marketActivity.setCreateTime(DateUtils.getDate());
		
		//调用service方法
		MarketActivityService marketActivityService = (MarketActivityService) ServiceFactory.getService(new MarketActivityServiceImpl());
		int ret = marketActivityService.insertCreateMarketActivity(marketActivity);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (ret > 0) {
			map.put("success", true);
		}else{
			map.put("succcess", false);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
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
