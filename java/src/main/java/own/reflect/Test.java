package own.reflect;// v1 版本
import java.lang.reflect.Method;

public class Test {
	public static void target(int i) {
		// 空方法
	}

	public static void main(String[] args) throws Exception {
		Class<?> klass = Class.forName("Test");
		Method method = klass.getMethod("target", int.class);
		method.setAccessible(true);  // 关闭权限检查
		polluteProfile();

		long current = System.currentTimeMillis();
		for (int i = 1; i <= 2_000_000_000; i++) {
			if (i % 100_000_000 == 0) {
				long temp = System.currentTimeMillis();
				System.out.println(temp - current);
				current = temp;
			}

			method.invoke(null, 128);
		}
	}

	public static void polluteProfile() throws Exception {
		Method method1 = Test.class.getMethod("target", int.class);
		Method method2 = Test.class.getMethod("target", int.class);
		System.out.println(method2 == method1);
		for (int i = 0; i < 2000; i++) {
			method1.invoke(null, 0);
			method2.invoke(null, 0);
		}
		System.out.println("----");
	}
	public static void target1(int i) { }
	public static void target2(int i) { }
}
