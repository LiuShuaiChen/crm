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
import javax.servlet.http.HttpSession;

import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.DateUtils;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class EditMarketActivityDetailController
 * @author LauShuaichen
 * 更新市场活动明细页的信息
 */
@WebServlet("/workbench/activity/detail/editMarketActivityDetail.do")
public class EditMarketActivityDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.activity.web.controller.UpdateMarketActivityDetailController");
		
		
		//获取参数  根据id拿到市场活动进行对其修改
		String id = request.getParameter("id");
		
		
		MarketActivityService marketActivityService = (MarketActivityService) ServiceFactory.getService(new MarketActivityServiceImpl());
		MarketActivity marketActivity = marketActivityService.updateEditMarketActivityDetail(id);
		
		UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
		List<User> userList =  userService.queryAllUsers();
		
		//把 marketActivity 和 userList 转成json  返回到客户端
		Map<String, Object> map = new HashMap<String, Object>();
		if (marketActivity != null) {
			map.put("success", true);
			map.put("marketActivity",marketActivity);
			map.put("userList", userList);
		}else {
			map.put("success", false);
		}
		
		//把map 转成 json格式
		String json = new ObjectMapper().writeValueAsString(map);
		
		//输出json 验证
		System.out.println(json);
		
		
		request.setAttribute("data", json);
		request.getRequestDispatcher("/data.jsp").forward(request, response);
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
