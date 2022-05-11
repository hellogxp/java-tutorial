package singlethreadexecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Chopin
 * @date 2022/4/2
 */
public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        AtomicInteger atomicInteger = new AtomicInteger(0);

        for (int i = 0; i < 10; ++i) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " : " + atomicInteger.getAndIncrement());
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