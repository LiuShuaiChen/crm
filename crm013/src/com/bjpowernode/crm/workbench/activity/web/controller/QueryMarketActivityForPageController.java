package com.bjpowernode.crm.workbench.activity.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.vo.PaginationVO;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author LauShuaichen
 * 分页查询市场活动列表
 * Servlet implementation class QueryMarketActivityForPageController
 */
@WebServlet("/workbench/activity/queryMarketActivityForPage.do")
public class QueryMarketActivityForPageController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.activity.web.controller.MarketActivityQueryForPageController");
		// 获取参数
		String pageNoStr = request.getParameter("pageNo");
		String pageSizeStr = request.getParameter("pageSize");
		String name = request.getParameter("name");
		String owner = request.getParameter("owner");
		String type = request.getParameter("type");
		String state = request.getParameter("state");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		// 封装参数
		Map<String, Object> map = new HashMap<String, Object>();

		long pageNo = 1;
		if (pageNoStr != null && pageNoStr.trim().length() > 0) {
			pageNo = Long.parseLong(pageNoStr);
		}

		int pageSize = 5;
		if (pageSizeStr != null && pageSizeStr.trim().length() > 0) {
			pageSize = Integer.parseInt(pageSizeStr);
		}

		long beginNo = (pageNo - 1) * pageSize;

		map.put("beginNo", beginNo);
		map.put("pageSize", pageSize);
		map.put("name", name);
		map.put("owner", owner);
		map.put("type", type);
		map.put("state", state);
		map.put("startDate", startDate);
		map.put("endDate", endDate);

		// 调用service方法 查询市场活动
		MarketActivityService marketActivityService = (MarketActivityService) ServiceFactory
				.getService(new MarketActivityServiceImpl());
		PaginationVO<MarketActivity> vo = marketActivityService.queryMarketActivityForPageByCondition(map);

		// 把vo转换成json对象
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(vo);

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
