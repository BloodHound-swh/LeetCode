/**
 * 颜色分类

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

// 未看答案版（看了提示）
// 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
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

// 答案一
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
                colLast--; // 注意这里没有index++，因为还需要和colFirst比较
            }
        }
    }

    public void switchColor(int[] colors, int i, int j) {
        int temp = colors[i];
        colors[i] = colors[j];
        colors[j] = temp;
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

// 答案二
// 类似冒泡法
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