package java基础面试题.basic.callback;

/**
 * 学生接口，解决问题需要传入老师的引用不然无法完成对具体实例的回调
 * 学生都可以实现这个接口，老师可以通过传入List<Student>来聚合学生</>
 * @author kidjaya
 */
public interface Student {
    void resolveProblem(Teacher teacher);
}
