/**
 * 最长上升子序列

给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:

输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
说明:

可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
你算法的时间复杂度应该为 O(n2) 。
进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?


 */

// 未看答案没有做出

// 动态规划Dynamic Programming的解法，这种解法的时间复杂度为O(n2)，类似brute force的解法
// 我们维护一个一维dp数组，其中dp[i]表示以nums[i]为结尾的最长递增子串的长度
// 对于每一个nums[i]，我们从第一个数再搜索到i，如果发现某个数小于nums[i]，我们更新dp[i]，更新方法为dp[i] = max(dp[i], dp[j] + 1)
// 即比较当前dp[i]的值和那个小于num[i]的数的dp值加1的大小，我们就这样不断的更新dp数组，到最后dp数组中最大的值就是我们要返回的LIS的长度
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

// 构建最长升序序列长度的数组,找到dp[0]到dp[i-1]中最大的升序序列长度且nums[j]<nums[i]
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] lis = new int[nums.length];
        lis[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            // 找到dp[0]到dp[i-1]中最大的升序序列长度且nums[j]<nums[i]
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    lis[i] = Math.max(lis[i], lis[j]);
                }
            }
            // 加1就是该位置能构成的最长升序序列长度
            lis[i] += 1; // 相当于上面的Arrays.fill(lis, 1)
            // 更新全局长度
            max = Math.max(max, lis[i]);
        }
        return max;
    }
}

// 答案二
// https://segmentfault.com/a/1190000003819886
// 在1,3,5,2,8,4,6这个例子中，当到6时，我们一共可以有四种
// (1)不同长度
// (2)且保证该升序序列在同长度升序序列中末尾最小
// 的升序序列

// 1
// 1,2
// 1,3,4
// 1,3,5,6
// 如果nums[i]比所有序列的末尾都大，说明有一个新的不同长度序列产生，我们把最长的序列复制一个，并加上这个nums[i]。
// 如果nums[i]比所有序列的末尾都小，说明长度为1的序列可以更新了，更新为这个更小的末尾。
// 如果在中间，则更新那个末尾数字刚刚大于等于自己的那个序列，说明那个长度的序列可以更新了。
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // len + 1表示当前最长的升序序列长度
        int len = 0;
        // tails[i]表示长度为i + 1的升序序列其末尾的数字
        int[] tails = new int[nums.length];
        tails[0] = nums[0];
        // 根据三种情况更新不同升序序列的集合
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < tails[0]) {
                tails[0] = nums[i];
            } else if (nums[i] > tails[len]) {
                tails[++len] = nums[i];
            } else {
                // 如果在中间，则二分搜索, 看每个不同长度序列的最后一位插入
                tails[binarySearch(tails, 0, len, nums[i])] = nums[i];
            }
        }
        return len + 1;
    }

    private int binarySearch(int[] tails, int min, int max, int target) {
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (tails[mid] == target) {
                return mid;
            }
            if (tails[mid] < target) {
                min = mid + 1;
            }
            if (tails[mid] > target) {
                max = mid - 1;
            }
        }
        return min;
    }

    private int binarySearch2(int[] tails, int min, int max, int target) { // 使用标准二分法模版运行速度更快
        while (min + 1 < max) {
            int mid = min + (max - min) / 2;
            if (tails[mid] >= target)
                max = mid;
            else
                min = mid;
        }
        if (tails[min] == target)
            return min;
        return max;
    }
}
