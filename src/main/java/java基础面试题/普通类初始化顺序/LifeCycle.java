package java基础面试题.普通类初始化顺序;

import java.util.*;

/**
 * @author kidjaya
 */
public class LifeCycle {
    /**
     * 静态属性---静态方法块---普通属性---普通方法块----构造函数---普通方法
     */
    private static String staticField = getStaticField();
    static {
        System.out.println(staticField);
        System.out.println("静态方法初始化");
    }

    private String field = getField();

    {
        System.out.println(field);
    }

    public LifeCycle() {
        System.out.println("构造函数初始化");
    }

    public static String getStaticField(){
        return "Static Field Initial";
    }

    public String getField(){
        return "Field initial";
    }

    public static void main(String[] args) {
        new LifeCycle();
    }
}

class A{
    public int test(Character[] tasks, int n){
        if(n == 0) {
            return tasks.length;
        }
        int count  = tasks.length;
        int time = 0;
        HashMap<Character, Queue<Character>> visited = new LinkedHashMap<>();

        for(int i = 0;i<count;i++){
            Queue<Character> queue;
            if(!visited.containsKey(tasks[i])){
                queue = new LinkedList<>();
                queue.add(tasks[i]);
                visited.put(tasks[i],queue);
            }else{
                queue = visited.get(tasks[i]);
                queue.add(tasks[i]);
                visited.put(tasks[i],queue);
            }
        }

        HashSet<Character> set = new HashSet<>();

        while(count>0){
            int flag = 0;
            Set<Character> characters = visited.keySet();
            Iterator<Character> iterator1 = characters.iterator();
            while (iterator1.hasNext()){
                Character c = iterator1.next();
                Queue<Character> queue2 = visited.get(c);
                if(queue2.size()>0){
                    if(!set.contains(c)&&flag==0){
                        set.add(c);
                        queue2.poll();
                        time++;
                        flag = n;
                    }else{
                        time++;
                        flag--;
                    }
                }else{
                    count--;
                    break;
                }
            }
        }

        return time;
    }
}
