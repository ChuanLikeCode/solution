package huiwen;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String input = sc.nextLine();
            String inputReverse = new StringBuilder(input).reverse().toString();
            int[][] dp = new int[input.length()][inputReverse.length()];
            dp[0][0] = input.charAt(0) == inputReverse.charAt(0)?1:0;
            for (int i = 1;i<inputReverse.length();i++)
                dp[i][0] = Math.max(dp[i-1][0],input.charAt(0)==inputReverse.charAt(i)?1:0);
            for (int i = 1;i<inputReverse.length();i++)
                dp[0][i] = Math.max(dp[0][i-1],input.charAt(i)==inputReverse.charAt(0)?1:0);
            for (int i = 1;i<inputReverse.length();i++){
                for (int j = 1;j<input.length();j++){
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                    if (input.charAt(j)==inputReverse.charAt(i))
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1]+1);
                }
            }

//            int[] count = new int[input.length()];
//            count[0] = input.charAt(0)==inputReverse.charAt(0)?1:0;
//            for (int i = 1;i< input.length();i++){
//                count[i] = Math.max(count[i-1],input.charAt(i)==inputReverse.charAt(0)?1:0);
//            }
//
//            for (int i = 1;i<inputReverse.length();i++){
//                count[0] = Math.max(count[0],inputReverse.charAt(i)==input.charAt(0)?1:0);
//                for (int j = 1;j<input.length();j++){
//                    count[j] = Math.max(count[j-1],count[j]);
//                    if (inputReverse.charAt(i)==input.charAt(j)){
//                        count[j] = Math.max(count[j-1]+1,count[j]);
//                    }
//                }
//            }
            for (int i = 0;i<inputReverse.length();i++){
                for (int j = 0;j<input.length();j++) {
                    System.out.print(dp[i][j]+" ");
                }
                System.out.println();
            }
//            System.out.println(input.length()-dp[input.length()-1][input.length()-1]);
        }
    }
}