package wangyi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//牛牛总是睡过头，所以他定了很多闹钟，只有在闹钟响的时候他才会醒过来并且决定起不起床。
// 从他起床算起他需要X分钟到达教室，上课时间为当天的A时B分，请问他最晚可以什么时间起床
//
//        输入描述:
//        每个输入包含一个测试用例。
//        每个测试用例的第一行包含一个正整数，表示闹钟的数量N(N<=100)。
//        接下来的N行每行包含两个整数，表示这个闹钟响起的时间为Hi(0<=A<24)时Mi(0<=B<60)分。
//        接下来的一行包含一个整数，表示从起床算起他需要X(0<=X<=100)分钟到达教室。
//        接下来的一行包含两个整数，表示上课时间为A(0<=A<24)时B(0<=B<60)分。
//        数据保证至少有一个闹钟可以让牛牛及时到达教室。
//
//        输出描述:
//        输出两个整数表示牛牛最晚起床时间。
//
//        输入例子1:
//        3
//        5 0
//        6 0
//        7 0
//        59
//        6 59
//
//        输出例子1:
//        6 0

public class Main11 {

    public static void main(String[] args){
        Scanner scanner= new Scanner(System.in);
        int n = scanner.nextInt();
        int[] hourmin = new int[n];
        for (int i=0;i<n;i++){
            int hour = scanner.nextInt();
            int min = scanner.nextInt();
            hourmin[i] = hour *60 +min;
        }
        int pay = scanner.nextInt();
        int delayhour = scanner.nextInt();
        int delaymin =scanner.nextInt();
        int take = delayhour*60+delaymin-pay;
        int max = 0;
        for (int i = 0;i<n;i++){
            if (hourmin[i] <= take && max < hourmin[i]){
                max = hourmin[i];
            }
        }
        System.out.println(max/60+" "+max%60);
    }


}