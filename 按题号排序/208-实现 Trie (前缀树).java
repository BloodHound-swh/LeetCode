/**
 * 实现 Trie (前缀树)

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

// 未看答案版，发现在search和startwith方法其实可以公用一些代码的
class Trie {
    class TrieNode {
        public boolean is_word;
        public TrieNode[] children;

        TrieNode() {
            is_word = false;
            children = new TrieNode[26];
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    TrieNode root;

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (p.children[c - 'a'] == null)
                p.children[c - 'a'] = new TrieNode();
            p = p.children[c - 'a'];
        }
        p.is_word = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (p.children[c - 'a'] != null)
                p = p.children[c - 'a'];
            else
                return false;
        }
        return p.is_word;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (p.children[c - 'a'] != null)
                p = p.children[c - 'a'];
            else
                return false;
        }
        return true;
    }
}

// 答案一
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

// 使用HashMap，思想同方法一
class Trie {

    private class TrieNode {
        private HashMap<Character, TrieNode> children;
        private boolean hasWord;

        public TrieNode() {
            this.children = new HashMap<>();
            this.hasWord = false;
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        curr.hasWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curr = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return curr.hasWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode curr = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return true;
    }
}