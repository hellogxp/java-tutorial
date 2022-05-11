package threadpoolexecutordemo;

import java.time.LocalDateTime;

/**
 * @author Chopin
 * @date 2022/4/1
 */
public class MyRunnable implements Runnable{
    private String command;

    public MyRunnable(String command) {
        this.command = command;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start time:" + LocalDateTime.now());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " End time:" + LocalDateTime.now());
    }

}