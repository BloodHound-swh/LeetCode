/**
 * 分割数组的最大值
给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。

注意:
数组长度 n 满足以下条件:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
示例:

输入:
nums = [7,2,5,10,8]
m = 2

输出:
18

解释:
一共有四种方法将nums分割为2个子数组。
其中最好的方式是将其分为[7,2,5] 和 [10,8]，
因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 */

// 先算出子组的和的取值范围m = nums.lenght时left = [max], m = 1时, right = sum
// 使用二分法，当mid = (left + right) 时
// 看子组的个数是否大于m, 若小于等于m, 则right = mid
// 若子组的个数大于m, 则left = mid + 1
// https://blog.csdn.net/u014688145/article/details/69525838
class Solution {
    public int splitArray(int[] nums, int m) {
        int right = 0;
        int left = 0;
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        return binaryHelper(nums, m, left, right);
    }

    public int binaryHelper(int[] nums, int m, int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (valid(nums, m, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean valid(int[] nums, int m, int mid) {
        int cur = 0;
        int count = 1;
        for (int num : nums) {
            cur += num;
            if (cur > mid) {
                cur = num;
                count++;
                if (count > m)
                    return false;
            }
        }
        return true;
    }
}