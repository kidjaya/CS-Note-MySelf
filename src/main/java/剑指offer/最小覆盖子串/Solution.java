package 剑指offer.最小覆盖子串;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    public String minWindow(String s, String t) {
        //生成两个HashMap存放windows 和 needs，设置的最小长度，start起始位置，left，right分别指向0
        int start = 0,miniLength = Integer.MAX_VALUE;
        int left = 0,right = 0;

        HashMap<Character,Integer> windows = new HashMap<>(t.length());
        HashMap<Character,Integer> needs = new HashMap<>(t.length());

        //将需要的字符串遍历到needs的hashMap中
        for (Character c : t.toCharArray()){
            if (needs.containsKey(c)){
                needs.put(c,needs.get(c)+1);
            }else {
                needs.put(c, 1);
            }
        }

        //定义一个遍历存储match的个数
        int match = 0;
        //循环（当右边小于need的长度时退出循环）
        char[] cur = s.toCharArray();
        while(right<s.length()) {

            //如果当前map的key的value的等与needs的key的value。match++，right++
            if (needs.containsKey(cur[right])){
                if (windows.containsKey(cur[right])){
                    windows.put(cur[right],windows.get(cur[right])+1);
                }else{
                    windows.put(cur[right],1);
                }

                if (windows.get(cur[right]).equals(needs.get(cur[right]))){
                    match++;
                }
            }
            right++;
            //循环
            while (match == needs.size()) {
                //如果match == size.length,记录最小的长度，然后left++
                if (right-left < miniLength){
                    start = left;
                    miniLength = right - left;
                }
                char c2 = cur[left];

                if (needs.containsKey(c2)){
                    windows.put(c2,windows.get(c2)-1);
                    if (windows.get(c2) < needs.get(c2)){
                        //如果之前的left匹配某个match的话 match --
                        match --;
                    }
                }
                left++;
            }
        }
        //返回最优解字符串
        return miniLength == Integer.MAX_VALUE ? "":s.substring(start,start+miniLength);
    }

    public static void main(String[] args) {
        String s = new Solution().minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);

        List<Integer> anagrams = new Solution().findAnagrams("cbaebabacd", "abc");

        System.out.println(anagrams);
    }

    public List<Integer> findAnagrams(String s, String p) {
        int left = 0,right = 0;
        List<Integer> res = new ArrayList<>();

        HashMap<Character,Integer> windows = new HashMap<>();
        HashMap<Character,Integer> needs = new HashMap<>();

        for(char c : p.toCharArray()){
            if(needs.containsKey(c)){
                needs.put(c,needs.get(c)+1);
            }else{
                needs.put(c,1);
            }
        }

        int match = 0;
        char[] cur = s.toCharArray();

        while(right<s.length()){
            char c1 = cur[right];
            if(needs.containsKey(c1)){
                if(windows.containsKey(c1)){
                    windows.put(c1,windows.get(c1)+1);
                }else{
                    windows.put(c1,1);
                }

                if (windows.get(c1).equals(needs.get(c1))){
                    match++;
                }
            }
            right++;

            while(match == p.length()){
                if (right-left == p.length()){
                    res.add(left);
                }

                char c2 = cur[left];
                if(needs.containsKey(c2)){
                    windows.put(c2,windows.get(c2)-1);
                    if(windows.get(c2) < needs.get(c2)){
                        match --;
                    }
                }
                left++;
            }
        }

        return res;
    }
}