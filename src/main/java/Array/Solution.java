package Array;

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(
                new int[]{3, 3},
                6)));
    }
    public static int[] twoSum(int[] nums, int target) {
        int[] temp = new int[nums.length];
        System.arraycopy(nums,0,temp,0,nums.length);
        Arrays.sort(nums);
        int[] res = new int[2];
        int i = 0,j = nums.length - 1;
        int m=0,n=0,z;
        while(i<j){
            int sum = nums[i] + nums[j];
            if(target<sum){
                j--;
            }else if(target>sum){
                i++;
            }else if (target == sum){
                m = i;
                n = j;
                break;
            }
        }

        for(z = 0;z< temp.length;z++){
            if(temp[z] == nums[m]){
                res[0] = z;
                break;
            }
        }
        for(int k = 0;k< temp.length;k++){
            if(temp[k] == nums[n]&&k!=z){
                res[1] = k;
            }
        }

        return res;
    }
}