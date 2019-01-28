/**
 * 设计哈希映射
不使用任何内建的哈希表库设计一个哈希映射

具体地说，你的设计应该包含以下的功能

put(key, value)：向哈希映射中插入(键,值)的数值对。如果键对应的值已经存在，更新这个值。
get(key)：返回给定的键所对应的值，如果映射中不包含这个键，返回-1。
remove(key)：如果映射中存在这个键，删除这个数值对。

示例：

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);          
hashMap.put(2, 2);         
hashMap.get(1);            // 返回 1
hashMap.get(3);            // 返回 -1 (未找到)
hashMap.put(2, 1);         // 更新已有的值
hashMap.get(2);            // 返回 1 
hashMap.remove(2);         // 删除键为2的数据
hashMap.get(2);            // 返回 -1 (未找到) 

注意：

所有的值都在 [1, 1000000]的范围内。
操作的总数目在[1, 10000]范围内。
不要使用内建的哈希库。
 */


// 方法一
// 既然题目中说了数字的范围不会超过1000000，那么我们就申请这么大空间的数组，只需将数组的初始化值改为-1即可。
// 空间足够大了，我们就可以直接建立映射，移除时就将映射值重置为-1，由于默认值是-1，所以获取映射值就可以直接去
class MyHashMap {
    int[] arr;

    /** Initialize your data structure here. */
    public MyHashMap() {
        arr = new int[1000000];
        Arrays.fill(arr, -1);
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        arr[key] = value;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map
     * contains no mapping for the key
     */
    public int get(int key) {
        if (arr[key] != -1)
            return arr[key];
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping
     * for the key
     */
    public void remove(int key) {
        arr[key] = -1;
    }
}

// 方法二
// 我们可以来适度的优化一下空间复杂度，由于存入HashMap的映射对儿也许不会跨度很大，那么直接就申请长度为1000000的数组可能会有些浪费
// 那么我们其实可以使用1000个长度为1000的数组来代替，那么就要用个二维数组啦，
// 实际上开始我们只申请了1000个空数组，对于每个要处理的元素，我们首先对1000取余，得到的值就当作哈希值，对应我们申请的那1000个空数组的位置，
// 在建立映射时，一旦计算出了哈希值，我们将对应的空数组resize为长度1000，然后根据哈希值和key/1000来确定具体的加入映射值的位置。
// 获取映射值时，计算出哈希值，若对应的数组不为空，直接返回对应的位置上的值。
// 移除映射值一样的，先计算出哈希值，如果对应的数组不为空的话，找到对应的位置并重置为-1。
class MyHashMap {

    int buckets = 1000;
    int itemPerBuckets = 1000;
    int[][] set;

    /** Initialize your data structure here. */
    public MyHashMap() {
        set = new int[buckets][];
    }

    public int hashKey(int key) {
        return key / buckets;
    }

    public int pos(int key) {
        return key % itemPerBuckets;
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        if (set[hashKey(key)] == null) {
            set[hashKey(key)] = new int[itemPerBuckets];
            Arrays.fill(set[hashKey(key)], -1);
        }
        set[hashKey(key)][pos(key)] = value;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map
     * contains no mapping for the key
     */
    public int get(int key) {
        if (!containsKey(key))
            return -1;
        return set[hashKey(key)][pos(key)];
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping
     * for the key
     */
    public void remove(int key) {
        if (containsKey(key))
            set[hashKey(key)][pos(key)] = -1;
    }

    public boolean containsKey(int key) {
        return set[hashKey(key)] != null && set[hashKey(key)][pos(key)] != -1;
    }
}



// 使用链表
// 有点复杂，感觉让我复现有些困难
class MyHashMap {
    ListNode[] map;

    /** Initialize your data structure here. */
    public MyHashMap() {
        map = new ListNode[10000];
    }

    public int hash(int key) {
        return Integer.hashCode(key) % map.length;
    }

    public ListNode findPrev(int key, ListNode bucket) {
        ListNode curr = bucket, prev = null;
        while (curr != null && curr.key != key) {
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hashkey = hash(key);
        if (map[hashkey] == null) {
            map[hashkey] = new ListNode(-1, -1);
        }
        ListNode prev = findPrev(key, map[hashkey]);
        if (prev.next == null)
            prev.next = new ListNode(key, value);
        else
            prev.next.value = value;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map
     * contains no mapping for the key
     */
    public int get(int key) {
        int hashkey = hash(key);
        if (map[hashkey] == null)
            return -1;
        ListNode prev = findPrev(key, map[hashkey]);
        return prev.next == null ? -1 : prev.next.value;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping
     * for the key
     */
    public void remove(int key) {
        int hashkey = hash(key);
        if (map[hashkey] == null)
            return;
        ListNode prev = findPrev(key, map[hashkey]);
        if (prev.next == null)
            return;
        prev.next = prev.next.next;
    }

    class ListNode {
        int key, value;
        ListNode next;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
