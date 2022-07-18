/**
 * 搜寻名人
假设你是一个专业的狗仔，参加了一个 n 人派对，其中每个人被从 0 到 n - 1 标号。在这个派对人群当中可能存在一位 “名人”。所谓 “名人” 的定义是：其他所有 n - 1 个人都认识他/她，而他/她并不认识其他任何人。

现在你想要确认这个 “名人” 是谁，或者确定这里没有 “名人”。而你唯一能做的就是问诸如 “A 你好呀，请问你认不认识 B呀？” 的问题，以确定 A 是否认识 B。你需要在（渐近意义上）尽可能少的问题内来确定这位 “名人” 是谁（或者确定这里没有 “名人”）。

在本题中，你可以使用辅助函数 bool knows(a, b) 获取到 A 是否认识 B。请你来实现一个函数 int findCelebrity(n)。

派对最多只会有一个 “名人” 参加。若 “名人” 存在，请返回他/她的编号；若 “名人” 不存在，请返回 -1。

 

示例 1:



输入: graph = [
  [1,1,0],
  [0,1,0],
  [1,1,1]
]
输出: 1
解释: 有编号分别为 0、1 和 2 的三个人。graph[i][j] = 1 代表编号为 i 的人认识编号为 j 的人，而 graph[i][j] = 0 则代表编号为 i 的人不认识编号为 j 的人。“名人” 是编号 1 的人，因为 0 和 2 均认识他/她，但 1 不认识任何人。
示例 2:



输入: graph = [
  [1,0,1],
  [1,1,0],
  [0,1,1]
]
输出: -1
解释: 没有 “名人”
 

提示：

n == graph.length
n == graph[i].length
2 <= n <= 100
graph[i][j] 是 0 或 1.
graph[i][i] == 1
 

进阶：如果允许调用 API knows 的最大次数为 3 * n ，你可以设计一个不超过最大调用次数的解决方案吗？
 */

/* The knows API is defined in the parent class Relation.
boolean knows(int a, int b); */

// 从0开始假设为名人候选
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 0; i < n; i++) {
            // 如果candidate认识别人，那么自己必然不是名人，直接假设i是名人。
            // 当某个i确实为名人时，由于他谁都不认识，此后candidate就不会再变了，剩下的就只需要验证candidate确实为名人
            if (knows(candidate, i)) {
                candidate = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != candidate) {
                // 如果candidate认识了别人，就说明他不是名人
                if (knows(candidate, i)) {
                    return -1;
                }
                // 如果有人不认识candidate，也说明candidate不是名人
                if (!knows(i, candidate)) {
                    return -1;
                }
            }
        }

        return candidate;
    }
}

// 暴力解
public class Solution extends Relation {
    public int findCelebrity(int n) {
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                // 过滤自己认识自己的情况
                if (i == j) {
                    continue;
                }
                // 假设i为名人， 如果i认识除自己以外的其他人，或者有其他人（j）不认识i
                // 那么就可以直接断定当前的i并不是名人
                if (knows(i, j) || !knows(j, i)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }
}