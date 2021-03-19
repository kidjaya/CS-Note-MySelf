package DemoTest;

import java.util.Arrays;

/**
 * @author kidjaya
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] test = {11,8,3,4,77,1,3,88,5};
        mergeSort(test);
        System.out.println(Arrays.toString(test));
    }

    private static void mergeSort(int[] arr){
        if(arr.length == 0) {
            return;
        }
        int[] result = new int[arr.length];
        mergeSort(arr,0,arr.length-1,result);
    }

    private static void mergeSort(int[] arr,int start,int end,int[] result){
        if(start == end) {
            return;
        }
        int middle = (start + end) >>> 1;
        mergeSort(arr,start,middle,result);
        mergeSort(arr,middle+1,end,result);
        merge(arr,start,end,result);
    }

    private static void merge(int[] arr,int start,int end,int[] result){
        int end1 = (start + end) / 2;
        int start2 = end1 + 1;
        int index1 =start;
        int index2 = start2;

        int index = start;
        while(index1 <= end1 && index2 <= end){
            if(arr[index1]<=arr[index2]){
                result[index++] = arr[index1++];
            }else{
                result[index++] = arr[index2++];
            }
        }
        while(index1<=end1){
            result[index++] = arr[index1++];
        }
        while(index2<=end){
            result[index++] = arr[index2++];
        }
        while(start<=end){
            arr[start] = result[start++];
        }
    }


}
