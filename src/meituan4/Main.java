package meituan4;

import java.util.Scanner;
import java.util.Stack;

/*给出两个字符串（可能包含空格）,找出其中最长的公共连续子串,输出其长度。

        输入描述:
        输入为两行字符串（可能包含空格），长度均小于等于50.

        输出描述:
        输出为一个整数，表示最长公共连续子串的长度。

        输入例子1:
        abcde
        abgde

        输出例子1:
        2*/
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String str1 = scanner.nextLine();
            String str2 = scanner.nextLine();
            int row = 0;
            int col = str1.length()-1;
            int max = 0;
            while (row < str2.length()){
                int i = row;
                int j = col;
                int len = 0;
                while (i<str2.length() && j <str1.length()){
                    if (str1.charAt(j) != str2.charAt(i)){
                        len = 0;
                    }else {
                        len++;
                    }
                    max = Math.max(len,max);
                    i++;
                    j++;
                }
                if (col > 0)
                    col--;
                else
                    row++;
            }
            System.out.println(max);




//            int[][] dp = new int[str1.length()][str2.length()];
//            for (int i = 0;i<str1.length();i++){
//                dp[i][0] = str1.charAt(i) == str2.charAt(0)?1:0;
//            }
//            for (int i = 1;i<str2.length();i++){
//                dp[0][i] = str1.charAt(0) == str2.charAt(i)?1:0;
//            }
//            int max = dp[0][0];
//            for (int i = 1;i<str1.length();i++)
//                for (int j = 1;j<str2.length();j++){
//                    if (str1.charAt(i) == str2.charAt(j)){
//                        dp[i][j] = dp[i-1][j-1]+1;
//                    }
//                    max = Math.max(dp[i][j],max);
//                }
//            System.out.println(max);
        }
    }
}