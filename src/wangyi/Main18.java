package wangyi;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//随着又一届学生的毕业，社团主席换届选举即将进行。
//
//      一共有n个投票者和m个候选人，小易知道每一个投票者的投票对象。
// 但是，如果小易给某个投票者一些糖果，那么这个投票者就会改变他的意向，小易让他投给谁，他就会投给谁。
//
//      由于小易特别看好这些候选人中的某一个大神，这个人的编号是1，
// 所以小易希望能尽自己的微薄之力让他当选主席，但是小易的糖果数量有限，
// 所以请你帮他计算，最少需要花多少糖果让1号候选人当选。某个候选人可以当选的条件是他获得的票数比其他任何候选者都多。
//
//
//        输入描述:
//        第一行两个整数n和m，表示投票者的个数和候选人的个数。
//        接下来n行，每一行两个整数x和y，x表示这个投票者的投票对象，y表示需要花多少个糖果让这个人改变意向。
//        满足1 <= n, m <= 3000，1 <= x <= m，1 <= y <= 109。
//
//        输出描述:
//        一个整数，糖果的最小花费。
//
//        输入例子1:
//        1 2
//        1 20
//
//        输出例子1:
//        0
//
//        输入例子2:
//        5 5
//        2 5
//        3 5
//        4 5
//        5 6
//        5 1
//
//        输出例子2:
//        6
//AC 90
public class Main18 {
    public static void main(String[] args) throws IOException {
        /*FileReader reader = new FileReader("C:\\Users\\user\\IdeaProjects\\solution\\src\\wangyi\\test.txt");
        char[] buf = new char[4000];
        int num=reader.read(buf);
        String[] input = new String(buf,0,num).split(" ");
        int n = Integer.parseInt(input[0]);//投票者的个数
        int m = Integer.parseInt(input[1]);//候选人的个数。
        int[][] voter = new int[n][2];
        int[] person = new int[m+1];
        for (int i=2;i<input.length-2;i += 2 ){
            voter[i/2][0] = Integer.parseInt(input[i]);//候选人号
            voter[i/2][1] = Integer.parseInt(input[i+1]);//票数
            person[voter[i/2][0]] += 1;
        }
        Arrays.sort(voter, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        int candy = 0;
        int[] res = getMax(person);
        if (res[0]==1){
            System.out.println(candy);
        }else {
            int index = 0;
            while (res[0] != 1){
                int tempCandy = voter[index][1];
                int tempNum = voter[index][0];
                candy += tempCandy;
                person[tempNum] -= 1;
                person[1]  += 1;
                res = getMax(person);
                index++;
            }
            System.out.println(candy);
        }*/


        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//投票者的个数
        int m = scanner.nextInt();//候选人的个数。
        int[][] voter = new int[n][2];
        int[] person = new int[m+1];
        for (int i=0;i<n;i++){
            voter[i][0] = scanner.nextInt();//候选人号
            voter[i][1] = scanner.nextInt();//票数
            person[voter[i][0]] += 1;
        }
        Arrays.sort(voter, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        int candy = 0;
        int[] res = getMax(person);
        if (res[0]==1){
            System.out.println(candy);
        }else {
            int index = 0;
            while (res[0] != 1){
                int tempCandy = voter[index][1];
                int tempNum = voter[index][0];
                candy += tempCandy;
                person[tempNum] -= 1;
                person[1]  += 1;
                res = getMax(person);
                index++;
            }
            System.out.println(candy);
        }

    }

    public static int[] getMax(int[] person){
        int max = 0;
        int index = 0;
        for (int i = 1;i<person.length;i++){
            if (person[i] >= max){
                max = person[i];
                index = i;
            }
        }
        return new int[]{index,max};
    }
}