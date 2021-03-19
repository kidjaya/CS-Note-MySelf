package 剑指offer.三个数的最大乘积;


import java.util.PriorityQueue;

class Solution {
    public static void main(String[] args) {
        System.out.println(maximumProduct(new int[]{11, 1, 2, 3, 12, 8, 7, 6, 13}));
    }

    public static int maximumProduct(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int positive = 0;
        for(int num : nums){
            if(num > 0){
                positive++;
            }
            queue.add(num);
        }

        int res = 1;
        if(positive >= 3){
            while(queue.size()!=3){
                queue.poll();
            }
            while(!queue.isEmpty()){
                res *= queue.poll();
            }
            return res;
        }else{
            return 0;
        }
    }
}