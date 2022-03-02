/**
 * 75. 颜色分类
给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

必须在不使用库的sort函数的情况下解决这个问题。

 

示例 1：

输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
示例 2：

输入：nums = [2,0,1]
输出：[0,1,2]
 

提示：

n == nums.length
1 <= n <= 300
nums[i] 为 0、1 或 2
 

进阶：

你可以不使用代码库中的排序函数来解决这道题吗？
你能想出一个仅使用常数空间的一趟扫描算法吗？
 */


// 最简单的方法，首先计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
class Solution {
    public void sortColors(int[] nums) {
        int red = 0, white = 0, blue = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int tmp = nums[i];
            if (tmp == 0) {
                red++;
            } else if (tmp == 1) {
                white++;
            } else {
                blue++;
            }
        }
        while (red > 0) {
            list.add(0);
            red--;
        }
        while (white > 0) {
            list.add(1);
            white--;
        }
        while (blue > 0) {
            list.add(2);
            blue--;
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = list.get(i);
        }
    }
}

// 单指针：使用指针pt
// 我们可以考虑对数组进行两次遍历。在第一次遍历中，我们将数组中所有的0交换到pt之前。
// 在第二次遍历中，我们将数组中所有的1交换到pt之前。此时，所有的2都出现在pt以及pt之后，这样我们就完成了排序。
class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
    }
}

// 双指针，思路差不多
class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int left = 0;
        int right = nums.length - 1;
        int index = 0;
        while (index <= right) {
            if (nums[index] == 0) {
                swap(nums, index++, left++);
            } else if (nums[index] == 1) {
                index++;
            } else {
                swap(nums, index, right--);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}