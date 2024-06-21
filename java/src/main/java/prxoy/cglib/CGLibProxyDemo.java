package prxoy.cglib;

/**
 * @author CaptainWang
 * @since 2024/6/20
 */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

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
    }
}