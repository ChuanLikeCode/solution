package singleInstace;

/**
 * 单例模式 线程安全
 * 静态内部类
 *
 */
public class SingleInstance5 {
    private static class SingleHolder{
        private static final SingleInstance5 instance5 = new SingleInstance5();
    }
    private SingleInstance5(){}
    public static SingleInstance5 getInstance(){
        return SingleHolder.instance5;
    }
}
