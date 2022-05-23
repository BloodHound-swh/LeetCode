/**
 * 198. 打家劫舍
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

 

示例 1：

输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
示例 2：

输入：[2,7,9,3,1]
输出：12
解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 

提示：

1 <= nums.length <= 100
0 <= nums[i] <= 400
 */


// 打劫到第i家的最大收益需要比较打劫到第i-1家时的最大收益与打劫到第i-2家的最大收益加上打劫第i家获得的金钱(nums[i])
// 这样写容易理解，但是空间复杂度高，注意dp[0]预先定为0
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }

        return dp[nums.length];
    }
}

// 节省空间复杂度
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}

// 节省空间复杂度二
// 每个房间只有两种状态，打劫，不打劫，而到下一个房间为止的总收入只与到上一个房间为止的总收入有关
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int rob = 0;
        int notRob = 0;
        for (int num : nums) {
            int pre = Math.max(rob, notRob);
            rob = notRob + num;
            notRob = pre;  //当前不打劫，那么之前的房子可以打劫也可以不打劫，取最大利益
        }
        return Math.max(rob, notRob);
    }
}