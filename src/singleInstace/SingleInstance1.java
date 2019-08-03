package singleInstace;

/**
 * 单例模式 线程不安全
 * 懒汉
 */
public class SingleInstance1 {
    private static SingleInstance1 instance1;
    private SingleInstance1(){}
    public static SingleInstance1 getInstance1(){
        if (instance1==null)
            return new SingleInstance1();
        return instance1;
    }
}
