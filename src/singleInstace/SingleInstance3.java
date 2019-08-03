package singleInstace;

/**
 * 单例模式 线程安全
 * 饿汉
 * 在类加载时 初始化的时候就已经实例化了 没有达到lazy_loading的效果
 */
public class SingleInstance3 {
    private static SingleInstance3 instance3= new SingleInstance3();
    private SingleInstance3(){}
    public static SingleInstance3 getInstance3(){
        return instance3;
    }
}
