/**
有效的字母异位词给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
例如，
s = "anagram"，t = "nagaram"，返回 true
s = "rat"，t = "car"，返回 false
注意:
假定字符串只包含小写字母。
提升难度:
输入的字符串包含 unicode 字符怎么办？你能能否调整你的解法来适应这种情况？

 */


//是用两个Map，并用equals来判定
class Solution {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> maps = new HashMap<Character, Integer>();
        Map<Character, Integer> mapt = new HashMap<Character, Integer>();
        char[] arrs = s.toCharArray();
        char[] arrt = t.toCharArray();
        for(int i=0;i<arrs.length;i++){
            if(maps.containsKey(arrs[i]))
                maps.put(arrs[i], maps.get(arrs[i])+1);
            else
                maps.put(arrs[i],1);
        }
        for(int i=0;i<arrt.length;i++){
            if(mapt.containsKey(arrt[i]))
                mapt.put(arrt[i], mapt.get(arrt[i])+1);
            else
                mapt.put(arrt[i],1);
        }
        if(maps.equals(mapt))
            return true;
        else
            return false;
    }
}

//使用两个数组，并是用arr[i]-‘a'的技巧
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s==t)
            return true;
        
        if(s.length() != t.length())
            return false;
        
        if(s != null && t != null && s.equals(t))
            return true;
        
        int[] a = new int[26];
        int[] b = new int[26];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        
        
        
        for(int i=0;i<sc.length;i++)
          a[sc[i]-'a']++;
        
        for(int j=0;j<tc.length;j++)
            b[tc[j]-'a']++;
        
        for(int m =0;m<26;m++)
            if(a[m]!=b[m])
                 return false;
        
        return true;
    }
}

//只使用一个数组
class Solution {
    public boolean isAnagram(String s, String t) {
    int[] alphabet = new int[26];
    for (int i = 0; i < s.length(); i++) 
        alphabet[s.charAt(i) - 'a']++;
    for (int i = 0; i < t.length(); i++) 
        alphabet[t.charAt(i) - 'a']--;
    for (int i : alphabet) 
        if (i != 0) 
            return false;
    return true;
    }
}