package singleInstace;

/**
 * 单例模式 线程安全 懒汉
 * 效率低 99%情况下不需要同步
 */
public class SingleInstance2 {
    private static SingleInstance2 instance2;
    private SingleInstance2(){}
    public static synchronized SingleInstance2 getInstance2(){
        if (instance2==null)
            instance2 =  new SingleInstance2();
        return instance2;
    }
}
