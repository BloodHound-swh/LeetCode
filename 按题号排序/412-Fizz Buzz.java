/**
 * Fizz Buzz
题目描述提示帮助提交记录社区讨论阅读解答
写一个程序，输出从 1 到 n 数字的字符串表示。

1. 如果 n 是3的倍数，输出“Fizz”；

2. 如果 n 是5的倍数，输出“Buzz”；

3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。

示例：

n = 15,

返回:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
 */


// 未看答案版
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> s = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {  // i % 15 == 0 也行，而且速度更快一些
                s.add("FizzBuzz");
            } else if (i % 3 == 0) {
                s.add("Fizz");
            } else if (i % 5 == 0) {
                s.add("Buzz");
            } else {
                s.add(String.valueOf(i));
            }
        }
        return s;
    }
}

// 答案
// 就是简单的条件判定，注意i+""是将i转变成字符串，也可以用String.valueOf(i)
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                res.add("FizzBuzz");
            } else if (i % 3 == 0) {
                res.add("Fizz");
            } else if (i % 5 == 0) {
                res.add("Buzz");
            } else {
                res.add(i + "");
            }
        }
        return res;
    }
}