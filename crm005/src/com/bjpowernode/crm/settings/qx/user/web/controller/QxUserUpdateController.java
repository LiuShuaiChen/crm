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
		String deptId = request.getParameter("deptId");
		String email = request.getParameter("email");
		String expireTime = request.getParameter("expireTime");
		String lockStatus = request.getParameter("lockStatus");
		String allowIps = request.getParameter("allowIps");
		
		User user = new User();
		user.setId(id);
		user.setLoginAct(loginAct);
		user.setLoginPwd(loginPwd);
		user.setName(name);
		user.setDeptId(deptId);
		user.setEmail(email);
		user.setExpireTime(expireTime);
		user.setLockStatus(lockStatus);
		user.setAllowIps(allowIps);
				
		
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
