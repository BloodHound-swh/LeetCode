/**
 * 字母异位词分组

给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:

输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
说明：

所有输入均为小写字母。
不考虑答案输出的顺序。
 */

// 未看答案没有做出，无思路。。。

// 答案一
//使用Hashmap来存放字母异位词，将词组中每个单词的字母排序，作为key，相同的key则将该单词加入进对应的value的list中，否则新建一个key-value(list)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        ArrayList<List<String>> res = new ArrayList<List<String>>();
        if (strs == null || strs.length == 0)
            return res;
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i < strs.length; i++) {
            char[] curr = strs[i].toCharArray();
            Arrays.sort(curr);
            String key = String.valueOf(curr);
            if (!map.containsKey(key))
                map.put(key, new ArrayList<String>());
            map.get(key).add(strs[i]);
        }

        return new ArrayList<List<String>>(map.values());
        // res.addAll(map.values()); 也可以
        // return res;
    }
}

// 同答案一
//注意 map.putIfAbsent 等效于
// if (!map.containsKey(key)) 
//     return map.put(key, value);
// else
//     return map.get(key);
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // write your code here
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] sc = s.toCharArray();
            Arrays.sort(sc);
            String key = String.valueOf(sc);
            map.putIfAbsent(key, new ArrayList<>()); 
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}

// 答案二，计数分类
// https://leetcode-cn.com/articles/group-anagrams/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}