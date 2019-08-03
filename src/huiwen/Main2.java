package huiwen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//输入一个字符串，求出该字符串包含的字符集合
//
//        输入描述:
//        每组数据输入一个字符串，字符串最大长度为100，且只包含字母，不可能为空串，区分大小写。
//
//        输出描述:
//        每组数据一行，按字符串原有的字符顺序，输出字符集合，即重复出现并靠后的字母不输出。
//
//        输入例子1:
//        abcqweracb
//dBowNgxyPZXZXlBTMKFskExQEK
//        输出例子1:
//        abcqwer
//dBowNgxyPZXlTMKFskEQ
public class Main2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
//        String input = "SoriEYVEzZDtnWWWoCEFAKUFYEOENgPaNplIPybEQrHmzKMPN";
        List<Character> list = new ArrayList<>();
        List<Character> listbig = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<input.length();i++){
            char cha = input.charAt(i);
            if (cha >='a'){//小写
                if (!list.contains(cha)){
                    sb.append(input.charAt(i));
                    list.add(cha);
                }
            }else {//大写
                if (!listbig.contains(cha)){
                    sb.append(input.charAt(i));
                    listbig.add(cha);
                }
            }

        }
        System.out.println(sb.toString());
    }

}