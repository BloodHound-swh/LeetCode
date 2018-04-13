/**
反转字符串
请编写一个函数，其功能是将输入的字符串反转过来。
示例：
输入：s = "hello"
返回："olleh"
 */

 //沿着“中线”翻转即可

 class Solution {
    public String reverseString(String s) {
        char[] arr = s.toCharArray();
        for(int i=0;i<arr.length/2;i++){
            char temp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = temp;
        }
        return String.valueOf(arr);
    }
}