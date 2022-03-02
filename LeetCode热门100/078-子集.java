/**
 * 78. 子集
给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

 

示例 1：

输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
示例 2：

输入：nums = [0]
输出：[[],[0]]
 

提示：

1 <= nums.length <= 10
-10 <= nums[i] <= 10
nums 中的所有元素 互不相同
 */


// 回溯法
// k表示子集的长度，start表示起始坐标
class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        for (int k = 0; k <= nums.length; k++) {
            backTrack(0, k, new ArrayList<Integer>(), nums);
        }

        return res;
    }

    public void backTrack(int start, int k, List<Integer> cur, int[] nums) {
        if (k == 0) {
            res.add(new ArrayList<Integer>(cur));
            return;
        } 

        for (int i = start; i < nums.length; i++) {
            cur.add(nums[i]);
            backTrack(i + 1, k - 1, cur, nums);
            cur.remove(cur.size() - 1);
        }
    }
}