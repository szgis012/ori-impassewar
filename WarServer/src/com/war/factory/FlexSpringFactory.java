package com.war.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import flex.messaging.FactoryInstance;
import flex.messaging.FlexFactory;
import flex.messaging.config.ConfigMap;
import flex.messaging.services.ServiceException;  

public class FlexSpringFactory implements FlexFactory
{
	private static final String SOURCE = "source";

	private static ApplicationContext ac = null;


	public void initialize(String id, ConfigMap configMap) {}

	public FactoryInstance createFactoryInstance(String id, ConfigMap properties)
	{
		SpringFactoryInstance instance = new SpringFactoryInstance(this, id, properties);
		instance.setSource(properties.getPropertyAsString(SOURCE, instance.getId()));
		return instance;
	}

	public Object lookup(FactoryInstance inst)
	{
		SpringFactoryInstance factoryInstance = (SpringFactoryInstance) inst;
		return factoryInstance.lookup();
	}


	static class SpringFactoryInstance extends FactoryInstance
	{
		SpringFactoryInstance(FlexSpringFactory factory, String id, ConfigMap properties)
		{
			super(factory, id, properties);
			
			if (ac==null) {
				ac = WebApplicationContextUtils.getWebApplicationContext(flex.messaging.FlexContext.getServletConfig().getServletContext());
			}
		}


		public String toString()
		{
			return "SpringFactory instance for id=" + getId() + " source=" + getSource() + " scope=" + getScope();
		}

		public Object lookup() 
		{
			try
			{
				return ac.getBean(getSource());
			}
			catch (NoSuchBeanDefinitionException nexc)
			{
				ServiceException e = new ServiceException();
				String msg = "Spring service named '" + getSource() + "' does not exist.";
				e.setMessage(msg);
				e.setRootCause(nexc);
				e.setDetails(msg);
				e.setCode("Server.Processing");
				throw e;
			}
			catch (BeansException bexc)
			{
				ServiceException e = new ServiceException();
				String msg = "Unable to create Spring service named '" + getSource() + "' ";
				e.setMessage(msg);
				e.setRootCause(bexc);
				e.setDetails(msg);
				e.setCode("Server.Processing");
				throw e;
			} 
		}
		
	} 

} 