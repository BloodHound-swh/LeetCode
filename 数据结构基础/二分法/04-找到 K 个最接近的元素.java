/**
 * 找到 K 个最接近的元素
给定一个排序好的数组，两个整数 k 和 x，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。如果有两个数与 x 的差值一样，优先选择数值较小的那个数。

示例 1:

输入: [1,2,3,4,5], k=4, x=3
输出: [1,2,3,4]
 

示例 2:

输入: [1,2,3,4,5], k=4, x=-1
输出: [1,2,3,4]
 

说明:

k 的值为正数，且总是小于给定排序数组的长度。
数组不为空，且长度不超过 10^4
数组里的每个元素与 x 的绝对值不超过 10^4

 */

// 每次从头尾分别看与目标值得差值，删去差值大的那个元素
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        while (list.size() > k) {
            int left = 0, right = list.size() - 1;
            if (x - list.get(left) <= list.get(right) - x) {
                list.remove(right);
            } else {
                list.remove(left);
            }
        }
        return list;
    }
}

// 其实没太搞明白原理
// 二分法的判定条件做了一些改变，就可以直接找到要返回的k的数字的子数组的起始位置，感觉非常的神奇。
// 每次比较的是mid位置和x的距离跟mid+k跟x的距离，以这两者的大小关系来确定二分法折半的方向，最后找到最近距离子数组的起始位置
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        int start = 0, end = arr.length - k;
        while (start < end) {
            int mid = (start + end) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                start = mid + 1;
            } else {
                end = mid;
            }

        }
        for (int j = start; j < start + k; j++) {
            list.add(arr[j]);
        }
        return list;
    }
}