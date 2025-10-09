import java.util.concurrent.CountDownLatch;

//Basically CountDownLatch is an tool for synchroning threads in java

class Worker extends Thread {
    private final CountDownLatch latch;
    private final String name;

    public Worker(String name, CountDownLatch latch) {
        this.name = name;
        this.latch = latch;
    }

    public void run() {
        System.out.println(name + " is starting their working");
        try {
            Thread.sleep(1000); // provide simulate that work is in setup
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(name + " is done their work!");
        latch.countDown(); // this countDown tell the latch that current thread is finised their work
    }
}

public class threadAndCountDownLatch {
    public static void main(String[] args) throws InterruptedException { 
        CountDownLatch latch = new CountDownLatch(3); // Number of worker threads count is 3

        System.out.println("Main Thread starting all the workers");
        Worker w1 = new Worker("Worker 1", latch);
        Worker w2 = new Worker("Worker 2", latch);
        Worker w3 = new Worker("Worker 3", latch);

        w1.start(); 
        w2.start(); 
        w3.start(); 

        // this is the main thread which waits untill all the worker threads will finised their work
        latch.await();
        System.out.println("Hey! i am the main thread which waits untill all workers finised their work and then proceed");
    }
}
