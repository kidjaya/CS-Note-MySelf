package java基础面试题.basic.IO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author kidjaya
 */
public class System2 {
    public static void main(String[] args) {
        for(int i = 100;i<1000;i++){
            int[] arr = new int[3];
            int temp = i;
            arr[0] = temp / 100;
            arr[1] = (temp - arr[0]*100)/10;
            arr[2] = temp % 10;
            if (Math.pow(arr[0],3)+Math.pow(arr[1],3)+Math.pow(arr[2],3) == i){
                System.out.println(i);
            }
        }
    }



}
