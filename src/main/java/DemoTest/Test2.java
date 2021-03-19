package DemoTest;

import com.sun.org.apache.xpath.internal.operations.String;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author kidjaya
 */
public class Test2 {
    public static void main(String[] args) throws IOException {
       StringBuilder str = new StringBuilder();

    }

    public static int[][] getInstance(int length){
        if(length <= 0) {
            return null;
        }

        //初始化一个数组
        int tempNumber = 1;
        int[][] temp = new int[length][length];
        for(int i = 0;i<length;i++){
            for(int j = 0;j<length;j++){
                temp[i][j] = tempNumber++;
            }
            System.out.println(Arrays.toString(temp[i]));
        }

        int[][] ans = new int[length][length];
        int top = 0,bottom = length - 1;
        int left = 0,right = length - 1;
        int total = length * length;
        int index = 0,sum = 1;
        while(total > 1){
            for(int i = left;i<=right && total > 1;i++) {
                System.out.print(temp[top][i]+" ");
                total--;
            }
            System.out.println();
            top++;
            for(int i = top;i<=bottom && total > 1;i++){
                System.out.print(temp[i][right]+" ");
                total--;
            }
            System.out.println();
            right--;
            for(int i = right;i>=left && total > 1;i--){
                System.out.print(temp[bottom][i]+" ");
                total--;
            }
            System.out.println();
            bottom--;
            for(int i = bottom;i<=top && total > 1;i--){
                System.out.print(temp[i][left]+" ");
                total--;
            }
            System.out.println();
            left++;
        }
        return ans;
    }
}
