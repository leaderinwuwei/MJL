package prxoy.cglib;

/**
 * @author CaptainWang
 * @since 2024/6/20
 */
public class MyTarget {
    public void doSomething() {
        System.out.println("Doing something in target...");
    }

    public void interactDoSomething() {
        this.doSomething();
    }
}
