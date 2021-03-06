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
 * Servlet implementation class DictionaryTypeUpdateController
 * @author LauShuaichen
 */
@WebServlet("/settings/dictionary/type/update.do")
public class DictionaryTypeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("/crm002/src/com/bjpowernode/crm/settings/dictionary/web/controller/DictionaryTypeUpdateController.java");

		
		request.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		DictionaryType dictionaryType = new DictionaryType(code, name, description);
		
		DictionaryTypeService dictionaryTypeService = (DictionaryTypeService) ServiceFactory.getService(new DictionaryTypeServiceImpl());
		dictionaryTypeService.update(dictionaryType);
		
		//request.getRequestDispatcher("/settings/dictionary/type/list.do").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/settings/dictionary/type/list.do");

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
