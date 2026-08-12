package com.war.common;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Spring服务
 */

public class SpringService {
	
	private static ServletContext servletContext;
	private static WebApplicationContext webApplictionContext;
	
	private static ApplicationContext ctx;
	
	public static void setCtx(ApplicationContext ctx) {
		SpringService.ctx = ctx;
	}
	

	public static Object getBean(String name){
		return webApplictionContext.getBean(name);
		//return ctx.getBean(name);
	}
	
	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static void setServletContext(ServletContext servletContext) {
		SpringService.servletContext = servletContext;
		SpringService.webApplictionContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}

	public static ApplicationContext getApplicationContext() throws BeansException, IOException {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext.xml");
		//ApplicationContext ctx = new FileSystemXmlApplicationContext("../webapps/War/WEB-INF/applicationContext.xml");
		return ctx;
	}
	
}
