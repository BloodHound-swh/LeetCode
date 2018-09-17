

//最简单的循环判断
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


//考虑到int的范围，且3^19等于1162261467  int范围-2147483648~2147483648
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        if (1162261467%n == 0) return true;
        return false;
    }
}

//对数的方法：一个数是3的次方，那么以3为底n的对数一定是个 整数。
class Solution {
    public boolean isPowerOfThree(int n) {
        double tem = Math.log10(n) / Math.log10(3);
        return (tem - (int)(tem)) == 0?true:false;
    }
}
