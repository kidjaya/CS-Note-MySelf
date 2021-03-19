package Array;

import java.lang.reflect.Array;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        System.out.println(wordPattern("abba",
                "dog cat cat fish"));
    }

    public static boolean wordPattern(String pattern, String s) {

        String[] letters = s.split(" ");
        HashMap<String,Character> letterMap = new HashMap<>(letters.length);
        char[] p = pattern.toCharArray();
        HashMap<Character,String> patternMap = new HashMap<>(p.length);

        if(p.length != letters.length){
            return false;
        }

        if(p.length == 1){
            return true;
        }

        for(int i = 0;i<letters.length;i++){
            if(!letterMap.containsKey(letters[i])){
                letterMap.put(letters[i],p[i]);
            }else{
                char a =letterMap.get(letters[i]);
                if (a != p[i]){
                    return false;
                }
            }
            if(!patternMap.containsKey(p[i])) {
                patternMap.put(p[i], letters[i]);
            }else{
                String b = patternMap.get(p[i]);
                if (!b.equals(letters[i])){
                    return false;
                }
            }
        }

        return true;
    }


    public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        path(root,p,stack1);
        path(root,q,stack2);
        while(stack1.size()>0 && stack2.size()>0){
            TreeNode t1 = stack1.pop();
            TreeNode t2 = stack2.pop();
            if(t1.val == t2.val){
                return t1;
            }
        }
        return null;
    }
    public TreeNode path(TreeNode root,TreeNode target,Stack trace){
        if(root == null) {
            return null;
        }
        if(root.val > target.val){
            root = path(root.left,target,trace);
            trace.push(root.left);
        }else if(root.val < target.val){
            root = path(root.right,target,trace);
            trace.push(root.right);
        }else if(root.val == target.val){
            return root;
        }
        return null;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,String> map = new HashMap<>();
        HashMap<String,List<String>> track = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for (String str : strs) {
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            map.put(str, Arrays.toString(ch));
        }

        for (String str : strs) {
            if (!track.containsKey(map.get(str))){
                List<String> list = track.get(map.get(str));
                list.add(str);
                track.put(map.get(str),list);
            }else{
                List<String> list = new ArrayList<>();
                list.add(str);
                track.put(map.get(str),list);
            }
        }

        for (String s : track.keySet()) {
            res.add(track.get(s));
        }

        return res;
    }
    public boolean containsDuplicate(int[] nums) {
//        HashSet<Integer> set = new HashSet<>();
//        for (int num : nums) {
//            set.add(num);
//        }
//        return set.size() == nums.length;

//        Arrays.sort(nums);
//        for (int i = 0; i < nums.length-1; i++) {
//            if (nums[i] == nums[i+1]){
//                return true;
//            }
//        }
//        return false;

        HashSet<Integer> map = new HashSet<>();
        for (Integer integer : nums) {
            if (map.contains(integer)){
                return false;
            }
            map.add(integer);
        }
        return true;


    }
}
