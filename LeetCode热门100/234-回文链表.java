/**
 * 234. 回文链表

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

// 找到中间位置，然后对后半部分进行反转链表的操作
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;

        ListNode middle = findMiddle(head);
        middle.next = reverseList(middle.next);
        ListNode p = middle.next;
        while (p != null) { // 后半段的长度一定是小于等于前半段的
            if (p.val != head.val)
                return false;
            p = p.next;
            head = head.next;
        }
        return true;
    }

    public ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) { // 注意这里的判定条件
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

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

// 简单点，stack
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        Stack<Integer> stack = new Stack<>();
        ListNode p = head;
        while (p != null) {
            stack.push(p.val);
            p = p.next;
        }

        while (head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }
}