/**
 *  复制带随机指针的链表
给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

要求返回这个链表的深度拷贝。 
 */


/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

//  https://www.youtube.com/watch?v=kGfsMookkzw
// 用Hash map，第一遍遍历生成所有新节点时同时建立一个原节点和新节点的哈希表
// 第二遍给随机指针赋值(连接随机指针)
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = head;
        while (cur != null) {
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
}

// 方法二需要自己画图理解
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode cur = head;
        // 1.新节点接到原对应节点的后面。
        while (cur != null) {
            RandomListNode node = new RandomListNode(cur.label);
            node.next = cur.next;
            cur.next = node; // 画图即可辨析
            cur = node.next;
        }
        cur = head;
        // 2.参照原节点的random，改变新节点的rondom
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next; // 注意为什么这里是cur.next.next
        }
        // 3.将两部分分离
        RandomListNode clonedHead = head.next;
        cur = head.next;
        while (head != null) {
            head.next = head.next.next;
            head = head.next;
            if (cur.next != null) {
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }
        return clonedHead;
    }
}