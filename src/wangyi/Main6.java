package wangyi;

import java.util.*;

//一个合法的括号匹配序列被定义为:
//        1. 空串""是合法的括号序列
//        2. 如果"X"和"Y"是合法的序列,那么"XY"也是一个合法的括号序列
//        3. 如果"X"是一个合法的序列,那么"(X)"也是一个合法的括号序列
//        4. 每个合法的括号序列都可以由上面的规则生成
//        例如"", "()", "()()()", "(()())", "(((()))"都是合法的。
//        从一个字符串S中移除零个或者多个字符得到的序列称为S的子序列。
//        例如"abcde"的子序列有"abe","","abcde"等。
//        定义LCS(S,T)为字符串S和字符串T最长公共子序列的长度,即一个最长的序列W既是S的子序列也是T的子序列的长度。
//        小易给出一个合法的括号匹配序列s,小易希望你能找出具有以下特征的括号序列t:
//        1、t跟s不同,但是长度相同
//        2、t也是一个合法的括号匹配序列
//        3、LCS(s, t)是满足上述两个条件的t中最大的
//        因为这样的t可能存在多个,小易需要你计算出满足条件的t有多少个。
//
//        如样例所示: s = "(())()",跟字符串s长度相同的合法括号匹配序列有:
//        "()(())", "((()))", "()()()", "(()())",其中LCS( "(())()", "()(())" )为4,其他三个都为5,所以输出3.
//
//        输入描述:
//        输入包括字符串s(4 ≤ |s| ≤ 50,|s|表示字符串长度),保证s是一个合法的括号匹配序列。
//
//        输出描述:
//        输出一个正整数,满足条件的t的个数。
//
//        输入例子1:
//        (())()
//
//        输出例子1:
//        3
public class Main6 {
    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode out = reverseKGroup(head,2);
        while (out!=null){
            System.out.print(out.val+" ");
            out = out.next;
        }

    }
// K 个一组翻转链表
    public static ListNode reverseKGroup(ListNode head, int k) {
        int count = 0;
        ListNode temp = head;
        while (temp != null){
            count++;
            temp = temp.next;
            if (count == k-1)
                break;
        }
        if (temp == null)
            return head;
        int num = k;
        temp = temp.next;
        ListNode headt = head;
        ListNode pre = null;
        while (num!=0){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            num--;
        }
        headt.next=reverseKGroup(temp,k);
        return pre;
    }
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null||lists.length == 0) return null;
        int total = lists.length;
        int skip = 1;
        while (skip < total){
            for (int i=0;i<total;i = i+skip*2){
                lists[i] = merge(lists[i],lists[i+skip]);
            }
            skip*=2;
        }
        return lists[0];
    }

    public static ListNode merge(ListNode l1,ListNode l2){
        ListNode head = new ListNode(0);
        ListNode temp = head;
        while (l1 != null || l2 != null){
            int value1 = l1 == null?Integer.MAX_VALUE:l1.val;
            int value2 = l2 == null?Integer.MAX_VALUE:l2.val;
            temp.next = new ListNode(Math.min(value1,value2));
            temp = temp.next;
            if (value1 < value2)
                l1 = l1.next;
            else if (value1 > value2)
                l2 = l2.next;
        }
        return head.next;
    }




    static List<String> result = new ArrayList<>();
    public static List<String> generateParenthesis(int n) {
        if (n <= 0) return result;
        rollback("",n,0,0);
        return result;
    }

    public static void rollback(String combaine,int n,int left,int right){
        if (combaine.length() == n*2){
            result.add(combaine);
            return;
        }
        if (left < n)
            rollback(combaine+"(",n,left+1,right);
        if (right < left)
            rollback(combaine+")",n,left,right+1);
    }


    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0;i<nums.length-1;i++){
            if (nums.length-4 < i) break;
            if (i >0&&nums[i] == nums[i-1]) continue;
            for (int j =i+1;j<nums.length;j++){
                if (nums.length-3 < j) break;
                if (j >i+1&&nums[j] == nums[j-1]) continue;
                int left = j+1;
                int right = nums.length-1;
                while (left < right){
                    int sum = nums[i]+nums[j]+nums[left]+nums[right];
                    if (sum > target){
                        right--;
                    }else if (sum<target){
                        left++;
                    }else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);list.add(nums[j]);
                        list.add(nums[left]);list.add(nums[right]);
                        result.add(list);
                        while (left<right&&nums[left]==nums[left+1]) left++;
                        while (left<right&&nums[right]==nums[right-1]) right--;
                        left++;
                        right--;
                    }
                }
            }

        }
        return result;
    }


    static Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    static List<String> output = new ArrayList<>();
    public static List<String> letterCombinations(String digits) {
        if (digits.length() != 0)
            backtrack("", digits);
        return output;
    }

    public static void backtrack(String combination, String next_digits) {
       if (next_digits.length()==0){
            output.add(combination);
       }else {
           String digit  = next_digits.substring(0,1);
           String letter = phone.get(digit);
           for (int i = 0;i<letter.length();i++){
               char let = letter.charAt(i);
               backtrack(combination+let,next_digits.substring(1));
           }
       }
    }



    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int  i = 0;i<nums.length;i++){
            if (nums[i]-target > min) break;
            int left = i+1;
            int right = nums.length-1;
            while (left<right){
                int temp = nums[i]+nums[left]+nums[right];
                if (Math.abs(temp-target) < min){
                    min = temp;
                }
                if (temp > target)
                    right--;
                else if (temp<target)
                    left++;
                else
                    return temp;
            }
        }
        return sum;
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums==null||nums.length <3) return result;
        Arrays.sort(nums);
        for (int  i = 0;i<nums.length;i++){
            if (nums[i]>0) break;
            if (i >0&&nums[i] == nums[i-1]) continue;
            int left = i+1;
            int right = nums.length-1;
            while (left < right){
                if (nums[i]+nums[left]+nums[right]==0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    while (left<right && nums[left]==nums[left+1]) left++;
                    while (left<right && nums[right]==nums[right-1]) right--;
                    left++;
                    right--;
                }else if (nums[i]+nums[left]+nums[right]>0){
                    right--;
                }else {
                    left++;
                }
            }

        }
        return result;
    }

    public static int maxArea(int[] height) {
        int left =0;
        int right = height.length-1;
        int max = 0;
        while (left <= right){
            max = Math.max(max,(right-left)*Math.min(height[right],height[left]));
            if (height[right] > height[left]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}