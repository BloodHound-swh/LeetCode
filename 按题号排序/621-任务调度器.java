/**
 * 任务调度器

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

// 未看答案，大概能猜到是插入法（实际上是贪心算法），但是具体怎么写不会


// 三种答案都是用的一个思路，只是写法不同而已

// 答案一
// https://leetcode.cn/problems/task-scheduler/solution/tong-zi-by-popopop/
// 按照出现频率最多（假设频率为k）的将其分为了k块，然后每一轮向这k个区间个插入其他任务，如果不够n则加入idle凑齐n，这样每个区间最大长度就是n + 1
// 若是最高频率元素为s个，那么最后一个区间的长度就为s，因为这s个频率相同的任务经过前面的分配都只剩下一个了。
// 若是最高频率都用完了，但是还是没有把所有task执行完，那么总时间就是task的长度（我也不知道怎么证明）
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        int maxFreq = 0;
        int maxFreqTask = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] > maxFreq) {
                maxFreq = freq[i];
                maxFreqTask = 1;
            } else if (freq[i] == maxFreq) {
                maxFreqTask++;
            }
        }

        return Math.max(tasks.length, (maxFreq - 1) * (n + 1) + maxFreqTask);
    }
}

// https://leetcode.cn/problems/task-scheduler/solution/tong-zi-by-popopop/
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