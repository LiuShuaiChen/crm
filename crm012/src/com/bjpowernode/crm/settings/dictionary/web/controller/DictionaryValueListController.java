package com.bjpowernode.crm.settings.dictionary.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.settings.dictionary.domain.DictionaryValue;
import com.bjpowernode.crm.settings.dictionary.service.DictionaryValueService;
import com.bjpowernode.crm.settings.dictionary.service.impl.DictionaryValueServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;

/**
 * Servlet implementation class DictionaryValueListController
 */
@WebServlet("/settings/dictionary/value/list.do")
public class DictionaryValueListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charSet=utf-8");


		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("/crm002/src/com/bjpowernode/crm/settings/dictionary/web/controller/DictionaryValueListController.java");
		
		
//		String id = request.getParameter("id");
//		String code = request.getParameter("code");
//		String value = request.getParameter("value");
//		String text = request.getParameter("text");
//		String orderNo = request.getParameter("orderNo");
		
		
		DictionaryValueService dictionaryValueService = (DictionaryValueService) ServiceFactory.getService(new DictionaryValueServiceImpl());
		List<DictionaryValue> dictionaryValueList = dictionaryValueService.getByAll();

		request.setAttribute("dictionaryValueList", dictionaryValueList);

		request.getRequestDispatcher("/settings/dictionary/value/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

}
