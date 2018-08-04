/**
 * 合并区间
给出一个区间的集合，请合并所有重叠的区间。

示例 1:

输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2:

输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */

/**
 * 下面这种解法将起始位置和结束位置分别存到了两个不同的数组starts和ends中，然后分别进行排序，之后用两个指针i和j，初始化时分别指向starts和ends数组的首位置，然后如果i指向starts数组中的最后一个位置，或者当starts数组上i+1位置上的数字大于ends数组的i位置上的数时，此时说明区间已经不连续了，我们来看题目中的例子，排序后的starts和ends为：

starts:    1    2    8    15

ends:     3    6    10    18

红色为i的位置，蓝色为j的位置，那么此时starts[i+1]为8，ends[i]为6，8大于6，所以此时不连续了，将区间[starts[j], ends[i]]，即 [1, 6] 加入结果res中，然后j赋值为i+1继续循环
 */
/**
 * Definition for an interval. public class Interval { int start; int end;
 * Interval() { start = 0; end = 0; } Interval(int s, int e) { start = s; end =
 * e; } }
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new LinkedList<Interval>();
        if (intervals == null)
            return res;
        int[] start = new int[intervals.size()];
        int[] end = new int[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int i = 0;
        while (i < intervals.size()) {
            int st = start[i];
            while (i < intervals.size() - 1 && end[i] >= start[i + 1])
                i++;
            int en = end[i];
            Interval in = new Interval(st, en);
            res.add(in);
            i++;
        }
        return res;
    }
}

// 使用双指针优化
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        int n = intervals.size();
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        // loop through
        List<Interval> res = new ArrayList<Interval>();
        for (int i = 0, j = 0; i < n; i++) { // j is start of interval.
            if (i == n - 1 || starts[i + 1] > ends[i]) {
                res.add(new Interval(starts[j], ends[i]));
                j = i + 1;
            }
        }
        return res;

    }
}