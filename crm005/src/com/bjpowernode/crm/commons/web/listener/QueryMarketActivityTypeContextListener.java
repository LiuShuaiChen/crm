package com.bjpowernode.crm.commons.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bjpowernode.crm.settings.dictionary.domain.DictionaryValue;
import com.bjpowernode.crm.settings.dictionary.service.DictionaryValueService;
import com.bjpowernode.crm.settings.dictionary.service.impl.DictionaryTypeServiceImpl;
import com.bjpowernode.crm.settings.dictionary.service.impl.DictionaryValueServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;

/**
 * 缓存 市场活动类型
 * @author LauShuaichen
 *
 */
public class QueryMarketActivityTypeContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("com.bjpowernode.crm.commons.web.listener.QueryMarketActivityTypeContextListener");

		//调用service 查询市场活动类型
		DictionaryValueService dictionaryValueService = (DictionaryValueService) ServiceFactory.getService(new DictionaryValueServiceImpl());
		List<DictionaryValue> activityTypeList = dictionaryValueService.queryDicValueByType("marketActivityType");
		//把activityType缓存到context
		ServletContext context = sce.getServletContext();
		context.setAttribute("activityTypeList", activityTypeList);
	}

	
	
}
