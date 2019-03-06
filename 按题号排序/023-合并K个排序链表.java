/**
 * 合并K个排序链表

合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:

输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
 */

// 链表题不要仅仅局限于指针操作，还可以用其它的集合工具作为辅助


// 使用优先队列
// 将每个list的第一个node放入pq中，poll出最小的元素，在将这个元素的next节点加入pq中，再取出这时的最小元素
// 以此类推
// 注意Comparator需要自己重写
class Solution {
    class NodeComparator implements Comparator<ListNode> {
        public int compare(ListNode a, ListNode b) {
            return a.val - b.val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        if (lists == null || lists.length == 0)
            return dummy.next;
        int size = lists.length;
        ListNode curr = dummy;
        NodeComparator cmp = new NodeComparator();
        PriorityQueue<ListNode> pq = new PriorityQueue(cmp);

        for (int i = 0; i < size; i++) {
            if (lists[i] != null)
                pq.add(lists[i]);
        }

        while (pq.size() != 0) {
            ListNode node = pq.poll();
            curr.next = node;
            curr = curr.next;
            if (node.next != null)
                pq.add(node.next);
        }

        return dummy.next;
    }
}