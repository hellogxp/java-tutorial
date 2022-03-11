/**
 * @author chopin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/17 10:41
 */
public class Test1 {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        final int i = 0;
        myClass.changValue(i);
    }
}

class MyClass {
    public void changValue(int i) {
        ++i;
    }
}