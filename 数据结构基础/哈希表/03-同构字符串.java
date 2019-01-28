/**
 * 同构字符串
给定两个字符串 s 和 t，判断它们是否是同构的。

如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。

所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

示例 1:

输入: s = "egg", t = "add"
输出: true
示例 2:

输入: s = "foo", t = "bar"
输出: false
示例 3:

输入: s = "paper", t = "title"
输出: true
说明:
你可以假设 s 和 t 具有相同的长度。
 */


// 方法一
// 根据一对一映射的特点，我们需要用两个哈希表分别来记录原字符串和目标字符串中字符出现情况
// 由于ASCII码只有256个字符，所以我们可以用一个256大小的数组来代替哈希表，并初始化为0
// 我们遍历原字符串，分别从源字符串和目标字符串取出一个字符，然后分别在两个哈希表中查找其值
// 若不相等，则返回false，若相等，将其值更新为i + 1，因为默认的值是0，所以我们更新值为i + 1，这样当 i=0 时，则映射为1
// 如果不加1的话，那么就无法区分是否更新了
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)])
                return false;
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }
        return true;
    }
}

// 方法二
// 使用HashMap和HashSet
// 注意不能多对一映射
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        Set<Character> set = new HashSet<Character>();
        if (s.length() != t.length())
            return false;
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i), tc = t.charAt(i);
            if (map.containsKey(sc)) {
                // 如果已经给s中的字符建立了映射，检查该映射是否和当前t中字符相同
                if (tc != map.get(sc))
                    return false;
            } else {
                // 如果已经给t中的字符建立了映射，就返回假，因为出现了多对一映射
                if (set.contains(tc))
                    return false;
                map.put(sc, tc);
                set.add(tc);
            }
        }
        return true;
    }
}