/**
 * 回文链表

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

 
// 未看答案版，偶数时正确，奇数时出现了问题，并且对于后半段应该反转才对
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;

        while (p2 != null && p2.next != null) { // p2 != null 不可以少，因为p2 == null 但是p2.next 却不一定为null，考虑内存关系
            p1 = p1.next;
            p2 = p2.next.next;
        }

        while (p1 != null) {
            if (head.val != p1.val)
                return false;
            head = head.next;
            p1 = p1.next;
        }

        return true;
    }
}

// 答案一
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

        while (fast != null && fast.next != null) {
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

// 答案二，先直接反转链表再比较，注意反转链表是先复制再反转
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode cur = head;
        ListNode pre = null;

        while (cur != null) {
            ListNode dummmy = new ListNode(cur.val); // 不可以对原链表进行操作
            dummmy.next = pre;
            pre = dummmy;
            cur = cur.next;
        }

        while (pre != null) {
            if (pre.val != head.val)
                return false;
            pre = pre.next;
            head = head.next;
        }
        return true;
    }
}

// 答案三，用stack
class Solution {
    public boolean isPalindrome(ListNode head) {

        // 使用栈，此处 O(n) 时间复杂度和 O(n) 空间复杂度
        Stack<Integer> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }

        while (!stack.isEmpty() && head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }

        return true;
    }
}