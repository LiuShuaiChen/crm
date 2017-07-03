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
 * Application Lifecycle Listener implementation class QueryMarketActivityStateContextListener
 *
 */
@WebListener
public class QueryMarketActivityStateContextListener implements ServletContextListener {

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
        System.out.println("com.bjpowernode.crm.commons.web.listener.QueryMarketActivityStateContextListener");
        //调用service  查询市场活动状态
        DictionaryValueService dictionaryValueService = (DictionaryValueService) ServiceFactory.getService(new DictionaryValueServiceImpl());
        List<DictionaryValue> acitivityStatusList =  dictionaryValueService.queryDicValueByType("marketActivityStatus");
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("acitivityStatusList", acitivityStatusList);
    }
	
}
