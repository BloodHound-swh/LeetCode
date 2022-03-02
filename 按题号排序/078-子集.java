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

// 未看答案没有做出，其实这是DFS的变形题

// 答案一
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

// DFS
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0)
            return res;
        LinkedList<Integer> cur = new LinkedList<Integer>();
        subsetsHelper(res, cur, 0, nums);
        return res;
    }

    public void subsetsHelper(List<List<Integer>> res, LinkedList<Integer> cur, int idx, int[] nums) {
        res.add(new LinkedList<Integer>(cur));
        for (int i = idx; i < nums.length; i++) {
            cur.add(nums[i]);
            subsetsHelper(res, cur, i + 1, nums);
            cur.remove(cur.size() - 1);
        }
    }
}

// 对nums的每一个数字判断，是否加入到curr中
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        if (nums == null || nums.length == 0)
            return result;
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

// 答案二
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        res.add(new LinkedList<>());
        for (int i = 0; i < nums.length; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                // List<Integer> tmp = res.get(j); // 注意不能这样写，因为这时修改tmp就修改了res.get(j)
                List<Integer> tmp = new LinkedList<>();
                tmp.addAll(res.get(j));
                tmp.add(nums[i]);
                res.add(new LinkedList<Integer>(tmp));
            }
        }
        return res;
    }
}

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