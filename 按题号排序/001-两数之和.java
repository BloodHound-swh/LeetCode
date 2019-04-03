/**
 * 两数之和

给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。

你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
 */



// 未看答案版，由于没有注意到题目中说答案是唯一的，也就是res只是一个2维数组而已，所以错误的使用了list来记录答案
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                list.add(map.get(temp));
                list.add(i);
            } else {
                map.put(nums[i], i);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}

// 未看答案版二，个人觉得我的答案更好
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
            } else {
                if (!map.containsKey(nums[i]))
                    map.put(nums[i], i);
            }
        }

        return res;
    }
}

// 答案一，超时，暴力循环
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }
}

// 答案二
// 用哈希表先把数组中的数字和对应的下标存储一遍，即数字作为键，下标作为值，存储.
// 当遍历数组的时候用target-nums[i]，得到差v，然后在map中找是否存在 v，找到即返回v所对应的value,也就是所对应的数组下标。
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++)
            map.put(nums[i], i);

        for (int i = 0; i < nums.length; i++) {
            int v = target - nums[i];
            if (map.containsKey(v) && i != map.get(v)) { // 注意第二个条件，防止自己刚好是目标值一半的时候把自己的index写进答案了
                result[0] = i;
                result[1] = map.get(v);
                return result;
            }
        }
        return result;
    }
}

// 答案三，也是不看答案版的标准写法
// 上面方法的简便写法，遍历数组时，将numbers[i]与target的差值作为键，坐标作为值存储。并判断下一个数组元素值是否是map中的一个键
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (map.get(numbers[i]) != null) {
                int[] result = { map.get(numbers[i]), i };
                return result;
            }
            map.put(target - numbers[i], i);
        }

        int[] result = {};
        return result;
    }
}