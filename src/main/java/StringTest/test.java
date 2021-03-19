package StringTest;

public class test {
    public static void main(String[] args) {
        System.out.println(reverse("abcde"));
    }

    public static String reverse(String str){
        if(str.length() == 0){
            return str;
        }
        return reverse(str.substring(1))+str.charAt(0);
    }
}

