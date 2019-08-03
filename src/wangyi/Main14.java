package wangyi;

import java.util.Arrays;
import java.util.Scanner;

//小易准备去拜访他的朋友，他的家在0点，
// 但是他的朋友的家在x点(x > 0)，均在一条坐标轴上。
// 小易每一次可以向前走1，2，3，4或者5步。问小易最少走多少次可以到达他的朋友的家。
//
//        输入描述:
//        一行包含一个数字x(1 <= x <= 1000000)，代表朋友家的位置。
//
//        输出描述:
//        一个整数，最少的步数。
//
//        输入例子1:
//        4
//
//        输出例子1:
//        1
//
//        输入例子2:
//        10
//
//        输出例子2:
//        2
//用例:
//        622654
//
//        对应输出应该为:
//
//        124531
//
//        你的输出为:
//
//        124534
public class Main14 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        if (num<=5){
            System.out.println(1);
        }else {
            System.out.println(num%5==0?num/5:num/5+1);
        }
    }
}