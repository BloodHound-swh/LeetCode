/**
 * 找到所有数组中消失的数字
给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。

 

示例 1：

输入：nums = [4,3,2,7,8,2,3,1]
输出：[5,6]
示例 2：

输入：nums = [1,1]
输出：[2]
 

提示：

n == nums.length
1 <= n <= 10^5
1 <= nums[i] <= n
进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
 */


// 最传统和简单的做饭，使用set
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                res.add(i);
            }
        }

        return res;
    }
}

// 把原数组映射为哈希表，不用额外空间
// 遍历数组nums，对每个元素num进行映射，映射到数组下标为num-1的位置，把此位置元素变为负数。
// 细节就是，num可能已经被置为负数了，因此使用num时需要加上绝对值。以及有的位置可能多次映射，因此，必须nums[index] = -Math.abs(nums[index]);才能百分百修改为负数。
// 遍历数组，对于num大于0的位置，说明没有元素映射过来，下标+1即为消失的数字。
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        for (int n : nums) {
            // 注意这里就要使用绝对值，因为可能数字已经被置为负数了
            int idx = Math.abs(n) - 1;
            nums[idx] = - Math.abs(nums[idx]);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }

        return res;
    }
}