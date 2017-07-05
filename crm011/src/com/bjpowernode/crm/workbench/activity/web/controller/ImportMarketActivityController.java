package com.bjpowernode.crm.workbench.activity.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.utils.DateUtils;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.UUIDutils;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 导入市场活动
 * @author LauShuaichen
 * Servlet implementation class ImportMarketActivityController
 */
@WebServlet("/workbench/activity/remark/importMarketActivity.do")
public class ImportMarketActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("com.bjpowernode.crm.workbench.activity.web.controller.ImportMarketActivityController");
		
		Map<String, Object>retMap = new HashMap<String,Object>();
		
		int ret = 0;
		
		//使用apache-fileupload 解析请求获取excel
		//创建磁盘文件工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//设置临时目录
		String tempFile = this.getServletContext().getRealPath("/tempFile");
		factory.setRepository(new File(tempFile));
		
		//创建文件上传的核心处理类
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//使用upload解析请求 获取数据
		
		try {
			List<FileItem> itemList = upload.parseRequest(request);
			if (itemList != null && itemList.size() > 0) {
				for (FileItem item : itemList) {
					if (item.isFormField()) {
						System.out.println(item.getFieldName() + "=" + item.getString("utf-8"));
					}else {
						InputStream is = item.getInputStream();
						//解析excel 把数据封装成list<marketActivity>
						HSSFWorkbook wb = new HSSFWorkbook(is);
						HSSFSheet sheet = wb.getSheetAt(0);
						HSSFRow row = null;
						HSSFCell cell = null;
						
						List<MarketActivity> activityList = new ArrayList<MarketActivity>();
						MarketActivity activity = null;
						
						for (int i = 1; i <= sheet.getLastRowNum(); i++) {
							row = sheet.getRow(i);
							activity = new MarketActivity();
							activity.setCreateBy(((User)request.getSession().getAttribute("user")).getId());
							activity.setCreateTime(DateUtils.getDate());
							activity.setId(UUIDutils.getUUid());
							activity.setOwner(((User)request.getSession().getAttribute("user")).getId());
							
							for (int j = 0; j < row.getLastCellNum(); j++) {
								cell = row.getCell(j);
								if (j == 0) {
									activity.setName(cell.getStringCellValue());
								}else if (j == 1) {
									activity.setStartDate(cell.getStringCellValue());
								}else if (j == 2) {
									activity.setEndDate(cell.getStringCellValue());
								}else if (j == 3) {
									activity.setBudgetCost((long)cell.getNumericCellValue());
								}else if (j == 4) {
									activity.setBudgetCost((long)cell.getNumericCellValue());
								}else if (j == 5) {
									activity.setDescription(cell.getStringCellValue());
								}
							}
							activityList.add(activity);
							
							if (i % 5 == 0) {
								MarketActivityService marketActivityService = (MarketActivityService) ServiceFactory.getService(new MarketActivityServiceImpl());
								ret += marketActivityService.saveCreateMarketActivityByList(activityList);
								activityList.clear();
							}
						}
						
						if (activityList.size() > 0) {
							MarketActivityService marketActivityService = (MarketActivityService) ServiceFactory.getService(new MarketActivityServiceImpl());
							ret += marketActivityService.saveCreateMarketActivityByList(activityList);
						}
						
						if (ret > 0) {
							retMap.put("success", true);
							retMap.put("count", ret);
						}else {
							retMap.put("success", false);
						}
						
					}
				}
			}
		} catch (FileUploadException e) {
			retMap.put("success", false);
			e.printStackTrace();
		}
		
		String json = new ObjectMapper().writeValueAsString(retMap);
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
