/**
 * 17. 电话号码的字母组合
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



 

示例 1：

输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
示例 2：

输入：digits = ""
输出：[]
示例 3：

输入：digits = "2"
输出：["a","b","c"]
 

提示：

0 <= digits.length <= 4
digits[i] 是范围 ['2', '9'] 的一个数字。
 */


// 使用广度优先遍历，用一个Queue，每次push当前的size()次，将push出的字符串append下一个数字对应的字母，再添加到Queue的末尾
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }

        HashMap<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        Queue<String> queue = new LinkedList<>();
        char c = digits.charAt(0);
        String s = map.get(c);
        for (int i = 0; i < s.length(); i++) {
            queue.offer(s.charAt(i) + "");
        }

        for (int i = 1; i < digits.length(); i++) {
            c = digits.charAt(i);
            s = map.get(c);
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String str = queue.poll();
                for (int k = 0; k < s.length(); k++) {
                    queue.offer(str + s.charAt(k));
                }
            }
        }

        while(!queue.isEmpty()) {
            res.add(queue.poll());
        }

        return res;

    }
}