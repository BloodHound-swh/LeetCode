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
        if (s.length() == 0)
            return null;

        List<String> res = new LinkedList<>();

        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            List<String> partRes = null;
            if (set.contains(sub)) {
                partRes = helper(s.substring(i), set, used);
                if (partRes == null) {
                    res.add(sub);
                } else {
                    for (String str : partRes) {
                        res.add(sub + " " + str);
                    }
                }
            }
        }
        used.put(s, res);
        return res;
    }
}

// DFS
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