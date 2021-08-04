/*
 * 全排列
给定一个没有重复数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */

// 使用clist和Hashset，clist存储当前状态，set用来判断数字是否已经出现。每次递归返回后，去除clist最后一位，向上返回，选取下一个数字
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
        if (nums == null || nums.length == 0)
            return res;
        permuteHelper(res, new LinkedList<Integer>(), nums, new HashSet<Integer>());
        return res;
    }

    public void permuteHelper(List<List<Integer>> res, List<Integer> clist, int[] nums, HashSet<Integer> set) {
        if (clist.size() == nums.length) {
            res.add(new LinkedList<Integer>(clist));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!se t.contains(nums[i])) {
                    clist.add(nums[i]);
                    int last = clist.size() - 1;
                    set.add(nums[i]);
                    permuteHelper(res, clist, nums, set);
                    set.remove(nums[i]);
                    clist.remove(last);
                }
            }
        }
    }
}

// 将hashset改成boolean数组，思路相同
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue;
            used[i] = true;
            list.add(nums[i]);
            backtrack(res, list, nums, used);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
}

// 其实用list.contains(nums[i])就可以代替set后者boolean[]的功能
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        helper(res, new ArrayList<Integer>(), nums);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i]))
                continue;
            list.add(nums[i]);
            helper(res, list, nums);
            list.remove(list.size() - 1);
        }
    }
}

// 每次将当前位置的数字与它后面的每一个数字交换
public class Solution {

    // 最终返回的结果集
    List<List<Integer>> res = new ArrayList<List<Integer>>();

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        if (len == 0 || nums == null)
            return res;

        // 采用前后元素交换的办法，dfs解题
        exchange(nums, 0, len);
        return res;
    }

    public void exchange(int[] nums, int i, int len) {
        // 将当前数组加到结果集中
        if (i == len - 1) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                list.add(nums[j]);
            }
            res.add(list);
            return;
        }
        // 将当前位置的数跟后面的数交换，并搜索解
        for (int j = i; j < len; j++) {
            swap(nums, i, j); // swap(nums, j, i)更好理解
            exchange(nums, i + 1, len);
            swap(nums, i, j); // 换回去，进行下一个循环
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}