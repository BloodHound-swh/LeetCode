/**
 * 寻找重复数

给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

示例 1:

输入: [1,3,4,2,2]
输出: 2
示例 2:

输入: [3,1,3,4,2]
输出: 3
说明：

不能更改原数组（假设数组是只读的）。
只能使用额外的 O(1) 的空间。
时间复杂度小于 O(n2) 。
数组中只有一个重复的数字，但它可能不止重复出现一次。
 */


// 我们在区别[1, n]中搜索，首先求出中点mid，然后遍历整个数组，统计所有小于等于mid的数的个数
// 如果个数大于mid，则说明重复值在[mid+1, n]之间，反之，重复值应在[1, mid-1]之间
// 然后依次类推，直到搜索完成
// 找到合适的二分法模板
class Solution {
    public int findDuplicate(int[] nums) {
        int lo = 1, hi = nums.length;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid)
                    cnt++;
            }

            if (cnt <= mid)
                lo = mid + 1;
            else
                hi = mid - 1;
        }

        return lo;
    }
}

// 最简单的思路，使用set。但是面试肯定不加分
class Solution {
    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                return n;
            }
            set.add(n);
        }

        return -1;
    }
}

// 我们对nums数组建图，每个位置i连一条i→nums[i]的边。由于存在的重复的数字target，因此target这个位置一定有起码两条指向它的边，因此整张图一定存在环
// 且我们要找到的target就是这个环的入口，那么整个问题就等价于寻找环的入口
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}