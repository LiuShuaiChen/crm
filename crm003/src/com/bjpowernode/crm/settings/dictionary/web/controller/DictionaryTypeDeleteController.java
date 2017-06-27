package com.bjpowernode.crm.settings.dictionary.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.settings.dictionary.service.DictionaryTypeService;
import com.bjpowernode.crm.settings.dictionary.service.impl.DictionaryTypeServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;

/**
 * Servlet implementation class DictonaryTypeDeleteController
 * 
 * 删除操作
 * @author LauShuaichen
 */
@WebServlet("/settings/dictionary/type/delete.do")
public class DictionaryTypeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("/crm002/src/com/bjpowernode/crm/settings/dictionary/web/controller/DictonaryTypeDeleteController.java");
		
		String[] codes = request.getParameterValues("code");
		
		DictionaryTypeService dictionaryTypeService = (DictionaryTypeService) ServiceFactory.getService(new DictionaryTypeServiceImpl());
		
		dictionaryTypeService.delete(codes);

		response.sendRedirect(request.getContextPath() + "/settings/dictionary/type/list.do");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
