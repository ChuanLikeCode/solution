import com.sun.scenario.effect.Merge;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;


import java.lang.reflect.ParameterizedType;
import java.util.*;

public class Solution {
    private static int x =0;
    public static void  main(String[] args){
//        char[][] board = {{'5','3','.','.','7','.','.','.','.'},
//                {'6','.','.','1','9','5','.','.','.'},
//                {'.','9','8','.','.','.','.','6','.'},
//                {'8','.','.','.','6','.','.','.','3'},
//                {'4','.','.','8','.','3','.','.','1'},
//                {'7','.','.','.','2','.','.','.','6'},
//                {'.','6','.','.','.','.','2','8','.'},
//                {'.','.','.','4','1','9','.','.','5'},
//                {'.','.','.','.','8','.','.','7','9'}};
//        int[][] count = new int[3][2];
//        count[1] = new int[]{0, 2};
//        count[0] = new int[]{1, 4};
//        count[2] = new int[]{3, 5};



//        ListNode node1 = new ListNode(1);
//        node1.next = new ListNode(2);
//        node1.next.next = new ListNode(3);
//        node1.next.next.next= new ListNode(5);
//        node1.next.next.next.next = new ListNode(6);

//        char[][] board = {{'a'}};

        System.out.println(longestPalindrome("aabaavvvvvvv"));
    }

    /**
     * 5. 最长回文子串
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * 示例 1：
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     *
     * 输入: "cbbd"
     * 输出: "bb"
     * Manacher算法
     */
    public static String longestPalindrome(String s) {
        StringBuilder sb =new StringBuilder();
        for (int i = 0;i<s.length();i++){
            sb.append('#').append(s.charAt(i));
        }
        sb.append('#');
        s = sb.toString();
        int len = s.length();//字符串长度
        int rightSide = 0;//右边界
        int rightSideCenter = 0;//右边界对应的中心
        int[] halfLenArr = new int[len];//保存以每个字符为中心的回文字符串长度一半(向下取整)
        int center = 0;//记录回文中心
        int longestHalf = 0;//记录最长回文长度
        for (int i = 0;i<len;i++){
            boolean needCalc = true;//是否需要中心扩展
            //如果在右边界的覆盖范围以内
            if (rightSide > i){
                int leftCenter = 2*rightSideCenter-i;//计算 i 位置相对于 rightSideCenter的对称点
                halfLenArr[i] = halfLenArr[leftCenter];//根据回文性质可以得到，对称的点与现在的点的回文长度一样
                if (i+halfLenArr[i] > rightSide){//如果超过了右边界，进行调整
                    halfLenArr[i] = rightSide-i;
                }

                //如果根据已知条件计算得出的最长回文小于右边界，则不需要扩展
                if (i+halfLenArr[leftCenter] < rightSide)
                    needCalc = false;
            }
            //中心扩展
            if (needCalc){
                while (i-1-halfLenArr[i] >= 0 && i+1+halfLenArr[i] < len){
                    if (s.charAt(i+1+halfLenArr[i])==s.charAt(i-1-halfLenArr[i])){
                        halfLenArr[i]++;
                    }else {
                        break;
                    }
                }
                //更新右边界及中心
                rightSide = i+halfLenArr[i];
                rightSideCenter = i;
                if (halfLenArr[i]>longestHalf){
                    center=i;
                    longestHalf = halfLenArr[i];
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = center-longestHalf+1;i<=center+longestHalf;i+=2)
            res.append(s.charAt(i));
        return res.toString();
    }
    /**
     * 207. 课程表
     * 现在你总共有 n 门课需要选，记为 0 到 n-1。
     *
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
     *
     * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
     *
     * 示例 1:
     *
     * 输入: 2, [[1,0]]
     * 输出: true
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
     * 示例 2:
     *
     * 输入: 2, [[1,0],[0,1]]
     * 输出: false
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
     * 说明:
     *
     * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
     * 你可以假定输入的先决条件中没有重复的边。
     * 提示:
     *
     * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
     * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
     * 拓扑排序也可以通过 BFS 完成。
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <=0) return false;
        int plen = prerequisites.length;
        if (plen == 0) return true;
        int[] inDegree = new int[numCourses];
        for (int[] p:prerequisites){
            inDegree[p[0]]++;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0;i<inDegree.length;i++){
            if (inDegree[i]==0){
                queue.addLast(i);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()){
            Integer num = queue.removeFirst();
            res.add(num);
            for (int[] p :prerequisites){
                if (p[1]==num){
                    inDegree[p[0]]--;
                    if (inDegree[p[0]]==0)
                        queue.addLast(p[0]);
                }
            }
        }
//        System.out.println(res);
        return res.size()==numCourses;
    }

    /**
     * 61. 旋转链表
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     *
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * 示例 2:
     *
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NULL
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        int count = 0;
        ListNode temp = head;
        while (temp!=null){
            count++;
            temp = temp.next;
        }
        if (k%count==0 ||count==1) return head;
        int quy =count - k%count;
        temp = head;
        while (quy != 0){
            temp = temp.next;
            quy--;
        }
        ListNode second = temp.next;
        temp.next = null;
        temp = second;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = head;
        return second;
    }

    /**
     * 215. 数组中的第K个最大元素
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * 示例 1:
     *
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * 示例 2:
     *
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * 说明:
     *
     * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        if (nums.length==0) return 0;
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    /**
     * 221. 最大正方形
     * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
     *
     * 示例:
     *
     * 输入:
     *
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     *
     * 输出: 4
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }

    static  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    /**
     * 236. 二叉树的最近公共祖先
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){return root; }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null? right : (right == null? left : root);
    }

    /**
     * 300. 最长上升子序列
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     *
     * 示例:
     *
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * 说明:
     *
     * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
     * 你算法的时间复杂度应该为 O(n2) 。
     * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums==null||nums.length==0) return 0;
        int[] dp = new int[nums.length];
        for (int i = 0;i<nums.length;i++){
            dp[i] = 1;
            for (int j = 0;j<i;j++){
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        int max = 0;
        for (int n:dp){
            if (n>max)
                max= n;
        }
        return max;
    }


    /**
     * 200. 岛屿数量
     * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，
     * 并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
     *
     * 示例 1:
     *
     * 输入:
     * 11110
     * 11010
     * 11000
     * 00100
     *
     * 输出: 1
     * 示例 2:
     *
     * 输入:
     * 11000
     * 11000
     * 00100
     * 00011
     *
     * 输出: 3
     * @param grid
     * @return
     */
    public static int numIslands(char[][] grid) {
        if (grid==null||grid.length==0) return 0;
        int num = 0;
        for (int i = 0;i<grid.length;i++){
            for (int j = 0;j<grid[i].length;j++){
                if (grid[i][j]=='1'){
                    num++;
                    dfs(grid,i,j);
                }
            }
        }
        return num;
    }

    public static void dfs(char[][] grid,int i,int j){
        int ni = grid.length;
        int nj = grid[0].length;
        if (i<0||i>=ni||j<0||j>=nj||grid[i][j]=='0')
            return;
        grid[i][j] = '0';
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }

    /**
     * 239. 滑动窗口最大值
     *
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
     *
     * 返回滑动窗口最大值。
     *
     * 示例:
     *
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     *
     *   滑动窗口的位置                 最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length==0) return new int[]{};
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[nums.length-k+1];
        int index = 0;
        for (int i = 0;i<nums.length;i++){
            while (!qmax.isEmpty()&&nums[qmax.peekLast()]<=nums[i])
                qmax.pollLast();
            qmax.addLast(i);
            if (qmax.peekFirst()<(i-k+1))
                qmax.pollFirst();
            if (i+1>=k){
                res[index] = nums[qmax.peekFirst()];
//                System.out.println(nums[qmax.peekFirst()]);
                index++;
            }
        }
        return res;
    }

    /**
     * 60. 第k个排列
     * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
     *
     * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     *
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * 给定 n 和 k，返回第 k 个排列。
     *
     * 说明：
     *
     * 给定 n 的范围是 [1, 9]。
     * 给定 k 的范围是[1,  n!]。
     * 示例 1:
     *
     * 输入: n = 3, k = 3
     * 输出: "213"
     * 示例 2:
     *
     * 输入: n = 4, k = 9
     * 输出: "2314"
     * @param n
     * @param k
     * @return
     *
     * 康托展开运算
     *
     * 其中,  为整数,并且  。
     *   表示原数的第i位在当前未出现的元素中是排在第几个
     * z康托展开的逆运算
     * 既然康托展开是一个双射，那么一定可以通过康托展开值求出原排列，即可以求出n的全排列中第x大排列。
     * 如n=5,x=96时：
     * 首先用96-1得到95，说明x之前有95个排列.(将此数本身减去1)用95去除4! 得到3余23，
     * 说明有3个数比第1位小，所以第一位是4.用23去除3! 得到3余5，说明有3个数比第2位小，
     * 所以是4，但是4已出现过，因此是5.用5去除2!得到2余1，类似地，这一位是3.用1去除1!
     * 得到1余0，这一位是2.最后一位只能是1.所以这个数是45321。
     * 按以上方法可以得出通用的算法。 [1]
     */
    public static String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        for (int i = 1;i<=n;i++){
            list.add(i);
        }
        k--;
        while (list.size()!=0){
            n--;
            int temp = getN(n);
            int kc = k/temp;
            int ky = k%temp;
            sb.append(list.get(kc));
            list.remove(kc);
            k=ky;
        }
        return sb.toString();

//         StringBuilder sb = new StringBuilder();
//        List<Integer> list = new ArrayList<>();
//        for (int i = 1;i<=n;i++){
//            list.add(i);
//        }
//        while (n!=0){
//            int num = getN(n-1);
//            int strNum = k/num;
//            int ky = k%num;
//            if (k==num||k==0){
//                sb.append(list.get(0));
//                list.remove(0);
//            } else {
//                sb.append(list.get(ky==0?strNum-1:(strNum==n?strNum-1:strNum)));
//                list.remove(ky==0?strNum-1:(strNum==n?strNum-1:strNum));
//            }
//            k = k!=num?(k-num<0?k:k-num*(ky==0?strNum-1:(strNum==n?strNum-1:strNum))):k;
//            n--;
//
//        }
//        return sb.toString();
        }

    public static int getN(int n){
        int s = 1;
        for(int i=1;i<=n;i++)
        {
            s*=i;
        }
        return s;
    }

    /**
     * 57. 插入区间
     * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
     *
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     *
     * 示例 1:
     *
     * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出: [[1,5],[6,9]]
     * 示例 2:
     *
     * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * 输出: [[1,2],[3,10],[12,16]]
     * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     * @param intervals
     * @param newInterval
     * @return
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length==0) return new int[][]{{newInterval[0],newInterval[1]}};
        int start  = 0;
        int end = 0;
        for (int i = 0;i<intervals.length;i++){
            int val = intervals[i][0];
            int val2 = intervals[i][1];
            if (newInterval[0]>=val&&newInterval[0]<=val2){
                start=i;
            }
            if (newInterval[1]>=val && newInterval[0]<=val2){
                end = i;
            }
        }
        int[][] res = new int[intervals.length-(end-start+1)+1][2];
        for (int i = 0;i<start;i++){
            res[i] = intervals[i];
        }

        res[start][0] = intervals[start][0];
        res[start][1] = intervals[end][1];
        for (int i = start+1;i<res.length;i++){
            res[i] = intervals[end+1];
            end++;
        }
        return res;
    }


    /***
     *
     * 45. 跳跃游戏 II
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * [2,3,1,1,4]
     * 输入: [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * @param nums
     * @return
     */
    //cur表示当前状态下不需要增加步数的剩余跳动距离，range表示增加一步的剩余跳动距离。 线性遍历一遍，cur的跳动距离消耗完之后增加步数，同时把rang赋给cur
    public static int jump(int[] nums) {
        int step =0;
        int range = 0;
        int cur = 0;
        for (int i = 0;i<nums.length-1;i++) {
            if (nums[i] > range)
                range = nums[i];
            if (cur == 0){
                cur = range;
                step++;
            }
            cur--;
            range--;
        }
        return step;
    }

    public static String countAndSay(int n) {
        if (n==1) return "1";
        String input = countAndSay(n-1);
        int count = 0;
        char pre = input.charAt(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<input.length();i++){
            if (input.charAt(i)==pre){
                count++;
            } else {
                sb.append(count).append(pre);
                count=1;
            }
            pre = input.charAt(i);
        }
        sb.append(count).append(pre);
        return sb.toString();
    }



    public static int romanToInt(String s) {
        Map<String,Integer> map = new HashMap<>();
        map.put("I",1);
        map.put("IV",4);
        map.put("V",5);
        map.put("IX",9);
        map.put("X",10);
        map.put("XL",40);
        map.put("L",50);
        map.put("XC",90);
        map.put("C",100);
        map.put("CD",400);
        map.put("D",500);
        map.put("CM",900);
        map.put("M",1000);

        int ans = 0;
        for (int i = 0;i<s.length();i++){
            String key;
            if (i<s.length()-1){
                key = s.charAt(i)+""+s.charAt(i+1);
            }else {
                key = s.charAt(i)+"";
            }
            if (map.containsKey(key)){
                ans += map.get(key);
                i++;
            }else {
                ans += map.get(s.charAt(i)+"");
            }
        }
        return ans;
    }

    public static String intToRoman(int num) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"I");
        map.put(4,"IV");
        map.put(5,"V");
        map.put(9,"IX");
        map.put(10,"X");
        map.put(40,"XL");
        map.put(50,"L");
        map.put(90,"XC");
        map.put(100,"C");
        map.put(400,"CD");
        map.put(500,"D");
        map.put(900,"CM");
        map.put(1000,"M");
        int[] nums = new int[]{1,4,5,9,10,40,50,90,100,400,500,900,1000};
        StringBuilder sb = new StringBuilder();
        int index = nums.length-1;
        while (num != 0){
            for (int i = index;i>=0;i--){
                if (num >= nums[i]){
                    sb.append(map.get(nums[i]));
                    num -= nums[i];
                    index = i;
                    break;
                }
            }
        }
        return sb.toString();
    }

    /**
     * 字符串转数字
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        String trim = str.trim();
        if(trim.length()==0) return 0;
        if (trim.length()==1&&(trim.charAt(0)<'0'||trim.charAt(0)>'9'))
            return 0;
        boolean flag = true;
        boolean change = false;
        if (trim.charAt(0)=='-'){
            flag = false;
        }else if (trim.charAt(0)=='+'){
            flag = true;
            change = true;
        }else if (trim.charAt(0)<'0'||trim.charAt(0)>'9'){
            return 0;
        }
        int index = 0;
        if (!flag||change){
            index = 1;
        }
        StringBuilder res = new StringBuilder();
        boolean swi = true;
        for (int i = index;i<trim.length();i++){
            if (swi&&trim.charAt(i)=='0')
                continue;
            if (trim.charAt(i)>='0'&&trim.charAt(i)<='9'){
                res.append(trim.charAt(i));
                swi = false;
            }else {
                break;
            }
        }
        if (res.length()==0) return 0;
        String max = Integer.MAX_VALUE+"";
        String min = Integer.MIN_VALUE+"";
        if (!flag){
            res.insert(0,"-");
            return result(min,res.toString());
        }else {
            return result(max,res.toString());
        }
    }

    public static int result(String input,String res){
        if (res.length()>input.length())
            return Integer.parseInt(input);
        else if (res.length()==input.length()){
            int resindex = 0;
            int minIndex = 0;
            boolean jinwei = false;
            while (resindex<res.length()){
                if (res.charAt(resindex)>input.charAt(minIndex)&&!jinwei)
                    return Integer.parseInt(input);
                else if (res.charAt(resindex)<input.charAt(minIndex)){
                    jinwei = true;
                }
                resindex++;
                minIndex++;
            }
            return Integer.parseInt(res);
        }else {
            return Integer.parseInt(res);
        }
    }


    public static int[] countBits(int num) {
        int[] res = new int[num+1];
        if(num == 0){
            res[0] = 0;
            return res;
        }
        res[1] = 1;
        if(num == 1){

            return res;
        }
        res[2] = 1;
        if(num == 2){

            return res;
        }
        res[3] = 2;
        if(num == 3){
            return res;
        }
        int index = 1;
        for(int i = 4;i<=num;i++){
            if((i&(i-1))==0)
                index++;
            res[i] = res[i%((int)Math.pow(2,index))]+1;

        }
        return res;
    }
    public static boolean exist(char[][] board, String word) {
        Map<Character,Integer> map = new HashMap<>();
        boolean[][] used = new boolean[board.length][board[0].length];
        int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (back(board, used,direction,i,j,0,word)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean back(char[][] board,boolean[][] used,int[][] direction,int i,int j,int start,String word){
        if (start == word.length()-1){
            return board[i][j] == word.charAt(start);
        }
        if (board[i][j] == word.charAt(start)){
            used[i][j] = true;
            for (int k = 0;k<4;k++){
                int x = i+direction[k][0];
                int y = j+direction[k][1];
                if (x>=0&&x<board.length&&y>=0&&y<board[0].length&&!used[x][y]){
                    if (back(board,used,direction,x,y,start+1,word)){
                        return true;
                    }
                }
            }
            used[i][j] = false;
        }
        return false;
    }

    public static String minWindow(String s, String t) {
        int min = Integer.MAX_VALUE;
        int start = 0;
        int len = 0;
        int left = -1;
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0;i<t.length();i++){
            char key = t.charAt(i);
            map.put(t.charAt(i),map.getOrDefault(key,0)+1);
        }
        for (int i = 0;i<s.length();i++){
            char key = s.charAt(i);
            if (map.containsKey(key)){
                map.put(key,map.get(key)-1);
                len = map.get(key)==0?len+1:len;
            }
            if (len == map.size()){
                while (len == map.size()){
                    if (map.containsKey(s.charAt(start))){
                        int num = map.get(s.charAt(start))+1;
                        map.put(s.charAt(start),num);
                        if (num == 1){
                            len--;
                        }
                    }
                    start++;
                }
                if (i-start+1 < min){
                    left = start-1;
                    min = i-left;
                }
            }
        }
        return left==-1?"":s.substring(left,left+min+1);
    }

    public static void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        int cur = 0;
        while ( cur <= end && start < end){
            if (nums[cur]==0){
                swapNum(nums,cur,start);
                start++;
                cur++;
            }else if (nums[cur]==2){
                swapNum(nums,cur,end);
                end--;
            }else {
                cur++;
            }

        }
        System.out.println();
    }

    private static void swapNum(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    static class Interval{
        int num1;
        int num2;
        public Interval(int a,int b){
            num1 = a;
            num2 = b;
        }
    }

    /**
     * 合并区间
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        //int[][] res = new int[intervals.length][2];
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        List<Interval> list = new ArrayList<>();
        for(int i = 0;i<intervals.length;i++){
            int left = i+1;
            int val = intervals[i][1];
            while(left < intervals.length && val >=intervals[left][0]){
                left++;
                val = Math.max(val,intervals[left][1]);
            }
            Interval temp = new Interval(intervals[i][0],Math.max(intervals[i][1],intervals[left-1][1]));
            list.add(temp);
            i = left-1;
        }
        int[][] res = new int[list.size()][2];
        for (int i = 0;i<list.size();i++){
            res[i][0] = list.get(i).num1;
            res[i][1] = list.get(i).num2;
        }
        return res;
    }

    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val ;
        }
    }

    public static boolean pow2(int n){
        return (n&(n-1))==0;
    }

    public static ListNode Merge(ListNode list1,ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode temp = head;
        while(list1!=null||list2!=null){
            int val1 = list1==null?Integer.MAX_VALUE:list1.val;
            int val2 = list2==null?Integer.MAX_VALUE:list2.val;
            temp.next = val1 < val2?new ListNode(val1):(new ListNode(val2));
            temp = temp.next;
        }
        return head.next;
    }

    public static String replaceSpace(StringBuffer str) {
        //String str1 = str.toString();
        //return str1.replaceAll(" ","%20");
        for(int i = 0;i<str.length();i++){
            if (str.charAt(i)==' '){
                str.delete(i,i+1);
                str.insert(i,"%20");
            }
        }

        return str.toString();
    }

    public static boolean canJump(int[] nums) {
        if(nums.length==0 ||nums.length==1) return true;
        if(nums[0]==0) return false;
        int len = nums.length;
        int max = 1;
        for(int i = 0;i<nums.length;i++){
            if(len-i-1<=nums[i]&&max>0){
                return true;
            }
            max--;
            if (max >=0)
                max = Math.max(max,nums[i]);


        }
        return false;
    }


    public static int trap1(int[] height) {
        if(height.length==0) return 0;
        int max =0;
        int leftMax=0,rightMax = 0;
        int left = 0,right=height.length-1;
        while(left < right){
            if(height[left] < height[right]){
                if(height[left] > leftMax){
                    leftMax = height[left];
                }else{
                    max +=leftMax-height[left];
                }
                left++;
            }else{
                if(height[right]>rightMax){
                    rightMax = height[right];
                }else{
                    max += rightMax-height[right];
                }
                right++;
            }
        }
        return max;
    }

    public  static int trap(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i =0;i<height.length;i++){
            while (!stack.isEmpty() && height[i] > height[stack.peek()]){
                int h = height[stack.peek()];
                stack.pop();
                if (stack.isEmpty()) break;
                int distance = i - stack.peek() - 1; //两堵墙之前的距离
                int min = Math.min(height[stack.peek()], height[i]);
                sum = sum + distance * (min - h);
            }
            stack.push(i);
        }
        return sum;
    }


    public static int search12(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while (left < right){
            int mid = (left+right)/2;
            if (nums[mid] < nums[left]){
                right = mid;
            }else if (nums[mid] > nums[right]){
                left = mid+1;
            }else {
                break;
            }
        }
        return left;
    }



    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        if (matrix[0].length == 0) return false;
        int left = 0;
        int right = matrix.length-1;
        int index = matrix[0].length-1;
        int mid = (left+right)/2;
        while (left < right){
            if (matrix[mid][0] == target){
                return true;
            }else if (matrix[mid][0] < target ){
                if (matrix[mid][index] < target){
                    left = mid+1;
                }else {
                    break;
                }
            }else {
                right=mid-1;
            }
            mid = (left+right)/2;
        }
        int start = 0;
        index = mid;
        int end = matrix[index].length-1;
        mid = (start+end)/2;
        while (start <= end){
            if (matrix[index][mid] == target){
                return true;
            }else if (matrix[index][mid] < target){
                start = mid+1;
            }else
                end = mid -1;
            mid = (start+end)/2;
        }
        return false;
    }

    public static int mySqrt(int x) {
        if (x ==0) return 0;
        double last = x-1;
        double res = x;
        while (res != last){
            last = res;
            res = (res + x/res)/2;
        }
        return (int)res;
    }
    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int mid = (left+right)/2;
        while (left<=right){
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] > target){
                right = mid-1;
            }else {
                left = mid+1;
            }
            mid = (left+right)/2;
        }
        return left;
    }


    public static List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length==0) return null;
        if (matrix[0].length==0) return null;
        int rowStart = 0;
        int rowEnd = matrix.length-1;
        int colStart = 0;
        int colEnd = matrix[0].length-1;
        List<Integer> list = new ArrayList<>();
        int total = matrix.length*matrix[0].length;
        while (list.size() < total){
            for (int i=colStart;i<=colEnd;i++){
                list.add(matrix[rowStart][i]);
            }
            rowStart++;
            if (list.size()==total) break;
            for (int i=rowStart;i<=rowEnd;i++){
                list.add(matrix[i][colEnd]);
            }
            colEnd--;
            if (list.size()==total) break;
            for (int i=colEnd;i>=colStart;i--){
                list.add(matrix[rowEnd][i]);
            }
            rowEnd--;
            if (list.size()==total) break;
            for (int i = rowEnd;i>=rowStart;i--){
                list.add(matrix[i][colStart]);
            }
            colStart++;
            if (list.size()==total) break;
        }
        return list;
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i=0;i<heights.length;i++){
            while (!stack.isEmpty()&&heights[i]<heights[stack.peek()]){
                int j = stack.pop();
                int k = stack.isEmpty()?-1:stack.peek();
                max = Math.max(max,(i-k-1)*heights[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int j = stack.pop();
            int k = stack.isEmpty()?-1:stack.peek();
            max = Math.max(max,(heights.length-k-1)*heights[j]);
        }
        return max;
    }



    public static int minPathSum(int[][] grid) {
        for (int i = 0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                if (i==0){
                    grid[i][j] += j>0?grid[i][j-1]:0;
                }else if (j==0){
                    grid[i][j] += grid[i-1][j];
                }else {
                    grid[i][j] += Math.min(grid[i-1][j],grid[i][j-1]);
                }
            }
        }
        return grid[grid.length-1][grid[grid.length-1].length-1];
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[] dp = new int[obstacleGrid[0].length];
        dp[0] = 1;
        for (int i = 1;i<obstacleGrid[0].length;i++){
            dp[i] = dp[i-1];
            if (obstacleGrid[0][i] == 1){
                dp[i] = 0;
            }
        }
        for (int i = 1;i<obstacleGrid.length;i++){
            for (int j=0;j<obstacleGrid[i].length;j++) {
                if (j==0){
                    if (obstacleGrid[i][0] == 1){
                        dp[j] = 0;
                    }
                }else {
                    dp[j] = dp[j-1]+dp[j];
                    if (obstacleGrid[i][j]==1){
                        dp[j] = 0;
                    }
                }
            }
        }
        return dp[dp.length-1];
    }

    public static int uniquePaths(int m, int n) {
        int[] dp = new int[m];
        for (int i = 0;i<m;i++){
            dp[i] = 1;
        }
        for (int i = 1;i<n;i++){
            for (int j=1;j<m;j++){
                dp[j] = dp[j-1]+dp[j];
            }
        }
        return dp[m-1];
    }

    public static List<List<String>> solveNQueens(int n) {
        boolean[] rowUsed = new boolean[n];
        boolean[] colUsed = new boolean[n];
        //主对角线 i>=j时 [i-j] i<j时 [j-i+n-1]
        boolean[] zhuxieUsed = new boolean[2*n-1];
        //次对角线 [i+j]
        boolean[] fuxieUsed = new boolean[2*n-1];
        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        solveBack(zhuxieUsed,fuxieUsed,rowUsed,colUsed,n,result,list);
        return result;
    }

    private static void solveBack(boolean[] zhuxieUsed,boolean[] fuxieUsed,boolean[] rowUsed, boolean[] colUsed, int n, List<List<String>> result, List<String> list) {
        if (list.size() == n) {
            result.add(new ArrayList<>(list));
        }else {
            for (int i = 0;i<n;i++){
                if (i == list.size()){
                    for (int j = 0;j<n;j++){
                        boolean canUse;
                        if (i >=j){
                            canUse = rowUsed[i] || colUsed[j] || zhuxieUsed[i-j]|| fuxieUsed[i+j];
                        }else {
                            canUse = rowUsed[i] || colUsed[j] || zhuxieUsed[j-i+n-1] || fuxieUsed[i+j];
                        }
                        if (!canUse){
                            list.add(putString(j,n));
                            rowUsed[i] = true;
                            colUsed[j] = true;
                            if (i >=j){
                                zhuxieUsed[i-j] = true;
                            }else {
                                zhuxieUsed[j-i+n-1] = true;
                            }
                            fuxieUsed[i+j] = true;
                            solveBack(zhuxieUsed,fuxieUsed,rowUsed,colUsed,n,result,list);
                            list.remove(list.size()-1);
                            rowUsed[i] = false;
                            colUsed[j] = false;
                            if (i >=j){
                                zhuxieUsed[i-j] = false;
                            }else {
                                zhuxieUsed[j-i+n-1] = false;
                            }
                            fuxieUsed[i+j] = false;
                        }
                    }
                }



            }
        }
    }

    private static String putString(int index,int n){
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<n;i++){
            if (i == index){
                sb.append("Q");
            }else {
                sb.append(".");
            }
        }
        return sb.toString();
    }


    public static void rotate(int[][] matrix) {
        resever(matrix);
        change(matrix);
        for(int i = 1;i<matrix.length;i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.println(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void change(int[][] matrix){
        int n = matrix.length;
        for(int i = 1;i<n;i++){
            for(int j = 0;j<i;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void resever(int[][] nums){
        int left = 0;
        int right = nums.length-1;
        while(left < right){
            int[] temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
    }






    public static int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0;i<len;i++){
            if (nums[i] <= 0||nums[i]>=len) continue;
            if (nums[i] == i+1) continue;
            int temp = nums[i];
            nums[i] = nums[temp-1];
            nums[temp-1] = temp;
            if (nums[i]>0&&nums[i]<len&&nums[i]!=nums[nums[i]-1])
                i--;
        }
        for (int i = 0;i<len;i++){
            if (nums[i]!=i+1){
                return i+1;
            }
        }
        return len;
    }



    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length==0) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int n:candidates){
            if (map.containsKey(n)){
                map.put(n,map.get(n)+1);
            }else {
                map.put(n,1);

            }
        }
        back(result,list,candidates,target,0,map);
        return result;
    }

    public static void back(List<List<Integer>> result,List<Integer> list,int[] candidates,int target,int sum,Map<Integer,Integer> map){
        if (sum == target){
            List<Integer> temp = new ArrayList<>(list);
            Collections.sort(temp);
            if (!result.contains(temp))
                result.add(temp);
        }else {
            if (sum < target) {
                for (int i = 0;i<candidates.length;i++){
                    if (map.get(candidates[i]) == 0) continue;
                    if (sum + candidates[i] > target) break;
                    sum += candidates[i];
                    list.add(candidates[i]);
                    map.put(candidates[i],map.get(candidates[i])-1);
                    back(result,list,candidates,target,sum,map);
                    list.remove((Integer)candidates[i]);
                    sum-=candidates[i];
                    map.put(candidates[i],map.get(candidates[i])+1);
                }
            }

        }
    }


    private static void print(char[][] board){
        for (int row = 0;row<9;row++){
            for (int col = 0;col<9;col++) {
                System.out.print(board[row][col]+ " ");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }

    static boolean sudokuSolved = false;
    public static void solveSudoku(char[][] board) {
        boolean[][] rowUsed = new boolean[9][10];//行
        boolean[][] colUsed = new boolean[9][10];//列
        boolean[][] boxUsed = new boolean[9][10];//小格子

        for (int row = 0;row<9;row++){
            for (int col = 0;col<9;col++){
                if (board[row][col] != '.'){
                    int num = board[row][col] - '0';
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[(row/3)*3+col/3][num] = true;
                }
            }
        }
        backtrack(0,0,board,rowUsed,colUsed,boxUsed);
    }

    public static void backtrack(int row,int col,char[][] board,boolean[][] rowUsed,boolean[][] colUsed,boolean[][] boxUsed){
        if (col==9){
            col = 0;
            row++;
            if (row == 9)
                sudokuSolved = true;
        }
        if (board[row][col] == '.'){
            for (int i = 1;i<10;i++){
                boolean canUsed = rowUsed[row][i] || colUsed[col][i] || boxUsed[(row/3)*3+col/3][i];
                if (!canUsed){
                    colUsed[row][i] = true;
                    rowUsed[row][i] = true;
                    boxUsed[(row/3)*3+col/3][i] = true;
                    board[row][col] = (char)('0' + i);
                    print(board);
                    backtrack(row,col+1,board,rowUsed,colUsed,boxUsed);

                    if (!sudokuSolved){
                        board[row][col] = '.';
                        colUsed[row][i] = false;
                        rowUsed[row][i] = false;
                        boxUsed[(row/3)*3+col/3][i] = false;
                    }

                }
            }
        }else {
            backtrack(row,col+1,board,rowUsed,colUsed,boxUsed);
        }
    }




    public static int searchRange(int[] nums, int target) {
        int right = index(nums,target,true);
        int left = index(nums,target,false);
        if (nums[left]!=target){
            left++;
            left = left >= nums.length?-1:nums[left]!=target?-1:left;
        }
        if (nums[right] != target){
            right--;
            right = right < 0?-1:nums[right]!=target?-1:right;
        }

        System.out.println(left+" "+right);
        return left;
    }

    public static int index(int[] nums,int target,boolean flag){
        int left = 0;
        int right = nums.length-1;
        while (left < right){
            int mid = (left+right)/2;
            if (target == nums[mid]){
                if (flag)
                    left = mid+1;
                else
                    right = mid-1;
            }else if (target > nums[mid]){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return left;
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while (left < right){
            int mid = (left+right)/2;
            if (nums[mid] < nums[left]){
                right = mid;
            }else if (nums[mid] > nums[right]){
                left = mid+1;
            }else {
                break;
            }
        }
        if (target > nums[nums.length-1]){
            right = left-1;
            left = 0;
        }else if(target < nums[nums.length-1]){
            right = nums.length-1;
        } else{
            return nums.length-1;
        }
//        while (left <= right){
//            int mid = (left+right)/2;
//            if (target > nums[mid]){
//                left = mid+1;
//            }else if (target < nums[mid]){
//                right = mid-1;
//            }else {
//                return mid;
//            }
//        }

        return -1;
    }


    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        stack.push(-1);
        for (int i = 0;i <s.length();i++){
            if (s.charAt(i)=='('){
                stack.push(i);
            }else {
                stack.pop();
                if (stack.isEmpty()){
                    stack.push(i);
                }else {
                    max = Math.max(max,i-stack.peek());
                }
            }
        }
        return max;
    }

    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        int min = nums[len-1];
        int i=len-1;
        while (i>=0&&min <= nums[i]){
            min = nums[i];
            i--;
        }
        if (i == -1){
            resver(nums,0,len-1);
        }else {
            min = nums[i];
            int j = i+1;
            while (j < len && min < nums[j]){
                j++;
            }
            swap(nums,i,j-1);
            resver(nums,i+1,len-1);
        }
        for (int n:nums){
            System.out.print(n+" ");
        }

    }

    private static void resver(int[] nums, int i, int end) {
        while (i < end){
            swap(nums,i,end);
            i++;
            end--;
        }
    }

    private static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
