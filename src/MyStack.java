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
 * @date 2019/6/3 13:58
 * email:zc19930508@163.com
 * QQ:461346605
 * Tel:13218329781
 * If you have any question,please call me.
 **/

import java.util.LinkedList;
import java.util.Queue;

/**
 *@author zhouchuan
 *@date 2019/6/3 13:58
 *email:zc19930508@163.com
 *QQ:461346605 
 *Tel:13218329781
 *If you have any question,please call me.
 */
public class MyStack {
    /** Initialize your data structure here. */
    private Queue<Integer> queue;
    public MyStack() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (!queue.isEmpty()){
            int num = queue.size();
            while (num != 1){
                queue.add(queue.poll());
                num--;
            }
            return queue.poll();
        }else {
            return -1;
        }
    }

    /** Get the top element. */
    public int top() {
        if (!queue.isEmpty()){
            int num = queue.size();
            while (num != 1){
                queue.add(queue.poll());
                num--;
            }
            int result = queue.poll();
            queue.add(result);
            return result;
        }else {
            return -1;
        }
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
