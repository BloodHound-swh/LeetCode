/**
 * 剑指 Offer 61. 扑克牌中的顺子
从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。

 

示例 1:

输入: [1,2,3,4,5]
输出: True
 

示例 2:

输入: [0,0,1,2,5]
输出: True
 

限制：

数组长度为 5 

数组的数取值为 [0, 13] .
 */



// 此55张牌是顺子的充分条件 如下
// 1-除大小王外，所有牌无重复;
// 2-设此5张牌中最大的牌为max，最小的牌为min(大小王除外)，则需满足：max - min < 5
class Solution {
    public boolean isStraight(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0, min = 13;
        for (int n : nums) {
            if (n == 0) {
                continue;
            }
            if (set.contains(n)) {
                return false;
            }
            min = Math.min(min, n);
            max = Math.max(max, n);
            set.add(n);
        }

        return max - min < 5;
    }
}


class Solution {
    public boolean isStraight(int[] nums) {
        int joker = 0;
        // 数组排序
        Arrays.sort(nums); 
        for(int i = 0; i < 4; i++) {
            // 统计大小王数量
            if(nums[i] == 0) {
                joker++; 
            } else if (nums[i] == nums[i + 1]) {
                // 若有重复，提前返回 false
                 return false; 
            }
            
        }
        // 最大牌 - 最小牌 < 5 则可构成顺子
        return nums[4] - nums[joker] < 5; 
    }
}