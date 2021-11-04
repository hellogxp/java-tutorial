package cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: TODO
 * @Author chopin
 * @Date 2021/5/11 11:38 PM
 * @Version 1.0
 */
public class CyclicBarrierExample {

    /**
     * The barrier also could be initialized with some action that is be performed
     * once all the threads have cross it.
     */
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
        System.out.println("All Tasks cross the barrier");
    });

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            executorService.execute(new Task(cyclicBarrier, i));
        }
        executorService.shutdown();
    }
}