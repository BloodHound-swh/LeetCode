/**
 * 剑指 Offer 64. 求1+2+…+n
求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

 

示例 1：

输入: n = 3
输出: 6
示例 2：

输入: n = 9
输出: 45
 

限制：

1 <= n <= 10000
 */


// 小学生都会的求和公式，但是题目里要求不能使用乘法，所以这个方法不符合要求
class Solution {
    public int sumNums(int n) {
        return n * (n + 1) / 2;
    }
}

// 使用迭代会用到if，仍然不符合要求
public int sumNums(int n) {
    if(n == 1) {
        return 1;
    }
    n += sumNums(n - 1);
    return n;
}

// 运用逻辑符号短路的特性，可以避免使用if来作为递归的终止条件
// https://leetcode-cn.com/problems/qiu-12n-lcof/solution/mian-shi-ti-64-qiu-1-2-nluo-ji-fu-duan-lu-qing-xi-/
// 大佬依然很强， 但是出这种限制真的是纯八股文了
class Solution {
    int res = 0;
    public int sumNums(int n) {
        boolean x = n > 1 && sumNums(n - 1) > 0;
        res += n;
        return res;
    }
}