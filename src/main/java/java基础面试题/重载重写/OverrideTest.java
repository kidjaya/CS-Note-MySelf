package java基础面试题.重载重写;

public class OverrideTest {
 
    class Father{
        public void doSomething(Father father) {
            System.out.println("Father do something");
        }
    }
 
    class Son extends Father {
        @Override
        public void doSomething(Father father) {
            System.out.println("Son do something");
        }

    }

    public static void main(String [] args){
        OverrideTest overrideTest = new OverrideTest();
        Father son = overrideTest.new Son();
        Father father = overrideTest.new Father();
        son.doSomething(son);
        father.doSomething(father);
    }
}