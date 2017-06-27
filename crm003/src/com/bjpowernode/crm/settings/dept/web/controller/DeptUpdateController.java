package com.bjpowernode.crm.settings.dept.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.settings.dept.domain.Dept;
import com.bjpowernode.crm.settings.dept.service.DeptService;
import com.bjpowernode.crm.settings.dept.service.impl.DeptServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;

/**
 * Servlet implementation class DeptUpdateController
 */
@WebServlet("/settings/dept/update.do")
public class DeptUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("com.bjpowernode.crm.settings.dept.web.controller.DeptUpdateController");
		

		
		String id = request.getParameter("id");
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String manager = request.getParameter("manager");
		String phone = request.getParameter("phone");
		String description = request.getParameter("description");
		
		DeptService deptService = (DeptService) ServiceFactory.getService(new DeptServiceImpl());
		Dept dept = new Dept(id, no, name, manager, phone, description);
		deptService.update(dept);
		
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
