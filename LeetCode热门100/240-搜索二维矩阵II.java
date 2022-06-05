/**
 * 240. 搜索二维矩阵 II

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

// 从第一行最后一个数开始，target小于它就往左移，target大于它就往下移
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }

        return false;
    }
}

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