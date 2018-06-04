/**
 * 打家劫舍
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:

输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
示例 2:

输入: [2,7,9,3,1]
输出: 12
解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */



//三个算法都是考虑打劫到第i-1家时的最大收益与打劫到第i-2家的最大收益,加上打劫第i家获得的金钱(nums[i])
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int rob = 0;
        int notrob = 0;
        for(int num:nums){
            int pre = Math.max(rob, notrob);
            rob = notrob + num;
            notrob = pre;//当前不打劫，那么之前的房子可以打劫也可以不打劫，取最大利益
        }
        return Math.max(rob, notrob);
    }
}


class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int steal = nums[0], notSteal = 0;
        for(int i = 1 ; i < nums.length ; i++){
        	int tmpS = steal;
        	steal = nums[i] + notSteal;
        	notSteal = Math.max(tmpS, notSteal); 
        }
        return Math.max(steal, notSteal);
    }
}

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0;
        long []res = new long[n+1];
        
        
        res[0] = 0;
        res[1] = nums[0];
        for(int i = 2; i <= n; i++) {
            res[i] = Math.max(res[i-1], res[i-2] + nums[i-1]);
        }
        return (int)res[n];
    }
}