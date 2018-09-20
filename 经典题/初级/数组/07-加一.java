/*
加一
给定一个非负整数组成的非空数组，给整数加一。
可以假设整数不包含任何前导零，除了数字0本身。
最高位数字存放在列表的首位。
 */

//简单的实现, 加1, 进位. 唯一取巧的地方, 最后如果要多一位, 一定是10000...这个模式, 可以走捷径, 直接来个+1size的array, 然后第一位=1.
class Solution {
    public int[] plusOne(int[] digits) {
        digits[digits.length - 1] += 1;
        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i] != 10)
                return digits;
            else
                digits[i] = 0;
            digits[i - 1] += 1;
        }
        if (digits[0] != 10)
            return digits;
        else {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }

    }
}

// 使用进位符号carry
class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1;

        for (int i = digits.length - 1; i >= 0; i--) {
            if (carry == 0) {
                return digits;
            }
            int tmp = digits[i] + carry;
            carry = tmp / 10;
            digits[i] = tmp % 10;
        }

        if (carry != 0) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }

        return digits;
    }
}