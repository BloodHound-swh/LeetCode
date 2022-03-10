/**
 * 96. 不同的二叉搜索树
给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。

 

示例 1：


输入：n = 3
输出：5
示例 2：

输入：n = 1
输出：1
 

提示：

1 <= n <= 19
 */


// 递归
class Solution {

    public Map<Integer, Integer> map = new HashMap<>();

    public int numTrees(int n) {
        return helper(n);
    }

    public int helper(int n) {
        //如果只有0，或者1个节点，则可能的子树情况为1种
        if (n == 0 || n == 1) {
            return 1;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            // 当用i这个节点当做根节点时, 左边有多少种子树
            int leftNum = helper(i - 1);
            // 当用i这个节点当做根节点时, 右边有多少种子树
            int rightNum = helper(n - i);
            // 乘起来求和就是当用i这个节点当做根节点时，总共的搜索二叉树个数
            count += leftNum * rightNum;
        }
        // 大量的重复计算造成时间过长，因此可以用一个HashMap存储n和子树数量的映射，如果已经计算过了当前n的子树数量，直接取出用即可
         map.put(n, count);
         return count;
    }
}

// 官方题解，动态规划。其实思路同上面一样
// https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode-solution/
class Solution {
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }
}