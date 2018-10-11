/*
环形链表
给定一个链表，判断链表中是否有环。

进阶：
你能否不使用额外空间解决此题？
 */

/*
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

//由于每一个父亲只有可能有一个孩子，故这里的环实际上是指list中某一个节点的孩子同时也是它自己或者他的祖先。 这个问题需要注意几种情况：
// 1. 空链表不成环
// 2. 一个节点自环
// 3. 一条链表完整成环
// 不能开额外的空间，即空间复杂度是o(1)。该问题是经典面试问题，其标准解法是用两个指针，一快一慢，如果在快的指针能够追上慢的指针，则有环，否则无环。
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

// 对上面代码的优化
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