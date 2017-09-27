/**
 * 
 */
package com.example.demo.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;


/**
 * @author shuai.b.zhang
 *
 */
public class PropertiesUtils extends PropertySourcesPlaceholderConfigurer implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The properties map. */
	private static Map<String, String> propertiesMap = new HashMap<String, String>();


	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
			ConfigurablePropertyResolver propertyResolver) throws BeansException {
		super.processProperties(beanFactoryToProcess, propertyResolver);
		try {
			Properties props = this.mergeProperties();
			propertiesMap = new HashMap<String, String>();
			for (Object key : props.keySet()) {
				String keyStr = key.toString();
				propertiesMap.put(keyStr, props.getProperty(keyStr));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the property.
	 *
	 * @param name
	 *            the name
	 * @return the property
	 */
	public static String getProperty(String name) {
		return propertiesMap.get(name);
	}

}
