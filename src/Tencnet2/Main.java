package Tencnet2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            StringBuilder sb = new StringBuilder(input);
            int index = input.length();//控制次数
            int j = 0;
            int temp;
            while ( index >0 ){
                temp = j;
                char c = sb.charAt(j);
                if (c>='A'&&c<='Z'){
                    sb.deleteCharAt(j);
                    j=temp;
                    sb.append(c);
                }else {
                    j++;
                }

                index--;
            }
            System.out.println(sb.toString());
        }
    }
}