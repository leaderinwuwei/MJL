package spi;

import own.spi.Spi;

import java.util.ServiceLoader;

/**
 * @author CaptainWang
 * @since 2024/5/30
 */
public class SpiTest {

    public static void main(String[] args) {
        String classpath = System.getProperty("java.class.path");
        System.out.println("当前类路径: " + classpath);

        ServiceLoader<Spi> serviceLoader = ServiceLoader.load(Spi.class);
        for (Spi spi : serviceLoader) {
            // 使用SPI加载的实现类
            spi.sayHello();
        }
    }
}
