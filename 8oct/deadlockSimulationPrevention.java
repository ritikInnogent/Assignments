class Resourse {
    final Object resourse1 = new Object();
    final Object resourse2 = new Object();
}

class Deadlock {
    Resourse resourse;

    public Deadlock(Resourse res) {
        this.resourse = res;
    }

    // in this task1 method thread 1 lock first resourse 1 then resourse 2 to simulate deadlock
    public void task1() {
        synchronized (resourse.resourse1) {
            System.out.println("Thread 1 lock resourse 1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
            synchronized (resourse.resourse2) {
                System.out.println("Thread 2 lock resourse 2");
            }
        }
    }

    // in this task2 method thread 2 lock first resourse 2 then resourse 1 to simulate deadlock
    public void task2() {
        synchronized (resourse.resourse2) {
            System.out.println("Thread 2 lock resourse 2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
            synchronized (resourse.resourse1) {
                System.out.println("Thread 2 lock resourse 1");
            }
        }
    }

    // in this finalTask method both thread lock first resourse 1 then resourse 2 in same time to prevent deadlock
    public void finalTask() {
        synchronized (resourse.resourse1) {
            System.out.println(Thread.currentThread().getName() + " This Thread lock resourse 1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
            synchronized (resourse.resourse2) {
                System.out.println(Thread.currentThread().getName() + "This Thread lock resourse 2");
            }
        }
    }
}

public class deadlockSimulationPrevention {
    public static void main(String args[]) {
        Resourse res = new Resourse();
        Deadlock dl = new Deadlock(res);

        System.out.println("----- check the deadlock simulation ----");
        Thread t1 = new Thread(() -> dl.task1());
        Thread t2 = new Thread(() -> dl.task2());
        t1.start();
        t2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {
        }

        System.out.println("----- check the deadlock prevention ----");
        Thread t3 = new Thread(() -> dl.finalTask());
        Thread t4 = new Thread(() -> dl.finalTask());
        t3.start();
        t4.start();
    }

}
