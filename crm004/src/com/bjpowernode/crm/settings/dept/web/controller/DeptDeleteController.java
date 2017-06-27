package com.bjpowernode.crm.settings.dept.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.settings.dept.service.DeptService;
import com.bjpowernode.crm.settings.dept.service.impl.DeptServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;

/**
 * Servlet implementation class DeptDeleteController
 */
@WebServlet("/settings/dept/delete.do")
public class DeptDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("com.bjpowernode.crm.settings.dept.web.controller.DeptDeleteController");
		String[] ids = request.getParameterValues("id");
		
		DeptService deptService = (DeptService) ServiceFactory.getService(new DeptServiceImpl());
		deptService.delete(ids);
		
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
