package com.bjpowernode.crm.settings.dictionary.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.settings.dictionary.domain.DictionaryType;
import com.bjpowernode.crm.settings.dictionary.service.DictionaryTypeService;
import com.bjpowernode.crm.settings.dictionary.service.impl.DictionaryTypeServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;

/**
 * Servlet implementation class DictionaryTypeEditController
 * 编辑操作
 * 
 * @author LauShuaichen
 */
@WebServlet("/settings/dictionary/type/edit.do")
public class DictionaryTypeEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("/crm002/src/com/bjpowernode/crm/settings/dictionary/web/controller/DictionaryTypeEditController.java");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charSet=utf-8");
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		String code = request.getParameter("code");
		System.out.println("要修改的code是==>" + code);
		
//		String name = request.getParameter("name");
//		String description = request.getParameter("description");

		DictionaryTypeService dictionaryTypeService = (DictionaryTypeService) ServiceFactory.getService(new DictionaryTypeServiceImpl());
		DictionaryType dictionaryType = dictionaryTypeService.getByCode(code);
		
//		dictionaryTypeService.update(dictionaryType);

		request.setAttribute("dictionaryType", dictionaryType);

		request.getRequestDispatcher("/settings/dictionary/type/edit.jsp").forward(request, response);
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
