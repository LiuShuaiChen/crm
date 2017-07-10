package com.bjpowernode.crm.workbench.contacts.remark.web.controller;

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
import com.bjpowernode.crm.workbench.contacts.remark.domain.ContactsRemark;
import com.bjpowernode.crm.workbench.contacts.remark.service.ContactRemarkService;
import com.bjpowernode.crm.workbench.contacts.remark.service.impl.ContactRemarkServiceImpl;
import com.bjpowernode.crm.workbench.customer.remark.service.CustomerRemarkService;
import com.bjpowernode.crm.workbench.customer.remark.service.impl.CustomerRemarkServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 更新联系人备注
 * @author LauShuaichen
 * Servlet implementation class UpdateContactsRemaerkController
 */
@WebServlet("/workbench/contacts/remark/updateContactsRemaerk.do")
public class UpdateContactsRemaerkController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.contacts.remark.web.controller.UpdateContactsRemaerkController");
		
		//获取参数
		String id = request.getParameter("id");
		String noteContent  = request.getParameter("noteContent");
		String contactsId = request.getParameter("contactsId");
		
		//封装对象
		ContactsRemark contactsRemark = new ContactsRemark();
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		contactsRemark.setId(id);
		contactsRemark.setNoteContent(noteContent);
		contactsRemark.setContactsId(contactsId);
		contactsRemark.setEditFlag(1);
		contactsRemark.setEditPerson(user.getId());
		contactsRemark.setEditTime(DateUtils.getDate());
		
		//调用service
		ContactRemarkService contactRemarkService =  (ContactRemarkService) ServiceFactory.getService(new ContactRemarkServiceImpl());
		

		int ret = contactRemarkService.updateContactsRemaerk(contactsRemark);
		
		Map<String, Object> map = new HashMap<String,Object>();
		if (ret > 0 ) {
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
