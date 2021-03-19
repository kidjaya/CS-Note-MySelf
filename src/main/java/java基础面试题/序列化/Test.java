package java基础面试题.序列化;

import java.io.*;

/**
 * @author kidjaya
 */
public class Test {
    public static void main(String[] args) {
        A a =  new A();
        a.i = 1;
        a.s = "test";
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;
        try{
            fileOutputStream = new FileOutputStream("temp");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(a);
            fileOutputStream.close();

            //通过文件读取obj
            fileInputStream = new FileInputStream("temp");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            A a2 = (A) objectInputStream.readObject();
            fileInputStream.close();
            System.out.println(a2.i);
            System.out.println(a2.s);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}

class A implements Serializable {
    int i;
    String s;
}

class B implements Externalizable{

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
