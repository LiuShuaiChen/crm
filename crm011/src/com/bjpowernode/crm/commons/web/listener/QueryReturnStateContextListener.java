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
 * Application Lifecycle Listener implementation class QueryReturnStateContextListener
 *
 */
@WebListener
public class QueryReturnStateContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public QueryReturnStateContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         System.err.println("---***com.bjpowernode.crm.commons.web.listener.QueryReturnStateContextListener");
    	 DictionaryValueService dictionaryValueService = (DictionaryValueService) ServiceFactory
  				.getService(new DictionaryValueServiceImpl());
  		List<DictionaryValue> returnStateList = dictionaryValueService.queryDicValueBygrade("returnState");
  		ServletContext servletContext = sce.getServletContext();
  		servletContext.setAttribute("returnStateList", returnStateList);
    }
	
}
