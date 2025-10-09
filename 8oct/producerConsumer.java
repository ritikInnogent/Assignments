import java.util.LinkedList;

class Buffer {
    private final int maxsize;
    private final LinkedList<Integer> queue = new LinkedList<>();

    public Buffer(int maxsize) {
        this.maxsize = maxsize;
    }

    // in this function producer can fill the items in buffer
    public void produce(int num) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() == maxsize) {
                System.out.println("Buffer is full and producer have to wait");
                queue.wait();
            }
            System.out.println("Item produced by producer is" + " " +  num);
            queue.add(num);
            queue.notify();
        }
    }

    // in this function consumer can takes the item in buffer
    public void consume() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                System.out.println("Buffer is empty and consumer have to wait");
                queue.wait();
            }
            int item = queue.removeFirst();
            System.out.println("consumer consumes the item is" +  " " +item);
            queue.notify();
        }
    }
}

class Producer extends Thread {
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 0; i <= 10; i++) {
                buffer.produce(i);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

class Consumer extends Thread {
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 0; i <= 10; i++) {
                buffer.consume();
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

public class producerConsumer {
    public static void main(String args[]) {
        Buffer buffer = new Buffer(5);
        Producer p = new Producer(buffer);
        Consumer c = new Consumer(buffer);
        p.start();
        c.start();
    }
}