package wangyi;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//小易很喜欢斑马，因为它们身上黑白相间的花纹。
//        一天小易得到了一串橡皮泥，这串橡皮泥只有黑色和白色，
// 小易想把这串橡皮泥重新拼凑一下，让这个橡皮泥串中最长的连续的黑白相间的子串最长，但是小易有强迫症，所以他可以对橡皮泥串进行以下的操作0次或多次：
//        把橡皮泥串从某个地方切割开，将两个得到的两个串同时翻转，再拼接在一起。
//        这个橡皮泥串可能太长了，所以小易没有办法计算最终可以得到的最长的连续的黑白相间的子串的长度，希望你能帮他计算出这个长度。
//
//        输入描述:
//        一个字符串s，只包含字母'b'和字母'w'，分别表示黑色和白色的橡皮泥块。
//        满足1 <= |s| <= 105，|s|代表字符串的长度。
//
//        输出描述:
//        一个整数，表示改变之后最长的连续的黑白相间的子串的长度。
//
//        输入例子1:
//        bwbwb
//
//        输出例子1:
//        5
//
//        输入例子2:
//        wwb
//
//        输出例子2:
//        3
public class Main19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int ans = 0,temp = 1;
        for (int i = 1;i<input.length();i++){
            if (input.charAt(i)==input.charAt(i-1)){
                ans = Math.max(ans,temp);
                temp =1;
            }else {
                temp+=1;
            }
        }
        ans = Math.max(ans,temp);
        System.out.println(ans);

//        for i in range(1, len(s)):
//        if s[i] == s[i - 1]:
//        ans = max(ans, tmp)
//        tmp = 1
//        else:
//        tmp += 1
//        ans = max(ans, tmp)
//        print(ans)
    }
}