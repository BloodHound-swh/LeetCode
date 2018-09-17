/**
 * 子集
给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */


// 使用递归（深度优先），每次在curr加入nums[currIdx]，循环调用helper函数，调用结束后，去除最后一个数字，然后currIdx++
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        if (nums == null || nums.length == 0)
            return result;
        subsetsHelper(nums, 0, result, new LinkedList<Integer>());
        return result;
    }

    public void subsetsHelper(int[] nums, int currIdx, List<List<Integer>> result, List<Integer> curr) {
        result.add(new LinkedList<Integer>(curr));
        for (int idx = currIdx; idx < nums.length; idx++) {
            curr.add(nums[idx]);
            subsetsHelper(nums, idx + 1, result, curr);
            curr.remove(curr.size() - 1);
        }
    }
}


// 对nums的每一个数字判断，是否加入到curr中
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        if (nums == null || nums.length == 0) return result;
        subsetsHelper(nums, 0, result, new LinkedList<Integer>());
        return result;
    }
    
    public void subsetsHelper(int[] nums, int currIdx, List<List<Integer>> result, List<Integer> curr) {
        if (currIdx == nums.length) {
            result.add(new LinkedList<Integer>(curr));
        } else {
            subsetsHelper(nums, currIdx + 1, result, curr); // 当前的数字不加入curr
            curr.add(nums[currIdx]);// 当前的数字加入curr
            subsetsHelper(nums, currIdx + 1, result, curr);
            curr.remove(curr.size() - 1);
        }
    }
}