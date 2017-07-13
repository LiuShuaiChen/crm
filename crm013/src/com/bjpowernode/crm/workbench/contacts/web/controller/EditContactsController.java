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

import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.contacts.domain.Contacts;
import com.bjpowernode.crm.workbench.contacts.service.ContactsService;
import com.bjpowernode.crm.workbench.contacts.service.impl.ContactsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 修改 联系人
 * @author LauShuaichen
 * Servlet implementation class EditContactsController
 */
@WebServlet("/workbench/contacts/editContacts.do")
public class EditContactsController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.contacts.web.controller.EditContactsController");
		
		String id = request.getParameter("id");
		
		//调用service 查询联系人
		ContactsService contactsService = (ContactsService) ServiceFactory.getService(new ContactsServiceImpl());
		Contacts contacts = contactsService.EditContactsGetById(id);
		
		UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
		List<User> userList = userService.queryAllUsers();

		Map<String, Object> map = new HashMap<String,Object>();
		
		if (contacts != null) {
			map.put("success", true);
			map.put("contacts", contacts);
			map.put("userList", userList);
		}else {
			map.put("success", false);
		}
		
		String json = new ObjectMapper().writeValueAsString(map);
		
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
