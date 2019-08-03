package singleInstace;

/**
 * 单例模式 线程安全
 * 饿汉
 *
 */
public class SingleInstance4 {
    private static SingleInstance4 instance4= null;

    static {
        instance4 = new SingleInstance4();
    }
    private SingleInstance4(){}
    public static SingleInstance4 getInstance3(){
        return instance4;
    }
}
