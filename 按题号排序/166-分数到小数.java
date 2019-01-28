/**
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

// 未看答案没有做出来，如何求小数部分，以及如何判定循环的部分都是难点

// 答案一
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
                String pre = frac.substring(0, map.get(num));
                String loop = frac.substring(map.get(num));
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

// 答案二，思想同答案一
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        long num = numerator;
        long den = denominator;
        if (num == 0 || den == 0) {
            return "0";
        }
        // 判断结果正负号
        boolean negative = (num > 0 && den < 0) || (num < 0 && den > 0);
        num = Math.abs(num);
        den = Math.abs(den);
        // 得到整数部分
        String integ = (negative ? "-" : "") + String.valueOf(num / den);
        // 如果存在小数部分
        if (num % den != 0) {
            num = num % den;
            HashMap<Long, Integer> map = new HashMap<Long, Integer>();
            int pos = 0;
            map.put(num, pos);
            StringBuilder frac = new StringBuilder();
            // 计算小数部分
            while (num != 0) {
                // 先把算出的小数加上，再判断余数是否重复，如果余数重复的话，小数会从下一个开始重复
                num = num * 10;
                frac.append(num / den);
                num = num % den;
                // 如果该余数之前出现过，说明有循环，上次出现的位置到当前位置就是循环的部分
                if (map.containsKey(num)) {
                    // 将非循环部分和循环部分分开
                    String pre = frac.substring(0, map.get(num));
                    String loop = frac.substring(map.get(num));
                    // 返回有循环的结果
                    return integ + "." + pre + "(" + loop + ")";
                }
                pos++;
                // 记录下当前余数和他对应小数的位置
                map.put(num, pos);
            }
            // 返回无循环有小数的结果
            return integ + "." + frac.toString();
        }
        // 返回无小数的结果
        return integ;
    }
}
