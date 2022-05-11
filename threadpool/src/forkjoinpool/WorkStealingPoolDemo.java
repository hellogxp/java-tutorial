package forkjoinpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Chopin
 * @date 2022/4/2
 */
public class WorkStealingPoolDemo {
    private static final int N_THREADS = 10;

    static CountDownLatch countDownLatch = new CountDownLatch(N_THREADS);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newWorkStealingPool();

        for (int i = 0; i < N_THREADS; ++i) {
            executorService.execute(() -> {
                System.out.println("Thread: " + Thread.currentThread().getName());
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}