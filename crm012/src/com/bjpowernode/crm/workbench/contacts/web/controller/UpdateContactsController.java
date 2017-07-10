package com.bjpowernode.crm.workbench.contacts.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.utils.DateUtils;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.contacts.domain.Contacts;
import com.bjpowernode.crm.workbench.contacts.service.ContactsService;
import com.bjpowernode.crm.workbench.contacts.service.impl.ContactsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 更新联系人
 * @author LauShuaichen
 * Servlet implementation class UpdateContactsController
 */
@WebServlet("/workbench/contacts/updateContacts.do")
public class UpdateContactsController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.contacts.web.controller.UpdateContactsController");
		
		//获取表单
		String id = request.getParameter("id");
		String owner = request.getParameter("owner");
		String source = request.getParameter("source");
		String appellation = request.getParameter("appellation");
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String job = request.getParameter("job");
		String mphone = request.getParameter("mphone");
		String description = request.getParameter("description");
		String country = request.getParameter("country");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String zipcode = request.getParameter("zipcode");
		String birth = request.getParameter("birth");
		String contactSummary = request.getParameter("contactSummary");
		
		//封装对象
		Contacts contacts = new Contacts();
		contacts.setId(id);
		contacts.setOwner(owner);
		contacts.setSource(source);
		contacts.setAppellation(appellation);
		contacts.setFullName(fullName);
		contacts.setEmail(email);
		contacts.setJob(job);
		contacts.setMphone(mphone);
		contacts.setDescription(description);
		contacts.setCountry(country);
		contacts.setProvince(province);
		contacts.setCity(city);
		contacts.setStreet(street);
		contacts.setZipcode(zipcode);
		contacts.setBirth(birth);
		contacts.setContactSummary(contactSummary);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		contacts.setEditBy(user.getId());
		contacts.setEditTime(DateUtils.getDate());

		//调用service
		ContactsService contactsService = (ContactsService) ServiceFactory.getService(new ContactsServiceImpl());
		int ret = contactsService.updateContacts(contacts);
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		if (ret > 0) {
			map.put("success", true);
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
