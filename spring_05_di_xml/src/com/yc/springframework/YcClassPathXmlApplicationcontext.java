package com.yc.springframework;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.*;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class YcClassPathXmlApplicationcontext implements YcApplicationContext {
	// TODO: 将这个map修改的concurrent HashMap 支持并发
	private Map<String, Object> beans = new HashMap<String, Object>();

	public YcClassPathXmlApplicationcontext(String xmlpath)
			throws JDOMException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
		// 1.读取xmlpath文件
		SAXBuilder sb = new SAXBuilder();

		/*
		 * this.getClass().getClassLoader(); /*sb.build(arg0);
		 */
		// 2. 解析 xml ->
		InputStream lis = this.getClass().getClassLoader().getResourceAsStream(xmlpath);
		Document doc = sb.build(lis);
		Element rootElement = doc.getRootElement();
		// 3. 得到所有的 bean, 是 list列表
		List<Element> list = rootElement.getChildren();
		// 4. 循 环这个list列表
		for (Element element : list) {
			// 5. 取出 id class
			String id = element.getAttributeValue("id");
			String classpath = element.getAttributeValue("class");
			// 6. 以反射方式根据class值来创建一个 object对象. (调用了这个对象无参构造方法)
			Object obj = Class.forName(classpath).newInstance();
			// 7. beans.put( id, object );
			beans.put(id, obj);
			// 8.查看这个bean下是否可有节点property

			// 如果有 则循环所有的子节点 取出name value /ref
			for (Element proelement : (List<Element>) element.getChildren()    ) {
				String name = proelement.getAttributeValue("name");
				String value = proelement.getAttributeValue("value");
				String ref = proelement.getAttributeValue("ref");

				// 从class中找到这个方法   取出对应的setXxx（）方法   name的首字母大写
				String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
				Method m = findMethod(methodName, classpath);

				if(m==null){
					continue ;
				}
				//判断m的参数类型
				String typename=m.getParameters()[0].getType().getName();
				if(value != null ){
					if("int".equals(typename) || "java.lang.Integer".equals(typename)){
						int v = Integer.parseInt(value);
						m.invoke(obj, v);
					}else if("float".equals(typename) || "java.lang.Float".equals(typename)){
						float v = Float.parseFloat(value);
						m.invoke(obj, v);
					}else if("double".equals(typename) || "java.lang.Double".equals(typename)){
						double v = Double.parseDouble(value);
						m.invoke(obj, v);
					}else{
						m.invoke(obj, value);
					}
				}else if(ref != null){
					//取出ref的值stuDao 这个就是bean 在beans集合中的键
					Object  toInjectObject = beans.get(ref);
					//查找class对象中的setXxx（）  则激活
					//TODO:要判断一个m中的参数类型是否与toInjectObject的类型一样
					m.invoke(obj, toInjectObject);
				}
			}
			// 10 判断是否是value ？
		}
	}

	// 实例化当前类 再得到其所有的方法 循环 如果和classpath 相等则返回
	private Method findMethod(String methodName, String classpath) throws ClassNotFoundException {
		Class c = Class.forName(classpath);
		Method[] ms = c.getMethods();
		for (Method m : ms) {
			if (m.getName().equals(methodName)) {
				return m;
			}
		}
		return null;
	}

	@Override
	public Object getBean(String id) {

		return beans.get(id);
	}

}
