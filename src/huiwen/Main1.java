package huiwen;

import java.util.Scanner;
//有一个数组a[N]顺序存放0~N-1，要求每隔两个数删掉一个数，
//        到末尾时循环至开头继续进行，
//        求最后一个被删掉的数的原始下标位置。
//        以8个数(N=7)为例:｛，，，，，6，｝，0->1->2(删除)->3->4->5(删除)->6->7->0(删除),如此循环直到最后一个数被删除。
//
//        输入描述:
//        每组数据为一行一个整数n(小于等于1000)，为数组成员数,如果大于1000，则对a[999]进行计算。
//
//        输出描述:
//        一行输出最后一个被删掉的数的原始下标位置。
//
//        输入例子1:
//        8
//
//        输出例子1:
//        6
public class Main1 {
    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNextLine()){
//            int n = Integer.valueOf(sc.nextLine());
//            System.out.println(ysfdg(n-1,2,n-1));
//        }
//        int ans = 0;
//        int a[] = new int [249];
//        for(int i=0;i<249;i++){
//            a[i] = ans++;
//        }

        System.out.println("最后留下的是:" + ysfdg(249,3,249) + "号");
    }

    private static int ysfdg(int sum,int value,int n)
    {
        if(n==1)
            return (sum+value-1)%sum;
        else
            return (ysfdg(sum-1,value,n-1)+value)%sum;
    }
}