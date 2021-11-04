package cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description: TODO
 * @Author chopin
 * @Date 2021/5/11 11:38 PM
 * @Version 1.0
 */
public class Task implements Runnable{
    private CyclicBarrier cyclicBarrier;
    private int number;

    public Task(CyclicBarrier cyclicBarrier, int number) {
        this.cyclicBarrier = cyclicBarrier;
        this.number = number;
    }

    @Override
    public void run() {
        /** to do something */
        System.out.println("Task " + number + " has finished ");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("Task " + number + " has cross the barrier");
    }
}