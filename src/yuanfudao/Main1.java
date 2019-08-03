package yuanfudao;

import java.util.Scanner;
//猿辅导公司的 N位（N>=4）研发同学组织了一次秋游活动，
// 某同学带了个无人机在高空拍照，活动结束时，
// 先拍了一张所有同学排成公司猴头Logo的照片， 接着有人提议再排成“猿”的首字母Y字形来拍一张合照。
//
// 用字符串中的每一个字符（不是换行符或结束符'\0'）代表一位老师，
// 输出排好后的队形。要求 Y字除去中心点外，上下半部分等高，按照从左到右，
// 从上到下进行排序。队形中没人的部分用空格占位。
//        输入数据保证可以排出一个完整的Y字，即长度为 3k+1 （k>=1）
//
//        例如: 7个 x ，排成队形为（为了方便说明，这里用‘-’代替空格）：
//        x---x
//        -x-x
//        --x
//        --x
//        --x

//输入描述:
//        输入数据有两行，第一行输入N(N<=1000)，表示字符串长度。
//        第二行输入字符串。
//
//        输出描述:
//        用字符串表示的排好的队形，没人处用空格（' '）占位，行尾不能有多余字符，即每行最后一个字符（除了换行符以外），为字符串中代表该老师的字符。
//
//        输入例子1:
//        4
//        a3f/
//
//        输出例子1:
//        a 3
//        f
//        /
//
//        输入例子2:
//        7
//        abcdefg
//
//        输出例子2:
//        a   b
//        c d
//        e
//        f
//        g
//
//        输入例子3:
//        10
//        iiiiiiiiii
//
//        输出例子3:
//        i     i
//        i   i
//        i i
//        i
//        i
//        i
//        i
public class Main1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();
        int height = (n-1)/3;
        int totalHeight = n-height;
        int count = 0;
        int index = 0;
        for (int i = 0;i<totalHeight;i++){
            if (i < height){
                count = i;
                while (count!=0){
                    System.out.print(" ");
                    count--;
                }
                System.out.print(input.charAt(index));
                index++;
                int tempSpace = (height-i)*2-1;
                while (tempSpace !=0){
                    System.out.print(" ");
                    tempSpace--;
                }
                System.out.print(input.charAt(index));
                index++;
                System.out.println();
            }else {
                count = height;
                while (count!=0){
                    System.out.print(" ");
                    count--;
                }
                System.out.print(input.charAt(index));
                index++;
                System.out.println();
            }
        }
    }
}
