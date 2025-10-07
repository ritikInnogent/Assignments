class OddEvenClass {
    private int num=1;
    int max;

    public OddEvenClass(int max){
        this.max = max;
    };
    private final Object lock = new Object();

    public void printOdd(){
        while(num <= max){
            synchronized (lock) {
                if(num%2 != 0){
                    System.out.println("Thread odd" + num);
                     num++;
                     lock.notify();
                }
                else{
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                    }
                }

            }
        }
    }
    
        public void printEven(){
        while(num <= max){
            synchronized (lock) {
                if(num%2 == 0){
                    System.out.println("Thread Even" + num);
                     num++;
                     lock.notify();
                }
                else{
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                    }
                }

            }
        }
    }
} 
public class OddEven{
    public static void main(String args[])throws InterruptedException
    { 
        OddEvenClass oddeven = new OddEvenClass(20);

        Thread t1 = new Thread(()->oddeven.printOdd());
        Thread t2 = new Thread(()->oddeven.printEven());

        t1.start();
         
        t2.start();
         t1.join();
        t2.join();
       
}
}
