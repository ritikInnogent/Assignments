import java.util.*;

public class ArrayLinkedlist {

    public static long arraylist(ArrayList<Integer> al, int n) {
        long startTime = System.nanoTime();

        for (int i = 0; i < n; i++) {
            al.add(0);
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        return totalTime;
    }

    public static long arraylist_remove(ArrayList<Integer> al) {
        long startTime = System.nanoTime();

        for (int i = 0; i < al.size(); i++) {
            al.remove(i);
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        return totalTime;
    }

    public static long linkedlist(LinkedList<Integer> ll, int n) {
        long startTime = System.nanoTime();

        for (int i = 0; i < n; i++) {
            ll.add(0);
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        return totalTime;
    }

    public static long linkedlist_remove(LinkedList<Integer> li) {
        Iterator<Integer> it = li.iterator();
        long startTime = System.nanoTime();

        while (it.hasNext()) {
            it.next();
            it.remove();
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        return totalTime;
    }

    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<>();
        LinkedList<Integer> ll = new LinkedList<>();

        int[] size = { 5000, 10000, 20000 };

        for (int n : size) {
            System.out.println("For size: " + n);

            // Insertion
            long arrayListTime = arraylist(al, n);
            long linkedListTime = linkedlist(ll, n);

            // Deletion
            long arraylist_remove_time = arraylist_remove(al);
            long linkedlist_remove_time = linkedlist_remove(ll);

            System.out.println("Time taken by ArrayList to insert: " + (float) arrayListTime / 1000000000 + " Seconds");

            System.out
                    .println("Time taken by LinkedList to insert: " + (float) linkedListTime / 1000000000 + " Seconds");

            if (arrayListTime < linkedListTime) {
                System.out.println("ArrayList is faster than LinkedList by "
                        + (float) (linkedListTime - arrayListTime) / 1000000000 + " Seconds");

            } else if (linkedListTime < arrayListTime) {
                System.out.println("LinkedList is faster than ArrayList by "
                        + (float) (arrayListTime - linkedListTime) / 1000000000 + " Seconds");

            } else {
                System.out.println("Both are equal in time");
            }
            System.out.println("------------------------------------------------------------------------------------------");

            System.out.println(
                    "Time taken by ArrayList to remove : " + (float) arraylist_remove_time / 1000000000 + " Seconds");

            System.out.println(
                    "Time taken by LinkedList to remove : " + (float) linkedlist_remove_time / 1000000000 + " Seconds");

            if (arraylist_remove_time < linkedlist_remove_time) {
                System.out.println("ArrayList is faster than LinkedList in removing by "
                        + (float) (linkedlist_remove_time - arraylist_remove_time) / 1000000000 + " Seconds");

            } else if (linkedlist_remove_time < arraylist_remove_time) {
                System.out.println("LinkedList is faster than ArrayList in removing by "
                        + (float) (arraylist_remove_time - linkedlist_remove_time) / 1000000000 + " Seconds");

            } else {
                System.out.println("Both are equal in time for removing");
            }
            System.out.println("------------------------------------------------------------------------------------------");

        }
    }
}
