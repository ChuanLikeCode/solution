package huiwen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//数独是一个我们都非常熟悉的经典游戏，运用计算机我们可以很快地解开数独难题，现在有一些简单的数独题目，请编写一个程序求解。
//
//        输入描述:
//        输入9行，每行为空格隔开的9个数字，为0的地方就是需要填充的。
//
//        输出描述:
//        输出九行，每行九个空格隔开的数字，为解出的答案。

public class Main3 {
    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        int[][] nums = new int[9][9];
        int[][] nums = new int[][]{{7,2,6,9,0,4,0,5,1},
                {0,8,0,6,0,7,4,3,2},
                {3,4,1,0,8,5,0,0,9},
                {0,5,2,4,6,8,0,0,7},
                {0,3,7,0,0,0,6,8,0},
                {0,9,0,0,0,3,0,1,0},
                {0,0,0,0,0,0,0,0,0},
                {9,0,0,0,2,1,5,0,0},
                {8,0,0,3,0,0,0,0,0}};
        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        boolean[][][] boxUsed = new boolean[3][3][10];
        for (int i = 0;i<9;i++){
            for (int j = 0;j<9;j++){
//                int temp = sc.nextInt();
                int temp = nums[i][j];
//                nums[i][j] = temp;
                rowUsed[i][temp] = true;
                colUsed[j][temp] = true;
                boxUsed[i/3][j/3][temp] =true;
            }
        }
        setNums(rowUsed,colUsed,boxUsed,nums,0,0);
        for (int i = 0;i<9;i++){
            for (int j = 0;j<9;j++) {
                System.out.print(nums[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static boolean setNums(boolean[][] rowUsed, boolean[][] colUsed, boolean[][][] boxUsed, int[][] nums,int row,int col) {
        if (col == nums[0].length){
            col = 0;
            row++;
            if (row == nums.length){
                return true;
            }
        }
        if (nums[row][col] == 0){
            for (int i = 1;i<10;i++){
                boolean canUsed = !(rowUsed[row][i]||colUsed[col][i]||boxUsed[row/3][col/3][i]);
                if (canUsed){
                    rowUsed[row][i] = true;
                    colUsed[col][i] = true;
                    boxUsed[row/3][col/3][i] = true;
                    nums[row][col] = i;
                    if (setNums(rowUsed,colUsed,boxUsed,nums,row,col+1)){
                        return true;
                    }
                    rowUsed[row][i] = false;
                    colUsed[col][i] = false;
                    boxUsed[row/3][col/3][i] = false;
                    nums[row][col] = 0;
                }
            }
        }else {
            return setNums(rowUsed,colUsed,boxUsed,nums,row,col+1);
        }
        return false;
    }
}