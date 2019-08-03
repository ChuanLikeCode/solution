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
 * @date 2019/6/3 14:17
 * email:zc19930508@163.com
 * QQ:461346605
 * Tel:13218329781
 * If you have any question,please call me.
 **/

import java.util.Stack;

/**
 *@author zhouchuan
 *@date 2019/6/3 14:17
 *email:zc19930508@163.com
 *QQ:461346605 
 *Tel:13218329781
 *If you have any question,please call me.
 */
public class MyQueue {

    private Stack<Integer> stack;
    private Stack<Integer> stack2;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (!stack.isEmpty()){
            while (!stack.isEmpty()){
                stack2.push(stack.pop());
            }
            int result = stack2.pop();
            while (!stack2.isEmpty()){
                stack.push(stack2.pop());
            }
            return result;
        }else {
            return -1;
        }
    }

    /** Get the front element. */
    public int peek() {
        if (!stack.isEmpty()){
            while (!stack.isEmpty()){
                stack2.push(stack.pop());
            }
            int result = stack2.peek();
            while (!stack2.isEmpty()){
                stack.push(stack2.pop());
            }
            return result;
        }else {
            return -1;
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}
