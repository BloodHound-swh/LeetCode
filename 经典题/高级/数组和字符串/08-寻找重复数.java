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
// 使用通用的二分法模板似乎很麻烦
class Solution {
    public int findDuplicate(int[] nums) {
        int low = 1;
        int high = nums.length - 1;
        int cnt = 0;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            cnt = 0;
            for (int num : nums) {
                if (num <= mid)
                    cnt++;
            }
            if (cnt <= mid)
                low = mid;
            else
                high = mid;
        }
        cnt = 0;
        for (int num : nums) {
            if (num <= low)
                cnt++;
        }
        if (cnt <= low)
            return high;
        else
            return low;
    }
}

// 使用模板二要方便不少
class Solution {
    public int findDuplicate(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cnt = 0;
            for (int a : nums) {
                if (a <= mid)
                    cnt++;
            }
            if (cnt <= mid)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }
}

// https://www.cnblogs.com/jimmycheng/p/7519870.html
// 使用了快慢指针，有些难以理解，类似于Linked List Cycle II 中会形成环
// 相遇1： slow 一次走一步；fast 一次走两步。

// 　　　　　　　　当它们相遇的时候，让slow 保留在相遇的点上。

// 相遇2：让fast 从起点开始走，一次走一步；slow 在上一次相遇的点上开始走，一次走一步。

// 　　　　　　　　当它们相遇的时候，相遇的点就是重复的数字。
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast)
                break;
        }
        fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[fast];
            if (slow == fast)
                break;
        }
        return slow;
    }
}