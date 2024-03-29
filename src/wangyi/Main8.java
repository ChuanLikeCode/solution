package wangyi;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.*;

//牛牛去犇犇老师家补课，出门的时候面向北方，但是现在他迷路了。虽然他手里有一张地图，但是他需要知道自己面向哪个方向，请你帮帮他。
//
//        输入描述:
//        每个输入包含一个测试用例。
//        每个测试用例的第一行包含一个正整数，表示转方向的次数N(N<=1000)。
//        接下来的一行包含一个长度为N的字符串，由L和R组成，L表示向左转，R表示向右转。
//
//        输出描述:
//        输出牛牛最后面向的方向，N表示北，S表示南，E表示东，W表示西。
//
//        输入例子1:
//        3
//        LRR
//
//        输出例子1:
//        E

public class Main8 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String input = scanner.next();
        Map<Character,Integer> map = new HashMap<>();
        map.put('L',-1);map.put('R',1);
        List<Character> list = new ArrayList<>();
        list.add('N');
        list.add('E');
        list.add('S');
        list.add('W');
        int sum = 0;
        for (int i = 0;i<n;i++){
            sum+=map.get(input.charAt(i));
        }
        System.out.println(list.get((sum+1000)%4));
    }

}