/**
 * 前K个高频元素

给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]
说明：

你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */

// 未看答案没有做出


// 答案一
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else
                map.put(nums[i], 1);
        }
        List res[] = new ArrayList[nums.length + 1];
        for (Integer val : map.keySet()) {
            if (res[map.get(val)] == null) {
                res[map.get(val)] = new ArrayList<>();
            }
            res[map.get(val)].add(val);
        }
        for (int i = res.length - 1; i >= 0; i--) {
            if (res[i] != null && ans.size() < k) {
                ans.addAll(res[i]);
            }

        }
        return ans;
    }
}

// 答案二，其实思路差不多
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
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

        return res;
    }
}

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