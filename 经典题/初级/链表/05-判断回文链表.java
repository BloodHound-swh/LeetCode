/*
回文链表
请判断一个链表是否为回文链表。

示例 1:

输入: 1->2
输出: false
示例 2:

输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */

// 遍历整个链表，将链表每个节点的值记录在数组中，再判断数组是不是一个回文数组，时间复杂度为O（n），但空间复杂度也为O（n），不满足空间复杂度要求。
// 利用栈先进后出的性质，将链表前半段压入栈中，再逐个弹出与链表后半段比较。时间复杂度O（n），但仍然需要n/2的栈空间，空间复杂度为O（n）。
// 反转链表法，将链表后半段原地翻转，再将前半段、后半段依次比较，判断是否相等，时间复杂度O（n），空间复杂度为O（1）满足题目要求
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;
        ListNode middle = findMiddle(head);
        middle.next = reverseList(middle.next);
        ListNode p = head;
        ListNode q = middle.next;
        while (p != null && q != null) {
            if (p.val != q.val)
                return false;
            p = p.next;
            q = q.next;
        }
        return true;
        // 这样写可能更简洁
        // while (p1 != null && p2 != null && p1.val == p2.val) {
        // p1 = p1.next;
        // p2 = p2.next;
        // }
        // return p2 == null;
    }

    public ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode pre = null;
        ListNode current = head;
        ListNode next = current.next;
        while (current != null) {
            current.next = pre;
            pre = current;
            current = next;
            if (current != null)
                next = current.next;
        }
        return pre;
    }
}
