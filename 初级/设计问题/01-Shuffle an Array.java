

//任意一种排列组合出现的几率相同那么肯定会用到random。random地对数字顺序进行交换即可
//需要注意的是有一种做法是，应该用i + rand() % (a.length - i)而不是rand() % a.length
//虽然也能通过OJ，但是根据概率图表，rand() % a.length的写法不是真正的随机分布
//参考http://www.i-programmer.info/programming/theory/2744-how-not-to-shuffle-the-kunth-fisher-yates-algorithm.html
//https://yjk94.wordpress.com/2017/03/17/%E6%B4%97%E7%89%8C%E7%9A%84%E6%AD%A3%E7%A1%AE%E5%A7%BF%E5%8A%BF-knuth-shuffle%E7%AE%97%E6%B3%95/
class Solution {
    int[] original;
    java.util.Random random;  
    public Solution(int[] nums) {
        original = nums;
        random = new Random();  
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if(original == null) 
            return null;  
        int[] a = original.clone();  
        for(int j = 1; j < a.length; j++) {  
            int i = random.nextInt(j + 1);  
            swap(a, i, j);  
        }  
        return a;          
    }
    private void swap(int[] a, int i, int j) {  
        int t = a[i];  
        a[i] = a[j];  
        a[j] = t;  
    }  
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */