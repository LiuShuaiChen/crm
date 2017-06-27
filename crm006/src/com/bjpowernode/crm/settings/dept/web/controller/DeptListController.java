package com.bjpowernode.crm.settings.dept.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.settings.dept.domain.Dept;
import com.bjpowernode.crm.settings.dept.service.DeptService;
import com.bjpowernode.crm.settings.dept.service.impl.DeptServiceImpl;
import com.bjpowernode.crm.utils.PageUtils;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class DeptListControllrt
 */
@WebServlet("/settings/dept/list.do")
public class DeptListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/crm003/src/com/bjpowernode/crm/settings/dept/web/controller/DeptListControllrt.java");
		request.setCharacterEncoding("utf-8");
		
		String pageNoStr = request.getParameter("pageNo");
		
		DeptService deptService = (DeptService) ServiceFactory.getService(new DeptServiceImpl());
		Map<String, Object>map = deptService.getAll(pageNoStr);
		PageUtils pu = (PageUtils) map.get("pu");
		List<Dept> dList = (List<Dept>) map.get("dList");
		
//		request.setAttribute("pu", pu);
//		request.setAttribute("deptList", deptList);
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("pu", pu);
		retMap.put("dList", dList);
		String buffer = "";
		ObjectMapper mapper = new ObjectMapper();
		buffer = mapper.writeValueAsString(retMap);
		
		System.out.println(buffer.toString());
		
//		request.setAttribute("data", buffer);
//		request.getRequestDispatcher("/data.jsp").forward(request, response);

		
		response.setContentType("text/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(buffer);
		pw.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
