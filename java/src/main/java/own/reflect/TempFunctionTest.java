package own.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TempFunctionTest {
	public static void main(String[] args) {
		try {
			// 1. 使用外部配置的实现，进行动态加载类
			TempFunctionTest test = (TempFunctionTest)Class.forName("TempFunctionTest").newInstance();
			test.sayHello("call directly");
			// 2. 根据配置的函数名，进行方法调用（不需要通用的接口抽象）
			Object t2 = new TempFunctionTest();
			Method method = t2.getClass().getDeclaredMethod("sayHello", String.class);
			method.invoke(test, "method invoke");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e ) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void sayHello(String word) {
		System.out.println("hello," + word);
	}

}
