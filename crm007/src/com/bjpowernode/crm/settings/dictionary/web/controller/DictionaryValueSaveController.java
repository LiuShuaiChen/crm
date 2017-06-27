package com.bjpowernode.crm.settings.dictionary.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.settings.dictionary.domain.DictionaryValue;
import com.bjpowernode.crm.settings.dictionary.service.DictionaryValueService;
import com.bjpowernode.crm.settings.dictionary.service.impl.DictionaryValueServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.UUIDutils;

/**
 * Servlet implementation class DictionaryValueSaveController
 */
@WebServlet("/settings/dictionary/value/save.do")
public class DictionaryValueSaveController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String id = UUIDutils.getUUid();
		String code = request.getParameter("code");
		String value = request.getParameter("value");
		String text = request.getParameter("text");
		String orderNo = request.getParameter("orderNo");
		
		DictionaryValue dictionaryValue = new DictionaryValue(id, code, value, text, Integer.valueOf(orderNo));
		DictionaryValueService dictionaryValueService = (DictionaryValueService) ServiceFactory.getService(new DictionaryValueServiceImpl());
		dictionaryValueService.insert(dictionaryValue);
		
		response.sendRedirect(request.getContextPath() + "/settings/dictionary/value/list.do");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
