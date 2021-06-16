/**
 * 题目描述 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */

// 传统HashMap法
public class Solution {
    public int MoreThanHalfNum_Solution(int[] array) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (!map.containsKey(array[i])) {
                map.put(array[i], 1);
            } else {
                map.put(array[i], map.get(array[i]) + 1);
            }

            if (map.get(array[i]) > array.length / 2) {
                res = array[i];
                break;
            }
        }

        return res;
    }
}

// 同LeetCode第169题，但是这里有可能不存在超过一半的数
public class Solution {
    public int MoreThanHalfNum_Solution(int[] array) {
        int res = array[0], count = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == res)
                count++;
            else {
                count--;
            }
            if (count == 0) {
                res = array[i];
                count = 1;
            }
        }
        count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == res)
                count++;
        }
        return count > array.length / 2 ? res : 0;
    }
}