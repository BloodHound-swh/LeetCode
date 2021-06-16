/**
 * 题目描述
请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */

// 没什么好说的
public class Solution {
    public String replaceSpace(StringBuffer str) {
    	StringBuffer res = new StringBuffer();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == ' ')
                res.append("%20");
            else
                res.append(str.charAt(i));
        }
        
        return res.toString();
    }
}