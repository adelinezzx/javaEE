package com.yc.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.yc.bean.Product;

public class RequestUtils {

	/**
	 *   工具类 ：将request中的参数取出，将参数值对应存到clz生成的对象中
	 *   注意：只能取出request中的参数是一个的情况，如果死多个值，请用request.getParameterValues("参数名")；
	 * @param request
	 * @param clz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> T parseRequest(HttpServletRequest request, Class<T> clz)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		Map<String, String[]> maps = request.getParameterMap();//得到request的属性集
		Map<String, String> m = new HashMap<String, String>();
		for (Entry<String, String[]> entry : maps.entrySet()) {//将遍历得到的每一个属性放到Map集合中
			m.put(entry.getKey(), entry.getValue()[0]);
		}
		return parseRequest(m, clz);
	}

	/**
	 * 将Map<String, String>的值取出 ，存到泛型类Class<T> 中的生成的set方法中---
	 * 1.创建clz的对象 反射实例   2..用allSetMethods取出所有set方法   3.循环m 取出每个参数、4.遍历出取到的set方法中所有的方法
	 * 5.   判断当前所有set方法中的参数类型是哪一种，再进行类型转换
	 *   
	 * @param m    Map对象
	 * @param clz   类
	 * @return   泛型
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> T parseRequest(Map<String, String> m, Class<T> clz)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// 1.创建clz的对象 反射实例
		T obj = clz.newInstance(); // Product p = new Product() ;
		// 2.用allSetMethods取出所有set方法
		List<Method> allSetMethods = getAllSetMethod(clz);
		// 3.循环m 取出每个参数、
		for (Map.Entry<String, String> entry : m.entrySet()) {
			String parameterName = entry.getKey();// 参数名
			String parameterValue = entry.getValue();// 值

			// 4.取出allSetMethods中所有的方法
			for (Method method : allSetMethods) { // 遍历
				String methodName = method.getName(); // 得到每一个方法的方法名
				// 5.判断当前method中的参数类型是哪一种，载进行类型转换
				if (methodName.equalsIgnoreCase("set" + parameterName)) { // 判断  方法名=  set+参数  ？  如果等于则继续
					String parameterTypeName = method.getParameterTypes()[0].getName();// 得到方法的类型
					if ("double".equals(parameterTypeName) || "java.lang.Double".equals(parameterTypeName)) {
						double v = Double.parseDouble(parameterValue);
						method.invoke(obj, v);
					} else if ("int".equals(parameterTypeName) || "java.lang.Integer".equals(parameterTypeName)) {
						int v = Integer.parseInt(parameterValue);
						method.invoke(obj, v);
					} else if ("float".equals(parameterTypeName) 	|| "java.lang.Float".equals(parameterTypeName)) {
						float v = Float.parseFloat(parameterValue);
						method.invoke(obj, v);
					} else {
						// 激活方法
						method.invoke(obj, parameterValue);
					}
				}
			}
		}
		return obj;
	}

	/**
	 * 得到一个类中的所有的以set开头的方法存到List中
	 * 
	 * @param clz
	 * @return
	 */
	private static List<Method> getAllSetMethod(Class clz) {
		List<Method> allSetMethod = new ArrayList<Method>();
		Method[] ms = clz.getMethods();// 得到所有的方法
		for (Method m : ms) {
			if (m.getName().startsWith("set")) {// 如果方法时以set开头的。
				allSetMethod.add(m);
			}
		}
		return allSetMethod;
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
     Map<String,String> map = new HashMap<String,String>();
     map.put("pid", 100+ "");
     map.put("pname", "pear");
     map.put("price", 100+"");
     System.out.println(parseRequest(map,  Product.class));
	}
}
