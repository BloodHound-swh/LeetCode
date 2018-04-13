/*
两数之和
给定一个整数数列，找出其中和为特定值的那两个数。
你可以假设每个输入都只会有一种答案，同样的元素不能被重用。
示例:
给定 nums = [2, 7, 11, 15], target = 9
因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
 */



//最简单的方法，双重循环
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }
}


//用哈希表先把数组中的数字和对应的下标存储一遍，即数字作为键，下标作为值，存储.
//当遍历数组的时候用target-nums[i]，得到差v，然后在map中找是否存在 v，找到即返回v所对应的value,也就是所对应的数组下标。
class Solution {  
    public int[] twoSum(int[] nums, int target) {  
        int[] result = new int[2];  
        Map<Integer,Integer> map = new HashMap<>();  
        for (int i = 0; i < nums.length; i++)  
            map.put(nums[i],i);  
  
        for (int i = 0; i < nums.length; i++) {  
            int v = target - nums[i];  
            if (map.containsKey(v) && i != map.get(v)){  
                result[0] = i;  
                result[1] = map.get(v);  
                return result;  
            }  
        }  
        return result;  
    }  
}  



//上面方法的简便写法，遍历数组时，将numbers[i]与target的差值作为键，坐标作为值存储。并判断下一个数组元素值是否是map中的一个键
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (map.get(numbers[i]) != null) {
                int[] result = {map.get(numbers[i]), i};
                return result;
            }
            map.put(target - numbers[i], i);
        }
        
        int[] result = {};
        return result;
    }
}