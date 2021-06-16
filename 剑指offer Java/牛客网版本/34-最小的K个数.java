/**
 * 题目描述
输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */

// 类似LeetCode第215题
public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (k == 0 || input == null || input.length == 0 || k > input.length)
            return res;
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < input.length; i++) {
            pq.add(input[i]);
            if (pq.size() > k)
                pq.poll();
        }

        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }

        return res;
    }
}