import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.DoubleSummaryStatistics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ReadCsvFilewithJava8MultiThreading {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(15);
        File filepath = new File("C:\\Users\\MS\\Desktop\\JavaAssignment");
        File files[] = filepath.listFiles();
        for (File file : files) {
            executorService.execute(new Runnable() {
                public void run() {
                    System.out.println("Thread: " +Thread.currentThread().getName()+" started!");
                    Path path = Paths.get(String.valueOf(file));
                    DoubleSummaryStatistics doubleSummaryStatistics = null;
                    try {
                        doubleSummaryStatistics = Files.lines(path).skip(1).map(line -> {String[] fields = line.split(","); return fields[4];}).map(Float::parseFloat)
                                .collect(Collectors.summarizingDouble(Float::doubleValue));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Average for filename: " + path.getFileName() + " is: " + doubleSummaryStatistics.getAverage()+ " Asynchronous task completed by" + Thread.currentThread().getName());
                }
            });
        }
        executorService.shutdown();
    }
}
