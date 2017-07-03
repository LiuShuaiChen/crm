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
import com.bjpowernode.crm.workbench.activity.domain.MarketActivityRemark;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityRemarkService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityRemarkServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 更新修改后的市场活动备注
 * @author LauShuaichen
 * Servlet implementation class UpdateMarketActivityRemarkController
 */
@WebServlet("/workbench/activity/detail/UpdateMarketActivityRemark.do")
public class UpdateMarketActivityRemarkController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.activity.web.controller.UpdateMarketActivityRemarkController");
		
		//获取参数
		String id = request.getParameter("id");
		String noteContent = request.getParameter("noteContent");
		
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		
		MarketActivityRemark marketActivityRemark = new MarketActivityRemark();
		marketActivityRemark.setId(id);
		marketActivityRemark.setEditFlag(1);
		marketActivityRemark.setEditTime(DateUtils.getDate());
		marketActivityRemark.setEditPerson(user.getId());
		marketActivityRemark.setNoteContent(noteContent);
		
		//调用service
		MarketActivityRemarkService marketActivityRemarkService = (MarketActivityRemarkService) ServiceFactory.getService(new MarketActivityRemarkServiceImpl());
		int ret = marketActivityRemarkService.updateMarketActivityRemarkById(marketActivityRemark);
		
		Map<String, Object>map = new HashMap<String,Object>();
		if (ret > 0) {
			map.put("success", true);
			map.put("remark", marketActivityRemark);
		}else {
			map.put("success", false);
		}
		
		String json = new ObjectMapper().writeValueAsString(map);
		
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
