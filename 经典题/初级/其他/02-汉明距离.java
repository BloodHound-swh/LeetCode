/**
汉明距离
两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。

给出两个整数 x 和 y，计算它们之间的汉明距离。

注意：
0 ≤ x, y < 231.

示例:

输入: x = 1, y = 4

输出: 2

解释:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

上面的箭头指出了对应二进制位不同的位置。
 */


//逐位比较
class Solution {
    public int hammingDistance(int x, int y) {
        int Distance = 0;
        for(int i = 0; i< 32; i++){
            if( x % 2 != y % 2)
                Distance ++;
            x >>= 1;
            y >>= 1;
        }
        return Distance;
    }
}

//优化上面的方法
class Solution {
    public int hammingDistance(int x, int y) {
        int count = 0;
        for(int i = 31;i >= 0; i--)
            if((x >>> i & 1) != (y >>> i & 1)){
                count ++;
            }
        return count;
    }
}

//使用z来标记不同地方，在用n&n-1来统计不同的个数
class Solution {
    public int hammingDistance(int x, int y) {
        int Distance = 0;
        int z = x ^ y;
        while(z != 0){
            z = z & (z - 1); 
            Distance ++;
        }
        return Distance;
    }
}

//改变了while的判定条件，发现变快了
class Solution {
    public int hammingDistance(int x, int y) {
        int hamming = x ^ y;
        int cnt = 0;
        while(hamming > 0){
            hamming = hamming & (hamming - 1);
            cnt++;
        }
        return cnt;
    }
}