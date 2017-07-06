package com.bjpowernode.crm.commons.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.bjpowernode.crm.settings.dictionary.domain.DictionaryValue;
import com.bjpowernode.crm.settings.dictionary.service.DictionaryValueService;
import com.bjpowernode.crm.settings.dictionary.service.impl.DictionaryTypeServiceImpl;
import com.bjpowernode.crm.settings.dictionary.service.impl.DictionaryValueServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;

/**
 * 称呼
 * Application Lifecycle Listener implementation class
 * QueryAppellationContextListener
 * @author LauShuaichen
 */
@WebListener
public class QueryAppellationContextListener implements ServletContextListener {

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		System.err.println("com.bjpowernode.crm.commons.web.listener.QueryClueGradeContextListener");
		// 调用 service 查询 **称呼** --!
		DictionaryValueService dictionaryValueService = (DictionaryValueService) ServiceFactory.getService(new DictionaryValueServiceImpl());
		List<DictionaryValue> appellationList = dictionaryValueService.queryDicValueBygrade("appellation");
		// 把aictivityTypeList 保存到context中
		ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("appellationList", appellationList);
		
	}

}