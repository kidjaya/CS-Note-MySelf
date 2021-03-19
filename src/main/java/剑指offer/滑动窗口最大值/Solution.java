package 剑指offer.滑动窗口最大值;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;class Solution {
 *     public int[] hitBricks(int[][] grid, int[][] hits) {
 *         //初始化
 *         int n = grid[0].length;
 *         int m = grid.length;
 *         int[] res = new int[n];
 *         UnionFind union = new UnionFind(m*n);
 *         // for(int[] hit : hits){
 *         //     int x = hit[0];
 *         //     int y = hit[1];
 *         //     grid[x][y] = 0;
 *         // }
 *
 *
 *
 *     }
 *
 *     class UnionFind{
 *         private int[] size;
 *         private int[] parent;
 *         private int count;
 *
 *         public UnionFind(int n){
 *             parent = new int[n];
 *             size = new int[n];
 *             this.count = count;
 *             for(int i = 0;i<n;i++){
 *                 parent[i] = i;
 *                 size[i] = 1;
 *             }
 *         }
 *
 *         public int find(int x){
 *             if(x != parent[x]){
 *                 x = find(parent[x]);
 *             }
 *             return parent[x];
 *         }
 *
 *         public void union(int p,int q){
 *             int rootP = find(p);
 *             int rootQ = find(q);
 *             if(rootP == rootQ){
 *                 return;
 *             }
 *
 *             if(size[rootP]>size[rootQ]){
 *                 parent[rootP] = rootQ;
 *                 size[rootQ] += size[rootP];
 *             }else{
 *                 parent[rootQ] = rootP;
 *                 size[rootP] += size[rootQ];
 *             }
 *             count--;
 *         }
 *
 *         public int count(){
 *             return this.count;
 *         }
 *     }
 * }class Solution {
 *     public int[] hitBricks(int[][] grid, int[][] hits) {
 *         //初始化
 *         int n = grid[0].length;
 *         int m = grid.length;
 *         int[] res = new int[n];
 *         UnionFind union = new UnionFind(m*n);
 *         // for(int[] hit : hits){
 *         //     int x = hit[0];
 *         //     int y = hit[1];
 *         //     grid[x][y] = 0;
 *         // }
 *
 *
 *
 *     }
 *
 *     class UnionFind{
 *         private int[] size;
 *         private int[] parent;
 *         private int count;
 *
 *         public UnionFind(int n){
 *             parent = new int[n];
 *             size = new int[n];
 *             this.count = count;
 *             for(int i = 0;i<n;i++){
 *                 parent[i] = i;
 *                 size[i] = 1;
 *             }
 *         }
 *
 *         public int find(int x){
 *             if(x != parent[x]){
 *                 x = find(parent[x]);
 *             }
 *             return parent[x];
 *         }
 *
 *         public void union(int p,int q){
 *             int rootP = find(p);
 *             int rootQ = find(q);
 *             if(rootP == rootQ){
 *                 return;
 *             }
 *
 *             if(size[rootP]>size[rootQ]){
 *                 parent[rootP] = rootQ;
 *                 size[rootQ] += size[rootP];
 *             }else{
 *                 parent[rootQ] = rootP;
 *                 size[rootP] += size[rootQ];
 *             }
 *             count--;
 *         }
 *
 *         public int count(){
 *             return this.count;
 *         }
 *     }
 * }
 *         this.right = right;
 *     }
 * }
 */
class Solution {

}