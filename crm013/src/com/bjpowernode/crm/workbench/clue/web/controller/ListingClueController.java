package com.bjpowernode.crm.workbench.clue.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.mail.ErrorInQuitException;

import com.bjpowernode.crm.commons.vo.PaginationVO;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.clue.domain.Clue;
import com.bjpowernode.crm.workbench.clue.service.ClueService;
import com.bjpowernode.crm.workbench.clue.service.impl.ClueServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 线索 首页  列表分页查询显示
 * @author LauShuaichen
 * Servlet implementation class ListingClueController
 */
@WebServlet("/workbench/clue/list.do")
public class ListingClueController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("com.bjpowernode.crm.workbench.clue.web.controller.ListingClueController");
		
		//获取参数_分页
		String pageNoStr = request.getParameter("pageNo");
		String pageSizeStr = request.getParameter("pageSize");
		
		//获取参数_条件
		String name = request.getParameter("name");
		String company = request.getParameter("company");
		String phone = request.getParameter("phone");
		String source = request.getParameter("source");
		String owner = request.getParameter("owner");
		String mphone = request.getParameter("mphone");
		String state = request.getParameter("state");
		String industry = request.getParameter("industry");
		String grade = request.getParameter("grade");
		
		//封装参数
		Map<String, Object>map = new HashMap<String,Object>();

		long pageNo = 1;
		if (pageNoStr != null && pageNoStr.trim().length() > 0) {
			pageNo = Long.parseLong(pageNoStr);
		}

		int pageSize = 5;
		if (pageSizeStr != null && pageSizeStr.trim().length() > 0) {
			pageSize = Integer.parseInt(pageSizeStr);
		}

		long beginNo = (pageNo - 1) * pageSize;
		map.put("beginNo", beginNo);
		map.put("pageSize", pageSize);
		
		map.put("name", name);
		map.put("company", company);
		map.put("phone", phone);
		map.put("source", source);
		map.put("owner", owner);
		map.put("mphone", mphone);
		map.put("state", state);
		map.put("industry", industry);
		map.put("grade", grade);
		
		//调用service  查询 线索
		ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
		PaginationVO<Clue> vo = clueService.queryClueForPageByCondition(map);
		
		//装入json 响应信息
		String json = new ObjectMapper().writeValueAsString(vo);
		request.setAttribute("data", json);
		request.getRequestDispatcher("/data.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
