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
import com.bjpowernode.crm.utils.UUIDutils;
import com.bjpowernode.crm.workbench.clue.domain.Clue;
import com.bjpowernode.crm.workbench.clue.service.ClueService;
import com.bjpowernode.crm.workbench.clue.service.impl.ClueServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 创建新线索
 * 
 * @author LauShuaichen Servlet implementation class CreateClueController
 */
@WebServlet("/workbench/clue/createClue.do")
public class CreateClueController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.clue.web.controller.CreateClueController");

		// 获取表单
		String id = request.getParameter("id");
		String owner = request.getParameter("owner");
		String company = request.getParameter("company");
		String phone = request.getParameter("phone");
		String website = request.getParameter("website");
		String grade = request.getParameter("grade");
		String industry = request.getParameter("industry");
		String annualIncome = request.getParameter("annualIncome");
		String empNums = request.getParameter("empNums");

		String country = request.getParameter("country");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String zipcode = request.getParameter("zipcode");
		String description = request.getParameter("description");
		String fullName = request.getParameter("fullName");
		String appellation = request.getParameter("appellation");
		String source = request.getParameter("source");
		String email = request.getParameter("email");
		String mphone = request.getParameter("mphone");
		String job = request.getParameter("job");
		String state = request.getParameter("state");

		String contactSummary = request.getParameter("contactSummary");
		String nextContactTime = request.getParameter("nextContactTime");

		Clue clue = new Clue();
		clue.setId(UUIDutils.getUUid());
		clue.setOwner(owner);
		clue.setCompany(company);
		clue.setPhone(mphone);
		clue.setWebsite(website);
		clue.setGrade(grade);
		clue.setIndustry(industry);
		clue.setAnnualIncome(Integer.parseInt(annualIncome));
		clue.setEmpNums(Integer.parseInt(empNums));
		clue.setCountry(country);
		clue.setProvince(province);
		clue.setStreet(street);
		clue.setZipcode(zipcode);
		clue.setDescription(description);
		clue.setFullName(fullName);
		clue.setAppellation(appellation);
		clue.setSource(source);
		clue.setEmail(email);
		clue.setMphone(mphone);
		clue.setJob(job);
		clue.setState(state);

		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");

		clue.setCreateBy(user.getId());
		clue.setCreateTime(DateUtils.getDate());

		ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
		int ret = clueService.createClue(clue);
		
		Map<String, Object>map = new HashMap<String,Object>();
		
		if (ret > 0) {
			map.put("success", true);
			map.put("clue", clue);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
