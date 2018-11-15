/**
 * 奇偶链表

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

示例 1:

输入: 1->2->3->4->5->NULL
输出: 1->3->5->2->4->NULL
示例 2:

输入: 2->1->3->5->6->4->7->NULL 
输出: 2->3->6->7->1->5->4->NULL
说明:

应当保持奇数节点和偶数节点的相对顺序。
链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。

 */


// 未看答案版，while中的条件是查看了答案的。。。
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (odd.next != null && even.next != null) { // 注意条件的取法
            odd.next = odd.next.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}

// 答案一
// 单独“分出”两个链表，然后头尾相接
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode odd = head, even = head.next, evenHead = head.next;
        while (odd.next != null && odd.next.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;
            if (even != null && even.next != null) { // 一开始不是很明白，为啥用even.next != null && even.next.next != null 不行
                                                     // 原来是尾节点的next指针必须指向NULL，否则无法判断链表结束
                even.next = even.next.next;
                even = even.next;
            }
        }
        odd.next = evenHead;
        return head;
    }
}

// 答案二
// 根据奇后是偶，偶后是奇的性质
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode odd = head, even = head.next, evenHead = even;
        while (odd.next != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}