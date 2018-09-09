package JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

//生成代理的类   jdk动态代理一定要面向接口
public class LogProxy implements InvocationHandler {

	// 真实主题的引用 target一定要实现某个接口
	private Object target;

	/**
	 * proxy : 刚生成的代理对象
	 * method：  调用的方法
	 * arg2：  调用的方法中传入的参数
	 */
	@Override//当在程序中调用代理对象的被代理的方法时  由  jvm自动回调 invoke（）
	public Object invoke(Object proxy, Method method, Object[] arg2) throws Throwable {
        Object  returnValue = method.invoke(this.target, arg2) ;  //目标对象 target  相当于  PersonBizImpl.add(1)
        if(method.getName().startsWith("add") || method.getName().startsWith("update")  || method.getName().startsWith("del")){
        	System.out.println("调用的信息");
        	System.out.println(proxy.getClass().getName());
        	System.out.println(method.getName());
        	 if(arg2 != null ){
        		 for (Object object : arg2) {
        			 System.out.println(object);
					
				}
        	 }
        	 log();//后置增强
        }
        //以上代码相当于静态代理中的  pb.add(1)
		return returnValue;
	}

	/**
	 * 写到这里 相当于只创建了一个 HelloProxyPersonBiz.java的类 它实现了一个接口
	 * 
	 * @param target
	 * @return
	 */
	public Object createProxy(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(),
				this);
	}

	// 增强方法
	private void log() {
		System.out.println("------------");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd  HH:mm:ss");
		System.out.println("操作时间：" + sdf.format(d));
	}
}
