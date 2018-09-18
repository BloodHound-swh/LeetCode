/**
 * 旋转数组

给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
示例 2:

输入: [-1,-100,3,99] 和 k = 2
输出: [3,99,-1,-100]
解释: 
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]

 */


// 未看答案，但是没有做到原地算法
class Solution {
    public void rotate(int[] nums, int k) {
        int[] dummy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dummy[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = dummy[i];
        }
    }
}

// 即便不是原地算法，上面的依然还可以优化
class Solution {
    public void rotate(int[] nums, int k) {
        int[] dummy = nums.clone(); // 注意不可以写成int[] dummy = nums;
        for (int i = 0; i < nums.length; i++) {
            nums[(i + k) % nums.length] = dummy[i];
        }
    }
}

// 答案一，也没有用原地算法，注意clone(),Arrays.copyOf()
// 长度为7的数组，向右旋转3步，数字的位置加3之后大于7的都要再从0号位置重新开始计算剩余的步子。这点特性就可以很好的用到求余。
class Solution {
    public void rotate(int[] nums, int k) {
        int[] temp = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < nums.length; i++) {
            nums[(i + k) % nums.length] = temp[i];
        }
    }
}

// 答案二，依然没有用到原地算法
// 使用System.arraycopy的技巧，建立一个length+k的数组，将从自己的第k位开始拷贝原数组，在将前k位赋值为自己的最后k位，再重新拷贝回原数组
class Solution {
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        int newnums[] = new int[length + k];
        System.arraycopy(nums, 0, newnums, k, length);
        for (int i = k - 1; i >= 0; i--) {
            newnums[i] = newnums[i + length];
        }
        System.arraycopy(newnums, 0, nums, 0, length);
    }
}

// 答案三，原地算法
// 思路是先把前n-k个数字翻转一下，再把后k个数字翻转一下，最后再把整个数组翻转一下：
// 1 2 3 4 5 6 7
// 4 3 2 1 5 6 7
// 4 3 2 1 7 6 5
// 5 6 7 1 2 3 4
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return;
        int n = nums.length;
        int t = k % nums.length;
        reverse(nums, 0, n - t - 1);
        reverse(nums, n - t, n - 1);
        reverse(nums, 0, n - 1);
    }

    public void reverse(int[] arr, int s, int e) {
        while (s < e) {
            int temp = arr[s];
            arr[s] = arr[e];
            arr[e] = temp;
            s++;
            e--;
        }
    }
}