/**
 * @author chopin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/16 23:00
 */
public class Final {
    public static void main(String[] args) {
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        // True, 变量b的值在编译期间便可确定，所以用到b的地方会直接将其替换为其值
        System.out.println(a == c);
        // False
        System.out.println(a == e);
    }

    public void test1() {
    }
}