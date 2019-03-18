/**
 * 直线上最多的点数

给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。

示例 1:

输入: [[1,1],[2,2],[3,3]]
输出: 3
解释:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
示例 2:

输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
输出: 4
解释:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
 */


// 关键在于如何判定是否在一条直线上，然后用一个Map来记录该直线上有多少个点
// 而直线可以用斜率来表示，用Map<Integer, Integer>来表示斜率


// 使用<dy,dx>来表示斜率，同时dy, dx要除以彼此的最大公约数
// 从某一个点算，斜率相同的线段必然是在同一条直线上
// 注意有些点本身就是重复的
class Solution {
    public int maxPoints(Point[] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Map<Integer, Integer>, Integer> map = new HashMap<>();
            int duplicate = 1;
            int localMax = 0;
            for (int j = i + 1; j < points.length; j++) {
                if (points[j].x == points[i].x && points[j].y == points[i].y) {
                    duplicate++;
                } else {
                    Map<Integer, Integer> temp = getSlope(points[i], points[j]);
                    map.put(temp, map.containsKey(temp) ? map.get(temp) + 1 : 1);
                    localMax = Math.max(localMax, map.get(temp));
                }
            }
            res = Math.max(res, duplicate + localMax);
        }
        return res;
    }

    public Map<Integer, Integer> getSlope(Point p1, Point p2) {
        int dx = p1.x - p2.x;
        int dy = p1.y - p2.y;
        Map<Integer, Integer> map = new HashMap<>();

        if (dy == 0) {
            map.put(p1.x, 0);
            return map;
        }

        if (dx == 0) {
            map.put(0, p1.y);
            return map;
        }

        int d = gcd(dx, dy);
        map.put(dx / d, dy / d);
        return map;
    }

    public int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}

// 另一种求同一条直线的方法，用x1 * y2 == x2 * y1
class Solution {
    public int maxPoints(Point[] points) {
        if (points.length == 0) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }

        int max = 2;
        for (int i = 0; i < points.length; i++) {
            int samePosition = 0; // 重复位置的点 个数
            int sameSlope = 1; // 斜率相同的点 个数
            for (int j = i + 1; j < points.length; j++) {
                // 判断是否为重复位置的点
                long x1 = points[j].x - points[i].x;
                long y1 = points[j].y - points[i].y;
                if (x1 == 0 && y1 == 0) {
                    samePosition++;
                } else {
                    sameSlope++;// 第二个点，所以可以直接++
                    for (int k = j + 1; k < points.length; k++) {
                        long x2 = points[k].x - points[i].x;
                        long y2 = points[k].y - points[i].y;

                        if (x1 * y2 == x2 * y1) {
                            sameSlope++;
                        }
                    }
                }
                if (max < (samePosition + sameSlope)) {
                    max = samePosition + sameSlope;
                }
                sameSlope = 1;
            }
        }
        return max;

    }
}