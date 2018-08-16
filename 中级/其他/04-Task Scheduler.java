/**
 * Task Scheduler
给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。

然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

你需要计算完成所有任务所需要的最短时间。

示例 1：

输入: tasks = ["A","A","A","B","B","B"], n = 2
输出: 8
执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
注：

任务的总个数为 [1, 10000]。
n 的取值范围为 [0, 100]。
 */



// 三种方法都是用的一个思路，只是写法不同而已
// 按照出现频率最多（假设频率为k）的将其分为了k块，然后每一轮向这k个区间个插入1个
// 若是最高频率元素为s个，那么最后就加上s
// 若是最高频率都用完了，但是还是没有把所有task执行完，那么总时间就是task的长度（我也不知道怎么证明）
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        int maxFreq = 0, maxFreqCount = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > maxFreq) {
                maxFreq = freq[i];
                maxFreqCount = 1;
            } else if (freq[i] == maxFreq) {
                maxFreqCount++;
            }
        }
        return Math.max(tasks.length, (maxFreq - 1) * (n + 1) + maxFreqCount);
    }
}

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] nums = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            nums[tasks[i] - 'A']++;
        }
        Arrays.sort(nums);
        int max = nums[25];
        int count = 0;
        for (int i = 25; i >= 0; i--) {
            if (nums[i] == max)
                count++;
            else
                break;
        }
        return Math.max(tasks.length, (max - 1) * (n + 1) + count);
    }
}

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] c = new int[26];
        for (char t : tasks) {
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while (i >= 0 && c[i] == c[25])
            i--;

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }
}