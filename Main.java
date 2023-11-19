import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Random randomGenerator = new Random();
//        char[][] matrix = new char[1000][1000];
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                matrix[i][j] = (char) (randomGenerator.nextInt(26) + 97);
//            }
//        }

        char[][] matrix = {
                {'a', 'b', 'd', 'g', 'o', 'v', 'z', 't', 'w', 'n', 'z', 'g', 'a', 'z', 'v'},
                {'g', 'e', 'b', 'v', 'a', 'u', 'u', 'v', 'a', 'v', 'a', 'b', 'g', 'p', 'p'},
                {'n', 'u', 't', 'p', 'v', 'i', 'g', 'p', 'i', 'a', 'v', 'u', 'm', 'f', 'b'},
                {'b', 'p', 'n', 'g', 'f', 'w', 'b', 'v', 'n', 'p', 'i', 'n', 'p', 'p', 'g'},
                {'p', 'b', 'v', 'b', 'p', 'i', 'a', 'w', 'g', 'z', 'w', 'z', 'u', 'n', 'i'},
                {'u', 'g', 'v', 'w', 'v', 'w', 'a', 'p', 'i', 'p', 'm', 'b', 'w', 'g', 'g'},
                {'n', 'g', 'u', 'o', 'i', 'b', 'w', 'v', 'g', 'a', 'w', 'v', 'u', 'f', 'v'},
                {'p', 'v', 'n', 'u', 'f', 'i', 'n', 'a', 'a', 'g', 'p', 'a', 'v', 'u', 'z'},
                {'v', 'z', 'w', 'a', 'w', 'u', 'k', 'v', 'n', 'v', 'u', 'z', 'u', 'n', 'u'},
                {'n', 'v', 'u', 'b', 'p', 'i', 'n', 'i', 'i', 'b', 'v', 'b', 'n', 'u', 'z'},
                {'z', 't', 'w', 'n', 'z', 'n', 'z', 'b', 'g', 't', 'v', 'z', 'v', 'b', 't'},
                {'v', 'u', 'p', 'z', 'k', 'i', 'w', 'm', 'w', 'n', 'u', 'f', 'z', 'a', 'u'},
                {'t', 'n', 'v', 'v', 'n', 'g', 'p', 'f', 'g', 'u', 'v', 'i', 'v', 'a', 'i'},
                {'p', 'w', 'z', 'b', 'w', 'p', 'z', 'g', 'z', 'f', 'u', 'n', 't', 'p', 'p'},
                {'f', 'u', 'n', 'i', 'p', 'n', 'v', 'z', 'g', 'u', 'w', 'w', 'p', 'v', 'b'},
                {'u', 'p', 'v', 'a', 'b', 'p', 'g', 'b', 'a', 'n', 'i', 'g', 'n', 'i', 'p'}};
        //TODO: Get the number of available processors (threads) in the system
        int numThreads = Runtime.getRuntime().availableProcessors();

        //TODO: Create a thread pool with the optimal number of threads
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        //TODO: Start measuring the execution time
        long startTime = System.nanoTime();

        //TODO: Calculate the size of each block
        int blockSize = matrix.length / numThreads;

        //TODO: Assign each block to a thread
        for (int i = 0; i < numThreads; i++) {
            int startRow = i * blockSize;
            int endRow = (i + 1) * blockSize;

            //TODO: Create a new SearchThread and assign it the block of rows to search
            executor.execute(new SearchThread(matrix, startRow, endRow));
        }

        //TODO: Shut down the executor and wait for all threads to finish
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //TODO: Stop measuring the execution time
        long endTime = System.nanoTime();
        double executionTimeInMilliseconds = (double) (endTime - startTime) / 1_000_000.0;

        //TODO: Print the execution time
        System.out.println("Parallel search took: " + executionTimeInMilliseconds + " milliseconds");



    //-------------------------------------------------------------------------------------------
        //TODO: Create an integer array of size 10,000,000 with random values
        int size = 10000000;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 10000000);
        }

        //TODO: Create a ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();

        //TODO: Create a HybridSort task for sorting the whole array
        HybridSort task = new HybridSort(array, 0, size);

        //TODO: Record the start time
         startTime = System.currentTimeMillis();

        //TODO: Invoke the task and wait for it to complete
        pool.invoke(task);

        //TODO: Record the end time
         endTime = System.currentTimeMillis();

        //TODO: Calculate the running time
        long runningTime = endTime - startTime;

        //TODO: Print the running time
        System.out.println("Running time: " + runningTime + " milliseconds");

        //TODO: Check if the array is sorted
        boolean sorted = true;
        for (int i = 0; i < size - 1; i++) {
            if (array[i] > array[i + 1]) {
                sorted = false;
                break;
            }
        }

        //TODO: Print the result
        System.out.println("Sorted: " + sorted);
    }


}