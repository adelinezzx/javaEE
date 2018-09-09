package com.yc.springframework.context.annotation;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ClassPathXMLApplicationContext implements ApplicationContext {

	Map<String, Object> container = new HashMap<String, Object>();

	public ClassPathXMLApplicationContext(String configName) throws JDOMException, IOException {
		psrseConfig(configName);
	}

	private void psrseConfig(String configName) throws JDOMException, IOException {
		try {
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(this.getClass().getClassLoader().getResourceAsStream(configName));

			Element rootElement = doc.getRootElement(); // 得到根元素
			List list = rootElement.getChildren();// 得到子元素节点的集合
			// 遍历子节点
			for (int i = 0; i < list.size(); i++) {
				Element element = (Element) list.get(i);
				String elementName = element.getName();
				if ("component-scan".equals(elementName)) {
					String packageName = element.getAttributeValue("base-package");// 得到属性名的属性值
																					// //
																					// ==
																					// //
																					// 包名
					findPackageSubPackageInClasses(packageName);// 根据包名
																// 来遍历包中所有的类
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	Set<Class<?>> classes = new LinkedHashSet<Class<?>>();

	/**
	 * 找到类路径下的包以及子包中的所有字节码文件
	 * 
	 * @param packageName
	 *            包名
	 * @throws IOException
	 */
	private void findPackageSubPackageInClasses(String packageName) throws IOException {
		// 将得到的包名com.yc 转化为路径名com/yc \\. 转义
		String packagePath = packageName.replaceAll("\\.", "/");
		try {
			// getContextClassLoader:返回该线程的上下文classLoader
			// getResources 查找具有指定名称的资源
			//得到当前的packagePath的绝对路径的URI表示法
			Enumeration<URL> files = Thread.currentThread().getContextClassLoader().getResources(packagePath);
			System.out.println(files);
			while (files.hasMoreElements()) {
				URL url = files.nextElement();
				System.out.println("配置要扫描的路径为：" + url.getFile());
				// 找到包中的字节码文件
				findPackageClasses(url.getFile(), packageName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("包资源:" + packagePath + "取到失败");
		}
	}

	private void findPackageClasses(String packagePath, String packageName) {
		
		// 从packagePath中这个包中查找所有的.class类及子包
		if(packagePath.startsWith("/")){
			packagePath = packagePath.substring(1);
		}
		File[] files = new File(packagePath).listFiles(new java.io.FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.getName().endsWith(".class") || file.isDirectory();
			}
			
		});
		if (files != null && files.length > 0 ) {
			// 循环找到的文件
			for (File f : files) {
				// 如果是目录则继续循环 否则 存到classes集合中 这些字节码文件就是将来IOC 的
				if (   f.isDirectory()      ) {
					findPackageClasses(  f.getAbsolutePath(), packageName + "." + f.getName());
				} else {
					URLClassLoader uc = new URLClassLoader(new URL[] {}); // 指定文件的路径
					try {
						classes.add(uc.loadClass(packageName + "." + f.getName().replace(".class", "")));// 根据前类名
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} finally {
						try {
							uc.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			} 
		}
		createComponentBean(classes);
	}

	/**
	 * 找到Component托管的bean的类对象
	 * @param classes
	 */
	
	
	private void createComponentBean(Set<Class<?>> classes) {
            for (Class<?> cls : classes) {
            	if(cls.isAnnotationPresent(Component.class)){
            		String beanName =cls.getAnnotation(Component.class).value();
            		try {
						container.put(beanName, cls.newInstance());
					} catch (InstantiationException | IllegalAccessException e) {
						throw new RuntimeException( "创建类对象失败" + cls.getName() + "  " + e);
					}
            	}
				
			}
            
            createAutowored(classes);
	}

	
	private void createAutowored(Set<Class<?>> classes) {
		 for (Class<?> cls : classes) {
			Field[]  fileds = cls.getDeclaredFields();//获取到该类中的各种字段  包括  private  public  protected 
			if(cls.getAnnotation(Component.class) == null ){
				continue ;
			}
			
			String  beanName = cls.getAnnotation(Component.class).value();
			for (Field field : fileds) {
				//autowires 自动装配   根据名字装配   真正的autowired是根据 类型来装配的
				if(field.isAnnotationPresent(Autowired.class)){
					String fieldName = field.getName();
					Object  fieldBean = container.get(field.getName());
					field.setAccessible(true);
					try {
						field.set(container.get(beanName),fieldBean);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
						System.out.println(cls.getName() + "的属性" + field.getName() + "注入失败");
					}
				}
			}
		}
	}

	@Override
	public Object getBean(String BeanName) {

		return container.get(BeanName);
	}

}
