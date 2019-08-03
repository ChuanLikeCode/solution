package thread; /**
 * ////////////////////////////////////////////////////////////////////
 * //                          _ooOoo_                               //
 * //                         o8888888o                              //
 * //                         88" . "88                              //
 * //                         (| ^_^ |)                              //
 * //                         O\  =  /O                              //
 * //                      ____/`---'\____                           //
 * //                    .'  \\|     |//  `.                         //
 * //                   /  \\|||  :  |||//  \                        //
 * //                  /  _||||| -:- |||||-  \                       //
 * //                  |   | \\\  -  /// |   |                       //
 * //                  | \_|  ''\---/''  |   |                       //
 * //                  \  .-\__  `-`  ___/-. /                       //
 * //                ___`. .'  /--.--\  `. . ___                     //
 * //              ."" '<  `.___\_<|>_/___.'  >'"".                  //
 * //            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
 * //            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
 * //      ========`-.____`-.___\_____/___.-`____.-'========         //
 * //                           `=---='                              //
 * //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
 * //            佛祖保佑       永不宕机     永无BUG                    //
 * ////////////////////////////////////////////////////////////////////
 *
 * @author zhouchuan
 * @date 2019/7/13 8:04
 * email:zc19930508@163.com
 * QQ:461346605
 * Tel:13218329781
 * If you have any question,please call me.
 **/

import java.util.concurrent.*;

/**
 *@author zhouchuan
 *@date 2019/7/13 8:04
 *email:zc19930508@163.com
 *QQ:461346605 
 *Tel:13218329781
 *If you have any question,please call me.
 */
public class ThreadDemo {
    public static void main(String[] args){
        BlockingQueue<Runnable> blockingQueue =  new LinkedBlockingQueue<>();
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<Runnable>(10);
        RejectHandlerException rejectHandlerException = new RejectHandlerException();
        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();//抛出异常
        ThreadPoolExecutor.DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();//丢弃任务 不做任何事
        //丢弃队列最前面的任务
        ThreadPoolExecutor.DiscardOldestPolicy discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
        //由发起的线程执行任务
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 8, 5, TimeUnit.SECONDS, arrayBlockingQueue,callerRunsPolicy);


        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);


        for (int i = 1;i<=40;i++){
            MyRunable runable = new MyRunable(i);
            threadPoolExecutor.execute(runable);
            System.out.println("线程池中现在的线程数目是："+threadPoolExecutor.getPoolSize()+",  队列中正在等待执行的任务数量为："+
                    threadPoolExecutor.getQueue().size());
        }
        threadPoolExecutor.shutdown();
    }
}
