package com.bjpowernode.crm.workbench.clue.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.clue.domain.Clue;
import com.bjpowernode.crm.workbench.clue.service.ClueService;
import com.bjpowernode.crm.workbench.clue.service.impl.ClueServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class ReflushClueDetailController
 */
@WebServlet("/workbench/clue/detail/reflushClueDetail.do")
public class ReflushClueDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("com.bjpowernode.crm.workbench.clue.web.controller.ReflushClueDetailController");
		
		//获取表单
		String id = request.getParameter("id");
		
		
		//调用service 
		ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
		Clue clue = clueService.queryClueForDetail(id);
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		if (clue != null) {
			map.put("success", true);
			map.put("clue", clue);
		}else {
			map.put("success", false);
		}
		
		//转成json 响应异步
		String json = new ObjectMapper().writeValueAsString(map);
		
		System.out.println(json);
		
		
		request.setAttribute("data", json);
		request.getRequestDispatcher("/data.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
