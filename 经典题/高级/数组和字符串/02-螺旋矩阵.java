/*
 * 螺旋矩阵
给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

示例 1:

输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]
示例 2:

输入:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */

// 规定好上下左右的边界，每绕一圈，边界各自变化，注意终止情况即可
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return new LinkedList<Integer>();
        LinkedList<Integer> res = new LinkedList<>();
        int left = 0, right = matrix[0].length - 1;
        int top = 0, bottom = matrix.length - 1;
        while (left < right && top < bottom) {
            for (int i = left; i < right; i++)
                res.add(matrix[top][i]);
            for (int i = top; i < bottom; i++)
                res.add(matrix[i][right]);
            for (int i = right; i > left; i--)
                res.add(matrix[bottom][i]);
            for (int i = bottom; i > top; i--)
                res.add(matrix[i][left]);
            left++;
            right--;
            top++;
            bottom--;
        }
        if (left == right) {
            for (int i = top; i <= bottom; i++)
                res.add(matrix[i][left]);
        } else if (top == bottom) {
            for (int i = left; i <= right; i++)
                res.add(matrix[top][i]);
        }
        return res;
    }
}


// 优化的边界判定，减少了最后的if else。
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0)
            return res;
        int left = 0, right = matrix[0].length - 1, top = 0, down = matrix.length - 1;
        while (left <= right && top <= down) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= down; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            if (top <= down) {
                for (int i = right; i >= left; i--) {
                    res.add(matrix[down][i]);
                }
                down--;
            }
            if (left <= right) {
                for (int i = down; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }

        return res;
    }
}