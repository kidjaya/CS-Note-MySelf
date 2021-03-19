package 高并发.test;

public class TestFinally {
    public static void main(String[] args) {
        try{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        }finally {
            System.out.println("a");
        }

    }
}
