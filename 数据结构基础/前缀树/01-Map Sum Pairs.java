/**
 * Map Sum Pairs
实现一个 MapSum 类里的两个方法，insert 和 sum。

对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。

对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。

示例 1:

输入: insert("apple", 3), 输出: Null
输入: sum("ap"), 输出: 3
输入: insert("app", 2), 输出: Null
输入: sum("ap"), 输出: 5
 */

// 因为这道题涉及到前缀，所以很自然的想到了前缀树。
// 在每次插入新的key是，就更新具有相同前缀的字符串的score += val - map[cur]
// 这样在sum的时候直接找到对应的prefix.score就是其子树之和了
class MapSum {

    HashMap<String, Integer> map;
    TrieNode root;

    class TrieNode {
        int score;
        Map<Character, TrieNode> children = new HashMap<>();
    }

    /** Initialize your data structure here. */
    public MapSum() {
        map = new HashMap();
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        TrieNode cur = root;
        cur.score += delta;
        for (char c : key.toCharArray()) {
            cur.children.putIfAbsent(c, new TrieNode());
            cur = cur.children.get(c);
            cur.score += delta;
        }
    }

    public int sum(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            cur = cur.children.get(c);
            if (cur == null)
                return 0;
        }
        return cur.score;
    }
}