/**
旋转图像
给定一个 n × n 的二维矩阵表示一个图像。
将图像旋转 90 度（顺时针）。
注意：
你必须在原矩阵中旋转图像，请不要使用另一个矩阵来旋转图像。
例 1:
给出的输入矩阵 = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

旋转输入矩阵，使其变为 :
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
] 

例 2:
给出的输入矩阵 =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

旋转输入矩阵，使其变为 :
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
 */


//直观上看，就是四个数字循环交换，从外圈一步步到内圈
class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int top = 0;
        int left = 0;
        int right = matrix.length-1;
        int bottom = matrix.length -1;
        int n = matrix.length;
        while(n>1){
            for(int i = 0; i< n-1;i++){
                int temp = matrix[top][left+i];
                matrix[top][left+i] = matrix[bottom-i][left];
                matrix[bottom-i][left] = matrix[bottom][right-i];
                matrix[bottom][right-i] = matrix[top+i][right];
                matrix[top+i][right] = temp;
            }
            top++;
            bottom--;
            left++;
            right--;
            n-=2;
        }
    }
}

//其实旋转可以看成是两次翻转，一次沿着左（其实右也可以）对角线翻转，第二次沿着中线翻转
class Solution {
	public void rotate(int[][] matrix) {
        for(int i=0;i<matrix.length;i++){
            for(int j=i;j<matrix.length;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length / 2;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
	}
}