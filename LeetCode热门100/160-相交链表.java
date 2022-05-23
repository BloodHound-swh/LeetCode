/**
 * 160. 相交链表

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


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

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

// 直接从两条链开始往后走，哪个先走到末尾，就从另一条链表的开头走，当两个指针都交换了链表之后，此时到各自链表的终点的距离都是相同的
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode p1 = headA;
        ListNode p2 = headB;

        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }

        return p1;
    }
}