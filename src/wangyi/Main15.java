package wangyi;

import java.util.Scanner;

//给定一个N*M的矩阵，在矩阵中每一块有一张牌，我们假定刚开始的时候所有牌的牌面向上。
//        现在对于每个块进行如下操作：
//        > 翻转某个块中的牌，并且与之相邻的其余八张牌也会被翻转。
//        XXX
//        XXX
//        XXX
//        如上矩阵所示，翻转中间那块时，这九块中的牌都会被翻转一次。
//        请输出在对矩阵中每一块进行如上操作以后，牌面向下的块的个数。
//
//        输入描述:
//        输入的第一行为测试用例数t(1 <= t <= 100000),
//        接下来t行，每行包含两个整数N,M(1 <= N, M <= 1,000,000,000)
//
//        输出描述:
//        对于每个用例输出包含一行，输出牌面向下的块的个数
//
//        输入例子1:
//        5
//        1 1
//        1 2
//        3 1
//        4 1
//        2 2
//
//        输出例子1:
//        1
//        0
//        1
//        2
//        0
public class Main15 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0;i<n;i++){
            long N = scanner.nextLong();
            long M = scanner.nextLong();
            long count = (N-2)*(M-2);
            if (count <= 2)
                System.out.println(Math.abs(count));
            else {
                System.out.println(getNum(String.valueOf(N-2),String.valueOf(M-2)));
            }
        }
    }
//835037940 435509194   363666700208820360
    public static String getNum(String x,String y){
        int[] count = new int[x.length()*y.length()];
        String num1 = new StringBuilder(x).reverse().toString();
        String num2 = new StringBuilder(y).reverse().toString();
        int index = 0;
        for (int i =0;i<num1.length();i++){
            for (int j = 0; j<num2.length();j++){
                int temp = (num2.charAt(j)-'0') * (num1.charAt(i)-'0');
                int sum = (count[index]+temp);
                count[index] = sum % 10;
                count[index+1] += sum /10;
                index++;
            }
            index = i+1;
        }
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        for (int i =count.length-1;i>=0;i--){
            if (count[i] !=0 && !flag){
                flag = true;
            }
            if (flag)
                sb.append(count[i]);

        }
        return sb.toString();
    }

}