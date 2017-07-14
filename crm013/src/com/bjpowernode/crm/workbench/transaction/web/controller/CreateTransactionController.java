package com.bjpowernode.crm.workbench.transaction.web.controller;

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
import com.bjpowernode.crm.workbench.transaction.domain.Transaction;
import com.bjpowernode.crm.workbench.transaction.service.TransactionService;
import com.bjpowernode.crm.workbench.transaction.service.impl.TransactionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 获取参数 创建交易
 * 
 * @author LauShuaichen Servlet implementation class CreateTransactionController
 */
@WebServlet("/workbench/tranaction/createTransaction.do")
public class CreateTransactionController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("com.bjpowernode.crm.workbench.transaction.web.controller.CreateTransactionController");

		// 获取参数
		String owner = request.getParameter("owner");
		String amountOfMoneyStr = request.getParameter("amountOfMoney");
		String name = request.getParameter("name");
		String expectedClosingDate = request.getParameter("expectedClosingDate");
		String customerId = request.getParameter("customerId");
		String stage = request.getParameter("stage");
		String type = request.getParameter("type");
		String source = request.getParameter("source");
		String activityId = request.getParameter("activityId");
		String contactsId = request.getParameter("contactsId");
		String description = request.getParameter("description");
		String createBy = request.getParameter("createBy");
		String createTime = request.getParameter("createTime");
		String contactSummary = request.getParameter("contactSummary");
		String nextContactTime = request.getParameter("nextContactTime");

		// 封装对象
		Transaction transaction = new Transaction();
		transaction.setId(UUIDutils.getUUid());
		transaction.setOwner(owner);

		if (amountOfMoneyStr != null && amountOfMoneyStr.trim().length() > 0) {
			transaction.setAmountOfMoney(Integer.parseInt(amountOfMoneyStr));
		}

		transaction.setName(name);
		transaction.setExpectedClosingDate(expectedClosingDate);
		transaction.setCustomerId(customerId);
		transaction.setStage(stage);
		transaction.setType(type);
		transaction.setSource(source);
		transaction.setActivityId(activityId);
		transaction.setDescription(description);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		transaction.setCreateBy(user.getId());
		transaction.setCreateTime(DateUtils.getDate());
		transaction.setContactSummary(contactSummary);
		transaction.setNextContactTime(nextContactTime);

		// 调用service 持久化对象
		TransactionService transactionService = (TransactionService) ServiceFactory.getService(new TransactionServiceImpl());
		int ret = transactionService.CreateTransaction(transaction);

		Map<String, Object> map = new HashMap<String, Object>();
		
		if (ret > 0) {
			map.put("success", true);
		} else {
			map.put("success", false);
		}
		
		//发送请求 处理响应
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
		doGet(request, response);
	}

}
