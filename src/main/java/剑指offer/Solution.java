package 剑指offer;

import javax.swing.tree.TreeNode;
import java.util.HashMap;

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null) {
            return null;
        }
        //存放preoder保存顺序
        HashMap<Integer,Integer> map = new HashMap<>();
        int length = inorder.length;
        for(int i = 0;i<length;i++){
            map.put(inorder[i],i);
        }

        TreeNode root = buildTree(0,preorder.length-1,preorder,0,inorder.length-1,inorder,map);
        return root;
    }

    public TreeNode buildTree(int preorderStart,int preorderEnd,int[] preorder,int inorderStart,int inorderEnd,int[] inorder,HashMap<Integer,Integer> map){
        if(preorderStart>preorderEnd){
            return null;
        }
        int rootValue = preorder[preorderStart];
        TreeNode root = new TreeNode(rootValue);
        if(preorderStart == preorderEnd){
            return root;
        }else{
            int rootIndex = map.get(rootValue);
            int leftNodes = rootIndex -  inorderStart,rightNodes = inorderEnd - rootIndex;
            TreeNode leftNode = buildTree(preorderStart,preorderStart+leftNodes,preorder,inorderStart,rootIndex-1,inorder,map);
            TreeNode rightNode = buildTree(preorderEnd-preorderStart+1,preorderEnd,preorder,rootIndex+1,inorderEnd,inorder,map);
            root.left = leftNode;
            root.right = rightNode;
        }

        return root;
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}