/**
 * 常数时间插入、删除和获取随机元素

设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。

insert(val)：当元素 val 不存在时，向集合中插入该项。
remove(val)：元素 val 存在时，从集合中移除该项。
getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
示例 :

// 初始化一个空的集合。
RandomizedSet randomSet = new RandomizedSet();

// 向集合中插入 1 。返回 true 表示 1 被成功地插入。
randomSet.insert(1);

// 返回 false ，表示集合中不存在 2 。
randomSet.remove(2);

// 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
randomSet.insert(2);

// getRandom 应随机返回 1 或 2 。
randomSet.getRandom();

// 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
randomSet.remove(1);

// 2 已在集合中，所以返回 false 。
randomSet.insert(2);

// 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
randomSet.getRandom();
 */



// 如何保证删除插入都是O(1)以及如何支持随机访问时难点

// 因为有时间复杂度的要求，所以使用HashMap来做插入和删除的处理，这样在判定过程中就是O(1)的复杂度，键：value，值：index
// 使用ArrayList来存储HashMap的键，也就是val，因为只有ArrayList才能getRandom。
// 利用到了一个一维数组和一个哈希表，其中数组用来保存数字，哈希表用来建立每个数字和其在数组中的位置之间的映射，
// 对于插入操作，我们先看这个数字是否已经在哈希表中存在，如果存在的话直接返回false，不存在的话，我们将其插入到数组的末尾，然后建立数字和其位置的映射。
// 删除操作是比较tricky的，我们还是要先判断其是否在哈希表里，如果没有，直接返回false。
// 由于哈希表的删除是常数时间的，而数组并不是，为了使数组删除也能常数级，我们实际上将要删除的数字和数组的最后一个数字调换个位置，然后修改对应的哈希表中的值，这样我们只需要删除数组的最后一个元素即可，保证了常数时间内的删除。
// 而返回随机数对于数组来说就很简单了，我们只要随机生成一个位置，返回该位置上的数字即可
class RandomizedSet {
    List<Integer> list; // 用来支持随机访问
    Map<Integer, Integer> map; // 用来支持插入删除的时间复杂度都是O(1)
    Random rand = new Random(); // 用来生成随机数

    /** Initialize your data structure here. */
    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain
     * the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified
     * element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val))
            return false;
        int index = map.get(val); // 获取要删除数字的坐标
        if (index != list.size() - 1) {
            int last = list.get(list.size() - 1); // 获取list中最后一个数字
            map.put(last, index); // 将list中的最后一个数字的坐标改成要删除数字的坐标
            list.set(index, last); // 将list中的最后一个数字复制到要删除数字的位置上
        }
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet(); boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val); int param_3 = obj.getRandom();
 */