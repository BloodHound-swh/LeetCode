/**
 * 实现strStr()
题目描述提示帮助提交记录社区讨论阅读解答
实现 strStr() 函数。

给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

示例 1:

输入: haystack = "hello", needle = "ll"
输出: 2
示例 2:

输入: haystack = "aaaaa", needle = "bba"
输出: -1
说明:

当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */

// 未看答案没有做出来，主要在于双指针的如何循环的问题没有弄清楚
// 个人觉得KMP超出面试的范畴。本题在面试中出现的作用就是考察基本的编程素养，以及边界条件的考虑。我们用暴力法即可。
class Solution {
    public int strStr(String haystack, String needle) {
        int hp = 0;
        int np = 0;
        if (needle == null)
            return 0;
        while (hp < hastack.length()) {
            for (np = 0; np < needle.length(); np++) {
                if (haystack.charAt(hp) == needle.charAt(np)) {
                    hp++;
                } else {
                    break;
                }
            }
        }
    }
}

// 答案一
// 最普通的双指针循环
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null)
            return 0;
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j >= needle.length())
            return i - needle.length();
        else
            return -1;
    }
}

// 同答案一，写法不同而已
public class Solution {
    public int strStr(String haystack, String needle) {
        int start = 0;
        // 如果剩下的字母不够needle长度就停止遍历
        while (start <= haystack.length() - needle.length()) {
            int i1 = start, i2 = 0;
            while (i2 < needle.length() && haystack.charAt(i1) == needle.charAt(i2)) {
                i1++;
                i2++;
            }
            if (i2 == needle.length())
                return start;
            start++;
        }
        return -1;
    }
}

// 答案二
// KMP
public class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int[] next = new int[needle.length()];
        // 得到next数组
        getNextArr(next, needle);
        // i是匹配串haystack的指针，j是模式串needle的指针
        int i = 0, j = 0;
        // 双指针开始匹配
        while (i < haystack.length() && j < needle.length()) {
            // 如果j=-1意味着刚刚失配过，下标+1后，下一轮就可以开始匹配各自的第一个了
            // 如果指向的字母相同，则下一轮匹配各自的下一个
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                // 如果失配，则将更新j为next[j]
            } else {
                j = next[j];
            }
        }
        return j == needle.length() ? i - j : -1;
    }

    private void getNextArr(int[] next, String needle) {
        // k是前缀中相同部分的末尾，同时也是相同部分的长度，因为长度等于k-0。
        // j是后缀的末尾，即后缀相同部分的末尾
        int k = -1, j = 0;
        next[0] = -1;
        while (j < needle.length() - 1) {
            // 如果k=-1，说明刚刚失配了，则重新开始计算前缀和后缀相同的长度
            // 如果两个字符相等，则在上次前缀和后缀相同的长度加1就行了
            if (k == -1 || needle.charAt(j) == needle.charAt(k)) {
                k++;
                j++;
                next[j] = k;
            } else {
                // 否则，前缀长度缩短为next[k]
                k = next[k];
            }
        }
    }
}
