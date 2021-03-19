package java基础面试题.basic.callback;

public class Tom implements Student {
    @Override
    public void resolveProblem(Teacher teacher) {
        try{
            Thread.sleep(3000);
            System.out.println("completed");
            teacher.tellAnswer(123);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
