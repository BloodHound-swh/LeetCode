/**
 * 搜索二维矩阵 II

编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
示例:

现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。
 */

// 未看答案版想到的是先用二分法找到行，再用二分法找到列
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int row = matrix.length;
        int col = matrix[0].length;

        int left = 0;
        int right = row - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid][0] > target) {
                right = mid;
            } else if (matrix[mid][0] < target) {
                left = mid;
            } else {
                return true;
            }
        }

        if (matrix[left][0] < target)
            row = right;
        else if (matrix[left][0] > target)
            row = left;
        else
            return true;

        for (int i = 0; i <= row; i++) {
            int l = 0;
            int r = col - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (matrix[i][mid] < target) {
                    l = mid + 1;
                } else if (matrix[i][mid] > target) {
                    r = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}

// 方法一
// 从第一行最后一个数开始，target小于它就往左移，target大于它就往下移
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0)
            return false;
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] < target)
                row++;
            else
                col--;
        }
        return false;
    }
}

// 方法二
// 对每一行使用二分法
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 对分一行二分法查找
        int row = matrix.length;
        if (row == 0)
            return false;
        int len = matrix[0].length;
        for (int i = 0; i < row; i++) {
            int left = 0;
            int right = len - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (matrix[i][mid] == target)
                    return true;
                else if (matrix[i][mid] < target)
                    left = mid + 1;
                else if (matrix[i][mid] > target)
                    right = mid - 1;
            }
        }
        return false;
    }
}