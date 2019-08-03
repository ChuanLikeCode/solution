package wangyi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//小Q得到一个神奇的数列: 1, 12, 123,...12345678910,1234567891011...。
//
//        并且小Q对于能否被3整除这个性质很感兴趣。
//
//        小Q现在希望你能帮他计算一下从数列的第l个到第r个(包含端点)有多少个数可以被3整除。
//
//
//        输入描述:
//        输入包括两个整数l和r(1 <= l <= r <= 1e9), 表示要求解的区间两端。
//
//        输出描述:
//        输出一个整数, 表示区间内能被3整除的数字个数。
//
//        输入例子1:
//        2 5
//
//        输出例子1:
//        3
//
//        例子说明1:
//        12, 123, 1234, 12345...
//        其中12, 123, 12345能被3整除。
public class Main5 {
    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        int start = scanner.nextInt();
//        int end = scanner.nextInt();
        int start = 10;
        int end = 11000000;
        int sum = 0;
        if (start%3==2){
            sum += 2;
            start +=2;
        } else if (start%3==0) {
            sum+= 1;
            start+=1;
        }
        if (end%3==1){
            end--;
        }else if (end%3==2){
            sum+=1;
            end-=2;
        }
        System.out.println(sum+(end-start+1)/3*2);
    }

}