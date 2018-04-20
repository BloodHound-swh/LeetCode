/**
数数并说
报数序列是指一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
1.     1
2.     11
3.     21
4.     1211
5.     111221
1 被读作  "one 1"  ("一个一") , 即 11。
11 被读作 "two 1s" ("两个一"）, 即 21。
21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
给定一个正整数 n ，输出报数序列的第 n 项。

注意：整数顺序将表示为一个字符串。
示例 1:
输入: 1
输出: "1"

示例 2:
输入: 4
输出: "1211"
 */


//使用count计数，直到与下一位不同，则将count于此位置的数字记录下来。注意&&只有在前一个条件为真才判断后一个条件，所以不能随意调换顺序
//oldchars[i] == oldchars[i+1]) && ((i+1) < oldchars.length)是不对的。
 class Solution {
    public String countAndSay(int n) {
        String oldString = "1";
        while(--n > 0){
            StringBuilder sb = new StringBuilder();
            char[] oldchars = oldString.toCharArray();
            
            for(int i = 0; i< oldchars.length;i++){
                int count = 1;
                while((i+1) < oldchars.length && oldchars[i] == oldchars[i+1]){
                    count++;
                    i++;
                }
                sb.append(String.valueOf(count)+String.valueOf(oldchars[i]));
            }
            oldString = sb.toString();
        }
        return oldString;
    }
}