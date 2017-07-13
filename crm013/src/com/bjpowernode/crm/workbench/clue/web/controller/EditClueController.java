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

import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.clue.domain.Clue;
import com.bjpowernode.crm.workbench.clue.service.ClueService;
import com.bjpowernode.crm.workbench.clue.service.impl.ClueServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 修改线索 获取id 拿到线索 放到 修改线索 模态窗口中
 * 
 * @author LauShuaichen
 * Servlet implementation class EditClueController
 */
@WebServlet("/workbench/clue/edit.do")
public class EditClueController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("com.bjpowernode.crm.workbench.clue.web.controller.EditClueController");
		
		//获取表单
		String id = request.getParameter("id");
				
		//调用service 拿到对象
		ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
		Clue clue = clueService.editClueById(id);
		
		//获取所有者
		UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
		List<User> userList = userService.queryAllUsers();
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		if (clue != null) {
			map.put("success", true);
			map.put("clue", clue);
			map.put("userList", userList);
		}else {
			map.put("success", false);
		}
		
		//响应
		String json = new ObjectMapper().writeValueAsString(map);
		System.err.println(json);
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
