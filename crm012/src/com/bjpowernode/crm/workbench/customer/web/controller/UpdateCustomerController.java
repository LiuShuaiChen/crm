package com.bjpowernode.crm.workbench.customer.web.controller;

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
import com.bjpowernode.crm.workbench.customer.domain.Customer;
import com.bjpowernode.crm.workbench.customer.service.CustomerService;
import com.bjpowernode.crm.workbench.customer.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 获取表单 更新客户信息
 * @author LauShuaichen
 * Servlet implementation class UpdateCustomerController
 */
@WebServlet("/workbench/customer/updateCustomer.do")
public class UpdateCustomerController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.customer.web.controller.UpdateCustomerController");
		
		//获取表单
		String id = request.getParameter("id");
		
		String owner = request.getParameter("owner");
				
		//公司名称作为客户名称
		String name = request.getParameter("name");
		String grade = request.getParameter("grade");
		String phone = request.getParameter("phone");
		String website = request.getParameter("website");
		String annualIncomeStr = request.getParameter("annualIncome");
		String empNumsStr = request.getParameter("empNums");
		String industry = request.getParameter("industry");
		String description = request.getParameter("description");
		String country = request.getParameter("country");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String zipcode = request.getParameter("zipcode");

		//封装对象
		Customer customer = new Customer();
		customer.setId(id);
		customer.setName(name);
		customer.setOwner(owner);
		customer.setGrade(grade);
		customer.setPhone(phone);
		customer.setWebsite(website);
		customer.setAnnualIncome(Integer.parseInt(annualIncomeStr));
		customer.setEmpNums(Integer.parseInt(empNumsStr));
		customer.setIndustry(industry);
		customer.setDescription(description);
		customer.setCountry(country);
		customer.setProvince(province);
		customer.setCity(city);
		customer.setStreet(street);
		customer.setZipcode(zipcode);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		customer.setEditBy(user.getId());
		customer.setEditTime(DateUtils.getDate());
		
		//调用service 将对象持久化
		CustomerService customerService = (CustomerService) ServiceFactory.getService(new CustomerServiceImpl());
		
		int ret = customerService.updateCustomer(customer);
		
		Map<String, Object> map  = new HashMap<String, Object>();
		
		if (ret > 0 ) {
			map.put("success", true);
		}else {
			map.put("success", false);
		}
		
		String json = new ObjectMapper().writeValueAsString(map);
		
		System.out.println("发送的请求==>" + json);
		
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
