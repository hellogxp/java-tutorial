package scheduledthreadpool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Chopin
 * @date 2022/4/2
 */
public class ScheduledThreadPoolDemo {
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        scheduledExecutorService.schedule(() -> {
            System.out.println(
                "[" + atomicInteger.getAndIncrement() + "] Thread: " + Thread.currentThread().getName() + " "
                    + getCurrentTime());
        }, 1, TimeUnit.SECONDS);

        scheduledExecutorService.schedule(() -> {
            System.out.println(
                "[" + atomicInteger.getAndIncrement() + "] Thread: " + Thread.currentThread().getName() + " "
                    + getCurrentTime());
        }, 5, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();
    }

    public static String getCurrentTime() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.now();
        return dateTimeFormatter.format(localDateTime);
    }
}