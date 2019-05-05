/**
 * 题目描述
输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 */


// 就是先反转链表，然后存入到res中即可。
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        if (listNode == null)
            return res;
        
        ListNode pre = null;
        ListNode curr = listNode;
        
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        
        ListNode head = pre;
        while (head != null) {
            res.add(head.val);
            head = head.next;
        }
        
        return res;
        
    }
}