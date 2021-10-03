/**
 * 剑指 Offer 59 - I. 滑动窗口的最大值
给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 

提示：

你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。


 */


// 使用双向队列
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length <= 0 || k <= 0) {
            return new int[0];
        }

        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        // 形成窗口前，只要新元素大于队尾元素就把队尾元素弹出，保证队列是递增的
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }

        res[0] = deque.peekFirst();
        
        // 形成窗口后，多一个判断——窗口的第一个元素正好是队列中的第一个元素时时需要弹出
        for (int i = k; i < nums.length; i++) {
            if (nums[i - k] == deque.peekFirst()) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }

        return res;
    }
}

// 双向队列中也可以存的是原数组中的下标，这样我们既可以知道这个数的值，也可以知道该数是不是窗口最左边的数。
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return new int[0];
        Deque<Integer> deque = new LinkedList<>(); // 直接用LinkedList也行
        int[] res = new int[nums.length + 1 - k];
        for (int i = 0; i < nums.length; i++) {
            // 每当新数进来时，如果发现队列头部的数的下标，是窗口最左边数的下标，则扔掉
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.poll();
            }
            // 把队列尾部所有比新数小的都扔掉，保证队列是降序的
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }
            // 加入新数
            deque.offerLast(i);
            // 队列头部就是该窗口内第一大的
            if (i + 1 >= k) {
                res[i + 1 - k] = nums[deque.peek()];
            }
        }
        return res;
    }
}

// 也可以不用双向队列，用index记录窗口中最大值的坐标，当坐标不在窗口内的时候需要在窗口内遍历找出最大值
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        int max = Integer.MIN_VALUE;
        int index = 0;
        int count = 0;
        for (int i = 0; i < k; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        res[count++] = max;

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            } else if (index < i - k + 1) {
                max = Integer.MIN_VALUE;
                for (int j = i; j > i - k; j--) {
                    if (nums[j] > max) {
                        max = nums[j];
                        index = j;
                    }
                }
            }
            res[count++] = max;
        }

        return res;
    }
}

// 使用优先队列，缺点是时间复杂度搞
// 维护一个大小为K的最大堆，依此维护一个大小为K的窗口，每次读入一个新数，都把堆中窗口最左边的数扔掉，再把新数加入堆中，这样堆顶就是这个窗口内最大的值。
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return new int[0];

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        int[] res = new int[nums.length + 1 - k];
        for (int i = 0; i < nums.length; i++) {
            if (i >= k)
                pq.remove(nums[i - k]);
            pq.offer(nums[i]);
            if (i + 1 - k >= 0)
                res[i + 1 - k] = pq.peek();
        }

        return res;
    }
}