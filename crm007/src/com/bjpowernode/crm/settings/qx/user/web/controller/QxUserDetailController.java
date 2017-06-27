package com.bjpowernode.crm.settings.qx.user.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class QxUserDetailController
 */
@WebServlet("/settings/qx/user/detail.do")
public class QxUserDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("com.bjpowernode.crm.settings.qx.web.controller.QxUserDetailController");
		
		String id = request.getParameter("id");
//		String code = request.getParameter("code");
//		String name = request.getParameter("name");
//		String manager = request.getParameter("manager");
//		String phone = request.getParameter("phone");
//		String description = request.getParameter("description");
		
		UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
		User user = userService.getById(id);
		
		
		
		String lockStatus1 = user.getLockStatus();
		String lockStatusStr = "启用";
		if ("1".equals(lockStatus1)) {
			lockStatusStr = "锁定";
		}

//		StringBuffer buffer = new StringBuffer();
//
//		buffer.append("{\"id\":\""+u.getId()+"\",\"loginAct\":\""+u.getLoginAct()+"\",\"loginPwd\":\""+u.getLoginPwd()+"\",\"name\":\""+u.getName()+"\",\"deptName\":\""+u.getDeptName()+"\",\"email\":\""+u.getEmail()+"\",\"lockStatus\":\""+u.getLockStatus()+"\",\"allowIps\":\""+u.getAllowIps()+"\",\"expireTime\":\""+u.getExpireTime()+"\",\"createBy\":\""+u.getCreateBy()+"\",\"createTime\":\""+u.getCreateTime()+"\",\"updateBy\":\""+u.getUpdateBy()+"\",\"updateTime\":\""+u.getUpdateTime()+"\",\"lockStatusStr\":\""+lockStatusStr+"\"}");
//		
		
		String buffer = "";
		ObjectMapper mapper = new ObjectMapper();
		buffer = mapper.writeValueAsString(user);
		
		PrintWriter pw = response.getWriter();
		pw.print(buffer.toString());
		pw.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
