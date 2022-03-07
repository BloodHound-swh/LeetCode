/**
 * 85. 最大矩形
给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

 

示例 1：


输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
输出：6
解释：最大矩形如上图所示。
示例 2：

输入：matrix = []
输出：0
示例 3：

输入：matrix = [["0"]]
输出：0
示例 4：

输入：matrix = [["1"]]
输出：1
示例 5：

输入：matrix = [["0","0"]]
输出：0
 

提示：

rows == matrix.length
cols == matrix[0].length
1 <= row, cols <= 200
matrix[i][j] 为 '0' 或 '1'
 */


// 大神的解法，牛逼
// https://leetcode-cn.com/problems/maximal-rectangle/solution/jie-jin-shuang-100javajie-fa-su-kan-by-w-47dv/
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] continuousOneMatrx = new int[m][n];
        
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == '1') {
                continuousOneMatrx[0][j] = 1;
            }
        }
        
        // 找出每个位置从上到下连续1的数目
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    continuousOneMatrx[i][j] = continuousOneMatrx[i - 1][j] + 1;
                }
            }
        }

        int res = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 优化条件，当矩阵的宽乘以当前的连续1的个数都小于res，就不用继续计算了
                if (continuousOneMatrx[i][j] * n < res) {
                    continue;
                }
                if (continuousOneMatrx[i][j] != 0) {
                    int cnt = 1;
                    for (int k = j + 1; k < n; k++) {
                        if (continuousOneMatrx[i][k] < continuousOneMatrx[i][j]) {
                            break;
                        }
                        cnt++;
                    }
                    for (int k = j - 1; k >= 0; k--) {
                        if (continuousOneMatrx[i][k] < continuousOneMatrx[i][j]) {
                            break;
                        }
                        cnt++;
                    }

                    res = Math.max(res, cnt * continuousOneMatrx[i][j]);
                }
            }
        }

        return res;
    }
}