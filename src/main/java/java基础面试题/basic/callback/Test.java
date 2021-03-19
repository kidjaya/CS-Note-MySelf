package java基础面试题.basic.callback;

public class Test {
    public static void main(String[] args) {
        Student tom = new Tom();
        Teacher lee = new Teacher(tom);
        lee.askProblem(tom);
    }
}
