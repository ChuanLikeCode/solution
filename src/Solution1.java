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
 * @date 2019/5/31 15:40
 * email:zc19930508@163.com
 * QQ:461346605
 * Tel:13218329781
 * If you have any question,please call me.
 **/

import java.util.*;

/**
 *@author zhouchuan
 *@date 2019/5/31 15:40
 *email:zc19930508@163.com
 *QQ:461346605 
 *Tel:13218329781
 *If you have any question,please call me.
 */
public class Solution1 {
    public static void main(String[] args){

//        ListNode node = new ListNode(1);
//        node.next = new ListNode(0);
//        node.next.next = new ListNode(0);

        System.out.println(getNums(10000));
    }

    public static int getNums(int n){
        int count = 0;
        for (int i = 1;i<=n;i++){
            int temp = i;
            while(temp != 0){
                count++;
                temp &= temp-1;
            }
        }
        return count;
    }



    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list= new ArrayList<>();
        if(nums.length==0) return result;
        back(result,list,nums,0);
        return result;
    }

    public void back(List<List<Integer>> result,List<Integer> list,int[] nums,int index){
        if(list.size() <= nums.length){
            result.add(new ArrayList<>(list));
        }
        for(int i=index;i<nums.length;i++){
            list.add(nums[i]);
            back(result,list,nums,i+1);
            list.remove(list.size()-1);
        }
    }


    /**
     * Definition for singly-linked list.*/
      public static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }


    public static boolean isPalindrome(ListNode head) {
        if(head == null || head.next==null) return true;
        ListNode next = head;
        ListNode fast = head.next.next;
        while(fast!=null){
            next = next.next;
            fast = fast.next==null?null:fast.next.next;
        }
        ListNode pre = null;
        ListNode temphead = next.next;
        while(temphead !=null){
            ListNode tempnext = temphead.next;
            temphead.next = pre;
            pre = temphead;
            temphead = tempnext;
        }
        next.next= null;
        while(head != null&&pre!=null){
            if(head.val != pre.val)
                return false;
            head = head.next;
            pre = pre.next;
        }
        return true;
    }


    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        Map<String,Integer> mapTemp = new HashMap<>();
        int len = words[0].length();
        for (String str:words){
            if (map.containsKey(str))
                map.put(str,map.get(str)+1);
            else
                map.put(str,1);
        }
        mapTemp.putAll(map);
        int ending = words.length*words[0].length();
        for (int i = 0;i<s.length()-ending+1;i++) {
            int start = i;
            int end = start + len;
            boolean change = false;
            while (end <= s.length()){
                String temp = s.substring(start,end);
                if (map.containsKey(temp)&&map.get(temp) < mapTemp.get(temp)*2){
                    map.put(temp,map.get(temp)+1);
                    start = end;
                    end = start+len;
                    change = true;
                }else {
                    break;
                }
            }

            boolean flag = false;
            Set<String> keySet = map.keySet();
            if (change)
                for (String key:keySet){
                    if (map.get(key)!=mapTemp.get(key)*2){
                        flag =true;
                        break;
                    }
                }


            if (!flag&&change){
                res.add(i);
//                i = i+words[0].length()-1;
            }
            if (change){
                map.clear();
                map.putAll(mapTemp);
            }


        }
        return res;
    }


    public static int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (dividend == Integer.MIN_VALUE && divisor == 1) return Integer.MIN_VALUE;
        int res = 0;
        boolean flag = false;//答案为负
        boolean temp = false;
        if ((dividend > 0 && divisor >0)||(dividend<0&&divisor<0))
            flag = true;//答案为正
        if (dividend == Integer.MIN_VALUE){
            dividend = Integer.MAX_VALUE-1;
            temp=true;
        }

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        while (dividend >= divisor){
            dividend -= divisor;
            res++;

        }
        return flag?res:temp?(0-res)-1:(0-res);
    }

    public static boolean lemonadeChange(int[] bills) {
        int yue5 = 0,yue10 = 0;
        for(int i = 0; i < bills.length ; i++){
            if(yue5*5+yue10*10 >= bills[i]-5){
                int tmp = bills[i]-5;
                while(tmp >= 10 && yue10  != 0){
                    yue10--;
                    tmp -= 10;
                }
                if(yue10 < 0) return false;
                while(tmp >= 5 && yue5 !=0){
                    yue5--;
                    tmp -= 5;
                }
                if(yue5 < 0) return false;
                if(bills[i] == 5){
                    yue5++;
                }else if(bills[i] == 10){
                    yue10++;
                }
            }else{
                return false;
            }
        }
        return true;
    }

    static int calPoints(String[] ops) {
        int sum = 0;
        for(int i = 0 ;i<ops.length;i++){
            switch(ops[i]){
                case "C":

                    break;
                case "D":

                    break;
                case "+":

                    break;
                default:
                    sum += Integer.valueOf(ops[i]);
                    break;
            }
        }
        return sum;
    }

    static String convertToTitle(int n) {
        if(n ==0) return "";
        n--;
        return convertToTitle(n/26)+(char)(n%26 + 'A');
    }

    public static boolean isPalindrome(String s) {
        s= s.toLowerCase();
        int left = 0,right = s.length()-1;
        while(left < right){
            while(!((s.charAt(left)>='A'&&s.charAt(left)<='Z')||(s.charAt(left)>='a'&&s.charAt(left)<='z')||(s.charAt(left)>='0'&&s.charAt(left)<='9'))&&left < right)
                left++;
            while(!((s.charAt(right)>='A'&&s.charAt(right)<='Z')||(s.charAt(right)>='a'&&s.charAt(right)<='z')||(s.charAt(right)>='0'&&s.charAt(right)<='9'))&&left < right)
                right--;
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;

        }
        return true;
    }
}
