/**
 * 相交链表

编写一个程序，找到两个单链表相交的起始节点。

 

例如，下面的两个链表：

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
在节点 c1 开始相交。

 

注意：

如果两个链表没有交点，返回 null.
在返回结果后，两个链表仍须保持原有的结构。
可假定整个链表结构中没有循环。
程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */

// 未看答案版，很不简洁
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        int k = 0;
        while (p1 != null && p2 != null) {
            if (p1 == p2)
                return p1;
            p1 = p1.next;
            p2 = p2.next;
        }
        if (p1 != null) {
            while (p1 != null) {
                p1 = p1.next;
                k++;
            }
            while (k > 0) {
                headA = headA.next;
                k--;
            }
            while (headA != null) {
                if (headA == headB)
                    return headA;
                headA = headA.next;
                headB = headB.next;
            }
        } else if (p2 != null) {
            while (p2 != null) {
                p2 = p2.next;
                k++;
            }
            while (k > 0) {
                headB = headB.next;
                k--;
            }
            while (headB != null) {
                if (headA == headB)
                    return headB;
                headA = headA.next;
                headB = headB.next;
            }
        }
        return null;
    }
}

// 答案一
// 先取得两个链表的长度，将长度较长的链表多出的部分去掉，然后同步往后走，相同时返回即可
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        int lenA = len(headA);
        int lenB = len(headB);

        if (lenA > lenB) {
            while (lenA != lenB) {
                headA = headA.next;
                lenA--;
            }
        } else {
            while (lenB != lenA) {
                headB = headB.next;
                lenB--;
            }
        }

        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    public int len(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }
}

// 直接从两条链开始往后走，哪个先走到末尾，就从另一条链表的开头走，当两个指针都交换了链表之后，恰恰就是方法一的情况
// 此时，俩指针过滤了较长链表多余的元素，此时看俩指针之后的链表长度相同
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}