package com.bjpowernode.crm.settings.qx.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.settings.qx.domain.User;
import com.bjpowernode.crm.settings.qx.service.UserService;
import com.bjpowernode.crm.settings.qx.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.DateUtils;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.UUIDutils;

/**
 * Servlet implementation class QxUserUpdateController
 */
@WebServlet("/settings/qx/user/update.do")
public class QxUserUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.settings.qx.web.controller.QxUserUpdateController");
		
		String id = request.getParameter("id");
		String loginAct = request.getParameter("loginActNo");
		String loginPwd = request.getParameter("loginPwd");
		String name = request.getParameter("name");
		String deptName = request.getParameter("deptName");
		String email = request.getParameter("email");
		String expireTime = request.getParameter("expireTime");
		String lockStatus = request.getParameter("lockStatus");
		String allowIps = request.getParameter("allowIps");
		
		User user = new User(UUIDutils.getUUid(), loginAct, loginPwd, name, email, lockStatus, expireTime, allowIps, deptName, "alice", DateUtils.getDate());
				
		
		UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
		userService.update(user);
		
		PrintWriter pw = response.getWriter();
		pw.print("success");
		pw.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
