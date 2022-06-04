import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadCsvFileUsingMultiThreading {
    public static void main(String[] args) {
        //long start = System.nanoTime();
        ExecutorService executorService = Executors.newFixedThreadPool(10); //Execution time: 5844000 nanoseconds
        File filepath= new File("C:\\Users\\MS\\Desktop\\JavaAssignment");
        //File filepath= new File("C:\\Users\\MS\\Desktop\\JavaAssignment\\test");
        File files[]= filepath.listFiles();
        for(File file: files) {
            executorService.execute(new Runnable() {
                public void run()
                {
                    BufferedReader br = null;
                    String line = "";
                    int sum=0;
                    int count=0;
                    String stockName=null;
                    try {

                        br = new BufferedReader(new FileReader(file));
                        br.readLine();
                        try {
                            while ((line = br.readLine()) != null) {

                                // use comma as separator
                                String[] open = line.split(",");
                                stockName = open[1];
                                double d = Double.parseDouble(open[4]);
                                int sal = (int) d;
                                sum=sum+sal;
                                count++;

                            }
                        } catch (NumberFormatException | IOException e) {
                            System.out.println("NA");
                            e.printStackTrace();
                        }


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Stock Name: " +  stockName + " Average price: " + sum/count +" Asynchronous task completed by" + Thread.currentThread().getName());

                }

            });
            }
        /*long end = System.nanoTime();
        long execution = end - start;
        System.out.println("Execution time: " + execution + " nanoseconds");*/
        executorService.shutdown();
        }

}

