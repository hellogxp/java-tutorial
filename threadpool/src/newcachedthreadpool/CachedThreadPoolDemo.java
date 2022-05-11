package newcachedthreadpool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Chopin
 * @date 2022/4/2
 */
public class CachedThreadPoolDemo {
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String time = dateTimeFormatter.format(localDateTime);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                System.out.println("[" + atomicInteger.getAndIncrement() + "] Thread: " + Thread.currentThread().getName() + " " + time);
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