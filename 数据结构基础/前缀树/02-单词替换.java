/**
 * 单词替换
在英语中，我们有一个叫做 词根(root)的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。

现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。

你需要输出替换之后的句子。

示例 1:

输入: dict(词典) = ["cat", "bat", "rat"]
sentence(句子) = "the cattle was rattled by the battery"
输出: "the cat was rat by the bat"
注:

输入只包含小写字母。
1 <= 字典单词数 <=1000
1 <=  句中词语数 <= 1000
1 <= 词根长度 <= 100
1 <= 句中词语长度 <= 1000
 */

// 使用前缀树
class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        TrieNode root = new TrieNode();
        for (String str : dict) {
            TrieNode cur = root;
            for (char letter : str.toCharArray()) {
                if (cur.children[letter - 'a'] == null)
                    cur.children[letter - 'a'] = new TrieNode();
                cur = cur.children[letter - 'a'];
            }
            cur.word = str;
        }

        StringBuilder ans = new StringBuilder();

        for (String word : sentence.split("\\s+")) {
            if (ans.length() > 0)
                ans.append(" ");

            TrieNode cur = root;
            for (char letter : word.toCharArray()) {
                if (cur.children[letter - 'a'] == null || cur.word != null)
                    break;
                cur = cur.children[letter - 'a'];
            }
            ans.append(cur.word != null ? cur.word : word);
        }

        return ans.toString();
    }
}

class TrieNode {
    TrieNode[] children;
    String word;

    TrieNode() {
        children = new TrieNode[26];
    }
}

// Prefix Hash
class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        Set<String> dictSet = new HashSet();
        for (String str : dict) {
            dictSet.add(str);
        }

        StringBuilder ans = new StringBuilder();
        for (String word : sentence.split("\\s+")) {
            String prefix = "";
            for (int i = 1; i <= word.length(); i++) {
                prefix = word.substring(0, i);
                if (dictSet.contains(prefix))
                    break;
            }
            if (ans.length() > 0)
                ans.append(" ");
            ans.append(prefix);
        }

        return ans.toString();
    }
}