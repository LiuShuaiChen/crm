package com.bjpowernode.crm.settings.dictionary.web.controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class DictionaryTypeListController
 * 
 * @author LauShuaichen
 */
@WebServlet("/settings/dictionary/type/list.do")
public class DictionaryTypeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
	
		System.out.println("/crm002/src/com/bjpowernode/crm/settings/dictionary/web/controller/DictionaryTypeListController.java");
		
		DictionaryTypeService dictionaryTypeService = (DictionaryTypeService) ServiceFactory.getService(new DictionaryTypeServiceImpl());
		List<DictionaryType> dicList = dictionaryTypeService.getByAll();
		request.setAttribute("dicList", dicList);
		
		request.getRequestDispatcher("/settings/dictionary/type/index.jsp").forward(request, response);
		
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
