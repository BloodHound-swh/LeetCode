/**
 * 621. 任务调度器
给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。

然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

你需要计算完成所有任务所需要的 最短时间 。

 

示例 1：

输入：tasks = ["A","A","A","B","B","B"], n = 2
输出：8
解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 
示例 2：

输入：tasks = ["A","A","A","B","B","B"], n = 0
输出：6
解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
诸如此类
示例 3：

输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
输出：16
解释：一种可能的解决方案是：
     A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 

提示：

1 <= task.length <= 10^4
tasks[i] 是大写英文字母
n 的取值范围为 [0, 100]
 */



// https://leetcode.cn/problems/task-scheduler/solution/tong-zi-by-popopop/
// 按照出现频率最多（假设频率为k）的将其分为了k块，然后每一轮向这k个区间个插入其他任务，如果不够n则加入idle凑齐n，这样每个区间最大长度就是n + 1
// 若是最高频率元素为s个，那么最后一个区间的长度就为s，因为这s个频率相同的任务经过前面的分配都只剩下一个了。
// 若是最高频率都用完了，但是还是没有把所有task执行完，那么总时间就是task的长度（我也不知道怎么证明）
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char c : tasks) {
            freq[c - 'A']++;
        }

        int maxTaskCount = 0;
        int maxTaskFreq = 0;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > maxTaskFreq) {
                maxTaskFreq = freq[i];
                maxTaskCount = 1;
            } else if (freq[i] == maxTaskFreq) {
                maxTaskCount++;
            }
        }

        return Math.max(tasks.length, (maxTaskFreq - 1) * (n + 1) + maxTaskCount);
    }
}