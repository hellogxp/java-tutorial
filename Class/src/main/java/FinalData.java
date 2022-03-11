/**
 * @author chopin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/17 22:17
 */
public class FinalData {
    private String id;
    private static final Value VAL_3 = new Value(33);
    private Value v1 = new Value(11);
    private final Value v2 = new Value(22);
    private final int[] a = {1, 2, 3, 4, 5, 6};
    public FinalData(String id) {
        this.id = id;
    }
    public static void main(String[] args) {
        FinalData fd1 = new FinalData("fd1");
        for (int i = 0; i < fd1.a.length; i++) {
            fd1.a[i]++;
        }
    }
}

class Value {
    int i;
    public Value(int i) {
        this.i = i;
    }
}