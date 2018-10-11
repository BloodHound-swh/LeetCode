// 未看答案版
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }
        return false;
    }
}


// 1. 空链表不成环
// 2. 一个节点自环
// 3. 一条链表完整成环
// 不能开额外的空间，即空间复杂度是o(1)。该问题是经典面试问题，其标准解法是用两个指针，一快一慢，如果在快的指针能够追上慢的指针，则有环，否则无环。

// 答案一，和未看答案版相同
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}

// 答案二
// 使用快慢指针，快指针每次走两步，慢指针每次走一步。若是有环出现，那么快指针终究会比慢指针多走一圈
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != slow) {
            if (fast == null || fast.next == null)
                return false;
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }
}