package meituan;

import java.util.Scanner;
import java.util.Stack;

/*给你六种面额 1、5、10、20、50、100 元的纸币，假设每种币值的数量都足够多，编写程序求组成N元（N为0~10000的非负整数）的不同组合的个数。

输入描述:
输入包括一个整数n(1 ≤ n ≤ 10000)

输出描述:
输出一个整数,表示不同的组合方案数

输入例子1:
1

输出例子1:
1*/
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int count = scanner.nextInt();
            int[] input = new int[count];
            for (int  i =0;i<input.length;i++){
                input[i] = scanner.nextInt();
            }
            int max = 0;
            Stack<Integer> stack = new Stack<>();//压入位置
            for (int i =0;i<input.length;i++){
                while (!stack.isEmpty() && input[i] <= input[stack.peek()]){
                    int j = stack.pop();
                    int k = stack.isEmpty()?-1:stack.peek();
                    int cur = (i-k-1)*input[j];
                    max = Math.max(max,cur);
                }
                stack.push(i);
            }
            while (!stack.isEmpty()){
                int j = stack.pop();
                int k = stack.isEmpty()?-1:stack.peek();
                int cur = (count-k-1)*input[j];
                max = Math.max(max,cur);
            }
            System.out.println(max);
        }












//        Scanner in = new Scanner(System.in);
//        int num = in.nextInt();
//
//        int[] m = {1, 5, 10, 20, 50, 100}; // 保存基本面额的数组
//        long[] data = new long[num+1]; // 保存计算数据的数组
//        for(int i = 0; i <= num; i++) // 边界条件A(n,1) = 1 (n>=0)
//            data[i] = 1;
//        for(int i = 1; i < 6; i++) // 基本面额从5开始，因为1元情况在数组初始化时已经写入了
//            for(int n = 1; n <= num; n++) // n从1开始，保证了边界条件A(0,m)=1 (m=1,5,10,20,50,100)
//                if(n >= m[i])
//                    data[n] += data[n - m[i]]; // 状态转移方程
//        System.out.println(data[num]);
//        in.close();




//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            int input = sc.nextInt();
//            int[] arr = {1,5,10,20,50,100};
//            long[][] dp = new long[arr.length][input+1];
//            for (int i = 0;i<arr.length;i++){
//                dp[i][0] = 1;
//            }
//            for (int i = 1;arr[0]*i<=input;i++){
//                dp[0][arr[0]*i] = 1;
//            }
//            int num = 0;
//            for (int i =1;i<arr.length;i++)
//                for (int j = 1;j<=input;j++){
//                    num = 0;
//                    for (int k = 0;j-arr[i]*k>=0;k++){
//                        num+=dp[i-1][j-arr[i]*k];
//                    }
//                    dp[i][j] = num;
//                }
//            System.out.println(dp[arr.length-1][input]);
//        }
    }
}