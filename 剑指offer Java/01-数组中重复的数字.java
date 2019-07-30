/**
 * 题目描述
在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 
例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 */


import java.util.HashMap;

public class Solution {
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < length; i++) {
            if (map.containsKey(numbers[i])) {
                map.put(numbers[i], map.get(numbers[i])+ 1);
            } else {
                map.put(numbers[i], 1);
            }
        }
        
        for (int i = 0; i < length; i++) {
            if (map.get(numbers[i]) > 1) {
                duplication[0] = numbers[i]; // 这里题目要求还需要用duplication[0]来存放第一个重复的数
                return true;
            }
        }
        
        return false;
    }
}

// 因为数组中的数字都在0~n-1的范围内，所以，如果数组中没有重复的数，那当数组排序后，数字i将出现在下标为i的位置。
// 现在我们重排这个数组，从头到尾扫描每个数字，当扫描到下标为i的数字时，首先比较这个数字(记为m)是不是等于i。
// 如果是，则接着扫描下一个数字；
// 如果不是，则再拿它和m位置上的数字进行比较
// 如果它们相等，就找到了一个重复的数字（该数字在下标为i和m的位置都出现了），返回true；
// 如果它和m位置上的数字不相等，就把第i个数字和第m个数字交换，把m放到属于它的位置。
// 接下来再继续循环，直到最后还没找到认为没找到重复元素，返回false。
public class Solution {
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if(length == 0)
            return false;
        for(int i = 0; i < length; i++){
            while(i != numbers[i]){
                if(numbers[i] == numbers[numbers[i]]){
                    duplication[0] = numbers[i];
                    return true;
                }else{
                    int temp = numbers[numbers[i]]; // 注意这里交换的顺序，很容易出错先改变了numbers[i]的值而导致交换失败
                    numbers[numbers[i]] = numbers[i];
                    numbers[i] = temp;
                }
            }
        }
        return false;
    }
}