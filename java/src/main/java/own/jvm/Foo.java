package own.jvm;

/**
 * @author CaptainWang
 * @since 2024/7/9
 */
public class Foo {
    public static void main(String[] args) {
        boolean 吃过饭没 = false; // 直接编译的话 javac 会报错
        if (吃过饭没) System.out.println(" 吃了 ");
        if (true == 吃过饭没) System.out.println(" 真吃了 ");
    }
}
