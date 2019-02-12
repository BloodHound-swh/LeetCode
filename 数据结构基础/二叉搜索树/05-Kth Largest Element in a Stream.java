/**
 * Kth Largest Element in a Stream
设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。

你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。

示例:

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
说明: 
你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 */


// 我们其实只关心前K大个数字就可以了，所以我们可以使用一个最小堆来保存前K个数字
// 当再加入新数字后，最小堆会自动排序，然后把排序后的最小的那个数字去除，则堆中还是K个数字，返回的时候只需返回堆顶元素即可
class KthLargest {

    PriorityQueue<Integer> q;
    int k;

    public KthLargest(int k, int[] nums) {
        q = new PriorityQueue<>();
        this.k = k;
        for (int num : nums) {
            q.offer(num);
            if (q.size() > k)
                q.poll();
        }
    }

    public int add(int val) {
        q.offer(val);
        if (q.size() > k)
            q.poll();
        return q.peek();
    }
}

// 有些类似方法一，但是在PriorityQueue的处理上有区别
class KthLargest {
    PriorityQueue<Integer> queue = null;
    int size = 0;

    public KthLargest(int k, int[] nums) {
        queue = new PriorityQueue<Integer>(k);
        size = k;

        for (Integer num : nums)
            add(num);
    }

    public int add(int val) {
        if (queue.size() < size)
            queue.offer(val);
        else {
            if (queue.peek() < val) {
                queue.poll();
                queue.offer(val);
            }

        }
        return queue.peek();
    }
}