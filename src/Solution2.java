import java.util.*;

public class Solution2 {
    public static void main(String[] args){
        System.out.println(removeDuplicates(new int[]{1,1,1,2,2,3}));
    }

    /**
     * 80. 删除排序数组中的重复项 II
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * 示例 1:
     *
     * 给定 nums = [1,1,1,2,2,3],
     *
     * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        int start = 1;
        int end = 1;
        int temp = nums[0];
        int count = 0;
        while (end<nums.length){
            if (nums[end]!=temp){
                nums[start] = nums[end];
                temp = nums[end];
                count = 0;

            }else {
                count++;
                if (count==2){
                    start = end;
                }
            }
            end++;
        }
        return start;
    }


    /**
     * 442. 数组中重复的数据
     * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
     *
     * 找到所有出现两次的元素。
     *
     * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
     *
     * 示例：
     *
     * 输入:
     * [4,3,2,7,8,2,3,1]
     *
     * 输出:
     * [2,3]
     * @param nums
     * @return
     */
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i:nums){
            if (nums[Math.abs(i)-1]<0){
                list.add(Math.abs(i));
            }
            nums[Math.abs(i)-1] = -Math.abs(nums[Math.abs(i)-1]);
        }
        return list;
    }

    /**
     * 448. 找到所有数组中消失的数字
     * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
     *
     * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     *
     * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
     *
     * 示例:
     *
     * 输入:
     * [4,3,2,7,8,2,3,1]
     *
     * 输出:
     * [5,6]
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        /**
         * 笔记】将所有正数作为数组下标，置对应数组值为负值。那么，仍为正数的位置即为（未出现过）消失的数字。
         *
         * 举个例子：
         *
         * 原始数组：[4,3,2,7,8,2,3,1]
         *
         * 重置后为：[-4,-3,-2,-7,8,2,-3,-1]
         *
         * 结论：[8,2] 分别对应的index为[5,6]（消失的数字）
         */
        List<Integer> list = new ArrayList<>();
        for (int i:nums){
            nums[Math.abs(i)-1] = -Math.abs(nums[Math.abs(i)-1]);
        }
        for (int i = 0;i<nums.length;i++){
            if (nums[i]>0)
                list.add(i+1);
        }
        return list;
       /* List<Integer> list = new ArrayList<>();
        int index = 0;
        while (index<nums.length){
            if (nums[index]!=index+1){
                if (nums[index]!=-1&&nums[nums[index]-1]!=nums[index]){
                    int temp = nums[index];
                    nums[index] = nums[nums[index]-1];
                    nums[temp-1] = temp;
                }else {
                    nums[index] = -1;
                    index++;
                }
            }else
                index++;
        }
        for (int i = 0;i<nums.length;i++){
            if (nums[i]==-1)
                list.add(i+1);
        }
        return list;*/
    }

    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        //方法一
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int res = 0;
        while (end < s.length()){
            char ch = s.charAt(end);
            map.put(ch,map.getOrDefault(ch,0)+1);
            end++;
            while (map.get(ch)>1){
                char c = s.charAt(start);
                map.put(c,map.get(c)-1);
                start++;
            }
            res = Math.max(res,end-start);
        }
        return res;

        //方法二
        /*int n = s.length() ,ans = 0;
        Map<Character,Integer> map = new HashMap<>();
        for(int start = 0,end = 0;end<n;end++){
            char a = s.charAt(end);
            if(map.containsKey(a)){
                start = Math.max(map.get(a),start);
            }
            ans = Math.max(ans,end-start+1);
            map.put(a,end+1);
        }
        return ans;*/
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     *
     * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     *
     * 说明：
     *
     * 字母异位词指字母相同，但排列不同的字符串。
     * 不考虑答案输出的顺序。
     * 示例 1:
     *
     * 输入:
     * s: "cbaebabacd" p: "abc"
     *
     * 输出:
     * [0, 6]
     *
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
     *  示例 2:
     *
     * 输入:
     * s: "abab" p: "ab"
     *
     * 输出:
     * [0, 1, 2]
     *
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length()==0) return res;
        Map<Character,Integer> needMap = new HashMap<>();
        int total = p.toCharArray().length;
        for (char ch:p.toCharArray()){
            if (needMap.containsKey(ch)){
                needMap.put(ch,needMap.get(ch)+1);
            }else
                needMap.put(ch,1);
        }
        Map<Character,Integer> windowMap = new HashMap<>();
        int start = 0;
        int end = 0;
        int match = 0;
        while (end < s.length()){
            char ch = s.charAt(end);
            if (needMap.containsKey(ch)){
                windowMap.put(ch,windowMap.getOrDefault(ch,0)+1);
                if (windowMap.get(ch).equals(needMap.get(ch)))
                    match++;
            }
            end++;
            while (match==needMap.size()){
                if (end-start==total){
                    res.add(start);
                }
                char c  = s.charAt(start);
                if (needMap.containsKey(c)){
                    windowMap.put(c,windowMap.get(c)-1);
                    if (windowMap.get(c)<needMap.get(c))
                        match--;
                }
                start++;
            }
        }
        return res;
    }
}
