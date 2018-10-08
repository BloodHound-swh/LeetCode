/**
 *  图像渲染
有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。

给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。

为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。

最后返回经过上色渲染后的图像。

示例 1:

输入: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
输出: [[2,2,2],[2,2,0],[2,0,1]]
解析: 
在图像的正中间，(坐标(sr,sc)=(1,1)),
在路径上所有符合条件的像素点的颜色都被更改成2。
注意，右下角的像素没有更改为2，
因为它不是在上下左右四个方向上与初始点相连的像素点。
注意:

image 和 image[0] 的长度在范围 [1, 50] 内。
给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
 */


// DFS
// 首先判断如果给定位置的颜色跟新的颜色相同的话，直接返回，否则就对给定位置调用递归函数。
// 在递归函数中，如果越界或者当前颜色跟起始颜色不同，直接返回。否则就给当前位置赋上新的颜色，然后对周围四个点继续调用递归函数
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor)
            return image;
        int m = image.length;
        int n = image[0].length;
        floodFill(image, sr, sc, m, n, image[sr][sc], newColor);
        return image;
    }

    public void floodFill(int[][] image, int x, int y, int m, int n, int orgColor, int newColor) {
        if (x < 0 || x >= m || y < 0 || y >= n)
            return;
        if (image[x][y] != orgColor)
            return;
        image[x][y] = newColor;

        floodFill(image, x + 1, y, m, n, orgColor, newColor);
        floodFill(image, x - 1, y, m, n, orgColor, newColor);
        floodFill(image, x, y + 1, m, n, orgColor, newColor);
        floodFill(image, x, y - 1, m, n, orgColor, newColor);
    }
}

// BFS
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor)
            return image;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { sr, sc });

        int[] gx = { 1, -1, 0, 0 };
        int[] gy = { 0, 0, 1, -1 };

        int oldColor = image[sr][sc];

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                image[x][y] = newColor;
                for (int k = 0; k < 4; k++) {
                    int nx = gx[k] + x;
                    int ny = gy[k] + y;
                    if (nx < 0 || ny < 0 || nx >= image.length || ny >= image[0].length || image[nx][ny] != oldColor)
                        continue;
                    queue.offer(new int[] { nx, ny });
                }
            }
        }
        return image;
    }
}