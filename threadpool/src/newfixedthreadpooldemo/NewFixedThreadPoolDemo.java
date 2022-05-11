package newfixedthreadpooldemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Chopin
 * @date 2022/4/1
 */
public class NewFixedThreadPoolDemo {
    public static void main(String[] args) {
        /*
        The input parameter is the fixed number of threads, it's both the number of core threads and the maximum number
        of threads. There are no idle threads, so keepAliveTime is equal to 0.
        The constructor of LinkedBlockingQueue takes this parameter: Integer.MAX_VALUE. Using such an unbounded
        queue, if the instantaneous request is very large, there is a risk of OOM. Except for newWorkStealingPool,
        the other four creation methods have the risk of running out of resources.
         */
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                System.out.println("Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }
}