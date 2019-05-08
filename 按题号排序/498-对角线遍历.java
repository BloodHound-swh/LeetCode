/**
 * 对角线遍历
给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。

 

示例:

输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

输出:  [1,2,4,7,5,3,6,8,9]

解释:

 

说明:

给定矩阵中的元素总数不会超过 100000 。
 */


// 由于移动的方向不再是水平或竖直方向，而是对角线方向，那么每移动一次，横纵坐标都要变化。
// 向右上移动的话要坐标加上[-1, 1]，向左下移动的话要坐标加上[1, -1]，那么难点在于我们如何处理越界情况，越界后遍历的方向怎么变换。
// 向右上和左下两个对角线方向遍历的时候都会有越界的可能，但是除了左下角和右上角的位置越界需要改变两个坐标之外，其余的越界只需要改变一个。
// 那么我们就先判断要同时改变两个坐标的越界情况，即在右上角和左下角的位置。
// 如果在右上角位置还要往右上走时，那么要移动到它下面的位置的，那么如果col超过了n-1的范围，那么col重置为n-1，并且row自增2，然后改变遍历的方向。
// 同理如果row超过了m-1的范围，那么row重置为m-1，并且col自增2，然后改变遍历的方向。
// 然后我们再来判断一般的越界情况，如果row小于0，那么row重置0，然后改变遍历的方向。同理如果col小于0，那么col重置0，然后改变遍历的方向。
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return new int[0];
        
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int[] res = new int[rowLen * colLen];
        
        int row = 0;
        int col = 0;
        
        int[][] dir = {{-1, 1}, {1, -1}};
        int k = 0;
        
        for (int i = 0; i < res.length; i++) {
            res[i] = matrix[row][col];
            row += dir[k][0];
            col += dir[k][1];
            
            // 先判断右上角和左上角，然后判断普通边界
            if (col > colLen - 1) {
                col = colLen - 1;
                row += 2;
                k = 1 - k;
            } else if (row > rowLen - 1) {
                row = rowLen - 1;
                col += 2;
                k = 1 - k;
            } else if (row < 0) {
                row = 0;
                k = 1 - k;
            } else if (col < 0) {
                col = 0;
                k = 1 - k;
            }
        }
        
        return res;
    }
}