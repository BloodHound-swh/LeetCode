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


// 先求出杨辉三角形，再取出对应行
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> pascal = new ArrayList<>();
        if (rowIndex >= 2) {
            pascal.add(Arrays.asList(1));
            pascal.add(Arrays.asList(1, 1));
            for (int i = 2; i <= rowIndex; i++) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(1);
                for (int j = 1; j < i; j++) {
                    Integer t = pascal.get(i - 1).get(j - 1) + pascal.get(i - 1).get(j);
                    tmp.add(t);
                }
                tmp.add(1);
                pascal.add(tmp);
            }
        } else if (rowIndex == 1) {
            return Arrays.asList(1, 1);
        } else if (rowIndex == 0) {
            return Arrays.asList(1);
        }

        return pascal.get(rowIndex);
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