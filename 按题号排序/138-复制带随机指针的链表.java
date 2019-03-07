/**
 * 复制带随机指针的链表

给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

要求返回这个链表的深拷贝。 

 

示例：



输入：
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

解释：
节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 

提示：

你必须返回给定头的拷贝作为对克隆列表的引用。
 */

// 此题的重点在于那些随机指针应该如何复制，因为第一遍遍历的时候，原链表的随机指针可能指向了它之后的节点，而此时的复制链表却没有那些之后的复制节点


// 答案
//  https://www.youtube.com/watch?v=kGfsMookkzw
// 用Hash map，第一遍遍历生成所有新节点时同时建立一个原节点和新节点的哈希表
// 第二遍给随机指针赋值(连接随机指针)
class Solution {
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node curr = head;
        while (curr != null) {
            Node tmp = new Node();
            tmp.val = curr.val;
            map.put(curr, tmp);
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head);
    }
}

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/