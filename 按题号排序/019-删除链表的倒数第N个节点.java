/**
 *  删除链表的倒数第N个节点

给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：

给定的 n 保证是有效的。

进阶：

你能尝试使用一趟扫描实现吗？
 */

// 未看答案版没有做出来，忘记使用双指针法来解决一次遍历的问题
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head.next != null) {
            head = head.next;
        }
    }
}

// 使用双指针完成上面的方法，看了答案后，我觉得为了规范代码，使用dummy更规范一些
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode curr = dummy;

        for (int i = 0; i < n; i++) {
            if (pre == null)
                return null;
            pre = pre.next;
        }

        while (pre.next != null) {
            pre = pre.next;
            curr = curr.next;
        }

        curr.next = curr.next.next;
        return dummy.next;
    }
}

// 答案一，双指针
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0)
            return null;
        ListNode p = new ListNode(0);
        p.next = head;

        ListNode delete = p;
        for (int i = 0; i < n; i++) {
            if (head == null)
                return null;
            head = head.next;
        }
        while (head != null) {
            head = head.next;
            delete = delete.next;
        }
        delete.next = delete.next.next;
        return p.next;
    }
}

// 同答案一
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return head;
        ListNode reverse = head;
        ListNode count = head;
        for (int i = 0; i < n; i++)
            reverse = reverse.next;
        if (reverse == null)
            return head.next;
        while (reverse.next != null) { // 注意这里的判定条件
            reverse = reverse.next;
            count = count.next;
        }
        count.next = count.next.next;
        return head;
    }
}