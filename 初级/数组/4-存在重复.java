/*
存在重复
给定一个整数数组，判断是否存在重复元素。

如果任何值在数组中出现至少两次，函数应该返回 true。如果每个元素都不相同，则返回 false。
*/

//



class Solution {
    public static boolean containsDuplcate(int[] nums) {
        if(nums==null||nums.length<=1){
            return false;
        }
        for(int i=0;i<nums.length-1;i++){
            for(int j = i+1;j<nums.length;j++){
                if(nums[i]==nums[j]){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
    	int[] nums = {1,2,3,3};
    	boolean x = containsDuplcate(nums);
    	System.out.println(x);
    }
}

//采用一个Set容器即可轻松解决。
class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        final HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}


//使用Sorry array, nLog(n)

class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
