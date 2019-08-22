/**
 * 题目描述
求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 */


// 找规律的方法比较牛逼，但是基础方法也要会
public class Solution {
    public int NumberOf1Between1AndN_Solution(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res = res + helper(i);
        }
        return res;
    }
    
    public int helper(int number) {
        int count = 0;
        int tmp = 0;
        while (number != 0) {
            tmp = number % 10;
            if (tmp == 1) {
                count++;
            }
            number = number / 10;
        }
        
        return count;
    }
}


// amazing
public class Solution {
    public int NumberOf1Between1AndN_Solution(int n) {
        if (n < 1)
            return 0;
        int len = getLenOfNum(n);
        if (len == 1)
            return 1;
        int tmp = (int) Math.pow(10, len - 1);
        int first = n / tmp;
        int firstOneNum = first == 1 ? n % tmp + 1 : tmp;
        int otherOneNUm = first * (len - 1) * (tmp / 10);
        return firstOneNum + otherOneNUm + NumberOf1Between1AndN_Solution(n % tmp);
    }
 
    private int getLenOfNum(int n) {
        int len = 0;
        while (n != 0) {
            len++;
            n /= 10;
        }
        return len;
    }
}