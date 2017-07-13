package com.bjpowernode.crm.settings.qx.user.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;

/**
 * Servlet implementation class QxUserChangeStatusUpdateController
 */
@WebServlet("/settings/qx/user/changeStatus.do")
public class QxUserChangeStatusUpdateController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.settings.qx.user.web.controller.QxUserChangeStatusUpdateController");
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		
		UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
		
		userService.changeStatus(id);
		
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
