package Test;

import java.util.*;

public class TEst {
    public static void main(String[] args) {
        System.out.println(canPermutePalindrome("aaaab"));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            queue.add(root);
        }
        while(!queue.isEmpty()){
            List<Integer> temp = new ArrayList<>();
            for (int i = queue.size();i>0;i--){
                TreeNode node = queue.poll();
                temp.add(node.val);
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(temp);
        }
        return res;
    }

    public void rotate(int[][] matrix) {
        int length = matrix.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++){
                if (i == length-1){
                    continue;
                }
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][length - j -1];
                matrix[i][length-j-1] = temp;
            }
        }
    }

    public void rotate2(int[][] matrix) {
        int len = matrix.length;
        //矩阵转置
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len ; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //每一行逆序
        for (int i = 0; i < len ; i++) {
            for (int j = 0; j < len/2 ; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][len - j - 1];
                matrix[i][len - j - 1] = temp;
            }
        }
    }

    public void rotate3(int[][] matrix) {
        int temp;
        //第一层...第n层 ==》 x增加，y减少，当剩下最后一层停止
        for (int x = 0,y = matrix[0].length - 1; x < y ; x++,y--) {
            //每一层的元素替换，当这层元素只有一行一列就停止
            for (int x1 = x,y1 = y; x1 < y; x1++,y1--) {
                //存放临时变量
                //x为固定位，x1为变量位，存放X行向后推移的某个元素，x1会向后推移
                temp = matrix[x][x1];
                //x位固定位，存放当前列，向下数第y1个元素的值，y1会向上推移
                matrix[x][x1] = matrix[y1][x];
                //y为固定存放当前行，y1存放y行第y1个元素，y1会向前推移
                matrix[y1][x] = matrix[y][y1];
                //y为固定存放当前列，x1会向下推移行数
                matrix[y][y1] = matrix[x1][y];
                //将最右边的替换为存放的上边的临时变量～
                matrix[x1][y] = temp;
            }
        }
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        LinkedList<Integer> result = new LinkedList<>();
        if(matrix==null||matrix.length==0) return result;
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        int numEle = matrix.length * matrix[0].length;
        while (numEle >= 1) {
            for (int i = left; i <= right && numEle >= 1; i++) {
                result.add(matrix[top][i]);
                numEle--;
            }
            top++;
            for (int i = top; i <= bottom && numEle >= 1; i++) {
                result.add(matrix[i][right]);
                numEle--;
            }
            right--;
            for (int i = right; i >= left && numEle >= 1; i--) {
                result.add(matrix[bottom][i]);
                numEle--;
            }
            bottom--;
            for (int i = bottom; i >= top && numEle >= 1; i--) {
                result.add(matrix[i][left]);
                numEle--;
            }
            left++;
        }
        return result;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return res;
        int rows = matrix.length,columns = matrix[0].length;
        int count = rows*columns;
        int left = 0,right = columns-1;
        int top = 0,bottom = rows-1;

        while(count>=1){
            for (int i = left;i <= right;i++){
                res.add(matrix[top][i]);
                count--;
            }
            top++;
            for (int i = top;i <= bottom;i++){
                res.add(matrix[i][right]);
                count--;
            }
            right--;
            for (int i = right;i<= left;i++){
                res.add(matrix[bottom][i]);
            }
            bottom--;
            for (int i = bottom; i <= top; i++) {
                res.add(matrix[i][left]);
            }
            left++;
        }

        return res;
    }

    static List<Integer> list = new ArrayList<>();

    public static int minDiffInBST(TreeNode root) {
        if(root == null) return 0;
        traverse(root);
        int[] temp = new int[list.size()];
        for (int i = 0; i < list.size() ; i++) {
            temp[i] = list.get(i);
        }
        Arrays.sort(temp);
        return temp[2]-temp[1];
    }

    public static void traverse(TreeNode node){
        list.add(node.val);
        traverse(node.left);
        traverse(node.right);
    }

    public int[] levelOrder2(TreeNode root) {
        Queue<TreeNode> queue =  new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            for(int i = queue.size();i>0;i--){
                TreeNode node = queue.poll();
                res.add(node.val);
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
        }

        int[] temp = new int[res.size()];
        for(int i = 0;i<res.size();i++){
            temp[i] = res.get(i);
        }
        return temp;
    }

    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 1;
        if(root == null){
            return res;
        };

        queue.add(root);

        while(!queue.isEmpty()){
            List<Integer> temp = new ArrayList<>();
            for(int i = queue.size();i>0;i--){
                TreeNode node = queue.poll();
                temp.add(node.val);
                if(level%2 == 0){
                    if(node.right!=null){
                        queue.add(root.right);
                    }
                    if(node.left!=null){
                        queue.add(root.left);
                    }
                }else{
                    if(node.left!=null){
                        queue.add(root.left);
                    }
                    if(node.right!=null){
                        queue.add(root.right);
                    }
                }
            }
            res.add(temp);
            level++;
        }
        return res;
    }

    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<>();
        int length = num.length();
        for (int i = 0; i < length; i++) {
            Character digit = num.charAt(i);
            while(!deque.isEmpty() && k > 0 && deque.peekLast()>digit){
                deque.peekLast();
                k--;
            }
            deque.offerLast(digit);
        }

        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }

        StringBuilder stringBuilder = new StringBuilder();
        boolean leadingZero = true;
        while(!deque.isEmpty()){
            char digit = deque.pollFirst();
            if(leadingZero && digit == '0'){
                continue;
            }
            leadingZero = false;
            stringBuilder.append(digit);
        }

        return stringBuilder.length() == 0? "0":stringBuilder.toString();
    }

    public String removeDuplicateLetters(String s) {


        StringBuilder str = new StringBuilder();

        return str.toString();
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null) {
            return res;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.add(root);
        while(!stack1.isEmpty() || !stack2.isEmpty()){
            LinkedList<Integer> item = new LinkedList<>();
            if(!stack1.isEmpty()){
                while(stack1.size()>0){
                    TreeNode node = stack1.pop();
                    item.addFirst(node.val);
                    if(node.left!=null){
                        stack2.add(node.left);
                    }
                    if(node.right!=null){
                        stack2.add(node.right);
                    }
                }
            }else{
                while(stack2.size()>0){
                    TreeNode node = stack2.pop();
                    item.addFirst(node.val);
                    if(node.left!=null){
                        stack1.add(node.left);
                    }
                    if(node.right!=null){
                        stack1.add(node.right);
                    }
                }
            }
            res.add(item);
        }

        return res;
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        for(int i = 0;i<nums1.length;i++){
            int maxIndex = Integer.MIN_VALUE;
            boolean flag = false;

            Stack<Integer> stack = new Stack<>();
//            stack.push()
            for(int j = 0;j < nums2.length ;j++){
                //如果不相同就跳过
                if(nums2[j] != nums1[i] && !flag){
                    flag = true;
                    continue;
                }
                //如果相同了，且这个值大于它保存这个值
                if(nums1[i]<nums2[j] && flag){
                    maxIndex = j;
                    break;
                }
            }

            res[i] = maxIndex == Integer.MIN_VALUE ? -1 : nums2[maxIndex];
        }
        return res;
    }

    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        String str = Integer.toString(x);
        int left = 0,right = str.length()-1;
        while(left < right){
            if(str.charAt(left) != str.charAt(right)){
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static boolean canPermutePalindrome(String s) {
        int n = s.length();
        int[] temp = new int[256];
        int count = 0;
        for(int i = 0;i<n;i++){
            int t = temp[s.charAt(i) - 'a'];
            if(t==0){
                temp[s.charAt(i) - 'a'] = 1;
            }else{
                temp[s.charAt(i) - 'a'] = t + 1;
            };
        }
        for(int i = 0;i<n;i++){
            if(temp[s.charAt(i)-'a']%2>0){
                count++;
            }
        }

        return count <= 1;
    }


}
