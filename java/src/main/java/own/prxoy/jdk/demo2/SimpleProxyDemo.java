package own.prxoy.jdk.demo2;


import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface IProxyClass {
	public int doSomething(int i);
}

 class ProxyClassImpl implements IProxyClass {
	 @Override
	 public int doSomething(int num) {
		 System.out.println("方法执行中.....");
		 return num;
	 }
 }


 class DynamicProxyHandler implements InvocationHandler {

	 private Object proxied;

	 /**
	  * @param proxied 被代理对象
	  */
	 public DynamicProxyHandler(Object proxied) {
		 this.proxied = proxied;
	 }

	 /**
	  * 返回代理对象
	  * @return
	  */
	 public Object newProxyInstance() {
		 return Proxy.newProxyInstance(proxied.getClass().getClassLoader(), proxied.getClass().getInterfaces(), this);
	 }

	 /**
	  *
	  * @param proxy 代理对象
	  * @param method 代理方法
	  * @param args 方法参数
	  * @return
	  * @throws Throwable
	  */
	 @Override
	 public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		 //将代理对象生成字节码到F盘上，方便反编译出java文件查看，实际动态代理是不需要自己生成的
		 addClassToDisk(proxy.getClass().getName(), ProxyClassImpl.class,"/Users/captainwang/Desktop/workSpace/javaspace/practice/MJL/Proxy1.class");
	     System.out.println("method:"+method.getName());
		 System.out.println("args:"+args[0].getClass().getName());
		 System.out.println("Before invoke method...");
		 Object object=method.invoke(proxied, args);
		 System.out.println("After invoke method...");
		 return object;
	 }

	 /**
	  * 用于生产代理对象的字节码，并将其保存到硬盘上
	  * @param className
	  * @param cl
	  * @param path
	  */
	 public static void addClassToDisk(String className, Class<?> cl, String path) {
		 //用于生产代理对象的字节码
		 byte[] classFile = ProxyGenerator.generateProxyClass(className, cl.getInterfaces());
		 FileOutputStream out = null;
		 try {
			 out = new FileOutputStream(path);
			 //将代理对象的class字节码写到硬盘上
			 out.write(classFile);
			 out.flush();
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			 try {
				 out.close();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
		 }
	 }

	 /**
	  * 用于生产代理对象的字节码，并将其保存到硬盘上
	  * @param className
	  * @param cl
	  */
	 public static void addClassToDiskDefaultPath(String className, Class<?> cl) {
		 addClassToDisk(className,cl,"/Users/captainwang/Desktop/workSpace/javaspace/practice/MJL/Proxy0.class");
	 }

 }


public class SimpleProxyDemo {
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		ProxyClassImpl c = new ProxyClassImpl();
		DynamicProxyHandler proxyHandler = new DynamicProxyHandler(c);
		IProxyClass proxyClass = (IProxyClass)proxyHandler.newProxyInstance();
		System.out.println(proxyClass.getClass().getName());
		System.out.println(proxyClass.doSomething(5));
	}
}