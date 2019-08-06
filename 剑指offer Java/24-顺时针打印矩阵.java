/**
 * 题目描述
输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */


// 同LeetCode第54题
public class Solution {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
       ArrayList<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            return res;
        
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        
        while (left < right && top < bottom) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            for (int i = right; i >= left; i--) {
                res.add(matrix[bottom][i]);
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
        }
        
        if (left == right) {
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][left]);
            }
        } else if (top == bottom) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
        }
        
        return res;
    }
}