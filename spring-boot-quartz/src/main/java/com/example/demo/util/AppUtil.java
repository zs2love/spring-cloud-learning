/**
 * 
 */
package com.example.demo.util;

/**
 * @author shuai.b.zhang
 *
 */
/**
 * 
 */

import java.util.Collection;

import com.google.gson.Gson;

/**
 * The Class AppUtil.
 *
 * @author pavan.solapure
 */
public class AppUtil {
	
	
	
	
	/**
	 * Gets the bean to json string.
	 *
	 * @param beanClass the bean class
	 * @return the bean to json string
	 */
	public static String getBeanToJsonString(Object beanClass) {
		return new Gson().toJson(beanClass);
	}
	
	/**
	 * Gets the bean to json string.
	 *
	 * @param beanClasses the bean classes
	 * @return the bean to json string
	 */
	public static String getBeanToJsonString(Object... beanClasses) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Object beanClass : beanClasses) {
			stringBuilder.append(getBeanToJsonString(beanClass));
			stringBuilder.append(", ");
		}
		return stringBuilder.toString();
	}
	
	
	
	
	
	
	
	/**
	 * Checks if is object empty.
	 *
	 * @param object
	 *            the object
	 * @return true, if is object empty
	 */
	public static boolean isObjectEmpty(Object object) {
		if (object == null)
			return true;
		else if (object instanceof String) {
			if (((String) object).trim().length() == 0) {
				return true;
			}
		} else if (object instanceof Collection) {
			return isCollectionEmpty((Collection<?>) object);
		}
		return false;
	}

	/**
	 * Checks if is collection empty.
	 *
	 * @param collection
	 *            the collection
	 * @return true, if is collection empty
	 */
	private static boolean isCollectionEmpty(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}
}
