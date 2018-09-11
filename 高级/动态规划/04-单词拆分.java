/**
 * 单词拆分
给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

说明：

拆分时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。
示例 1：

输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
示例 2：

输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     注意你可以重复使用字典中的单词。
示例 3：

输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false
 */


// 使用breakable[i]来记录前i个字符组成的字符串是否出现在了wordDict中
// break[0] = true;
// break[i] = true if substring(0, i) is within the dict
//          = true if breakable[i - j] && substring(i - j, i)                       in dict : j -> (0, i] i -> [1, s.length]
//                                    或者说breakable的[i - j + 1, i]下标对应的字符串
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null)
            return false;

        HashSet<String> dict = new HashSet<String>();
        for (String str : wordDict) {
            dict.add(str);
        }

        boolean[] breakable = new boolean[s.length() + 1];
        breakable[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = i; j > 0; j--) {
                String sub = s.substring(i - j, i); // 因为boolean[0]已经为true, 所以substring的左闭右开正好符合数组的的[i-j+1, i]
                if (dict.contains(sub)) {
                    if (breakable[i - j] == true) {
                        breakable[i] = true;
                        break;
                    }
                }
            }
        }

        return breakable[s.length()];
    }
}

// 优化，思想同上
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null)
            return false;

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}

// 改变j的循环顺序莫名的速度变快了，可能是这个顺序dp[j]为true出现的早吧
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}

// 使用memorize dp 为其follow做准备
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null) 
            return false;
        HashSet<String> dict = new HashSet<>(wordDict);
        HashMap<String, Boolean> mem = new HashMap<>();
        
        return wordBreak(s, mem, dict);
    }
    
    public boolean wordBreak(String s, HashMap<String, Boolean> mem, HashSet<String> dict) {
        // s 出现在了memorize中
        if (mem.containsKey(s)) 
            return mem.get(s);
        // 整个字符串都早字典中，或者说是将s分割为""与s
        if (dict.contains(s)) {
            mem.put(s, true);
            return true;
        }
        
        // 将字符串分割成左右两部分，先看右边是否在字典中，然后看左边是否breakable
        for (int i = 1; i <= s.length(); i++) {
            if (dict.contains(s.substring(i)) && wordBreak(s.substring(0, i), mem, dict)) {
                mem.put(s, true);
                return true;
            }
        }
        
        mem.put(s, false);
        return false;
    }
}