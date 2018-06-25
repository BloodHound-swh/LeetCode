/**
 * 三数之和
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */


//使用base指针，和twoSum的方法的左右指针
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length <= 2)
            return res;
        int n = nums.length;
        int i = 0;
        Arrays.sort(nums);
        while(i < n - 2){
            int base = nums[i];
            int left = i + 1;
            int right = n - 1;
            
            while(left < right){
                int sum = base + nums[left] + nums[right];
                if(sum == 0){
                    LinkedList<Integer> list = new LinkedList<Integer>();
                    list.add(base);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    left = moveRight(nums, left + 1);
                    right = moveLeft(nums, right - 1);
                }else if(sum > 0){
                    right = moveLeft(nums, right - 1);
                }else {
                    left = moveRight(nums, left + 1);
                }
            }
            
            i = moveRight(nums, i + 1);
        }
        return res;
    }
    
    
    public int moveRight(int[] nums, int left){
        while(left < nums.length && nums[left] == nums[left - 1]){
            left ++;
        }
        return left;
    }
    
    public int moveLeft(int[] nums, int right){
        while(right >= 0 && nums[right] == nums[right + 1]){
            right --;
        }
        return right;
    }
}


//代码优化
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0;i < nums.length - 2; i++){
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int low = i + 1, high = nums.length -1, sum = 0 - nums[i];
            while(low < high){
                if(nums[low] + nums[high] == sum){
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while(low < high && nums[low] == nums[low + 1]) low ++;
                    while(low < high && nums[high] == nums[high - 1]) high --;
                    low++;
                    high--;
                } else if (nums[low] + nums[high] < sum){
                    low ++;
                } else high --;
            }
        }
        return res;
    }
}