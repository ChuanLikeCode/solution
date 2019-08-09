import java.util.*;

public class Solution2 {

    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }




    /**
     * 406. 根据身高重建队列
     * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
     *
     * 注意：
     * 总人数少于1100人。
     *
     * 示例
     *
     * 输入:
     * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     *
     * 输出:
     * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     * @param people
     * @return
     */
    /**
     * 假设候选队列为 A，已经站好队的队列为 B.
     *
     * 从 A 里挑身高最高的人 x 出来，插入到 B.
     * 因为 B 中每个人的身高都比 x 要高，因此 x 插入的位置
     * ，就是看 x 前面应该有多少人就行了。比如 x 前面有 5 个人，那 x 就插入到队列 B 的第 5 个位置。
     *
     */
    public int[][] reconstructQueue(int[][] people) {
        // 先排序
        // [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]

        // 再一个一个插入。
        // [7,0]
        // [7,0], [7,1]
        // [7,0], [6,1], [7,1]
        // [5,0], [7,0], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]<o2[0])
                    return 1;
                else if (o1[0]>o2[0])
                    return -1;
                else
                    return o1[1]-o2[1];
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int[] aPeople : people) {
            list.add(aPeople[1], aPeople);
        }
        int[][] res = new int[list.size()][2];
        for (int i = 0;i<list.size();i++){
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 95. 不同的二叉搜索树 II
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
     *
     * 示例:
     *
     * 输入: 3
     * 输出:
     * [
     *   [1,null,3,2],
     *   [3,2,null,1],
     *   [3,1,null,null,2],
     *   [2,1,3],
     *   [1,null,2,null,3]
     * ]
     * 解释:
     * 以上的输出对应以下 5 种不同结构的二叉搜索树：
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     */
    public LinkedList<TreeNode> generate_trees(int start, int end) {
        LinkedList<TreeNode> all_trees = new LinkedList<>();
        if (start > end) {
            all_trees.add(null);
            return all_trees;
        }

        // pick up a root
        for (int i = start; i <= end; i++) {
            // all possible left subtrees if i is choosen to be a root
            LinkedList<TreeNode> left_trees = generate_trees(start, i - 1);

            // all possible right subtrees if i is choosen to be a root
            LinkedList<TreeNode> right_trees = generate_trees(i + 1, end);

            // connect left and right trees to the root i
            for (TreeNode l : left_trees) {
                for (TreeNode r : right_trees) {
                    TreeNode current_tree = new TreeNode(i);
                    current_tree.left = l;
                    current_tree.right = r;
                    all_trees.add(current_tree);
                }
            }
        }
        return all_trees;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generate_trees(1, n);
    }

    /**
     * 120. 三角形最小路径和
     * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     *
     * 例如，给定三角形：
     *
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     *
     * 说明：
     *
     * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
     * @param triangle
     * @return
     */
    public  int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[] dp = new int[row];
        for (int i = 0;i<row;i++)
            dp[i] = triangle.get(row-1).get(i);
        for (int i = row-2;i>=0;i--){
            for (int j = 0;j<=i;j++){
                dp[j] = Math.min(dp[j],dp[j+1])+triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
    /**
     * 322. 零钱兑换
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     * 示例 1:
     *
     * 输入: coins = [1, 2, 5], amount = 11
     * 输出: 3
     * 解释: 11 = 5 + 5 + 1
     * 示例 2:
     *
     * 输入: coins = [2], amount = 3
     * 输出: -1
     * 说明:
     * 你可以认为每种硬币的数量是无限的。
     * @param coins
     * @param amount
     * @return
     */
    public  int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int j = 1; j <= amount; j++) {
            dp[j] = Integer.MAX_VALUE;
            if (j - coins[0] >= 0 && dp[j - coins[0]] != Integer.MAX_VALUE) {
                dp[j] = dp[j - coins[0]] + 1;
            }
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                int temp = Integer.MAX_VALUE;
                if (j - coins[i] >= 0 && dp[j - coins[i]] != Integer.MAX_VALUE) {
                    temp = dp[j - coins[i]] + 1;
                }
                dp[j] = Math.min(temp, dp[j]);

            }
        }
        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }
    /**
     * 581. 最短无序连续子数组
     * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     *
     * 你找到的子数组应是最短的，请输出它的长度。
     *
     * 示例 1:
     *
     * 输入: [2, 6, 4, 8, 10, 9, 15]
     * 输出: 5
     * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     * 说明 :
     *
     * 输入的数组长度范围在 [1, 10,000]。
     * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
     * @param nums
     * @return
     */
    public  int findUnsortedSubarray(int[] nums) {
        //此解法参考英文官方LeetCode上的讨论
        //从左到右扫描（或从右到左）找局部极大值（或局部极小值），若位置放置不正确，找到其应该存在的地方
        int n = nums.length;
        //赋初始开始和结束值
        int start = -1;
        int end = -2;
        //结束值赋为-2是考虑在数组本身就是有序时,return也可以给出正确值
        int min = nums[n - 1];
        int max = nums[0];
        for(int i = 0, pos = 0; i < n; i++) {
            pos = n - 1 - i;
            //找出局部极大、极小值
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[pos]);
            //如果当前值小于局部极大值，用end记录该位置，找到该max的合适位置，
            if(nums[i] < max)
                end = i;
            //如果当前值大于局部极小值，用star记录该位置，找到该star的合适位置
            if(nums[pos] > min)
                start = pos;
        }
        //返回开始和结束的索引差
        return end - start + 1;
    }

    /**
     * 543. 二叉树的直径
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
     *
     * 示例 :
     * 给定二叉树
     *
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
     *
     * 注意：两结点之间的路径长度是以它们之间边的数目表示。
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root==null)
            return 0;
        return diameterOfBinaryTreeDFS(root);
    }
    int max = 0;
    public int diameterOfBinaryTreeDFS(TreeNode root){
        if (root == null)
            return 0;
        int left = diameterOfBinaryTreeDFS(root.left);
        int right = diameterOfBinaryTreeDFS(root.right);
        max = Math.max(left+right,max);
        return left<right?right+1:left+1;
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
     * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
     *
     * 例如：
     *
     * 输入: 二叉搜索树:
     *               5
     *             /   \
     *            2     13
     *
     * 输出: 转换为累加树:
     *              18
     *             /   \
     *           20     13
     *
     * @param root
     * @return
     */
    public  TreeNode convertBST(TreeNode root) {
        if (root==null)
            return null;
        BST(root);
        return root;
    }
     int sum = 0;
    public  void BST(TreeNode root){
        if (root==null)
            return;
        BST(root.right);
        root.val +=sum;
        sum=root.val;
        BST(root.left);

    }
    /**
     * 437. 路径总和 III
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     *
     * 找出路径和等于给定数值的路径总数。
     *
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     *
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     * @param root
     * @param sum
     * @return
     */
    public  int pathSum(TreeNode root, int sum) {
        if (root==null)
            return 0;
        return pathSum(root,sum)+findPath(root.left,sum)+findPath(root.right,sum);
    }

    public  int findPath(TreeNode root, int sum) {
        if (root == null)
            return 0;
        int res = 0;
        if (sum==root.val){
            res+=1;
        }
        res+=findPath(root.left,sum-root.val);
        res+=findPath(root.right,sum-root.val);
        return res;
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
    public  int removeDuplicates(int[] nums) {
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
    public  List<Integer> findDuplicates(int[] nums) {
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
    public  List<Integer> findDisappearedNumbers(int[] nums) {
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
    public  int lengthOfLongestSubstring(String s) {
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
    public  List<Integer> findAnagrams(String s, String p) {
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
