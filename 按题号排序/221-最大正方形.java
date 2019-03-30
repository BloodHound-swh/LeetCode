/**
 * 最大正方形
在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

示例:

输入: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

输出: 4
 */


// 在这道题目中，就需要建立一个结果数组（是会随着状态转化函数的变化而变得更加的健全）。
// 数组中的每个值，保存的是以该cell作为最右下角的元素构建正方形所能构建的最大的正方形的边长。
// 有了这个对状态的定义，我们就能够很好的建立我们的状态转移函数了~
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;

        int res = 0;
        int[][] side = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            side[i][0] = (matrix[i][0] == '0' ? 0 : 1);
            res = Math.max(res, side[i][0]);
        }

        for (int j = 0; j < matrix[0].length; j++) {
            side[0][j] = (matrix[0][j] == '0' ? 0 : 1);
            res = Math.max(res, side[0][j]);
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    side[i][j] = Math.min(Math.min(side[i][j - 1], side[i - 1][j]), side[i - 1][j - 1]) + 1;
                    res = Math.max(res, side[i][j]);
                }
            }
        }

        return res * res;
    }
}