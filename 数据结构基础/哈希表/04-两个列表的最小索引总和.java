/**
 * 两个列表的最小索引总和
假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。

你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。

示例 1:

输入:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
输出: ["Shogun"]
解释: 他们唯一共同喜爱的餐厅是“Shogun”。
示例 2:

输入:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
输出: ["Shogun"]
解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
提示:

两个列表的长度范围都在 [1, 1000]内。
两个列表中的字符串的长度将在[1，30]的范围内。
下标从0开始，到列表的长度减1。
两个列表都没有重复的元素。
 */



// 最先考虑到的就是要建立数据和其位置坐标之间的映射。
// 我们建立list1的值和坐标的之间的映射，然后遍历list2，如果当前遍历到的字符串在list1中也出现了，那么我们计算两个的坐标之和
// 如果跟我们维护的最小坐标和min相同，那么将这个字符串加入结果res中，如果比min小，那么mn更新为这个较小值，然后将结果res清空并加入这个字符串
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1 == null || list2 == null || list1.length == 0 || list2.length == 0)
            return new String[0];
        int min = list1.length + list2.length;
        List<String> res = new LinkedList<>();
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if (list1[i].equals(list2[j])) {
                    if (i + j < min) {
                        res = new LinkedList<>();
                        res.add(list1[i]);
                        min = i + j;
                    } else if (i + j == min) {
                        res.add(list1[i]);
                    }
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }
}