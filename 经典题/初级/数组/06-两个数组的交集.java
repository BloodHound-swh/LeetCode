/*
两个数组的交集 II
给定两个数组，写一个方法来计算它们的交集。
例如:
给定 nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2, 2].
注意：

   输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
   我们可以不考虑输出结果的顺序。
跟进:
如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小很多，哪种方法更优？
如果nums2的元素存储在磁盘上，内存是有限的，你不能一次加载所有的元素到内存中，你该怎么办？
*/

//使用map的键值对来确定重复性，建立一个链表，当键所对应的值大于0时，则在链表中加入该数字
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0;i<nums1.length;i++){
            if(map.containsKey(nums1[i]))
                map.put(nums1[i],map.get(nums1[i])+1);
            else
                map.put(nums1[i], 1);
        }
        List<Integer> results = new ArrayList<Integer>();
        for(int i=0;i<nums2.length;i++){
            if(map.containsKey(nums2[i])&&map.get(nums2[i])>0){
                results.add(nums2[i]);
                map.put(nums2[i],map.get(nums2[i])-1);
            }
        }
        int result[] = new int[results.size()];
        for(int i=0;i<results.size();i++){
            result[i] = results.get(i);
        }
        
        return result;
    }
}


//将两数组排序，使用三个指针
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
	   Arrays.sort(nums1);
	   Arrays.sort(nums2);
       int[] save = new int[nums1.length<nums2.length?nums1.length:nums2.length];
       int i=0,j=0,k=0;
       
       while(i<nums1.length&&j<nums2.length) {
       if(nums1[i]==nums2[j]) {
    	   save[k]=nums1[i];
    	   i++;j++;k++;
         }else if(nums1[i]<nums2[j]) {
    	   i++;
         }else {
    	   j++;
         }
       }
        return Arrays.copyOfRange(save,0 ,k);
     //  return Arrays.copyOf(save,k);
    }
}