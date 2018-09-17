/**
 * 前K个高频元素
给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

例如，

给定数组 [1,1,1,2,2,3] , 和 k = 2，返回 [1,2]。

注意：

你可以假设给定的 k 总是合理的，1 ≤ k ≤ 数组中不相同的元素的个数。
你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */


 
// 使用的是桶排序，先用map将数字与它的频率存储下来，然后在频率-坐标放到一个数组之中，按照评率从后往前输出
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        ArrayList<Integer>[] arr = (ArrayList<Integer>[]) new ArrayList[max + 1];
        for (int i = 1; i <= max; i++) {
            arr[i] = new ArrayList<Integer>();
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            int number = entry.getKey();
            arr[count].add(number);
        }
        List<Integer> result = new ArrayList<Integer>();
        for (int j = max; j >= 1; j--) {
            if (arr[j].size() > 0) {
                for (int a : arr[j]) {
                    result.add(a);
                    if (result.size() == k) {
                        return result;
                    }
                }
            }
        }
        return result;
    }
}

// 优化
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

// 优化2
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
                k -= list.size();
            }
        }

        return res;
    }
}