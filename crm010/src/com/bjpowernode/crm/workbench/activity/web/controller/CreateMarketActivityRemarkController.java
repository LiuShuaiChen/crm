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
import com.bjpowernode.crm.workbench.activity.domain.MarketActivityRemark;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityRemarkService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityRemarkServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 添加市场活动备注
 * Servlet implementation class CreateMarketActivityRemarkController
 */
@WebServlet("/workbench/activity/detail/createMarketActivityRemark.do")
public class CreateMarketActivityRemarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.activity.web.controller.CreateMarketActivityRemarkController");

		// 获取表单***********
		// 市场活动id
		String activityId = request.getParameter("activityId");
		// 备注表单
		String noteContent = request.getParameter("noteContent");

		// 封装参数************
		MarketActivityRemark marketActivityRemark = new MarketActivityRemark();
		marketActivityRemark.setId(UUIDutils.getUUid());

		//获取session的登陆用户
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");

		marketActivityRemark.setNotePerson(user.getId());
		marketActivityRemark.setNoteContent(noteContent);
		marketActivityRemark.setNoteTime(DateUtils.getDate());

		// 创建的时候 备注创建者 0 修改的时候 备注修改1*****
		marketActivityRemark.setEditFlag(0);
		marketActivityRemark.setId(UUIDutils.getUUid());
		marketActivityRemark.setNotePerson(user.getId());
		marketActivityRemark.setNoteTime(DateUtils.getDate());

		marketActivityRemark.setMarketingActivitiesId(activityId);

		MarketActivityRemarkService marketActivityRemarkService = (MarketActivityRemarkService) ServiceFactory.getService(new MarketActivityRemarkServiceImpl());
		int ret = marketActivityRemarkService.createMarketActivityRemaerk(marketActivityRemark);

		Map<String, Object> map = new HashMap<String, Object>();

		if (ret > 0) {
			map.put("success", true);
			map.put("marketActivityRemark", marketActivityRemark);
		} else {
			map.put("success", false);
		}

		String json = new ObjectMapper().writeValueAsString(map);
		//验证json
		System.out.println(json);

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
