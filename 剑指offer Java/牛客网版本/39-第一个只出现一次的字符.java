/**
 * 题目描述
在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 */


// 同LeetCode第387题
public class Solution {
    public int FirstNotRepeatingChar(String str) {
        char[] arr = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) == 1)
                return i;
        }

        return -1;
    }
}