package java基础面试题.basic.callback;

/**
 * 教师实例化回调接口，学生写完题目之后通过老师提供的方法进行回调
 * 学生如何调用到老师的方法呢，只要在学生类的方法传入老师的引用
 * 老师需要指定学生答题，所以也要传入学生的实例
 * @author kidjaya
 */
public class Teacher implements Callback {

    private  Student student;
    Teacher(Student student){
        this.student = student;
    }

    void askProblem(Student student){
        new Thread(()->{
            student.resolveProblem(this);
        }).start();

        for (int i = 0; i < 4; i++) {
            System.out.println("老师玩手机"+i);
        }
    }

    @Override
    public void tellAnswer(int answer) {
        System.out.println("answer is " + answer);
    }
}
