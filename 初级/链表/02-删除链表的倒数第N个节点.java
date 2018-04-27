/**
删除链表的倒数第N个节点
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
示例：
给定一个链表: 1->2->3->4->5, 和 n = 2.
当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：
给定的 n 保证是有效的。
进阶：
你能尝试使用一趟扫描实现吗？
 */




/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

 //注意删除节点不能用a = a.next而是b.next = b.next.next
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n<=0)
            return null;
        ListNode p = new ListNode(0);
        p.next = head;
        
        ListNode delete = p;
        for(int i=0;i<n;i++){
            if(head == null)
                return null;
            head = head.next;
        }
        while(head != null){
            head = head.next;
            delete = delete.next;
        }
        delete.next = delete.next.next;
        return p.next;
    }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return head;
        ListNode reverse = head;
        ListNode count = head;
        for(int i = 0; i < n; i++)
            reverse = reverse.next;
        if(reverse == null)
            return head.next;
        while(reverse.next != null){
            reverse = reverse.next;
            count = count.next;
        }
        count.next = count.next.next;
        return head;
    }
}