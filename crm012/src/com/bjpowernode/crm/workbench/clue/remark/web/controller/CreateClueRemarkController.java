package com.bjpowernode.crm.workbench.clue.remark.web.controller;

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
import com.bjpowernode.crm.workbench.clue.remark.domain.ClueRemark;
import com.bjpowernode.crm.workbench.clue.remark.service.ClueRemarkService;
import com.bjpowernode.crm.workbench.clue.remark.service.impl.ClueRemarkServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class CreateClueRemarkController
 */
@WebServlet("/workbench/clue/detail/remark/createClueRemark.do")
public class CreateClueRemarkController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.err.println("com.bjpowernode.crm.workbench.clue.remark.web.controller.CreateClueRemarkController");

		// 获取参数
		String clueId = request.getParameter("clueId");
		String noteContent = request.getParameter("noteContent");

		// 封装参数
		ClueRemark remark = new ClueRemark();
		remark.setId(UUIDutils.getUUid());

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		remark.setNotePerson(user.getId());
		remark.setNoteContent(noteContent);
		remark.setNoteTime(DateUtils.getDate());

		remark.setEditFlag(0);
		remark.setClueId(clueId);

		// 调用service 保存线索备注
		ClueRemarkService clueRemarkService = (ClueRemarkService) ServiceFactory.getService(new ClueRemarkServiceImpl());
		int ret = clueRemarkService.createClueRemark(remark);

		Map<String, Object> map = new HashMap<String, Object>();

		if (ret > 0) {
			map.put("success", true);
			map.put("remark", remark);
		} else {
			map.put("success", false);
		}

		//转换json 响应信息
		String json = new ObjectMapper().writeValueAsString(map);
		
		System.err.println(json);
		
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
