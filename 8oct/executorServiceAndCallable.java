import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class executorServiceAndCallable {
    public static void main(String args[]) {
        String directory_path = "C:\\Users\\Asus\\Desktop\\INNOGENT\\8oct";
        File files = new File(directory_path);


        File[] file = files.listFiles((dir, name) -> name.endsWith(".txt"));
        if (file == null || file.length == 0) {
            System.out.println("Directory is Empty");
            return;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futures = new ArrayList<>();

        for (File f : file) {
            Callable<Integer> task = () -> {
                int count = 0;
                try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
                    while (reader.readLine() != null)
                        count++;
                } catch (IOException e) {
                    System.out.println("Error reading file " + f.getName() + ": " + e.getMessage());
                }
                return count;

            };
            futures.add(executorService.submit(task));
        }

        int totalLines = 0;
        int files_number = 1;
        for (Future<Integer> future : futures) {
            try {
                totalLines += future.get();
                System.out.println("Lines in File number " + files_number++ + " is : " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error getting total lines: " + e.getMessage());
            }
        }
        System.out.println("Total Lines " + totalLines);
        executorService.shutdown();
}
}