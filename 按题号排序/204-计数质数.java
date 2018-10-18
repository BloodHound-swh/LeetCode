/**
 * 计数质数

统计所有小于非负整数 n 的质数的数量。

示例:

输入: 10
输出: 4
解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 */

// 未看答案版，没有做出，需要用上埃拉托斯特尼筛法

// 答案一
// 我们从2开始遍历到根号n，先找到第一个质数2，然后将其所有的倍数全部标记出来
// 然后到下一个质数3，标记其所有倍数，以此类推，此时数组中未被标记的数字就是质数。
// 我们需要一个n-1长度的bool型数组来记录每个数字是否被标记，长度为n-1的原因是题目说是小于n的质数个数，并不包括n。
class Solution {
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                res++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return res;
    }
}

// 答案二
// 第一个循环是直到根号n
class Solution {
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int res = 0;
        for (int i = 2; i <= Math.sqrt(n); i++) { 
            if (notPrime[i] == false) {
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        for (int j = 2; j < n; j++) {
            if (notPrime[j] == false) {
                res++;
            }
        }
        return res;
    }
}

// 答案三，可读性更好
//将boolean数组定为true，这种写法leetcode显示速度更快
public class Solution {
    public int countPrimes(int n) {
        boolean[] prime = new boolean[n];
        Arrays.fill(prime, true);
        for(int i = 2; i < n; i++){  //或者i<Math.sqrt(n)更好
            if(prime[i]){
                // 将i的2倍、3倍、4倍...都标记为非素数
                for(int j = i * 2; j < n; j =  j + i){  // 注意是j + i 而不要看成 j + 1
                    prime[j] = false;
                }
            }
        }
        int count = 0;
        for(int i = 2; i < n; i++){
            if(prime[i]) count++;
        }
        return count;
    }
}