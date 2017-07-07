package com.bjpowernode.crm.workbench.clue.web.controller;

import java.io.IOException;
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
 * 查看线索明细
 * @author LauShuaichen
 * Servlet implementation class LookClueDetailController
 */
@WebServlet("/workbench/clue/detail.do")
public class LookClueDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.clue.web.controller.LookClueDetailController");
		
		String id = request.getParameter("id");
		
		ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
		Clue clue = clueService.queryClueForDetail(id);
		
		//备注没有写
		
		
		request.setAttribute("clue", clue);
		request.getRequestDispatcher("/workbench/clue/detail.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
