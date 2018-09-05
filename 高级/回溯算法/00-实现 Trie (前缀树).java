/**
 * 实现 Trie (前缀树)
题目描述提示帮助提交记录社区讨论阅读解答
实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

示例:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");   
trie.search("app");     // 返回 true
说明:

你可以假设所有的输入都是由小写字母 a-z 构成的。
保证所有输入均为非空字符串。
 */

// https://www.youtube.com/watch?v=f48wGD-MuQw
// 初始化一个dummy的root节点，它下面相当于有26个子树，初始都为空
// 插入单词的时候，遍历单词中的字母，一级级往下走，对每个字母对应位置的子树进行Trienode构造
// 查抄函数则从root开始往下遍历
class Trie {
    class TrieNode {
        public boolean is_word;
        public TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[26];
            is_word = false;
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int index = (int) (word.charAt(i) - 'a');
            if (p.children[index] == null)
                p.children[index] = new TrieNode();
            p = p.children[index];
        }
        p.is_word = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = find(word);
        return node != null && node.is_word; // 相比于startsWith还需要判定最后的字母是不是真的是结尾了
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = find(prefix);
        return node != null;
    }

    public TrieNode find(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = (int) (prefix.charAt(i) - 'a');
            if (p.children[index] == null)
                return null;
            p = p.children[index];
        }
        return p;
    }
}

/**
 * Your Trie object will be instantiated and called as such: Trie obj = new
 * Trie(); obj.insert(word); boolean param_2 = obj.search(word); boolean param_3
 * = obj.startsWith(prefix);
 */