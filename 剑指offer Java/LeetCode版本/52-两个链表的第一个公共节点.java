/**
 * 剑指 Offer 52. 两个链表的第一个公共节点
输入两个链表，找出它们的第一个公共节点。

如下面的两个链表：
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
        if (headA == null || headB == null) {
            return null;
        }

        int lenA = len(headA);
        int lenB = len(headB);

        if (lenA > lenB) {
            while (lenA > lenB) {
                headA = headA.next;
                lenA--;
            }
        } else if (lenA < lenB) {
            while (lenA < lenB) {
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

    public int len(ListNode p) {
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }

        return len;
    }
}

//  两个链表长度分别为L1+C、L2+C， C为公共部分的长度 
// 第一个人走了L1+C步后，回到第二个人起点走L2步
// 第二个人走了L2+C步后，回到第一个人起点走L1步。 
// 当两个人走的步数都为L1+L2+C时就两个家伙就相爱了
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }

        return pA;
    }
}