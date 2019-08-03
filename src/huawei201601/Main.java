package huawei201601;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
//老师想知道从某某同学当中，分数最高的是多少，现在请你编程模拟老师的询问。当然，老师有时候需要更新某位同学的成绩.
//
//        输入描述:
//        输入包括多组测试数据。
//        每组输入第一行是两个正整数N和M（0 < N <= 30000,0 < M < 5000）,分别代表学生的数目和操作的数目。
//        学生ID编号从1编到N。
//        第二行包含N个整数，代表这N个学生的初始成绩，其中第i个数代表ID为i的学生的成绩
//        接下来又M行，每一行有一个字符C（只取‘Q’或‘U’），和两个正整数A,B,当C为'Q'的时候, 表示这是一条询问操作，他询问ID从A到B（包括A,B）的学生当中，成绩最高的是多少
//        当C为‘U’的时候，表示这是一条更新操作，要求把ID为A的学生的成绩更改为B。
//
//        输出描述:
//        对于每一次询问操作，在一行里面输出最高成绩.
//
//        输入例子1:
//        5 7
//        1 2 3 4 5
//        Q 1 5
//        U 3 6
//        Q 3 4
//        Q 4 5
//        U 4 5
//        U 2 9
//        Q 1 5
//
//        输出例子1:
//        5
//        6
//        5
//        9
public class Main {
    static class Node implements Comparable<Node>{
        public int index;
        public int score;

        public Node(int index, int score) {
            this.index = index;
            this.score = score;
        }

        @Override
        public int compareTo(Node o) {
            return o.score-this.score;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int stdNum = sc.nextInt();
            int opNum = sc.nextInt();
            List<Node> list = new ArrayList<>();
            for (int i = 0;i<stdNum;i++)
                list.add(new Node(i+1,sc.nextInt()));
            Collections.sort(list);
            for (int i =0;i<opNum;i++){
                String op = sc.next();
                if (op.equals("Q")){//查询
                    int mintemp = sc.nextInt();
                    int maxtemp = sc.nextInt();
                    int max = Math.max(maxtemp,mintemp);
                    int min = Math.min(maxtemp,mintemp);
                    for (Node node:list){
                        if (node.index >=min && node.index<=max){
                            System.out.println(node.score);
                            break;
                        }
                    }
                }else if (op.equals("U")){//更新
                    int id = sc.nextInt();
                    int score = sc.nextInt();
                    for (Node node:list){
                        if (node.index == id){
                            node.score = score;
                            break;
                        }
                    }
                    Collections.sort(list);
                }
            }
        }
    }
}

//        9 10
//        28 49 11 35 40 17 57 4 6
//        Q 9 9
//        U 9 79
//        Q 9 5
//        Q 4 8
//        U 2 27
//        U 8 40
//        U 4 77
//        U 7 71
//        U 4 44
//        U 8 51