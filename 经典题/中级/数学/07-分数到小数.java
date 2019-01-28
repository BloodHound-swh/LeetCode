/*
 * 分数到小数
给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。

如果小数部分为循环小数，则将循环的部分括在括号内。

示例 1:

输入: numerator = 1, denominator = 2
输出: "0.5"
示例 2:

输入: numerator = 2, denominator = 1
输出: "2"
示例 3:

输入: numerator = 2, denominator = 3
输出: "0.(6)"

 */

// 把每次的余数乘以10，再除以被除数就可以得到当前位的小数了。
// 对于循环小数，必然是出现了重复的余数，可以用HashMap来记录当前余数，以及它所出现的位置
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)
            return "0";
        StringBuilder res = new StringBuilder();
        res.append(((numerator > 0) ^ (denominator > 0) ? "-" : ""));
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        res.append(num / den);
        num %= den;
        if (num == 0)
            return res.toString();
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        int pos = 0;
        map.put(num, pos);
        StringBuilder frac = new StringBuilder();
        while (num != 0) {
            num = num * 10;
            frac.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                String pre = frac.substring(0, map.get(num)); // 非循环部分
                String loop = frac.substring(map.get(num)); // 循环部分
                res.append("." + pre + "(" + loop + ")");
                return res.toString();
            }
            pos++;
            map.put(num, pos);
        }
        res.append("." + frac.toString());
        return res.toString();
    }
}

// 注意substring的用法
public class Test {
    public static void main(String args[]) {
        String Str = new String("www.runoob.com");

        System.out.print("返回值 :");
        System.out.println(Str.substring(4));
        // 返回值 :runoob.com

        System.out.print("返回值 :");
        System.out.println(Str.substring(4, 10));
        // 返回值 :runoob
    }
}