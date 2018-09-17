/**
 *  最大数
给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。

示例 1:

输入: [10,2]
输出: 210
示例 2:

输入: [3,30,34,5,9]
输出: 9534330
说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 */


// 从第一位向后比较，如果有一位更大，则该数更大，如9大于15，24大于23。
// 如果某个数的前半部分和另一个数完全相同，则看该数剩下的那部分和另一个数的大小关系，如2332和23比较时，2332是大于23的，因为32大于23。
// 不过如果细分各种情况，会弄得非常复杂，这里有个技巧，就是我们把两个数拼在一起，然后再把这两个数换个顺序再拼在一起，这时候就可以直接比较了。
// 比如2332和23，变成233223和232332两个数，这时候那个数更大，就说明这个数前半部分的那个数是更大的，这里是2332。
class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0)
            return "";

        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            str[i] = String.valueOf(nums[i]);
        }

        // 排序的方法是前后相拼再后前相拼，比较大小
        Arrays.sort(str, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2);
            }
        });

        // 如果第一个数是0，则直接返回0。注意equals和==的区别
        if (str[0].equals("0")) {
            return "0";
        }

        // 将数从大到小拼起来就行了
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(str[i]);
        }

        return sb.toString();
    }
}


// 优化（其实只能算是美化）
class Solution {

    class MyComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            String temp1 = s1 + s2;
            String temp2 = s2 + s1;
            int differ = temp2.compareTo(temp1);
            return differ;
        }
    }

    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = "" + nums[i];
        }
        Arrays.sort(strs, new MyComparator());
        if (strs[0].equals("0")) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }
}