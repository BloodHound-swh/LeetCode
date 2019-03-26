/**
 * 字符串相乘
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

示例 1:

输入: num1 = "2", num2 = "3"
输出: "6"
示例 2:

输入: num1 = "123", num2 = "456"
输出: "56088"
说明：

num1 和 num2 的长度小于110。
num1 和 num2 只包含数字 0-9。
num1 和 num2 均不以零开头，除非是数字 0 本身。
不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */

// http://www.cnblogs.com/TenosDoIt/p/3735309.html
// 我们小时候都学过多位数的乘法过程，都是每位相乘然后错位相加，那么这里就是用到这种方法，参见网友JustDoIt的博客。
// 把错位相加后的结果保存到一个一维数组中，然后分别每位上算进位，最后每个数字都变成一位，然后要做的是去除掉首位0，最后把每位上的数字按顺序保存到结果中即可
class Solution {
    public String multiply(String num1, String num2) {
        int n = num1.length();
        int m = num2.length();
        int[] res = new int[n + m];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int posL = i + j + 1;
                int posH = i + j;
                int mul = (num2.charAt(j) - '0') * (num1.charAt(i) - '0');
                int sum = mul + res[posL];
                res[posL] = sum % 10;
                res[posH] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num : res) {
            if (sb.length() == 0 && num == 0)
                continue;
            sb.append(num);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}