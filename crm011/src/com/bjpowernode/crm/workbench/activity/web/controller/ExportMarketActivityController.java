package com.bjpowernode.crm.workbench.activity.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;

/**
 * @author LauShuaichen 市场活动列表导出excel Servlet implementation class
 *         ExportMarketActivityController
 */
@WebServlet("/workbench/activity/exportMarketActivity.do")
public class ExportMarketActivityController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("com.bjpowernode.crm.workbench.activity.web.controller.ExportMarketActivityController");

		// 获取表单
		String name = request.getParameter("name");
		String owner = request.getParameter("owner");
		String type = request.getParameter("type");
		String state = request.getParameter("state");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		// 封装参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("owner", owner);
		map.put("type", type);
		map.put("state", state);
		map.put("startDate", startDate);
		map.put("endDate", endDate);

		// 调用service 查询市场活动
		MarketActivityService marketActivityService = (MarketActivityService) ServiceFactory
				.getService(new MarketActivityServiceImpl());
		List<MarketActivity> marketActivities = marketActivityService.queryMarketActivityByCondition(map);

		// 根据marketActivities 生成 excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("市场活动列表");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);

		cell.setCellValue("id");
		cell = row.createCell(1);
		cell.setCellValue("name");
		cell = row.createCell(2);
		cell.setCellValue("owner");
		cell = row.createCell(3);
		cell.setCellValue("type");
		cell = row.createCell(4);
		cell.setCellValue("state");
		cell = row.createCell(5);
		cell.setCellValue("startDate");
		cell = row.createCell(6);
		cell.setCellValue("endDate");
		cell = row.createCell(7);
		cell.setCellValue("description");

		if (marketActivities != null && marketActivities.size() > 0) {
			MarketActivity marketActivity = null;
			for (int i = 0; i < marketActivities.size(); i++) {
				marketActivity = marketActivities.get(i);
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(marketActivity.getId());
				cell = row.createCell(1);
				cell.setCellValue(marketActivity.getName());
				cell = row.createCell(2);
				cell.setCellValue(marketActivity.getOwner());
				cell = row.createCell(3);
				cell.setCellValue(marketActivity.getType());
				cell = row.createCell(4);
				cell.setCellValue(marketActivity.getState());
				cell = row.createCell(5);
				cell.setCellValue(marketActivity.getStartDate());
				cell = row.createCell(6);
				cell.setCellValue(marketActivity.getEndDate());
			}
		}

		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		// 获取浏览器信息
		String browser = request.getHeader("User-Agent");

		String fileName = URLEncoder.encode("市场活动列表", "utf-8");
		if (browser.toLowerCase().contains("firefox")) {
			fileName = new String("市场活动列表".getBytes("utf-8"), "ISO8859-1");
		}
		response.addHeader("Content-Disposition", "atachment;filename=" + fileName + ".xls");
		
		//获取输出流
		OutputStream outputStream = response.getOutputStream();
		wb.write(outputStream);
		outputStream.flush();

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
