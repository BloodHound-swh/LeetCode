/**
 * 题目描述
一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */

// 跳n级台阶，可以从n-1级跳上来，也可以从n-2级跳上来，从n-3级跳上来，依次下去，从第1级跳上去，或直接跳上去.
// 所以，跳n级台阶的方法数相当于其它所有台阶数的方法的总和再加上从0级跳上去，表达式为 f(n) = f(n-1) + f(n-2) +...+ f(2) + f(1) + 1。
// 例如：
// 当跳1级台阶时，f(1) = 1;
// 当跳2级台阶时，f(2) = f(1) + 1 = 2;
// 当跳3级台阶时，f(3) = f(2) + f(1) + 1 = 4;
// 当跳4级台阶时，f(4) = f(3) + f(2) + f(1) + 1 = 8;
// 。。。。。。。。。。。。。。。。。。。。。
// f(n) = f(n-1) + f(n-2) +...+ f(2) + f(1) + 1
// f(n-1) = f(n-2) +...+ f(2) + f(1) + 1
// ===》》 f(n) - f(n-1) = f(n-1)     ===》》f(n) = 2 * f(n-1)
public class Solution {
    public int JumpFloorII(int target) {
        if (target == 0)
            return 0;
        if (target == 1)
            return 1;

        int num = 1;
        for (int i = 2; i <= target; i++) {
            num = num * 2;
        }

        return num;
    }
}

// 递归
public class Solution {
    public int JumpFloorII(int target) {
        if(target == 0){
            return -1;
        }
        if(target == 1){
            return 1;
        }
        return 2*JumpFloorII(target - 1);
    }
}