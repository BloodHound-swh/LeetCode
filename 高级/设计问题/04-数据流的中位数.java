/**
 * 数据流的中位数
中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
示例：

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
进阶:

如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 */

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

class MedianFinder {
    PriorityQueue<Integer> maxheap;
    PriorityQueue<Integer> minheap;

    /** initialize your data structure here. */
    public MedianFinder() {
        maxheap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
        minheap = new PriorityQueue<Integer>();
    }

    public void addNum(int num) {
        // 如果最大堆为空，或者该数小于最大堆堆顶，则加入最大堆
        if (maxheap.size() == 0 || num <= maxheap.peek()) {
            // 如果最大堆size已经大于最小堆，则需要平衡一下，最大堆的堆顶放入最小堆之中
            if (maxheap.size() > minheap.size()) {
                minheap.offer(maxheap.poll());
            }
            maxheap.offer(num);
            // 数字大于最小堆的堆顶
        } else if (minheap.size() == 0 || num > minheap.peek()) {
            if (minheap.size() > maxheap.size()) {
                maxheap.offer(minheap.poll());
            }
            minheap.offer(num);
            // 数字在两个堆顶之间
        } else {
            if (maxheap.size() <= minheap.size()) {
                maxheap.offer(num);
            } else {
                minheap.offer(num);
            }
        }
    }

    public double findMedian() {
        if (maxheap.size() > minheap.size()) {
            return maxheap.peek();
        } else if (maxheap.size() < minheap.size()) {
            return minheap.peek();
        } else if (maxheap.isEmpty() && minheap.isEmpty()) {
            return 0;
        } else {
            return (maxheap.peek() + minheap.peek()) * 0.5;
        }
    }
}

// 简洁版，只需理解上面的方法即可
class MedianFinder {

    PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>();
    PriorityQueue<Integer> minheap = new PriorityQueue<Integer>(Collections.reverseOrder());

    // Adds a number into the data structure.
    public void addNum(int num) {
        maxheap.offer(num);
        minheap.offer(maxheap.poll());
        if (maxheap.size() < minheap.size()) {
            maxheap.offer(minheap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        return maxheap.size() == minheap.size() ? (double) (maxheap.peek() + minheap.peek()) / 2.0 : maxheap.peek();
    }
}
