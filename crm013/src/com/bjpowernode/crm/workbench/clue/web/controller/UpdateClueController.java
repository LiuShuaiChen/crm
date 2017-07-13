package com.bjpowernode.crm.workbench.clue.web.controller;

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
import com.bjpowernode.crm.workbench.clue.domain.Clue;
import com.bjpowernode.crm.workbench.clue.service.ClueService;
import com.bjpowernode.crm.workbench.clue.service.impl.ClueServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 更新 线索 线索列表中的 修改 模态窗口中的 更新
 * 
 * @author LauShuaichen
 * 
 * Servlet implementation class UpdateClueController
 */
@WebServlet("/workbench/clue/update.do")
public class UpdateClueController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("com.bjpowernode.crm.workbench.clue.web.controller.UpdateClueController");

		//获取表单
		String id = request.getParameter("id");
		String owner = request.getParameter("owner");
		String company = request.getParameter("company");
		String appellation = request.getParameter("appellation");
		String fullName = request.getParameter("fullName");
		String job = request.getParameter("job");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String mphone = request.getParameter("mphone");
		String website = request.getParameter("website");
		String state = request.getParameter("state");
		String source = request.getParameter("source");
		String empNums = request.getParameter("empNums");
		String industry = request.getParameter("industry");
		String grade = request.getParameter("grade");
		String annualIncome = request.getParameter("annualIncome");
		String description = request.getParameter("description");
		String contactSummary = request.getParameter("contactSummary");
		String nextContactTime = request.getParameter("nextContactTime");
		String province = request.getParameter("province");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String zipcode = request.getParameter("zipcode");
		
		//封装对象
		Clue clue = new Clue();
		clue.setId(id);
		clue.setOwner(owner);
		clue.setCompany(company);
		clue.setAppellation(appellation);
		clue.setFullName(fullName);
		clue.setJob(job);
		clue.setEmail(email);
		clue.setPhone(phone);
		clue.setMphone(mphone);
		clue.setWebsite(website);
		clue.setState(state);
		clue.setSource(source);
		clue.setEmpNums(Integer.parseInt(empNums));
		clue.setIndustry(industry);
		clue.setGrade(grade);
		clue.setAnnualIncome(Integer.parseInt(annualIncome));
		clue.setDescription(description);
		clue.setContactSummary(contactSummary);
		clue.setNextContactTime(nextContactTime);
		clue.setProvince(province);
		clue.setCountry(country);
		clue.setCity(city);
		clue.setStreet(street);
		clue.setZipcode(zipcode);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		clue.setEditBy(user.getId());
		clue.setEditTime(DateUtils.getDate());
		
		//调用service
		ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
		int ret = clueService.updateClue(clue);
		
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
