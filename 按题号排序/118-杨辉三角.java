/**
 * 杨辉三角

给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。



在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:

输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
 */

// 未看答案版
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numRows == 0) {
            return res;
        }
        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(1);
        if (numRows == 1) {
            res.add(l1);
            return res;
        }
        List<Integer> l2 = new ArrayList<Integer>();
        l2.add(1);
        l2.add(1);
        if (numRows == 2) {
            res.add(l1);
            res.add(l2);
            return res;
        }
        res.add(l1);
        res.add(l2);
        for (int i = 2; i < numRows; i++) {
            List<Integer> temp = new ArrayList<Integer>();
            temp.add(1);
            for (int j = 1; j < i; j++) {
                temp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            }
            temp.add(1);
            res.add(temp);
        }
        return res;
    }
}

// 答案一
// 注意list.add(0,1)是插入制定位置，list.set(0,1)是在指定位置赋值,每次在row的第0位插入1,相当于另起一行了。
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascal = new ArrayList<List<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>();
        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++)
                row.set(j, row.get(j) + row.get(j + 1));
            pascal.add(new ArrayList<Integer>(row));
        }
        return pascal;
    }
}

// 答案二
// 直观的写法
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows > 2) {
            result.add(Arrays.asList(1));
            result.add(Arrays.asList(1, 1));
            for (int i = 2; i < numRows; i++) {
                List<Integer> tmp = new ArrayList<Integer>();
                tmp.add(1);// 注意与tmp.add(0,1)的区别
                for (int j = 1; j < i; j++) {
                    Integer t = result.get(i - 1).get(j - 1) + result.get(i - 1).get(j);
                    tmp.add(t);
                }
                tmp.add(1);
                result.add(tmp);
            }
        } else if (numRows == 2) {
            result.add(Arrays.asList(1));
            result.add(Arrays.asList(1, 1));
        } else if (numRows == 1) {
            result.add(Arrays.asList(1));
        }
        return result;
    }
}

// 思想同方法二
public class Solution {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        if (numRows == 0) {
            return rst;
        }

        ArrayList<Integer> first = new ArrayList<Integer>();
        first.add(0, 1);
        rst.add(first);

        for (int i = 1; i < numRows; i++) {
            ArrayList<Integer> tmp = new ArrayList<Integer>(i + 1);
            for (int j = 0; j < i + 1; j++) {
                tmp.add(-1);
            }
            ArrayList<Integer> prev = rst.get(i - 1);
            tmp.set(0, prev.get(0));
            tmp.set(i, prev.get(i - 1));
            for (int j = 1; j < i; j++) {
                tmp.set(j, prev.get(j - 1) + prev.get(j));
            }
            rst.add(tmp);
        }
        return rst;
    }
}