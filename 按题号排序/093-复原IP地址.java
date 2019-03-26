/**
 * 复原IP地址
给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

示例:

输入: "25525511135"
输出: ["255.255.11.135", "255.255.111.35"]
 */


// basketwang
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() > 12)
            return res;

        helper(res, s, "", 0);
        return res;
    }

    public void helper(List<String> res, String s, String curr, int dot) {
        if (dot == 4 && s.length() == 0) {
            res.add(curr.substring(1));
        } else if (dot == 4 || s.length() == 0) {
            return;
        } else {
            helper(res, s.substring(1), curr + "." + s.substring(0, 1), dot + 1);
            if (s.charAt(0) != '0' && s.length() > 1) {
                helper(res, s.substring(2), curr + "." + s.substring(0, 2), dot + 1);
                if (s.length() > 2 && Integer.valueOf(s.substring(0, 3)) <= 255) {
                    helper(res, s.substring(3), curr + "." + s.substring(0, 3), dot + 1);
                }
            }
        }
    }
}