package didi1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            int count = Integer.parseInt(sc.nextLine());
            String[] str = sc.nextLine().split(" ");
            int[] input = new int[count];
            for (int i = 0;i<count;i++){
                input[i] = Integer.parseInt(str[i]);
            }
            int res = input[0];
            int max = input[0];
            for (int i = 1;i< count;i++){
                max = (max+input[i]) > input[i] ? max+input[i] : input[i];
                res = max>res ? max : res;
            }
            System.out.println(res);
        }
    }
}