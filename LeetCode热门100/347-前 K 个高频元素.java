/**
 * 347. 前 K 个高频元素
给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

 

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]
 

提示：

1 <= nums.length <= 10^5
k 的取值范围是 [1, 数组中不相同的元素的个数]
题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 

进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 */


// 使用map记录出现次数，再使用一个size为k的优先队列来处理频率前k的数字
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int n = entry.getKey();
            int times = entry.getValue();
            // int n = entry.getKey(), times = entry.getValue();
            if (pq.size() == k) {
                if (pq.peek()[1] < times) {
                    pq.poll();
                    pq.offer(new int[]{n, times});
                }
            } else {
                pq.offer(new int[]{n, times});
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll()[0];
        }

        return res;
    }
}

// 使用数组下标作为数字出现的次数，数组元素为一个列表
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        // corner case: if there is only one number in nums, we need the bucket has
        // index 1.
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int n : map.keySet()) {
            int freq = map.get(n);
            if (bucket[freq] == null)
                bucket[freq] = new LinkedList<>();
            bucket[freq].add(n);
        }

        List<Integer> res = new LinkedList<>();
        for (int i = bucket.length - 1; i > 0 && k > 0; --i) {
            if (bucket[i] != null) {
                List<Integer> list = bucket[i];
                res.addAll(list);
                k -= list.size(); // 因为有些数字出现的频率可能相同
            }
        }

        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}