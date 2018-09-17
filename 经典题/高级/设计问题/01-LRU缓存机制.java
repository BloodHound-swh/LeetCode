/**
 *  LRU 缓存机制
运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。

进阶:

你是否可以在 O(1) 时间复杂度内完成这两种操作？

示例:

LRUCache cache = new LRUCache( 2 / 缓存容量 / );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回  1
cache.put(3, 3);    // 该操作会使得密钥 2 作废
cache.get(2);       // 返回 -1 (未找到)
cache.put(4, 4);    // 该操作会使得密钥 1 作废
cache.get(1);       // 返回 -1 (未找到)
cache.get(3);       // 返回  3
cache.get(4);       // 返回  4
 */


// O(1)的获取速度，这样看来只有哈希表可以胜任，但是哈希表无序的，所以还需要用上队列
// 所以我们将元素存在队列中，并用一个哈希表记录下键值和元素的映射，就可以做到O(1)获取速度，和先进先出的效果
// 可是队列并不支持对任意位置的增删操作。而最适合对任意位置增删操作的数据结构又是什么呢？是链表。
// 我可以用链表来实现一个队列，这样就同时拥有链表和队列的特性了。
// 不过，如果仅用单链表的话，在任意位置删除一个节点还是很麻烦的，要么记录下该节点的上一个节点，要么遍历一遍。
// 所以双向链表是最好的选择。我们用双向链表实现一个队列用来记录每个元素的顺序，用一个哈希表来记录键和值的关系，就行了。
class LRUCache {
    int size;
    int capacity;
    ListNode tail;
    ListNode head;
    Map<Integer, ListNode> map;

    // 构造双向链表的节点
    public class ListNode {
        ListNode prev;
        ListNode next;
        int val;
        int key;

        public ListNode(int k, int v) {
            this.key = k;
            this.val = v;
            this.prev = null;
            this.next = null;
        }
    }

    public LRUCache(int capacity) {
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
        this.capacity = capacity;
        this.map = new HashMap<Integer, ListNode>();
    }

    public int get(int key) {
        ListNode n = map.get(key);
        if (n != null) {
            moveToHead(n);
            return n.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        ListNode n = map.get(key);
        if (n == null) {
            n = new ListNode(key, value);
            attachToHead(n);
            size++;
        } else {
            n.val = value;
            moveToHead(n);
        }
        // 如果更新节点后超出容量，删除最后一个
        if (size > capacity) {
            removeLast();
            size--;
        }
        map.put(key, n);
    }

    // 将一个孤立的节点放在头部
    private void attachToHead(ListNode n) {
        n.next = head.next;
        n.next.prev = n;
        head.next = n;
        n.prev = head;
    }

    // 将链表中的某一个节点放在头部
    private void moveToHead(ListNode n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
        attachToHead(n);
    }

    // 移除链表的最后一个节点
    private void removeLast() {
        ListNode last = tail.prev;
        last.prev.next = tail;
        tail.prev = last.prev;
        map.remove(last.key);
    }
}

/**
* Your LRUCache object will be instantiated and called as such:
* LRUCache obj = new LRUCache(capacity);
* int param_1 = obj.get(key);
* obj.put(key,value);
*/