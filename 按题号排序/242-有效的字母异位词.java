/**
 * 有效的字母异位词

给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。

示例 1:

输入: s = "anagram", t = "nagaram"
输出: true
示例 2:

输入: s = "rat", t = "car"
输出: false
说明:
你可以假设字符串只包含小写字母。

进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */

// 未看答案版，运算时间排倒数。。。
class Solution {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> mapS = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (mapS.containsKey(s.charAt(i))) {
                mapS.put(s.charAt(i), mapS.get(s.charAt(i)) + 1);
            } else {
                mapS.put(s.charAt(i), 1);
            }
        }

        HashMap<Character, Integer> mapT = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (mapT.containsKey(t.charAt(i))) {
                mapT.put(t.charAt(i), mapT.get(t.charAt(i)) + 1);
            } else {
                mapT.put(t.charAt(i), 1);
            }
        }

        return mapS.equals(mapT);
    }
}

// 是用两个Map，并用equals来判定
class Solution {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> maps = new HashMap<Character, Integer>();
        Map<Character, Integer> mapt = new HashMap<Character, Integer>();
        char[] arrs = s.toCharArray();
        char[] arrt = t.toCharArray();
        for (int i = 0; i < arrs.length; i++) {
            if (maps.containsKey(arrs[i]))
                maps.put(arrs[i], maps.get(arrs[i]) + 1);
            else
                maps.put(arrs[i], 1);
        }
        for (int i = 0; i < arrt.length; i++) {
            if (mapt.containsKey(arrt[i]))
                mapt.put(arrt[i], mapt.get(arrt[i]) + 1);
            else
                mapt.put(arrt[i], 1);
        }
        if (maps.equals(mapt))
            return true;
        else
            return false;
    }
}

// 使用两个数组，并是用arr[i]-‘a'的技巧
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == t)
            return true;

        if (s.length() != t.length())
            return false;

        if (s != null && t != null && s.equals(t))
            return true;

        int[] a = new int[26];
        int[] b = new int[26];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        for (int i = 0; i < sc.length; i++)
            a[sc[i] - 'a']++;

        for (int j = 0; j < tc.length; j++)
            b[tc[j] - 'a']++;

        for (int m = 0; m < 26; m++)
            if (a[m] != b[m])
                return false;

        return true;
    }
}

// 只使用一个数组
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++)
            alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++)
            alphabet[t.charAt(i) - 'a']--;
        for (int i : alphabet)
            if (i != 0)
                return false;
        return true;
    }
}