/**
 * 253. 会议室 II
给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回 所需会议室的最小数量 。

 

示例 1：

输入：intervals = [[0,30],[5,10],[15,20]]
输出：2
示例 2：

输入：intervals = [[7,10],[2,4]]
输出：1
 

提示：

1 <= intervals.length <= 10^4
0 <= starti < endi <= 10^6
 */

// 开会也可以理解成坐公交，都是占用某个资源。
// 假设intervals = [[0,30],[5,10],[15,20]]
// 第一个人从0上车，从30下车，第二个人从5上车，10下车。。。
// 我们的问题转化为最多车上有几个人（也就是最多有多少会议室）。
// 显然：上车，车上人数+1；下车，车上人数-1
// 我们把intervals拆解一下
// 上车：[0, 1], [5, 1], [15, 1]
// 下车：[10, -1], [20, -1], [30, -1]
// 然后按照第一个数把上下车排好序
// 人数 1    2     1     2     1      0
//      0----5----10----15----20-----30
// 变化 +1   +1    -1    +1    -1    -1
// 最多车上两个人。
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int[] startTime = new int[intervals.length];
        int[] endTime = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            startTime[i] = intervals[i][0];
            endTime[i] = intervals[i][1];
        }

        Arrays.sort(startTime);
        Arrays.sort(endTime);

        int res = 0;
        int curr = 0;
        int startTimeIdx = 0;
        int endTimeIdx = 0;
        while (startTimeIdx < intervals.length && endTimeIdx < intervals.length) {
            if (startTime[startTimeIdx] < endTime[endTimeIdx]) {
                curr++;
                startTimeIdx++;
                res = Math.max(res, curr);
            } else {
                curr--;
                endTimeIdx++;
            }
        }

        return res;
    }
}