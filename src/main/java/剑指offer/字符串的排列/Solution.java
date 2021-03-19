package 剑指offer.字符串的排列;

import java.util.HashMap;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int left = 0,right = 0;
        int match = 0;

        HashMap<Character,Integer> window = new HashMap<>();
        HashMap<Character,Integer> need = new HashMap<>();

        for(char c : s2.toCharArray()){
            if(need.containsKey(c)){
                need.put(c,need.get(c)+1);
            }else{
                need.put(c,1);
            }
        }

        char[] cur = s1.toCharArray();
        while(right < s1.length()){
            char c1 = cur[right];
            if(need.containsKey(c1)){
                if(window.containsKey(c1)){
                    window.put(c1,window.get(c1)+1);
                }else{
                    window.put(c1,1);
                }

                if(window.get(c1).equals(need.get(c1))){
                    match++;
                }
            }
            right++;

            while(match == need.size()){
                char c2 = cur[left];
                if(right - left == s2.length()){
                    return true;
                }
                if(need.containsKey(c2)){
                    window.put(c2,window.get(c2)-1);
                    if(need.get(c2)>window.get(c2)){
                        match--;
                    }
                }
                left++;
            }
        }

        return false;
    }

    public static void main(String[] args) {

    }
}