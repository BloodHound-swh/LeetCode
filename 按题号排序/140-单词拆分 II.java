/**
 * 单词拆分 II

给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。

说明：

分隔时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。
示例 1：

输入:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
输出:
[
  "cats and dog",
  "cat sand dog"
]
示例 2：

输入:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
输出:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
解释: 注意你可以重复使用字典中的单词。
示例 3：

输入:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
输出:
[]
 */

// 即便是暴力循环都不是很好写。而且此题需要使用memorize list来记录已经分好的结果

// 答案一
// 使用递归查找，从前往后，每次找到包含在wordDict中的单词就切割下来，然后把后面的字符串再调用helper函数
// https://www.youtube.com/watch?v=pYKGRZwbuzs&t=645s
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        HashMap<String, List<String>> used = new HashMap<>();

        for (String str : wordDict) {
            set.add(str);
        }

        res = helper(s, set, used);
        return res;
    }

    public List<String> helper(String s, HashSet<String> set, HashMap<String, List<String>> used) {
        if (used.containsKey(s))
            return used.get(s);
        if (s.length() == 0) // 到达边界，相当于出口函数
            return null;

        List<String> res = new LinkedList<>();

        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            List<String> suffixRes = null;
            if (set.contains(sub)) {
                suffixRes = helper(s.substring(i), set, used);
                if (suffixRes == null) { // 当前词本身全部都在字典里了
                    res.add(sub);
                } else {
                    for (String str : suffixRes) {
                        res.add(sub + " " + str); // sub + str 形成新的一个str
                    }
                }
            }
        }
        used.put(s, res);
        return res;
    }
}

// 答案二，思路同上
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }

    List<String> DFS(String s, List<String> wordDict, HashMap<String, LinkedList<String>> map) {
        if (map.containsKey(s))
            return map.get(s);

        LinkedList<String> res = new LinkedList<String>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }
}