package com.yc.springframework;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.jdom.*;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class YcClassPathXmlApplicationcontext   implements  YcApplicationContext{
	//TODO: 将这个map修改的concurrent  HashMap  支持并发
	private  Map<String, Object>  beans = new HashMap <String,Object>();

	public YcClassPathXmlApplicationcontext(  String  xmlpath) throws JDOMException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		 //1.读取xmlpath文件
	    SAXBuilder  sb = new SAXBuilder();
	    
	    /* this.getClass().getClassLoader();
	    /*sb.build(arg0);*/
	    //2. 解析  xml  ->
	    InputStream  lis = this.getClass().getClassLoader().getResourceAsStream(xmlpath);
	    Document  doc = sb.build(lis);
	    Element  rootElement  = doc.getRootElement();
	    // 3. 得到所有的  bean,  是  list列表
	    List<Element>  list =  rootElement.getChildren();
	    //4.   循 环这个list列表
	    for (Element element : list) {
	    	//         5. 取出  id    class
               String id = element.getAttributeValue("id");
               String classpath = element.getAttributeValue("class");
               //          6.  以反射方式根据class值来创建一个 object对象. (调用了这个对象无参构造方法)
             Object  obj = Class.forName(  classpath).newInstance();
             //          7.   beans.put( id,  object );
              beans.put(id, obj)       ;
              
		}
	}

	@Override
	public Object getBean(String id) {
		 
		return null;
	}
	

}
