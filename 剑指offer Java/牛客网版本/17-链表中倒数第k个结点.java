/**
 * 题目描述
输入一个链表，输出该链表中倒数第k个结点。
 */


// 双指针，类似于LeetCode 19题
// 注意不同的是，这里只是展示倒数第k个节点，所以判断p是否为null的位置不同
public class Solution {
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode show = dummy;
        ListNode p = dummy;
        for (int i = 0; i < k; i++) {
            p = p.next;
            if (p == null) {
                return null;
            }
        }

        while (p != null) {
            p = p.next;
            show = show.next;
        }

        return show;
    }
}