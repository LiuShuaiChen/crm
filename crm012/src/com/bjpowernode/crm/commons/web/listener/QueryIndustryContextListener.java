package com.bjpowernode.crm.commons.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.bjpowernode.crm.settings.dictionary.domain.DictionaryValue;
import com.bjpowernode.crm.settings.dictionary.service.DictionaryValueService;
import com.bjpowernode.crm.settings.dictionary.service.impl.DictionaryValueServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;

/**
 * Application Lifecycle Listener implementation class
 * QueryIndustryContextListener
 *
 */
@WebListener
public class QueryIndustryContextListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public QueryIndustryContextListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		System.err.println("com.bjpowernode.crm.commons.web.listener.QueryIndustryContextListener");
		DictionaryValueService dictionaryValueService = (DictionaryValueService) ServiceFactory
				.getService(new DictionaryValueServiceImpl());
		List<DictionaryValue> industryList = dictionaryValueService.queryDicValueBygrade("industry");
		// 把aictivityTypeList 保存到context中
		ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("industryList", industryList);
	}

}
