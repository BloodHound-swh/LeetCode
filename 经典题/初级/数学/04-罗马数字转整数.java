/**
罗马数字转整数
罗马数字包含以下七种字符：I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

示例 1:

输入: "III"
输出: 3
示例 2:

输入: "IV"
输出: 4
示例 3:

输入: "IX"
输出: 9
示例 4:

输入: "LVIII"
输出: 58
解释: C = 100, L = 50, XXX = 30, III = 3.
示例 5:

输入: "MCMXCIV"
输出: 1994
解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */


//规律 如果左边的数字小于右边的数字 = 右 - 左
//将所有数相加，并减去特殊组合造成的多余部分
class Solution {
    public int romanToInt(String s) {
        if(s == null || s.length() == 0)
            return 0;
        int result = 0;
        if(s.indexOf("CM") != -1) result -= 200;
        if(s.indexOf("CD") != -1) result -= 200;
        if(s.indexOf("XC") != -1) result -= 20;
        if(s.indexOf("XL") != -1) result -= 20;
        if(s.indexOf("IX") != -1) result -= 2;
        if(s.indexOf("IV") != -1) result -= 2;
        
        for(char c:s.toCharArray()){
            if(c == 'M') result += 1000;
            else if (c == 'D') result +=500;
            else if (c == 'C') result +=100;
            else if (c == 'L') result +=50;
            else if (c == 'X') result +=10;
            else if (c == 'V') result +=5;
            else if (c == 'I') result +=1;
        }
        
        return result;
    }
}

//将右 - 左 详细的表示出来
class Solution {
    public int romanToInt(String s) {
        if(s == null || s.length() == 0)
            return 0;
        int res = toNumber(s.charAt(0));
        for(int i = 1; i < s.length(); i++){
            if(toNumber(s.charAt(i)) > toNumber(s.charAt(i - 1))){
                res += toNumber(s.charAt(i)) - 2 * toNumber(s.charAt(i-1));
            } else {
                res += toNumber(s.charAt(i));
            }
        }
        return res;
    }
    public static int toNumber(char c){
        int res = 0;
        switch(c){
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
        }
        return res;
    }
}