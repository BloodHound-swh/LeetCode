/**
最长公共前缀
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


//取字符串数组的第一个元素作为初始prefix，然后用j来表示之后每个元素与第一个元素重复的位数。每次循环，j置零进行比较。而prefix每次都截取0到j的长度，保证prefix为公用的部分。
class Solution {
    public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++) {
			int j = 0;
			while (j < strs[i].length() && j < prefix.length() && strs[i].charAt(j) == prefix.charAt(j)) {
				j++;
			}
			if (j == 0) {
				return "";
			}
			prefix = prefix.substring(0, j);
		}
		return prefix;
    }
}

//使用indexOf，然后循环判定pre
class Solution {
    public String longestCommonPrefix(String[] strs){
        if(null == strs || strs.length < 1){
            return "";
        } else if(strs.length == 1){
            return strs[0];
        }
        // orz 膜拜大神
        /*数组此时最少一个元素*/
        int i=1;
        String pre = strs[0];/*取第一个元素作为比较体*/
        while(i < strs.length) /*当数组个数大于1的时候才需要比较*/
        {
            while (strs[i].indexOf(pre)!=0) /*当比较体不作为开头*/
                pre = pre.substring(0,pre.length()-1);/*截取重复的部分 如果一直不是，就是截取到""空串，空串一定是开头，跳出*/
            i++; /*下一个对象比较*/
        }
        return pre;
    }
}
