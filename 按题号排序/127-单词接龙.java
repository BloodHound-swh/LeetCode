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


// BFS模板就可以了
// 因为要求最短路径，如果我们用深度优先搜索的话必须遍历所有的路径才能确定哪个是最短的
// 而用广度优先搜索的话，一旦搜到目标就可以提前终止了，而且根据广度优先的性质，我们肯定是先通过较短的路径搜到目标。
// 为了避免产生环路和重复计算，需要用到used。
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordDict = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        HashSet<String> used = new HashSet<>();
        int step = 1;

        queue.offer(beginWord);
        used.add(beginWord);
        if (!wordDict.contains(endWord))
            return 0;

        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                for (int j = 0; j < endWord.length(); j++) {
                    for (char letter = 'a'; letter <= 'z'; letter++) {
                        StringBuilder newWord = new StringBuilder(currWord);
                        newWord.setCharAt(j, letter);
                        String tmp = newWord.toString();
                        if (endWord.equals(tmp)) {
                            return step;
                        } else if (wordDict.contains(tmp) && !used.contains(tmp)) {
                            queue.offer(tmp);
                            used.add(tmp);
                        }
                    }
                }
            }
        }

        return 0;
    }
}