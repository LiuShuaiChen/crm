package com.bjpowernode.crm.workbench.customer.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.vo.PaginationVO;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.clue.domain.Clue;
import com.bjpowernode.crm.workbench.customer.domain.Customer;
import com.bjpowernode.crm.workbench.customer.remark.domain.CustomerRemark;
import com.bjpowernode.crm.workbench.customer.remark.service.CustomerRemarkService;
import com.bjpowernode.crm.workbench.customer.remark.service.impl.CustomerRemarkServiceImpl;
import com.bjpowernode.crm.workbench.customer.service.CustomerService;
import com.bjpowernode.crm.workbench.customer.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * customer
 * 分页查询 客户列表页
 * 
 * @author LauShuaichen Servlet implementation class ListingCustomerController
 */
@WebServlet("/workbench/customer/listingCustomer.do")
public class ListingCustomerController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.customer.web.controller.ListingCustomerController");

		// 获取参数_分页
		String pageNoStr = request.getParameter("pageNo");
		String pageSizeStr = request.getParameter("pageSize");

		//获取的条件
		String name = request.getParameter("name");
		String owner = request.getParameter("owner");
		String phone = request.getParameter("phone");
		String website = request.getParameter("website");
		String grade = request.getParameter("grade");
		String industry = request.getParameter("industry");
		
		//封装参数
		Map<String, Object> map = new HashMap<String,Object>();
		
		int pageNo = 1;
		if (pageNoStr != null && pageNoStr.trim().length() > 0) {
			pageNo = Integer.parseInt(pageNoStr);
		}

		int pageSize = 5;
		if (pageSizeStr != null && pageSizeStr.trim().length() > 0) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		int beginNo = (pageNo - 1) * pageSize;
		map.put("beginNo", beginNo);
		map.put("pageSize", pageSize);
		
		map.put("name", name);
		map.put("owner", owner);
		map.put("phone", phone);
		map.put("website", website);
		map.put("grade", grade);
		map.put("industry", industry);
		
		//调用service 
		CustomerService customerService = (CustomerService) ServiceFactory.getService(new CustomerServiceImpl());
		PaginationVO<Customer> vo = customerService.queryCustomerForPageByCondition(map);
		
		//转换json 响应json
		String json = new ObjectMapper().writeValueAsString(vo);
		request.setAttribute("data", json);
		request.getRequestDispatcher("/data.jsp").forward(request, response);

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
