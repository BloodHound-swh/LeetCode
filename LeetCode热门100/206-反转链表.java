/**
 * 206. 反转链表

反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = head;
        ListNode pre = null;
        while (dummy != null) {
            ListNode tmp = dummy.next;
            dummy.next = pre;
            pre = dummy;
            dummy = tmp;
        }

        return pre;
    }
}

// 如果觉得上面不好理解，就从三指针开始做。每次将中间的指针的next指向它的pre，然后三个指针向后平移一位，达到反转的目的。
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