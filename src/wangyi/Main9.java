package wangyi;

import java.util.*;

//牛牛以前在老师那里得到了一个正整数数对(x, y), 牛牛忘记他们具体是多少了。
//
//        但是牛牛记得老师告诉过他x和y均不大于n, 并且x除以y的余数大于等于k。
//        牛牛希望你能帮他计算一共有多少个可能的数对。
//
//
//        输入描述:
//        输入包括两个正整数n,k(1 <= n <= 10^5, 0 <= k <= n - 1)。
//
//        输出描述:
//        对于每个测试用例, 输出一个正整数表示可能的数对数量。
//
//        输入例子1:
//        5 2
//
//        输出例子1:
//        7
//
//        例子说明1:
//        满足条件的数对有(2,3),(2,4),(2,5),(3,4),(3,5),(4,5),(5,3)

public class Main9 {

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long sum = 0;
        int t = 0;
        int tt = 0;
        for(int i=k+1;i<=n;i++){
            t++;
            tt = (n%i - k + 1) >0 ? (n%i - k + 1):0;
            sum+=n/i*t+tt;
        }
        if(k == 0) sum-=n;// k=0 特殊情况  多计算了n次
        System.out.print(sum);
    }


}