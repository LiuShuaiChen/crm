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
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 更新市场活动
 * Servlet implementation class UpdateEditMarketActivityController
 * @author LauShuaichen
 */
@WebServlet("/workbench/activity/updateEditMarketActivity.do")
public class UpdateEditMarketActivityController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.activity.web.controller.UpdateEditMarketActivityController");
		
		//获取表单
		String id = request.getParameter("id");
		String owner = request.getParameter("owner");
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String state = request.getParameter("state");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String actualCost = request.getParameter("actualCost");
		String budgetCost = request.getParameter("budgetCost");
		String description = request.getParameter("description");
		
		
		//封装参数
		MarketActivity marketActivity = new MarketActivity();
		marketActivity.setId(id);
		marketActivity.setOwner(owner);
		marketActivity.setType(type);
		marketActivity.setName(name);
		marketActivity.setState(state);
		marketActivity.setStartDate(startDate);
		marketActivity.setEndDate(endDate);
		marketActivity.setActualCost(Long.parseLong(actualCost));
		marketActivity.setBudgetCost(Long.parseLong(budgetCost));
		marketActivity.setDescription(description);
		
		//调用service 保存 更新信息
		MarketActivityService marketActivityService = (MarketActivityService) ServiceFactory.getService(new MarketActivityServiceImpl());
		int ret = marketActivityService.updateEditMarketActivity(marketActivity);
		
		//根据处理结果 返回响应信息
		Map<String, Object> map = new HashMap<String,Object>();
		if (ret > 0) {
			map.put("success", true);
		}else {
			map.put("success", false);
		}
		
		//封装json
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		//转发
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
