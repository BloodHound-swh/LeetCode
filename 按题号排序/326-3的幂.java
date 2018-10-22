/**
 * 3的幂

给定一个整数，写一个函数来判断它是否是 3 的幂次方。

示例 1:

输入: 27
输出: true
示例 2:

输入: 0
输出: false
示例 3:

输入: 9
输出: true
示例 4:

输入: 45
输出: false
进阶：
你能不使用循环或者递归来完成本题吗？
 */

// 未看答案有错误，int问题
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n > 0 && n < 3)
            return false;
        while (n > 0) {
            n = n / 3;
            if (n == 1)
                return true;
        }
        return false;
    }
}

// 答案一
// 最简单的循环判断
class Solution {
    public boolean isPowerOfThree(int n) {
        int i = 0;
        while(true){
            if(Math.pow(3, i) == n)
                return true;
            if(Math.pow(3, i) > n)
                return false;
            i++;
        }
    }
}

// 答案二，这个方法应该最快
// 考虑到int的范围，且3^19等于1162261467 int范围-2147483648~2147483648
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0)
            return false;
        if (1162261467 % n == 0)
            return true;
        return false;
    }
}

// 答案三
// 对数的方法：一个数是3的次方，那么以3为底n的对数一定是个 整数。
class Solution {
    public boolean isPowerOfThree(int n) {
        double tem = Math.log10(n) / Math.log10(3);
        return (tem - (int) (tem)) == 0 ? true : false;
    }
}