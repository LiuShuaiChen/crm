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
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 获取要修改联系人备注
 * @author LauShuaichen
 * Servlet implementation class EditContactsRemarkController
 */
@WebServlet("/workbench/contacts/remark/editContactsRemark.do")
public class EditContactsRemarkController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.contacts.remark.web.controller.EditContactsRemarkController");
		
		//获取表单
		String id = request.getParameter("id");

		
		//调用service
		ContactRemarkService contactRemarkService = (ContactRemarkService) ServiceFactory.getService(new ContactRemarkServiceImpl());
		ContactsRemark contactsRemarkt = contactRemarkService.editContactsRemark(id);
		
		Map<String, Object>map = new HashMap<String,Object>();
		
		if (contactsRemarkt != null) {
			map.put("success", true);
			map.put("contactsRemarkt", contactsRemarkt);
		}else {
			map.put("success", false);
		}
		
		String json = new ObjectMapper().writeValueAsString(map);
		request.setAttribute("data", json);
		request.getRequestDispatcher("/workbench/contacts/detail.jsp").forward(request, response);
				
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
