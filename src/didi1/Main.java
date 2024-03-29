package didi1;

import java.util.Scanner;

/**
 * 一个数组有 N 个元素，求连续子数组的最大和。 例如：[-1,2,1]，和最大的连续子数组为[2,1]，其和为 3
 *
 * 输入描述:
 * 输入为两行。 第一行一个整数n(1 <= n <= 100000)，表示一共有n个元素 第二行为n个数，即每个元素,每个整数都在32位int范围内。以空格分隔。
 *
 * 输出描述:
 * 所有连续子数组中和最大的值。
 *
 * 输入例子1:
 * 3 -1 2 1
 *
 * 输出例子1:
 * 3
 */
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            int count = Integer.parseInt(sc.nextLine());
            String[] str = sc.nextLine().split(" ");
            int[] input = new int[count];
            for (int i = 0;i<count;i++){
                input[i] = Integer.parseInt(str[i]);
            }
            int res = input[0];
            int max = input[0];
            for (int i = 1;i< count;i++){
                max = (max+input[i]) > input[i] ? max+input[i] : input[i];
                res = max>res ? max : res;
            }
            System.out.println(res);
        }
    }
}