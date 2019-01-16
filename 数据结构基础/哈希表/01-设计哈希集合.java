/**
 * 设计哈希集合
不使用任何内建的哈希表库设计一个哈希集合

具体地说，你的设计应该包含以下的功能

add(value)：向哈希集合中插入一个值。
contains(value) ：返回哈希集合中是否存在这个值。
remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。

示例:

MyHashSet hashSet = new MyHashSet();
hashSet.add(1);         
hashSet.add(2);         
hashSet.contains(1);    // 返回 true
hashSet.contains(3);    // 返回 false (未找到)
hashSet.add(2);          
hashSet.contains(2);    // 返回 true
hashSet.remove(2);          
hashSet.contains(2);    // 返回  false (已经被删除)

注意：

所有的值都在 [1, 1000000]的范围内。
操作的总数目在[1, 10000]范围内。
不要使用内建的哈希集合库。
 */


// 方法一
// 既然题目中说了数字的范围不会超过1000000，那么我们就申请这么大空间的数组
// 这样对于在HashSet中的数字，我们就将其标记为1，不在或者删除了的就标记为0，检测的时候就看其值是否为1即可
class MyHashSet {

    int[] arr;

    /** Initialize your data structure here. */
    public MyHashSet() {
        arr = new int[1000000];
    }

    public void add(int key) {
        arr[key] = 1;
    }

    public void remove(int key) {
        arr[key] = 0;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return arr[key] == 1;
    }
}

// 思想同上
class MyHashSet {
    boolean set[];

    /** Initialize your data structure here. */
    public MyHashSet() {
        set = new boolean[1000000];
    }

    public void add(int key) {
        set[key] = true;
    }

    public void remove(int key) {
        set[key] = false;
    }

    /** Returns true if this set did not already contain the specified element */
    public boolean contains(int key) {
        return set[key];
    }
}

// 方法二
// 我们可以来适度的优化一下空间复杂度，由于存入HashSet的数字也许不会跨度很大
// 那么直接就申请长度为1000000的数组可能会有些浪费，那么我们其实可以使用1000个长度为1000的数组来代替，那么就要用个二维数组啦
// 实际上开始我们只申请了1000个空数组，对于每个要处理的元素，我们首先对1000取余，得到的值就当作哈希值，对应我们申请的那1000个空数组的位置
// 在加入元素时，一旦计算出了哈希值，我们将对应的空数组resize为长度1000，然后根据哈希值和key/1000来确定具体的加入位置。
// 移除数字一样的，先计算出哈希值，如果对应的数组不为空的话，找到对应的位置并赋值为0。
// 不过大家也可以看出来，我们在加入元素时会开辟1000的新空间，但是删除这个元素时，并没有检测这1000个位置是否均为0，是的话应该删除这1000个新空间。
// 但是这样可能会使得删除函数变慢一些，
class MyHashSet {

    int buckets = 1000;
    int itemPerBuckets = 1000;
    boolean[][] set;

    /** Initialize your data structure here. */
    public MyHashSet() {
        set = new boolean[buckets][];
    }

    public int hashKey(int key) {
        return key / buckets;
    }

    public int pos(int key) {
        return key % itemPerBuckets;
    }

    public void add(int key) {
        if (!contains(key) && set[hashKey(key)] == null)
            set[hashKey(key)] = new boolean[itemPerBuckets];
        set[hashKey(key)][pos(key)] = true;
    }

    public void remove(int key) {
        if (contains(key))
            set[hashKey(key)][pos(key)] = false;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return set[hashKey(key)] != null && set[hashKey(key)][pos(key)];
    }

}