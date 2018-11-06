/*
 * 扁平化多级双向链表
您将获得一个双向链表，除了下一个和前一个指针之外，它还有一个子指针，可能指向单独的双向链表。这些子列表可能有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。

扁平化列表，使所有结点出现在单级双链表中。您将获得列表第一级的头部。

 

示例:

输入:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

输出:
1-2-3-7-8-11-12-9-10-4-5-6-NULL

 */


// 1：如果当前节点有孩子节点，把当前节点的下一个节点放入栈中，并且把当前节点的孩子节点指向当前节点的下一个节点
// 2：否则如果当前节点的下一个节点为空且栈不为空（已经处理完所有的带孩子节点的节点，现在已经到了孩子节点的最后一个节点），那么显而易见的根据题意我们需要把孩子节点的最后一个节点指向当前节点。
// 3：每次都更新当前节点为下一个节点
class Solution {
    public Node flatten(Node head) {
        helper(head);
        return head;
    }

    public Node helper(Node head) {
        Node cur = head, pre = head;
        while (cur != null) {
            if (cur.child == null) {
                pre = cur;
                cur = cur.next;
            } else {
                Node temp = cur.next;
                Node child = helper(cur.child);
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
                child.next = temp;
                if (temp != null)
                    temp.prev = child;
                pre = child;
                cur = temp;
            }
        }
        return pre;
    }
}

class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return head;
        }

        Node cur = head;
        while (cur != null) {
            if (cur.child == null) {
                cur = cur.next;
                continue;
            }

            Node child = cur.child;
            Node childTail = child;
            while (childTail.next != null) {
                childTail = childTail.next;
            }

            cur.child = null;
            child.prev = cur;
            childTail.next = cur.next;
            if (cur.next != null) {
                cur.next.prev = childTail;
            }
            cur.next = child;
            cur = cur.next;
        }

        return head;
    }
}