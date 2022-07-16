/**
 * 163. 缺失的区间
给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。

示例：

输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
输出: ["2", "4->49", "51->74", "76->99"]
 */

// https://leetcode.cn/problems/missing-ranges/solution/by-jam007-3oru/
// 如果前后两个数 num - 1 > prev + 1， 说明缺失一个区间 prev + 1 -> num - 1
// 如果前后两个数 num - 1 == prev + 1， 说明缺失一个数值 num - 1
// 否者当前值比 lower 还小, 不需要处理
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        int prev = lower - 1;
        for (int n : nums) {
            if ((n - 1) > (prev + 1)) {
                res.add((prev + 1) + "->" + (n - 1));
            } else if ((n - 1) == (prev + 1)) {
                res.add((prev + 1) + "");
            }
            prev = n;
        }

        if (upper > (prev + 1)) {
            res.add((prev + 1) + "->" + upper);
        } else if (upper == (prev + 1)) {
            res.add(upper + "");
        }

        return res;
    }
}

class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        int pre = lower - 1;
        for (int num : nums) {
            add(ans, pre + 1, num - 1);
            pre = num;
        }
        add(ans, pre + 1, upper);
        return ans;
    }

    public void add(List<String> ans, int a, int b) {
        if (b >= a) {
            ans.add(b > a ? a + "->" + b : a + "");
        }
    }
}