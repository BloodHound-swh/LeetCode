/**
 * 杨辉三角 II
给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。



在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:

输入: 3
输出: [1,3,3,1]
进阶：

你可以优化你的算法到 O(k) 空间复杂度吗？
 */

// 这里的技巧在于从后向前计算，并且每次计算用当前位置的值和上一位置的值，来更新当前位置的值。
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> line = new ArrayList<Integer>();
        line.add(1); // 加入第一个1
        if (rowIndex <= 0)
            return line;
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = line.size() - 2; j >= 0; j--) {
                line.set(j + 1, line.get(j) + line.get(j + 1)); // 计算j+1位置的值，是根据j位置的值和j+1位置的值得到的
            }
            line.add(1); // 加上最后一个1
        }
        return line;
    }
}

// 正着加
class Solution {
    public List<Integer> getRow(int rowIndex) {
        Integer[] result = new Integer[rowIndex + 1];
        Arrays.fill(result, 0);
        result[0] = 1;
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                result[j] = result[j] + result[j - 1];
            }
        }
        return Arrays.asList(result);
    }
}