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
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class DeptServiceEditController
 */
@WebServlet("/settings/dept/edit.do")
public class DeptEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("com.bjpowernode.crm.settings.dept.web.controller.DeptServiceEditController");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String id = request.getParameter("id");

		DeptService deptService = (DeptService) ServiceFactory.getService(new DeptServiceImpl());

		Dept dept = deptService.getById(id);

//		StringBuffer buffer = new StringBuffer();
//		
//		buffer.append("{\"id\":\""+d.getId()+"\",\"no\":\""+d.getNo()+"\",\"name\":\""+d.getName()+"\",\"manager\":\""+d.getManager()+"\",\"phone\":\""+d.getPhone()+"\",\"description\":\""+d.getDescription()+"\"}");
//		

		String buffer = "";
		ObjectMapper mapper = new ObjectMapper();
		buffer = mapper.writeValueAsString(dept);

		
		String json = buffer.toString();

		PrintWriter pw = response.getWriter();

		pw.print(json);

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
