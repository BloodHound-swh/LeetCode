/**
 * 字符串中的第一个唯一字符

给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

案例:

s = "leetcode"
返回 0.

s = "loveleetcode",
返回 2.
 */


// 未看答案版
class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (map.containsKey(str[i])) {
                map.put(str[i], map.get(str[i]) + 1);
            } else {
                map.put(str[i], 1);
            }
        }
        for (int i = 0; i < str.length; i++) {
            if (map.get(str[i]) == 1)
                return i;
        }
        return -1;
    }
}

// 答案一
// 使用Map的键值对进行判定
class Solution {
    public int firstUniqChar(String s) {
        char[] arr = s.toCharArray();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else
                map.put(arr[i], 1);
        }
        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) == 1)
                return i;
        }
        return -1;
    }
}

// 取巧，使用indexOf()和lastIndexOf()函数，初始result为Integer.MAX_VALUE。
// indexOf()的用法：返回字符中indexof（string）中字串string在父串中首次出现的位置，从0开始！没有返回-1；
class Solution {
    public int firstUniqChar(String s) {
        int result = Integer.MAX_VALUE;
        boolean flag = false;
        for (char i = 'a'; i <= 'z'; i++) {
            int first = s.indexOf(i);
            int last = s.lastIndexOf(i);

            if (first == last && first >= 0) {
                result = Math.min(first, result);
                flag = true;
            }
        }
        if (falg)
            return result;
        return -1;
    }
}