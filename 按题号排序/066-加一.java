/**
 * 加一

给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

示例 1:

输入: [1,2,3]
输出: [1,2,4]
解释: 输入数组表示数字 123。
示例 2:

输入: [4,3,2,1]
输出: [4,3,2,2]
解释: 输入数组表示数字 4321。
 */


// 未看答案版，出现错误，因为数组被初始化后不能增加长度了
class Solution {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        digits = myReverse(digits);
        int flag = 1;
        digits[0] = (digits[0] + 1) % 10;
        if (digits[0] != 0) {
            return myReverse(digits);
        } else {
            for (int i = 1; i < len; i++) {
                digits[i] = (digits[i] + flag) % 10;
                if (digits[i] != 0)
                    flag = 0;
            }
            // if (digits[len - 1] == 0){
            // digits[len] = 1;
            // }
            return myReverse(digits);
        }
    }

    public int[] myReverse(int[] digits) {
        for (int i = 0; i <= digits.length / 2 - 1; i++) {
            int temp = digits[i];
            digits[i] = digits[digits.length - i - 1];
            digits[digits.length - i - 1] = temp;
        }
        return digits;
    }
}

// 答案一
// 简单的实现, 加1, 进位. 唯一取巧的地方, 最后如果要多一位, 一定是10000...这个模式, 可以走捷径, 直接来个+1size的array,
// 然后第一位=1.
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

// 答案二
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