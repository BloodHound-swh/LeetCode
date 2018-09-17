/**
 * 分类颜色
给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

注意:
不能使用代码库中的排序函数来解决这道题。

示例:

输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]
进阶：

一个直观的解决方案是使用计数排序的两趟扫描算法。
首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
你能想出一个仅使用常数空间的一趟扫描算法吗？
 */



// 使用first 和 last 指针，在first之前的数都小于1，在last之后的数都大于1
class Solution {
    public void sortColors(int[] colors) {
        if (colors == null || colors.length <= 1)
            return;
        int colFirst = 0;
        while (colFirst < colors.length && colors[colFirst] == 0)
            colFirst++;
        int colLast = colors.length - 1;
        while (colLast >= 0 && colors[colLast] == 2)
            colLast--;

        int index = colFirst;
        while (index <= colLast) {
            if (colors[index] == 1)
                index++;
            else if (colors[index] == 0) {
                switchColor(colors, colFirst, index);
                colFirst++;
                index++;
            } else if (colors[index] == 2) {
                switchColor(colors, colLast, index);
                colLast--;
            }
        }
    }

    public void switchColor(int[] colors, int i, int j) {
        int temp = colors[i];
        colors[i] = colors[j];
        colors[j] = temp;
    }
}

// 书写形式不同
class Solution {
    public void sortColors(int[] nums) {
        int first = -1; // [0,first]
        int i = 0; // [first + 1, i)
        int last = nums.length; // [last, n - 1]

        while (i < last) {
            if (nums[i] < 1) {
                first++;
                int temp = nums[first];
                nums[first] = nums[i];
                nums[i] = temp;
                i++;
            } else if (nums[i] > 1) {
                last--;
                int temp = nums[last];
                nums[last] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }
    }

}

// 对上面的方法的“美化”
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

// 冒泡法？
class Solution {
    public void sortColors(int[] nums) {
        if (nums.length <= 1)
            return;
        for (int i = 1; i < nums.length; i++) {
            int j = i;
            while (j > 0 && nums[j] < nums[j - 1]) {
                int tmp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = tmp;
                j -= 1;
            }
        }
    }
}