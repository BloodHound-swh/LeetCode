/**
 * 最长公共前缀
题目描述提示帮助提交记录社区讨论阅读解答
编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

示例 1:

输入: ["flower","flow","flight"]
输出: "fl"
示例 2:

输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
说明:

所有输入只包含小写字母 a-z 。
 */

// 未看答案没有做出来，只做到了当出现不同时停止，但没有做到当某一个单词很短越界怎么办
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length < 1)
            return "";
        if (strs.length == 1)
            return strs[0];
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (int i = 0; i < strs[0].length(); i++) {
            char temp = strs[0].charAt(i);
            if (flag == true) {
                for (int j = 1; j < strs.length; j++) {
                    if (strs[j].charAt(i) == temp)
                        continue;
                    else {
                        flag = false;
                        return sb.toString();
                    }
                }
                sb.append("" + temp);
            }
        }
        return sb.toString();
    }
}

// 将上面的做法做点修改就能通过了
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        StringBuilder res = new StringBuilder();
        for (int j = 0; j < strs[0].length(); ++j) {
            char c = strs[0].charAt(j);
            for (int i = 1; i < strs.length; ++i) {
                if (j >= strs[i].length() || strs[i].charAt(j) != c) { // 这样就能防止越界的问题
                    return res;
                }
            }
            res.append(c);
        }
        return res.toString();
    }
}

// 答案一
// 从前往后枚举字符串的每一列，先比较每个字符串相同列上的字符（即不同字符串相同下标的字符）然后再进行对下一列的比较。
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }
}

// 方法二
// 使用indexOf()方法，把strs[0]作为基准，每次看是否出现在其他元素的头部
// 如果不是就把strs[0]的长度减1，重新比较
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length < 1) {
            return "";
        }
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(res) != 0) {
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }
}