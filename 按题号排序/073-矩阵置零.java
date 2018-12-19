/**
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

// 未看答案没有做出

// 答案一
// 我们只需要直到哪些行，哪些列需要被置0就行了，最简单的方法就是建两个大小分别为M和N的数组，来记录哪些行哪些列应该被置0。
// 那有没有可能不用额外空间呢？我们其实可以借用原矩阵的首行和首列来存储这个信息。
// 这个方法的缺点在于，如果我们直接将0存入首行或首列来表示相应行和列要置0的话，我们很难判断首行或者首列自己是不是该置0。
// 这里我们用两个boolean变量记录下首行和首列原本有没有0，然后在其他位置置完0后，再单独根据boolean变量来处理首行和首列，就避免了干扰的问题。
class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0)
            return;
        boolean firstRowZero = false;
        boolean firstColZero = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i != 0 && j != 0 && matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                } else if (matrix[i][j] == 0) {
                    firstRowZero = i == 0 ? true : firstRowZero;
                    firstColZero = j == 0 ? true : firstColZero;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; firstColZero && i < matrix.length; i++) {
            matrix[i][0] = 0;
        }

        for (int j = 0; firstRowZero && j < matrix[0].length; j++) {
            matrix[0][j] = 0;
        }
    }
}

// 答案二，另一种写法
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