package com.bjpowernode.crm.security.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.security.exceptions.ApplicationException;
import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;

/**
 * 登录 servlet
 * Servlet implementation class LoginController
 */
@WebServlet("/security/login.do")
public class LoginController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		System.out.println("com.bjpowernode.crm.security.web.controller.LoginController");
		
		//获取表单
		String act = request.getParameter("act");
		String pwd = request.getParameter("pwd");
		
		//获取ip
		String ip = request.getRemoteAddr();
		
		UserService userService = new UserServiceImpl();
		
		System.out.println(userService.toString() + "==ip==>" + ip);
		
		String message = "";
		String json = "";
		
		try{
			User user = userService.login(act,pwd,ip);
			request.getSession().setAttribute("user",user);
			json = "{\"success\":true}";
		}catch(ApplicationException e){
			//e.printStackTrace();
			message = e.getMessage();
			json = "{\"success\":false,\"msg\":\""+message+"\"}";
		}
		
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.flush();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
