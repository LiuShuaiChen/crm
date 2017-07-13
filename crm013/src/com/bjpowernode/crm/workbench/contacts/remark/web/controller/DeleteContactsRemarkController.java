package com.bjpowernode.crm.workbench.contacts.remark.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.contacts.remark.service.ContactRemarkService;
import com.bjpowernode.crm.workbench.contacts.remark.service.impl.ContactRemarkServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 删除联系人备注id
 * @author LauShuaichen
 * Servlet implementation class DeleteContactsRemarkController
 */
@WebServlet("/workbench/contacts/remark/deleteContactsRemark.do")
public class DeleteContactsRemarkController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取表单
		String id = request.getParameter("id");
		
		//调用service
		ContactRemarkService contactRemarkService = (ContactRemarkService) ServiceFactory.getService(new ContactRemarkServiceImpl());
		int ret = contactRemarkService.deleteContactsRemark(id);
		
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
