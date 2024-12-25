package own.prxoy.jdk.demo1;

/**
 * @author CaptainWang
 * @since 2024/6/20
 */
import java.lang.reflect.*;
import java.util.concurrent.locks.LockSupport;

public class JDKProxyDemo {
    public static void main(String[] args) {
        MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
                MyInterface.class.getClassLoader(),
                new Class<?>[]{MyInterface.class},
                (proxy1, method, args1) -> {
                    System.out.println("Before method: " + method.getName());
                    Method m = MyImplementation.class.getDeclaredMethod(method.getName(), method.getParameterTypes());
                    Object result = m.invoke(new MyImplementation(), args1);
                    System.out.println("After method: " + method.getName());
                    return result;
                }
        );

        proxy.doSomething();

        while (true) {
            LockSupport.parkNanos(30000000000000L);
            System.out.println(" 我醒了 别管我");
        }
    }
}