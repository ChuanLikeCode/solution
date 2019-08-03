package singleInstace;

/**
 * 单例模式 线程安全
 * 枚举
 *
 */
public class SingleInstance7 {
    private volatile static SingleInstance7 instance7;
    private SingleInstance7(){}
    public static SingleInstance7 getInstance7(){
        if (instance7==null){
            synchronized(SingleInstance7.class){
                if (instance7==null){
                    instance7 = new SingleInstance7();
                }
            }
        }
        return instance7;
    }
}
