package com.yc.spring;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class YcAnnotationConfigApplicationContext implements YcApplicationContext {

	Map<String, Object> beans = new HashMap<String, Object>();

	public YcAnnotationConfigApplicationContext(Class clz) throws IllegalArgumentException, InvocationTargetException {

		try {
			Object obj = clz.newInstance();// 创建APPConfig对象
			// 取出它的所有方法

			Method[] ms = clz.getDeclaredMethods();
			// 判断哪些是含有@Bean注解的方法
			for (Method method : ms) {
				Annotation[] anos = method.getAnnotations();
				for (Annotation annotation : anos) {
					if (annotation instanceof Bean) {
						Object o = method.invoke(obj, null);
						// 取出方法名 作为 @Bean的键
						String id = method.getName();
						// 将这个值作为beans的值 存入 IOC容器
						beans.put(id, o);
					}
				}
			}
			// 判断哪些是含有@Bean注解的方法
			for (Method method : ms) {
				Annotation[] anos = method.getAnnotations();
				for (Annotation annotation : anos) {
					if (annotation instanceof Bean) {
						//Object o = method.invoke(obj, null);
						// 取出方法名 作为 @Bean的键
						String id = method.getName();
						// 将这个值作为beans的值 存入 IOC容器
					  	Object o = 	beans.get(id);
						
						//完成di
						//1。从o的反射实例中取出所有的方法
						Method[]  mms = o.getClass().getMethods();
						//2  循环所有的方法
						for (Method method2 : mms) {
							Annotation[] aa = method2.getAnnotations();
							for (Annotation annotation2 : aa) {
								if(annotation2 instanceof Resource  && method2.getName().startsWith("set")){
									//4  如果加了这个Resource注解  则取出其相应的name值
									String  beanid =( (Resource)annotation2).name() ;
									//5.根据name值 取出beans中的对象
									Object  beanObject = beans.get(beanid);
									//6  激活当前方法  将对象注入  di完成
									method2.invoke(o, beanObject);
								}
							}
						}
						
					}
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}


	}

	@Override
	public Object getBean(String id ) {

		return beans.get(id);
	}

}
