package zjtd1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
//有n个房间，现在i号房间里的人需要被重新分配，分配的规则是这样的：
// 先让i号房间里的人全都出来，接下来按照 i+1, i+2, i+3, ...
// 的顺序依此往这些房间里放一个人，n号房间的的下一个房间是1号房间，直到所有的人都被重新分配。
//
//        现在告诉你分配完后每个房间的人数以及最后一个人被分配的房间号x，
// 你需要求出分配前每个房间的人数。数据保证一定有解，若有多解输出任意一个解。
//
//
//        输入描述:
//        第一行两个整数n, x (2<=n<=10^5, 1<=x<=n)，代表房间房间数量以及最后一个人被分配的房间号；
//        第二行n个整数 a_i(0<=a_i<=10^9) ，代表每个房间分配后的人数。
//
//        输出描述:
//        输出n个整数，代表每个房间分配前的人数。
//
//        输入例子1:
//        3 1
//        6 5 1
//
//        输出例子1:
//        4 4 4
public class Main1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int last = scanner.nextInt()-1;
        int[] room = new int[n];
        int index = n-last-1;
        int count = 0;

        for (int i = n-1;i>=0;i--){
            int temp = scanner.nextInt();
            if (i<index)
                room[i] = temp;
            else{
                room[i] = temp-1;
                count++;
            }

        }
        index = 0;
        while (true){
            room[index%n] -= 1;
            if (room[index%n]==-1)
                break;
            count++;
            index++;
        }
        room[index%n] = count;
        for (int i = n-1;i>=0;i--){
            System.out.print(room[i]+" ");
        }
    }
}