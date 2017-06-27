package com.bjpowernode.crm.settings.qx.user.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.PageUtils;
import com.bjpowernode.crm.utils.ServiceFactory;

/**
 * @author LauShuaichen
 * Servlet implementation class QxUserListController
 */
@WebServlet("/settings/qx/user/list.do")
public class QxUserListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.settings.qx.web.controller.QxUserListController");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		String pageNoStr = request.getParameter("pageNo");
		String name = request.getParameter("name");
		String deptId = request.getParameter("deptId");
		String lockStatus = request.getParameter("lockStatus");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String expireTime = request.getParameter("expireTime");
		
		

		
		UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
		Map<String, Object> map = userService.getAll(pageNoStr, name, deptId, lockStatus, startTime, endTime);
		
		PageUtils pu = (PageUtils) map.get("pu");
		List<User> uList = (List<User>) map.get("uList");
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("{\"pu\":{\"pageNo\":"+pu.getPageNo()+",\"pageCount\":"+pu.getPageCount()+",\"pageSize\":"+pu.getPageSize()+",\"total\":"+pu.getTotal()+"},\"uList\":[");
		
		
		for (int i = 0; i < uList.size(); i++) {
			User u = uList.get(i);
			
			String lockStatus1 = u.getLockStatus();
			String lockStatusStr = "启用";
			if ("1".equals(lockStatus1)) {
				lockStatusStr = "锁定";
			}
			
			buffer.append("{\"id\":\""+u.getId()+"\",\"loginAct\":\""+u.getLoginAct()+"\",\"loginPwd\":\""+u.getLoginPwd()+"\",\"name\":\""+u.getName()+"\",\"deptId\":\"" + u.getDeptId() + "\",\"email\":\""+u.getEmail()+"\",\"lockStatus\":\""+u.getLockStatus()+"\",\"allowIps\":\""+u.getAllowIps()+"\",\"expireTime\":\""+u.getExpireTime()+"\",\"createBy\":\""+u.getCreateBy()+"\",\"createTime\":\""+u.getCreateTime()+"\",\"editBy\":\""+u.getEditBy()+"\",\"editTime\":\""+u.getEditTime()+"\",\"lockStatusStr\":\""+lockStatusStr+"\"}");

			if (i < uList.size() - 1) {
				buffer.append(",");
			}
		}
		
		buffer.append("]}");
		
		System.out.println(buffer.toString());
		
		PrintWriter pw = response.getWriter();
		
		pw.print(buffer.toString());
		pw.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
