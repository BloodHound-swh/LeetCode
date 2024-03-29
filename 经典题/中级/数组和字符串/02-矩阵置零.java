/*
 * 矩阵置零
给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。

示例 1:

输入: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
输出: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
示例 2:

输入: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
输出: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
进阶:

一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
你能想出一个常数空间的解决方案吗？
 */

//将第一行与第一列作为标志位，标志位为零，则整下的那一行或那一列置零，并用两个boolean变量判定第一列与第一行本身的变化
class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        boolean firstRowZero = false;
        boolean firstColZero = false;

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRowZero = true;
                break;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        for (int x = 1; x < matrix[0].length; x++) {
            for (int y = 1; y < matrix.length; y++) {
                if (matrix[y][x] == 0) {
                    matrix[0][x] = 0;
                    matrix[y][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < matrix.length; j++)
                    matrix[j][i] = 0;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[0].length; j++)
                    matrix[i][j] = 0;
            }
        }

        // for(int i = 1; i<rows; i++) {
        // for (int j=1; j< cols; j++) {
        // if(matrix[0][j] == 0 || matrix[i][0] == 0)
        // matrix[i][j] = 0;
        // }
        // }

        if (firstRowZero)
            for (int j = 0; j < matrix[0].length; j++)
                matrix[0][j] = 0;
        if (firstColZero)
            for (int j = 0; j < matrix.length; j++)
                matrix[j][0] = 0;

    }
}