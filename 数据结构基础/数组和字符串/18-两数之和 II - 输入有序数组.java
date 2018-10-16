/**
 * 两数之和 II - 输入有序数组
给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

说明:

返回的下标值（index1 和 index2）不是从零开始的。
你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
示例:

输入: numbers = [2, 7, 11, 15], target = 9
输出: [1,2]
解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 */


// 与它的原型题一模一样的解法，但是这种解法没有考虑到numbers是有序的数组
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            int temp = target - numbers[i];
            if (!map.containsKey(temp)) {
                map.put(numbers[i], i + 1);
            } else {
                list.add(map.get(temp));
                list.add(i + 1);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}

// 因为数组是有序的
// 我们只需要两个指针，一个指向开头，一个指向末尾，然后向中间遍历
// 如果指向的两个数相加正好等于target的话，直接返回两个指针的位置即可
// 若小于target，左指针右移一位，若大于target，右指针左移一位，以此类推直至两个指针相遇停止
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int[] result = new int[2];
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target){
                result[0] = ++left;
                result[1] = ++right;
                break;
            }
            else if (sum > target) {
                right--;
            }
            else if (sum < target){
                left++;
            }
        }
        return result;
    }
}

// 优化
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 1) {
            return new int[] { -1, -1 };
        }

        int index1 = 0, index2 = numbers.length - 1;

        while (index1 < index2) {
            int sum = numbers[index1] + numbers[index2];
            if (sum == target) {
                return new int[] { index1 + 1, index2 + 1 };
            } else if (sum > target) {
                index2--;
            } else {
                index1++;
            }
        }

        return new int[] { -1, -1 };
    }
}