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


// 维护一个最大堆，一个最小堆。最大堆存的是到目前为止较小的那一半数，最小堆存的是到目前为止较大的那一半数，这样中位数只有可能是堆顶或者堆顶两个数的均值。
class MedianFinder {

    PriorityQueue<Integer> increaseQueue;
    PriorityQueue<Integer> decreaseQueue;

    /** initialize your data structure here. */
    public MedianFinder() {
        increaseQueue = new PriorityQueue<Integer>();
        decreaseQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
    }

    public void addNum(int num) {
        increaseQueue.offer(num);
        decreaseQueue.offer(increaseQueue.poll());
        if (increaseQueue.size() < decreaseQueue.size()) {
            increaseQueue.offer(decreaseQueue.poll());
        }
    }

    public double findMedian() {
        if (increaseQueue.size() == decreaseQueue.size()) {
            return (increaseQueue.peek() + decreaseQueue.peek()) * 0.5;
        } else {
            return increaseQueue.peek();
        }
    }
}
