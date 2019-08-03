package factory;

import factory.simpleFactory.Car;
import factory.simpleFactory.SimpleFactory;
import org.omg.PortableInterceptor.INACTIVE;
import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    static class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public static void main(String[] args){
//        int[] nums = new int[]{9,8,5,1,6,4,7,2,3,10,25,11,27,68,35,29};
//        quick(nums,0,nums.length-1);
//        heapSort(nums);
//        for (int a:nums){
//            System.out.print(a+" ");
//        }
//        System.out.println(largestRectangleArea(new int[]{2,1,5,6,2,3}));



    }




    public static int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        dp[0][0] = 0;
        for (int i = 1; i < word2.length()+1; i++) {
            dp[0][i] = dp[0][i - 1] + 1;
        }
        for (int i = 1; i < word1.length()+1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int i = 1; i < word1.length(); i++) {
            for (int j = 1; j < word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[n1][n2];
    }


    public static String convert(String s, int numRows) {
        String[] str=new String[4];
        for (int i=0;i<numRows;i++){
            str[i]="";
        }
        int row=0;
        boolean flag = false;
        for (int i = 0;i<s.length();i++){
            str[row] += s.charAt(i);
            if (row==0||row==numRows-1){
                flag = !flag;
            }
            row += flag?1:-1;
        }
        StringBuilder ans = new StringBuilder();
        for (int i=0;i<numRows;i++){
            ans.append(str[i]);
        }
        return ans.toString();
    }


    /**
     * 堆排序
     * 父节点为i
     * 左子节点为(2*i+1)  右子节点为  2*(i+1)
     *
     * 子节点为i 父节点为 (i-1)/2
     * @param nums
     */
    private static void heapSort(int[] nums){
        for (int i = nums.length-1;i>=0;i--){
            heapBig(nums,i);
        }
    }
    private static void heapBig(int[] nums,int end){
        //初始化堆--> 大顶堆
        int index = 0;
        int max = nums[0];
        while ((2*index+1) <= end){
            if (2*index+1<=end&&nums[index] < nums[2*index+1]){
                int temp = nums[index];
                nums[index] = nums[2*index+1];
                nums[2*index+1] = temp;
            }
            if (2*(index+1)<=end&&nums[index] < nums[2*(index+1)]){
                int temp = nums[index];
                nums[index] = nums[2*(index+1)];
                nums[2*(index+1)] = temp;
            }
            max = Math.max(nums[index],max);
            index++;
        }
        if (max !=nums[0]){
            heapBig(nums,end);
        }else {
            int temp = nums[0];
            nums[0] = nums[end];
            nums[end] = temp;
        }
    }

    private static void heapSmall(int[] nums,int end){
        //初始化堆--> 小顶堆
        int index = 0;
        int min = nums[0];
        while ((2*index+1) <= end){
            if (2*index+1<=end&&nums[index] > nums[2*index+1]){
                int temp = nums[index];
                nums[index] = nums[2*index+1];
                nums[2*index+1] = temp;
            }
            if (2*(index+1)<=end&&nums[index] > nums[2*(index+1)]){
                int temp = nums[index];
                nums[index] = nums[2*(index+1)];
                nums[2*(index+1)] = temp;
            }
            min = Math.min(nums[index],min);
            index++;
        }
        if (min !=nums[0]){
            heapSmall(nums,end);
        }else {
            int temp = nums[0];
            nums[0] = nums[end];
            nums[end] = temp;
        }
    }


    /**
     * 快速排序
     * @param nums
     * @param start
     * @param end
     */
    private static void quick(int[] nums,int start,int end){
        if (start<end){
            int mid = quickSort(nums,start,end);
            quick(nums,start,mid-1);
            quick(nums,mid+1,end);
        }
    }

    private static int quickSort(int[] nums,int start,int end){
        int val = nums[start];
        while (start<end){
            while (start<end && nums[end]>=val)
                end--;
            nums[start] = nums[end];
            while (start<end && nums[start]<=val)
                start++;
            nums[end] = nums[start];
        }
        nums[start] = val;
        return start;
    }





    /**
     * 归并排序
     * @param nums
     */
    private static void guibingsort(int[] nums){
        gbsort(nums,0,nums.length-1);
    }

    private static void gbsort(int[] nums,int start,int end){
        if (start < end){
            int mid = (start+end)/2;
            gbsort(nums,start,mid);
            gbsort(nums,mid+1,end);
            merge(nums,start,mid,end);
        }
    }

    private static void merge(int[] nums,int left,int mid,int right){
        int[] temp = new int[right-left+1];
        int index = 0;
        int leftstart = left;
        int rightstart = mid+1;
        while (leftstart<= mid &&rightstart<= right){
            if (nums[leftstart] <= nums[rightstart]){
                temp[index] = nums[leftstart];
                leftstart++;
                index++;
            }else {
                temp[index] = nums[rightstart];
                rightstart++;
                index++;
            }
        }
        while (leftstart<= mid){
            temp[index] = nums[leftstart];
            leftstart++;
            index++;
        }
        while (rightstart<=right){
            temp[index] = nums[rightstart];
            rightstart++;
            index++;
        }
        for (int i = 0;i<temp.length;i++){
            nums[left] = temp[i];
            left++;
        }
    }


    /**
     * 插入排序
     * @param nums
     */
    private static void insertSort(int[] nums){
        for (int i = 1;i<nums.length;i++){
            int index = i;
            for (int j=i-1;j>=0;j--){
                if (nums[index]<nums[j]){
                    int temp = nums[index];
                    nums[index] = nums[j];
                    nums[j] = temp;
                    index--;
                }
            }
        }
    }


    /**
     * 冒泡排序
     * @param nums
     */
    private static void maopao(int[] nums){
        for (int i = 0;i<nums.length-1;i++){
            for (int j = 0;j<nums.length-1;j++){
                if (nums[j]>nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
    }



    /**
     * 选择排序
     * @param nums
     */
    private static void selectSort(int[] nums){
        for(int i=0;i<nums.length-1;i++){
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int j = i;j<nums.length;j++){
                if (nums[j] < min){
                    min = nums[j];
                    index=j;
                }
            }
            nums[index] = nums[i];
            nums[i] = min;
        }
    }






    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return generateTree(preorder, inorder, 0, preorder.length, 0);
    }

    private static TreeNode generateTree(int[] preorder, int[] inorder, int head, int len, int first) {
        TreeNode node = new TreeNode(preorder[head]);
        if (len > 1) {
            int index = first + len - 1;
            for (; index>=first; index--) {
                if (inorder[index] == preorder[head]) {
                    break;
                }
            }
            if (index != first && index -first > 0) {
                node.left = generateTree(preorder, inorder, head +1, index -first, first);
            }
            if (index != first + len) {
                if (len - index + first -1 > 0) {
                    node.right = generateTree(preorder, inorder, head + index - first + 1, len - index + first -1, index + 1);
                }
            }
        }
        return node;
    }





    /**
     * 可重复组合
     * @param result
     * @param list

     * @param nums
     */
    public static void noRepeatCombine(List<List<Integer>> result,List<Integer> list,int[] nums){
        if (list.size() == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }
//        if (list.size()==nums.length) return;
        for (int i = 0;i<nums.length;i++){
            if (!list.contains(nums[i])){
                list.add(nums[i]);
                noRepeatCombine(result,list,nums);
                list.remove(list.size()-1);
            }

        }
    }

    /**
     * 可重复组合
     * @param result
     * @param list

     * @param nums
     */
    public static void repeatCombine(List<List<Integer>> result,List<Integer> list,int[] nums){
        if (list.size() == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }
//        if (list.size()==nums.length) return;
        for (int i = 0;i<nums.length;i++){
            list.add(nums[i]);
            repeatCombine(result,list,nums);
            list.remove(list.size()-1);

        }
    }
    /**
     * 子集
     * @param result
     * @param list
     * @param index
     * @param nums
     */
    public static void combine(List<List<Integer>> result,List<Integer> list,int index,int[] nums){
        if (list.size() > 0&&list.size() <= nums.length){
            result.add(new ArrayList<>(list));
        }
        if (index >= nums.length) return;
        for (int i = index;i<nums.length;i++){
            list.add(nums[i]);
            combine(result,list,i+1,nums);
            list.remove(list.size()-1);
        }
    }
}
