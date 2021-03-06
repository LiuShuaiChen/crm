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
import com.bjpowernode.crm.utils.UUIDutils;

/**
 * Servlet implementation class DeptSaveController
 */
@WebServlet("/settings/dept/save.do")
public class DeptSaveController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.settings.dept.web.controller.DeptSaveController");

		String id = UUIDutils.getUUid();
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String manager = request.getParameter("manager");
		String phone = request.getParameter("phone");
		String description = request.getParameter("description");

		Dept dept = new Dept(id, no, name, manager, phone, description);

		DeptService deptService = (DeptService) ServiceFactory.getService(new DeptServiceImpl());
		deptService.insert(dept);

		System.out.println(dept.getId());

		PrintWriter pw = response.getWriter();
		pw.print("success");
		pw.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
