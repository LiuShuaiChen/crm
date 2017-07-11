package com.bjpowernode.crm.workbench.contacts.web.controller;

import java.io.Console;
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
import com.bjpowernode.crm.utils.UUIDutils;
import com.bjpowernode.crm.workbench.contacts.domain.Contacts;
import com.bjpowernode.crm.workbench.contacts.service.ContactsService;
import com.bjpowernode.crm.workbench.contacts.service.impl.ContactsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 创建联系人
 * 
 * @author LauShuaichen Servlet implementation class CreateContactsController
 */
@WebServlet("/workbench/contacts/createContacts.do")
public class CreateContactsController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.contacts.web.controller.CreateContactsController");

		// 获取表单
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
		String customerId = request.getParameter("customerId");
		String contactSummary = request.getParameter("contactSummary");

		//封装对象
		Contacts contacts = new Contacts();
		contacts.setId(UUIDutils.getUUid());
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
		contacts.setCustomerId(customerId);
		contacts.setContactSummary(contactSummary);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		contacts.setCreateBy(user.getId());
		contacts.setCreateTime(DateUtils.getDate());
		
		
		//调用service 持久化对象
		ContactsService contactsService = (ContactsService) ServiceFactory.getService(new ContactsServiceImpl());
		int ret = contactsService.createContacts(contacts);
		
		Map<String, Object>map = new HashMap<String,Object>();
		
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
