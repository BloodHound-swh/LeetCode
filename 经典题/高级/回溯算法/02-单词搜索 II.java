/**
 * 单词搜索 II
给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

示例:

输入: 
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

输出: ["eat","oath"]
说明:
你可以假设所有输入都由小写字母 a-z 组成。

提示:

你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 */


// 首先想到的是使用DFS递归来查找字符串，当发现当前字符串prefix就已经不可能我是我们要找的单词的前缀的时候就结束搜索
// 这时候就想到了用到前缀树，这样可以大大降低搜索的复杂度
class Solution {
    HashSet<String> result = new HashSet<>();

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();

        for (String word : words) {
            trie.insert(word);
        }

        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }

        return new ArrayList<String>(result);
    }

    public void dfs(char[][] board, boolean[][] visited, String str, int i, int j, Trie trie) {
        int m = board.length;
        int n = board[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n)
            return;

        if (visited[i][j])
            return;

        str = str + board[i][j];

        if (!trie.startsWith(str))
            return;

        if (trie.search(str))
            result.add(str);

        visited[i][j] = true;
        dfs(board, visited, str, i - 1, j, trie);
        dfs(board, visited, str, i + 1, j, trie);
        dfs(board, visited, str, i, j - 1, trie);
        dfs(board, visited, str, i, j + 1, trie);
        visited[i][j] = false; // 注意对于下以此dfs来说，需要重置为false
    }

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

        public Trie() {
            root = new TrieNode();
        }

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

        public boolean search(String word) {
            TrieNode node = find(word);
            return node != null && node.is_word;
        }

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
}

// dfs时也可以不用到visited[][], 下面是在网上找到的参考 对于已经遍历过得数先复制为#，之后再还原即可
/**
 * private void dfs(char[][] board, int row, int column, PrefixTreeNode node, List<String> result) {
        // 判断当前下标是否合法
        boolean validIndex = row >= 0 && column >= 0
                && row < board.length && column < board[0].length;
        if (!validIndex) {
            return;
        }

        // 如果当前遍历的字符为'#'，则说明该字符已经被使用过，返回。
        // 如果该字符在给定的所有单词构造的前缀树中不存在，则说明该字符不是任何单词的前缀，直接返回
        char c = board[row][column];
        if (c == '#' || node.links[c - 'a'] == null) {
            return;
        }

        // 继续遍历前缀树的下一个节点。如果遍历到前缀树中一个节点对应的单词不为空，则说明该单词可以在
        // 字符数组中查到，此时将该单词加入到结果中。为了避免重复，查到单词后，将节点中的单词置为空
        node = node.links[c - 'a'];
        if (node.word.length() != 0) {
            result.add(node.word);
            node.word = "";
        }

        // 标记字符被使用过
        board[row][column] = '#';

        // 进行上下左右搜索
        dfs(board, row - 1, column, node, result);
        dfs(board, row + 1, column, node, result);
        dfs(board, row, column - 1, node, result);
        dfs(board, row, column + 1, node, result);

        // 还原字符
        board[row][column] = c;
    }
}
 */

// 当然HashMap的Trie也可以
class TrieNode {
    String word;
    HashMap<Character, TrieNode> children;

    public TrieNode() {
        word = null;
        children = new HashMap<Character, TrieNode>();
    }
};

class TrieTree {
    TrieNode root;

    public TrieTree(TrieNode TrieNode) {
        root = TrieNode;
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!node.children.containsKey(word.charAt(i))) {
                node.children.put(word.charAt(i), new TrieNode());
            }
            node = node.children.get(word.charAt(i));
        }
        node.word = word;
    }
};

public class Solution {
    public int[] dx = { 1, 0, -1, 0 };
    public int[] dy = { 0, 1, 0, -1 };

    public void search(char[][] board, int x, int y, TrieNode root, List<String> results) {
        if (!root.children.containsKey(board[x][y])) {
            return;
        }

        TrieNode child = root.children.get(board[x][y]);

        if (child.word != null) {
            if (!results.contains(child.word)) {
                results.add(child.word);
            }
        }

        char tmp = board[x][y];
        board[x][y] = 0; // mark board[x][y] as used

        for (int i = 0; i < 4; i++) {
            if (!isValid(board, x + dx[i], y + dy[i])) {
                continue;
            }
            search(board, x + dx[i], y + dy[i], child, results);
        }

        board[x][y] = tmp; // revert the mark
    }

    private boolean isValid(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }

        return board[x][y] != 0;
    }

    public List<String> wordSearchII(char[][] board, List<String> words) {
        List<String> results = new ArrayList<String>();

        TrieTree tree = new TrieTree(new TrieNode());
        for (String word : words) {
            tree.insert(word);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                search(board, i, j, tree.root, results);
            }
        }

        return results;
    }
}