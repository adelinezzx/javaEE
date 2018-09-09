package JDKProxy;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class HelloLogCgligProxy implements MethodInterceptor {

	// 真实主题的引用 target一定要实现某个接口
	private Object target;

	@Override
	public Object intercept(Object proxy, Method method, Object[] arg2, MethodProxy arg3) throws Throwable {
		Object returnValue = method.invoke(this.target, arg2);
		if (method.getName().startsWith("add") || method.getName().startsWith("update")
				|| method.getName().startsWith("del")) {
			hello();
		}
		return returnValue;
	}

	public Object createProxy(Object target) {
		this.target = target;
		
		Enhancer  enhancer = new Enhancer() ;
		enhancer .setSuperclass(this.target.getClass());
		enhancer.setClassLoader(this.target.getClass().getClassLoader());
		enhancer.setCallback(this);
		
		return  enhancer.create();
	}
	
	// 增强方法
	private void hello() {
		System.out.println("hello");
	}
}
