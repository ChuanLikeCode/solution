/**
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
 * @date 2019/7/13 17:22
 * email:zc19930508@163.com
 * QQ:461346605
 * Tel:13218329781
 * If you have any question,please call me.
 **/
package thread;

/**
 *@author zhouchuan
 *@date 2019/7/13 17:22
 *email:zc19930508@163.com
 *QQ:461346605 
 *Tel:13218329781
 *If you have any question,please call me.
 */
public class MyRunable implements Runnable {
    private int num;

    public MyRunable(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "正在执行---MyRunable{" +
                "num=" + num +
                '}';
    }

    @Override
    public void run() {
        System.out.println(toString());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyRunnable " + num + "执行完毕");
    }
}
