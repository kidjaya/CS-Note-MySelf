package 八大排序算法.希尔排序;

/**
 * @author kidjaya
 */
public class Shell {
    public static void sort(int[] arr){
        int length = arr.length;
        int h = 1;
        while(h<length/3) {
            h = h * 3 + 1;
        }
        while(h>=1){
            for(int i = 0;i < length - h;i += h){
                for(int j = i+h;j>0;j-=h){
                    if(arr[j]<arr[j-h]){
                        int temp = arr[j];
                        arr[j] = arr[j-h];
                        arr[j-h] = temp;
                    }
                }
            }
            h/=3;
        }
    }

    public static void main(String[] args) {
        int[] a = {2,5,3,2,5,6,7,8,8,21,1,23,4,5,99};
        sort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
