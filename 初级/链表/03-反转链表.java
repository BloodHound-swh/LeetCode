/**
反转链表
反转一个单链表。
示例:
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


 //使用pre节点每次拆除一个原链表节点放在新链表的表头
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode pre = null;
        while(head!=null){
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }
}

//使用三个指针，每次将中间的指针的next指向它的pre，然后三个指针向后平移一位，达到反转的目的。
class Solution {
    public ListNode reverseList(ListNode head){
        if(head == null || head.next == null)
            return head;
        
        ListNode pre = null;
        ListNode current = head;
        ListNode next = current.next;
        while(current!=null){
            current.next = pre;
            pre = current;
            current = next;
            if(current != null)
                next = current.next;
        }
        return pre;
    }
}