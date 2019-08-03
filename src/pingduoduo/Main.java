package pingduoduo;

import java.util.Scanner;
/*给定一个无序数组，包含正数、负数和0，要求从中找出3个数的乘积，使得乘积最大，要求时间复杂度：O(n)，空间复杂度：O(1)

        输入描述:
        无序整数数组A[n]

        输出描述:
        满足条件的最大乘积

        输入例子1:
        3 4 1 2

        输出例子1:
        24*/
public class Main {
    //两个最小的和一个最大的
    //三个最大的
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String count = sc.nextLine();
            String[] str = sc.nextLine().split(" ");
            long[] input = new long[str.length];
            for (int i = 0;i<input.length;i++){
                input[i] = Integer.parseInt(str[i]);
            }
            long max1 = Integer.MIN_VALUE;
            long max2 = Integer.MIN_VALUE;
            long max3 = Integer.MIN_VALUE;
            long min1 = Integer.MAX_VALUE;
            long min2 = Integer.MAX_VALUE;
            for (int i =0;i<input.length;i++){
                if (input[i]>max1){
                    max3 = max2;
                    max2 = max1;
                    max1 = input[i];
                }else if(input[i] > max2){
                    max3 = max2;
                    max2 = input[i];
                }else if (input[i] > max3){
                    max3 = input[i];
                }

                if (input[i]<min1){
                    min2 = min1;
                    min1 = input[i];
                }else if (input[i] < min2){
                    min2 = input[i];
                }
            }
            System.out.println(Math.max(min1*min2*max1,max1*max2*max3));
        }
    }
}