/**
 * 电话号码的字母组合

给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



示例:

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
说明:
尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */

// 未看答案没有做出，考虑多多个数字如何组合字母是没有想到使用广度优先和深度优先

// 答案一，广度优先
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

        Queue<String> queue = new LinkedList<String>();
        char c = digits.charAt(0);
        String s = map.get(c);
        for (int i = 0; i < s.length(); i++) {
            queue.offer(s.charAt(i) + "");
        }

        int size = 0;
        for (int i = 1; i < digits.length(); i++) {
            c = digits.charAt(i);
            s = map.get(c);
            size = queue.size();
            for (int j = 0; j < size; j++) {
                String str = queue.poll();
                for (int k = 0; k < s.length(); k++) {
                    queue.offer(str + s.charAt(k));
                }
            }
        }
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }

        return res;
    }
}

// 使用广度优先遍历，用一个Queue，每次push当前的size()次，将push出的字符串append下一个数字对应的字母，再添加到Queue的末尾
public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> rst = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return rst;
        }
        // Init map
        HashMap<Character, String> map = new HashMap<Character, String>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        // Init 1 digits and the chars in queue
        Queue<String> queue = new LinkedList<String>();
        char c = digits.charAt(0);
        String s = map.get(c);
        for (int i = 0; i < s.length(); i++) {
            queue.offer(s.charAt(i) + "");
        }

        int size = 0;
        for (int i = 1; i < digits.length(); i++) {// iterate all numbers
            c = digits.charAt(i);
            s = map.get(c);
            size = queue.size();
            for (int j = 0; j < size; j++) {// iterate old queue
                String str = queue.poll();
                for (int k = 0; k < s.length(); k++) {// iterate possibile chars per number key
                    queue.offer(str + s.charAt(k));
                }
            }
        }
        while (!queue.isEmpty()) {
            rst.add(queue.poll());
        }

        return rst;
    }
}

// 答案二
// 使用map来存放数字与字母对应的键值对，使用深度优先遍历，每次一个数字，看数字对应的字母，根据currindex走向下一个数字，来寻找可能的字母组合
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<String>();
        if (digits == null || digits.length() == 0)
            return res;
        HashMap<Character, char[]> map = new HashMap<Character, char[]>();
        map.put('2', new char[] { 'a', 'b', 'c' });
        map.put('3', new char[] { 'd', 'e', 'f' });
        map.put('4', new char[] { 'g', 'h', 'i' });
        map.put('5', new char[] { 'j', 'k', 'l' });
        map.put('6', new char[] { 'm', 'n', 'o' });
        map.put('7', new char[] { 'p', 'q', 'r', 's' });
        map.put('8', new char[] { 't', 'u', 'v' });
        map.put('9', new char[] { 'w', 'x', 'y', 'z' });

        helper("", 0, digits, res, map);
        return res;
    }

    public void helper(String curr, int currIdx, String digits, List<String> res, HashMap<Character, char[]> map) {
        if (currIdx == digits.length()) {
            res.add(curr);
        } else {
            char c = digits.charAt(currIdx);
            if (map.containsKey(c)) {
                for (char ch : map.get(c)) {
                    helper(curr + ch, currIdx + 1, digits, res, map);
                }
            } else {
                // c == '1'
            }
        }
    }
}
