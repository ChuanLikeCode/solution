package pingduoduo1;

import javax.swing.plaf.synth.SynthUI;
import java.util.Scanner;

/*有两个用字符串表示的非常大的大整数,算出他们的乘积，也是用字符串表示。不能用系统自带的大整数类型。

输入描述:
空格分隔的两个字符串，代表输入的两个大整数

输出描述:
输入的乘积，用字符串表示

输入例子1:
72106547548473106236 982161082972751393

输出例子1:
70820244829634538040848656466105986748*/
//              8040848656466105986748
public class Main {

    public static void main(String[] args){
//        System.out.println(Math.pow(2017,100));
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String[] input = sc.nextLine().split(" ");
            int[] count = new int[input[0].length()*input[1].length()+1];
            String num1 = new StringBuilder(input[0]).reverse().toString();
            String num2 = new StringBuilder(input[1]).reverse().toString();
            int index = 0;
            for (int i =0;i<num1.length();i++){
                for (int j = 0; j<num2.length();j++){
                    int temp = (num2.charAt(j)-'0') * (num1.charAt(i)-'0');
                    int sum = (count[index]+temp);
                    count[index] = sum % 10;
                    count[index+1] += sum /10;
                    index++;
                }
                index = i+1;
            }
            boolean flag = false;
            StringBuilder sb = new StringBuilder();
            for (int i =count.length-1;i>=0;i--){
                if (count[i] !=0 && !flag){
                    flag = true;
                }
                if (flag)
                    sb.append(count[i]);

            }
            System.out.println(sb.toString());
        }
    }
}