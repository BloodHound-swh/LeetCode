/**
实现strStr()
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


//最普通的双指针循环
 class Solution {
    public int strStr(String haystack, String needle) {
        if(needle == null)
            return 0;
        int i = 0;
        int j = 0;
        while(i<haystack.length()&&j<needle.length()){
            if(haystack.charAt(i)==needle.charAt(j)){
                i++;
                j++;
            }
            else {
                i = i-j+1;
                j = 0;
            }
        }
        if(j>=needle.length())
            return i-needle.length();
        else
            return -1;        
    }
}


//KMP
class Solution {
    public static int[] get_next(String s,int[] next){
        next[0] = 0;
        int j = 0;
        int i = 1;
        while(i<s.length()){
            if(s.charAt(i) == s.charAt(j)){
                j++;
                next[i] = j;
                i++;
            }
            else{
                if(j>0)
                    j = next[j-1];
                else {
                    next[i] = j;//j=0;
                    i++;
                }
            }
        }
        return next;
    }
    public static int[] move_next(int[] next){
        int i;
        for(i = next.length-1;i>0;i--){
            next[i] = next[i-1];
        }
        next[0] = -1;
        return next;
    }
    
    public static int strStr(String haystack, String needle) {
       if(needle == null)
            return 0;
        int i = 0;
        int j = 0;
        while(i<haystack.length()&&j<needle.length()){
            if(haystack.charAt(i)==needle.charAt(j)){
                i++;
                j++;
            }
            else {
                i = i-j+1;
                j = 0;
            }
        }
        if(j>=needle.length())
            return i-needle.length();
        else
            return -1;        
    }
}
