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


// 使用<dy,dx>来表示斜率，同时dy, dx要除以彼此的最大公约数
// 从某一个点算，斜率相同的线段必然是在同一条直线上
// 注意有些点本身就是重复的
class Solution {
    public int maxPoints(Point[] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Map<Integer, Integer>, Integer> m = new HashMap<>();
            int duplicate = 1;
            int localMax = 0;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    duplicate++;
                } else {
                    Map<Integer, Integer> temp = getSlope(points[i], points[j]);
                    m.put(temp, m.containsKey(temp) ? m.get(temp) + 1 : 1);
                    localMax = Math.max(localMax, m.get(temp));
                }
            }
            res = Math.max(res, duplicate + localMax);
        }
        return res;
    }

    public Map<Integer, Integer> getSlope(Point p1, Point p2) {
        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;
        Map<Integer, Integer> map = new HashMap<>();
        if (dy == 0) {
            map.put(p1.y, 0);
            return map;
        }
        if (dx == 0) {
            map.put(0, p1.x);
            return map;
        }

        int d = gcd(dx, dy);
        map.put(dy / d, dx / d);
        return map;
    }

    public int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}

// 优化
class Solution {
    public int maxPoints(Point[] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Map<Integer, Integer>, Integer> m = new HashMap<>();
            int duplicate = 1;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    duplicate++;
                    continue;
                }
                int dx = points[j].x - points[i].x;
                int dy = points[j].y - points[i].y;
                int d = gcd(dx, dy);
                Map<Integer, Integer> t = new HashMap<>();
                t.put(dx / d, dy / d);
                m.put(t, m.getOrDefault(t, 0) + 1);
            }
            res = Math.max(res, duplicate); // 不是很理解
            for (Map.Entry<Map<Integer, Integer>, Integer> e : m.entrySet()) {
                res = Math.max(res, e.getValue() + duplicate);
            }
        }
        return res;
    }

    public int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}
