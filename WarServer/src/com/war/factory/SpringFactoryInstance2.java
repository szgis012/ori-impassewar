package com.war.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import flex.messaging.FactoryInstance;
import flex.messaging.FlexFactory;
import flex.messaging.config.ConfigMap;
import flex.messaging.services.ServiceException;

public class SpringFactoryInstance2 extends FactoryInstance {
	
	SpringFactoryInstance2(FlexFactory factory, String id, ConfigMap properties)
	{
		super(factory, id, properties);
	}

	public Object lookup() {
		// 这就是从spring容器中getbean了
		ApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext(flex.messaging.FlexContext
						.getServletConfig().getServletContext());
		String beanName = getSource();

		try {
			return appContext.getBean(beanName);
		} catch (NoSuchBeanDefinitionException nexc) {
			ServiceException e = new ServiceException();
			throw e;
		} catch (BeansException bexc) {
			ServiceException e = new ServiceException();
			throw e;
		}
	}

}
