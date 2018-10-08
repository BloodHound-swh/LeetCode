/**
 *  移除链表元素

删除链表中等于给定值 val 的所有节点。

示例:

输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5
您是否在真实的面试环节中遇到过这道题目呢？  

 */

// 未看答案版，在输入[1], val = 2时出现了问题，应该返回[1],而不是[]。但输入[1],val = 1应该返回[]，而不是[1]
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = head;
        if (head == null) {
            return null;
        }
        while (dummy.next != null) {
            if (dummy.next.val == val) {
                dummy.next = dummy.next.next;
            } else {
                dummy = dummy.next;
            }
        }
        return head;
    }
}

// 答案一，针对上面的问题，可以把head前面再加一个dummy节点即可
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head.next != null) {
            if (head.next.val == val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }

        return dummy.next;
    }
}

// 答案二，递归
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}