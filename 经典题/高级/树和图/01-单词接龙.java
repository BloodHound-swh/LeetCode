/**
 * 单词接龙
给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:

如果不存在这样的转换序列，返回 0。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
示例 1:

输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

输出: 5

解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     返回它的长度 5。
示例 2:

输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

输出: 0

解释: endWord "cog" 不在字典中，所以无法进行转换。
 */

// 因为要求最短路径，如果我们用深度优先搜索的话必须遍历所有的路径才能确定哪个是最短的
// 而用广度优先搜索的话，一旦搜到目标就可以提前终止了，而且根据广度优先的性质，我们肯定是先通过较短的路径搜到目标。
// 为了避免产生环路和重复计算，我们找到一个存在于字典的新的词时，就要把它从字典中移去。
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordDict = new HashSet<>(wordList); // 不转换成HashSet会超时。。。
        Queue<String> queue = new LinkedList<String>();
        int step = 2;
        queue.offer(beginWord);
        if (!wordDict.contains(endWord))
            return 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 控制size来确保一次while循环只计算同一层的节点，有点像二叉树level order遍历
            for (int j = 0; j < size; j++) {
                String currWord = queue.poll();
                // 循环这个词从第一位字母到最后一位字母
                for (int i = 0; i < endWord.length(); i++) {
                    // 循环这一位被替换成25个其他字母的情况
                    for (char letter = 'a'; letter <= 'z'; letter++) {
                        StringBuilder newWord = new StringBuilder(currWord);
                        newWord.setCharAt(i, letter);
                        if (endWord.equals(newWord.toString())) {
                            return step;
                        } else if (wordDict.contains(newWord.toString())) {
                            wordDict.remove(newWord.toString());
                            queue.offer(newWord.toString());
                        }
                    }
                }
            }
            step++;
        }
        return 0;
    }
}

// newWord.setCharAt(i, letter) 是在i位替换为letter 
// 也可以自己写
for (int i = 0; i < currWord.length(); i++) {
    char[] wordUnit = currWord.toCharArray();
    for (char j = 'a'; j <= 'z'; j++) {
        wordUnit[i] = j;
        String temp = new String(wordUnit);
    }
}

// 方法二
// https://www.youtube.com/watch?v=mgICIVXu2sQ
// 虽然这个耗时较长，但是实际上避免了for循环中许多重复的操作，且易于理解
// 对每一个单词，建立它能走到下一步的所有单词的list，然后从初试词开始进行广度优先遍历
class Solution {
    HashMap<String, List<String>> map = new HashMap<String, List<String>>();

    public void buildMap(List<String> wordList, String beginWord) {
        for (String str : wordList) {
            List<String> nlist = new LinkedList<String>();
            map.put(str, nlist);
            for (String nxt : wordList) {
                if (diff(str, nxt) == 1) {
                    map.get(str).add(nxt);
                }
            }
        }
        if (!map.containsKey(beginWord)) {
            List<String> nlist = new LinkedList<>();
            map.put(beginWord, nlist);
            for (String str : wordList) {
                if (diff(beginWord, str) == 1) {
                    map.get(beginWord).add(str);
                }
            }
        }
    }

    public int diff(String s, String t) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i))
                count++;
        }
        return count;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) 
            return 0;
        buildMap(wordList, beginWord);
        HashSet<String> doneSet = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);
        doneSet.add(beginWord); // 用来记录那些词已经出现过
        int steps = 1;
        while (queue.size() != 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(endWord))
                    return steps;
                List<String> nxtStrList = map.get(curr);
                for (String nxtStr : nxtStrList) {
                    if (!doneSet.contains(nxtStr)) {
                        queue.offer(nxtStr);
                        doneSet.add(nxtStr);
                    }
                }
            }
            steps++;
        }
        return 0;
    }
}