/**
 * 反转链表

反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */

// 未看答案没有做出来，原来除了双指针之外还有三指针法

// 答案一，三指针。每次将中间的指针的next指向它的pre，然后三个指针向后平移一位，达到反转的目的。
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode pre = null;
        ListNode curr = head;
        ListNode next = head.next;

        while (curr != null) {
            curr.next = pre;
            pre = curr;
            curr = next;
            if (next != null)
                next = next.next;
        }

        return pre;
    }
}

// 答案二，优化三指针，使用temp
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }

        return pre;
    }
}