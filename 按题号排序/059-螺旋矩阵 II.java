/**
 * 螺旋矩阵 II
给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

示例:

输入: 3
输出:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 */


// 思想同螺旋矩阵I，只是最后只需判定n是奇数还是偶数。
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if (n <= 0)
            return res;

        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;
        int k = 1;

        while (left < right && top < bottom) {
            for (int i = left; i <= right; i++) {
                res[top][i] = k++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                res[i][right] = k++;
            }
            right--;
            for (int i = right; i >= left; i--) {
                res[bottom][i] = k++;
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                res[i][left] = k++;
            }
            left++;
        }

        if (n % 2 != 0)
            res[n / 2][n / 2] = k;
        return res;
    }
}