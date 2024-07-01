package prxoy.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.concurrent.locks.LockSupport;

/**
 * @author CaptainWang
 * @since 2024/6/20
 */
public class CGLibProxyDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyTarget.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, v, methodProxy) -> {
            System.out.println("Before method: " + method.getName());
            Object result = methodProxy.invokeSuper(obj, v);
            System.out.println("After method: " + method.getName());
            return result;
        });

        MyTarget proxy = (MyTarget) enhancer.create();
        proxy.doSomething();

        while (true) {
            LockSupport.parkNanos(30000000000000L);
            System.out.println(" 我醒了 别管我");
        }
    }
}
//prxoy.cglib.MyTarget$$EnhancerByCGLIB$$1a45594c
//prxoy.cglib.MyTarget$$EnhancerByCGLIB$$1a45594c$$FastClassByCGLIB$$6b60ecb2
//prxoy.cglib.MyTarget$$FastClassByCGLIB$$6b2a52b8