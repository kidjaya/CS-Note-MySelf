package 剑指offer.中位数;

import java.util.PriorityQueue;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1 == null && nums2 == null){
            return 0.0;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        if(nums1!=null){
            for (int value : nums1) {
                queue.add(value);
            }
        }
        if(nums2!=null){
            for(int value : nums2){
                queue.add(value);
            }
        }

        if (queue.size() == 1){
            return queue.poll();
        }else{
            int size = queue.size();
            int mid = size/2;
            double res = 0.0;
            if((size & 1) == 1){
                while(mid!=0){
                    mid--;
                    queue.poll();
                }
                res = queue.poll();
            }else{
                int start = mid - 1;
                while(mid!=start){
                    queue.poll();
                    mid++;
                }
                res = (queue.poll() + queue.poll())/2;
            }
            return res;
        }
    }
}