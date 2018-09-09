package com.yc.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;



public class RequestUtils {

	/**
	 *   ������ ����request�еĲ���ȡ����������ֵ��Ӧ�浽clz���ɵĶ�����
	 *   ע�⣺ֻ��ȡ��request�еĲ�����һ�����������������ֵ������request.getParameterValues("������")��
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

		Map<String, String[]> maps = request.getParameterMap();//�õ�request�����Լ�
		Map<String, String> m = new HashMap<String, String>();
		for (Entry<String, String[]> entry : maps.entrySet()) {//�������õ���ÿһ�����Էŵ�Map������
			m.put(entry.getKey(), entry.getValue()[0]);
		}
		return parseRequest(m, clz);
	}

	/**
	 * ��Map<String, String>��ֵȡ�� ���浽������Class<T> �е����ɵ�set������---
	 * 1.����clz�Ķ��� ����ʵ��   2..��allSetMethodsȡ������set����   3.ѭ��m ȡ��ÿ��������
	 * 4.������ȡ����set���������еķ���
	 * 5.   �жϵ�ǰ����set�����еĲ�����������һ�֣��ٽ�������ת��
	 *   
	 * @param m    Map����
	 * @param clz   ��
	 * @return   ����
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> T parseRequest(Map<String, String> m, Class<T> clz)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// 1.����clz�Ķ��� ����ʵ��
		T obj = clz.newInstance(); // Product p = new Product() ;
		// 2.��allSetMethodsȡ������set����
		List<Method> allSetMethods = getAllSetMethod(clz);
		// 3.ѭ��m ȡ��ÿ��������
		for (Map.Entry<String, String> entry : m.entrySet()) {
			String parameterName = entry.getKey();// ������
			String parameterValue = entry.getValue();// ֵ

			// 4.ȡ��allSetMethods�����еķ���
			for (Method method : allSetMethods) { // ����
				String methodName = method.getName(); // �õ�ÿһ�������ķ�����
				// 5.�жϵ�ǰmethod�еĲ�����������һ�֣��ؽ�������ת��
				if (methodName.equalsIgnoreCase("set" + parameterName)) { // �ж�  ������=  set+����  ��  ������������
					String parameterTypeName = method.getParameterTypes()[0].getName();// �õ�����������
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
						// �����
						method.invoke(obj, parameterValue);
					}
				}
			}
		}
		return obj;
	}

	/**
	 * �õ�һ�����е����е���set��ͷ�ķ����浽List��
	 * 
	 * @param clz
	 * @return
	 */
	private static List<Method> getAllSetMethod(Class clz) {
		List<Method> allSetMethod = new ArrayList<Method>();
		Method[] ms = clz.getMethods();// �õ����еķ���
		for (Method m : ms) {
			if (m.getName().startsWith("set")) {// �������ʱ��set��ͷ�ġ�
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
    // System.out.println(parseRequest(map,  Topic.class));
	}
}
