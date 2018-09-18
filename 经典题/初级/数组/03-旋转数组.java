/*
旋转数组
将包含 n 个元素的数组向右旋转 k 步。
例如，如果  n = 7 ,  k = 3，给定数组  [1,2,3,4,5,6,7]  ，向右旋转后的结果为 [5,6,7,1,2,3,4]。
注意:
尽可能找到更多的解决方案，这里最少有三种不同的方法解决这个问题。
要求空间复杂度为 O(1)
关联的问题: 反转字符串中的单词 II
*/

//长度为7的数组，向右旋转3步，数字的位置加3之后大于7的都要再从0号位置重新开始计算剩余的步子。这点特性就可以很好的用到求余。
class Solution {
    public void rotate(int[] nums, int k) {
        int[] temp = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < nums.length; i++) {
            nums[(i + k) % nums.length] = temp[i];
        }
    }
}

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

// 原地算法
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