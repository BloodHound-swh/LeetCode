/**
 * 剑指 Offer 22. 链表中倒数第k个节点
输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。

 

示例：

给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

 // 双指针
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        ListNode slow = head;
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}

// LeetCode提交答案中，速度快且内存占用小的答案
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode temp = head;
        while(k != 0){
            temp = temp.next;
            k--;
        }
        while(temp != null){
            temp = temp.next;
            head = head.next;
        }
        return head;
    }
}