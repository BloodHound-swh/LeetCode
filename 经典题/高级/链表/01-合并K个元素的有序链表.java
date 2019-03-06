/*
 * 合并K个元素的有序链表
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

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */

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
        ListNode current = dummy;
        NodeComparator cmp = new NodeComparator();
        PriorityQueue<ListNode> pq = new PriorityQueue(cmp);
        for (int i = 0; i < size; i++) { // 将所有链表头结点加入到堆中
            if (lists[i] != null)
                pq.add(lists[i]);
        }
        while (pq.size() != 0) {
            ListNode node = pq.poll();
            current.next = node;
            current = current.next;
            if (node.next != null)
                pq.add(node.next);
        }
        return dummy.next;
    }
}

// 归并排序，不建议
class Solution {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return work(lists, 0, lists.length - 1);
    }

    // 利用归并排序的思想
    private static ListNode work(ListNode[] lists, int left, int right) {
        int mid = (left + right) / 2;
        if (left < right) {
            ListNode l1 = work(lists, left, mid);
            ListNode l2 = work(lists, mid + 1, right);
            return mergeTwo(l1, l2);
        }
        return lists[left]; // 如果left==right则直接返回左面的链表
    }

    private static ListNode mergeTwo(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwo(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwo(l2.next, l1);
            return l2;
        }
    }
}