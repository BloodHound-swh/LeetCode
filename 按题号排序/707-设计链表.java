/**
 * 设计链表
设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。

在链表类中实现这些功能：

get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。
deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 

示例：

MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
linkedList.get(1);            //返回2
linkedList.deleteAtIndex(1);  //现在链表是1-> 3
linkedList.get(1);            //返回3
 

提示：

所有值都在 [1, 1000] 之内。
操作次数将在  [1, 1000] 之内。
请不要使用内置的 LinkedList 库。
 */


// 这道题我感觉LeetCode题意不清楚，测试样例有问题，就是在addAtIndex和deleteAtIndex时，index<0的时候怎么办不对头
class MyLinkedList {
    int length;
    Node head;

    class Node {
        int val;
        Node next;

        Node(int x) {
            this.val = x;
        }
    }

    /** Initialize your data structure here. */
    public MyLinkedList() {
        this.length = 0;
        this.head = null;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is
     * invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= this.length) {
            return -1;
        } else {
            int cnt = 0;
            Node curr = head;
            while (cnt != index) {
                curr = curr.next;
                cnt++;
            }
            return curr.val;
        }
    }

    /**
     * Add a node of value val before the first element of the linked list. After
     * the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node p = new Node(val);
        p.next = this.head;
        this.head = p;
        this.length++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        if (this.length == 0) {
            addAtHead(val);
            return;
        }
        Node p = new Node(val);
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = p;
        p.next = null;
        this.length++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index
     * equals to the length of linked list, the node will be appended to the end of
     * linked list. If index is greater than the length, the node will not be
     * inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index == this.length) {
            addAtTail(val);
            return;
        }
        if (index >= this.length) {
            return;
        }
        if (index <= 0) {
            addAtHead(val);
            return;
        }
        
        // LeetCode增加了测试样例，出现了负数，所以要加上去
        // if (index < 0) {
        //     index = index + this.length + 1;
        //     return;
        // }

        Node p = new Node(val);
        Node temp = head;
        int cnt = 0;
        while (cnt != (index - 1)) {
            temp = temp.next;
            cnt++;
        }
        p.next = temp.next;
        temp.next = p;
        this.length++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= this.length) {
            return;
        }
        Node curr = head;
        if (index == 0) {
            head = curr.next;
            return;
        }
        int cnt = 0;
        while (cnt != (index - 1)) {
            curr = curr.next;
            cnt++;
        }
        curr.next = curr.next.next;
        this.length--;
    }
}