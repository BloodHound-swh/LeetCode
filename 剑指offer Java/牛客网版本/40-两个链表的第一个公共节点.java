/**
 * 题目描述
输入两个链表，找出它们的第一个公共结点。
 */

/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null)
            return null;

        int len1 = Len(pHead1);
        int len2 = Len(pHead2);

        if (len1 > len2) {
            while (len1 != len2) {
                pHead1 = pHead1.next;
                len1--;
            }
        } else if (len1 < len2) {
            while (len1 != len2) {
                pHead2 = pHead2.next;
                len2--;
            }
        }

        while (pHead1 != pHead2) {
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }

        return pHead1;
    }

    public int Len(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }
}