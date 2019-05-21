/**
 * 按奇偶排序数组

给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。

你可以返回满足此条件的任何数组作为答案。

 

示例：

输入：[3,1,2,4]
输出：[2,4,3,1]
输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 

提示：

1 <= A.length <= 5000
0 <= A[i] <= 5000
 */


// 双指针，但是不保证奇偶数原本的顺序
class Solution {
    public int[] sortArrayByParity(int[] A) {
        if (A == null || A.length <= 1) {
            return A;
        }

        int i = 0;
        int j = A.length - 1;

        while (i < j) {
            if (A[i] % 2 == 1 && A[j] % 2 == 0) {
                int tmp = A[j];
                A[j] = A[i];
                A[i] = tmp;
            } else if (A[i] % 2 == 0) {
                i++;
            } else if (A[j] % 2 == 1) {
                j--;
            }
        }

        return A;
    }
}

// 如果要保持顺序，可以使用类似冒泡排序的变形
class Solution {
    public int[] sortArrayByParity(int[] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length - 1; j++) {
                if (A[j] % 2 == 1 && A[j + 1] % 2 == 0) {
                    int tmp = A[j + 1];
                    A[j + 1] = A[j];
                    A[j] = tmp;
                }
            }
        }

        return A;
    }
}