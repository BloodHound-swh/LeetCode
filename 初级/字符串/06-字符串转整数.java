



//主要使用charAt(index)判定是否转换以及转换范围。
class Solution {
    public int myAtoi(String str) {
        if(str==null)
            return 0;
        str = str.trim();
        if(str.length()==0)
            return 0;
        int flag = 1;
        int index = 0;
        if(str.charAt(index)=='+')
            index++;
        else if(str.charAt(index)=='-'){
            flag = -1;
            index++;
        }
        long num = 0;
        for(;index<str.length();index++){
            if(str.charAt(index)<'0'||str.charAt(index)>'9')
                break;
            num = num * 10 + (str.charAt(index) - '0');
            if(num > Integer.MAX_VALUE)
                break;
        }
        if(num * flag > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        if(num * flag < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        return (int)num * flag;
    }
}

// step1.去掉空格；
// step2.判断±号；
// step3.依次遍历，如果为0~9，则base*10加上该数字，将得到的值赋给base。若base大于214746364，或等于214746364并且该数字大于7，说明int溢出，返回2147463647或2147463648；
// step4.返回base*sign。

public class Solution {
    public int myAtoi(String str) {
        if (str.isEmpty()) return 0;
        int sign = 1, base = 0, i = 0, n = str.length();
        while (i < n && str.charAt(i) == ' ') ++i;
        if (str.charAt(i) == '+' || str.charAt(i) == '-') {
            sign = (str.charAt(i++) == '+') ? 1 : -1;
        }
        while (i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            base = 10 * base + (str.charAt(i++) - '0');
        }
        return base * sign;
    }
}