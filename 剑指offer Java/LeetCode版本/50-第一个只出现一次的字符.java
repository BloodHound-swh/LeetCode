/**
 * 剑指 Offer 50. 第一个只出现一次的字符
在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。

示例:

s = "abaccdeff"
返回 "b"

s = "" 
返回 " "
 

限制：

0 <= s 的长度 <= 50000
 */


// 没啥好说的，就是用map记录一下
class Solution {
    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            int time = map.get(s.charAt(i));
            if (time == 1) {
                return s.charAt(i);
            }
        }

        return ' ';
    }
}

// 直接用Boolean值记录是否出现了第二次
// HashMap<Character, Boolean>
class Solution {
    public char firstUniqChar(String s) {
        HashMap<Character, Boolean> dic = new HashMap<>();
        char[] sc = s.toCharArray();
        for (char c : sc) {
            dic.put(c, !dic.containsKey(c));
        }

        for (char c : sc) {
            if (dic.get(c)) {
                return c;
            }
        }

        return ' ';
    }
}

// 使用LinkedHashMap节省第二次轮询
class Solution {
    public char firstUniqChar(String s) {
        Map<Character, Boolean> dic = new LinkedHashMap<>();
        char[] sc = s.toCharArray();
        for (char c : sc) {
            dic.put(c, !dic.containsKey(c));
        }

        for (Map.Entry<Character, Boolean> d : dic.entrySet()) {
            if (d.getValue()) {
                return d.getKey();
            }
        }
        return ' ';
    }
}
