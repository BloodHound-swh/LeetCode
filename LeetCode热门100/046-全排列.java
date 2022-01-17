/**
 * 46. 全排列
给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

 

示例 1：

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
示例 2：

输入：nums = [0,1]
输出：[[0,1],[1,0]]
示例 3：

输入：nums = [1]
输出：[[1]]
 

提示：

1 <= nums.length <= 6
-10 <= nums[i] <= 10
nums 中的所有整数 互不相同
 */


// 经典的回溯法，并且用used表示是否被使用，以空间换时间
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> tmp = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backTrack(res, tmp, nums, used);
        return res;
    }

    public void backTrack(List<List<Integer>> res, List<Integer> tmp, int[] nums, boolean[] used) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] == true) {
                continue;
            }
            used[i] = true;
            tmp.add(nums[i]);
            backTrack(res, tmp, nums, used);
            used[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }
}