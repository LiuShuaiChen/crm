package com.bjpowernode.crm.workbench.contacts.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.vo.PaginationVO;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.contacts.domain.Contacts;
import com.bjpowernode.crm.workbench.contacts.service.ContactsService;
import com.bjpowernode.crm.workbench.contacts.service.impl.ContactsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author LauShuaichen
 * Servlet implementation class ListingContactsController
 */
@WebServlet("/workbench/contacts/listingContacts.do")
public class ListingContactsController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.contacts.web.controller.ListingContactsController");
		
		//获取参数
		String pageNoStr = request.getParameter("pageNo");
		String pageSizeStr = request.getParameter("pageSize");
		String owner = request.getParameter("owner");
		String name = request.getParameter("name");
		String fullName = request.getParameter("fullName");
		String source = request.getParameter("source");
		String birth = request.getParameter("birth");
		
		//封装参数
		Map<String, Object>map = new HashMap<String,Object>();
		long pageNo = 1;
		if (pageNoStr != null && pageNoStr.trim().length() > 0) {
			pageNo = Long.parseLong(pageNoStr);
		}
		
		int pageSize = 5;
		if (pageSizeStr != null && pageSizeStr.trim().length() > 0) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		long beginNo = (pageNo - 1) * pageSize;
		map.put("beginNo", beginNo);
		map.put("pageSize", pageSize);
		map.put("owner", owner);
		map.put("name", name);
		map.put("fullName", fullName);
		map.put("source", source);
		map.put("birth", birth);
		
		
		//调用service 获取list对象
		ContactsService contactsService = (ContactsService) ServiceFactory.getService(new ContactsServiceImpl());
		PaginationVO<Contacts> vo = contactsService.listingContacts(map);
		
		String json = new ObjectMapper().writeValueAsString(vo);
		request.setAttribute("data", json);
		request.getRequestDispatcher("/data.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
