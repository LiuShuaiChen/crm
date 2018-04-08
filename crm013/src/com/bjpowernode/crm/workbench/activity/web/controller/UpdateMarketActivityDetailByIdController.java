package com.bjpowernode.crm.workbench.activity.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.utils.DateUtils;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author LauShuaichen Servlet implementation class
 *         UpdateMarketActivityDetailByIdController
 */
@WebServlet("/workbench/activity/detail/updateMarketActivityDetailById.do")
public class UpdateMarketActivityDetailByIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.err.println(
				"com.bjpowernode.crm.workbench.activity.web.controller.UpdateMarketActivityDetailByIdController");

		// 获取参数
		String id = request.getParameter("id");
		String owner = request.getParameter("owner");
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String state = request.getParameter("state");
		String startTime = request.getParameter("startDate");
		String endTime = request.getParameter("endDate");
		String actualCost = request.getParameter("actualCost");
		String budgetCost = request.getParameter("budgetCost");
		String description = request.getParameter("description");

		// 封装对象
		MarketActivity marketActivity = new MarketActivity();
		marketActivity.setId(id);
		marketActivity.setOwner(owner);
		marketActivity.setType(type);
		marketActivity.setName(name);
		marketActivity.setState(state);
		marketActivity.setStartDate(startTime);
		marketActivity.setEndDate(endTime);
		marketActivity.setActualCost(Long.parseLong(actualCost));
		marketActivity.setBudgetCost(Long.parseLong(budgetCost));
		marketActivity.setDescription(description);
		marketActivity.setEditTime(DateUtils.getDate());
		marketActivity.setEditBy(((User)request.getSession().getAttribute("user")).getId());

		// 调用service
		MarketActivityService marketActivityService = (MarketActivityService) ServiceFactory.getService(new MarketActivityServiceImpl());
		int ret = marketActivityService.updateEditMarketActivityDetailByMarketActivity(marketActivity);

		Map<String, Object> map = new HashMap<String, Object>();

		if (ret > 0) {
			map.put("success", true);
			map.put("marketActivity", "marketActivity");
		} else {
			map.put("success", false);
		}

		// 转换json
		String json = new ObjectMapper().writeValueAsString(map);

		// 响应
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
