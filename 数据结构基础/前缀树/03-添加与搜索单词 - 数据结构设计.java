/**
 * 添加与搜索单词 - 数据结构设计
设计一个支持以下两种操作的数据结构：

void addWord(word)
bool search(word)
search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。

示例:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
说明:

你可以假设所有单词都是由小写字母 a-z 组成的。
 */

// TrieNode, 这里的children没有用数组而是用map来节省空间
class WordDictionary {
    class TrieNode {
        HashMap<Character, TrieNode> map;
        boolean end;

        TrieNode() {
            this.map = new HashMap<>();
            this.end = false;
        }
    }

    TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        char[] arr = word.toCharArray();
        TrieNode node = root;
        for (char c : arr) {
            if (!node.map.containsKey(c)) {
                node.map.put(c, new TrieNode());
            }
            node = node.map.get(c);
        }
        node.end = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot
     * character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return searchHelper(root, word, 0);
    }

    public boolean searchHelper(TrieNode root, String word, int index) {
        if (index == word.length())
            return root.end;
        TrieNode node = root;
        char c = word.charAt(index);
        if (node.map.containsKey(c)) {
            return searchHelper(node.map.get(c), word, index + 1);
        } else if (c == '.') {
            for (Character ch : node.map.keySet()) {
                if (searchHelper(node.map.get(ch), word, index + 1)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}