package 剑指offer.字符模式;

import java基础面试题.basic.LRU.LRUCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().isIsomorphic("add","egg"));
    }
    public String PrintMinNumber(int [] s) {
        if(s==null) {
            return null;
        }
        String res="";
        ArrayList<Integer> list=new ArrayList<Integer>();
        for(int i=0;i<s.length;i++){
            list.add(s[i]);
        }
        Collections.sort(list,(Integer str1, Integer str2)->{
                    String s1 =str1+""+str2;
                    String s2 =str2+""+str1;
                    return s1.compareTo(s2);
                }
        );
        for(int j:list){
            res+=j;
        }
        return res;
    }
    public boolean isIsomorphic(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();

        //特判
        if(sLength != tLength) {
            return false;
        }
//        ArrayList
//        Collections.synchronizedList();
        HashMap<Character,Character> map = new HashMap<>();
        HashMap<Character,Character> re = new HashMap<>();
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        
        for(int i = 0;i<sLength;i++){
            System.out.println(s1[i]);
            System.out.println(t1[i]);
            if(map.containsKey(s1[i])){
                if(map.get(s1[i])!=t1[i]){
                    return false;
                }
            }else{
                map.put(s1[i],t1[i]);
            }

            if (re.containsKey(t1[i])){
                if (re.get(t1[i])!=s1[i]){
                    return false;
                }
            }else{
                re.put(t1[i],s1[i]);
            }
        }

        return true;  
    }
}