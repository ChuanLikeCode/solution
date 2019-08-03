package singleInstace;

/**
 * 单例模式 线程安全
 * 枚举
 *
 */
public enum  SingleInstance6 {
    INSTANCE6;
    SingleInstance6(){
        System.out.println("调用构造方法--enum  SingleInstance6");
    }

    public void print(){
        System.out.println("enum  SingleInstance6 print");
    }
}
