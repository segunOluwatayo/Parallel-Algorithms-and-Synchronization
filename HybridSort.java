import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

class HybridSort extends RecursiveAction {
    //TODO: The threshold for switching from MergeSort to InsertionSort
    static final int THRESHOLD = 100;
    //TODO: The array to be sorted
    private final int[] data;
    //TODO: The lower and upper bounds of the subarray
    private final int start;
    private final int end;
    //TODO: Create a constructor for this class
    public HybridSort(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }
    @Override
    protected void compute() {
        //TODO: If the subarray is small enough, use InsertionSort
        if (end - start <= THRESHOLD) {
            int[] segment = Arrays.copyOfRange(data, start, end);
            insertionSort(segment);
            System.arraycopy(segment, 0, data, start, segment.length);
        } else {
            //TODO: Otherwise, use MergeSort
            int mid = (start + end) / 2;
            HybridSort leftTask = new HybridSort(data, start, mid);
            HybridSort rightTask = new HybridSort(data, mid, end);
            invokeAll(leftTask, rightTask);
            merge(data, start, mid, end);
        }
    }

    //TODO: A helper method for sorting a subarray using InsertionSort
    public static void insertionSort(int[] input) {
        for (int i = 1; i < input.length; i++) {
            int key = input[i];
            int j = i - 1;
            while (j >= 0 && input[j] > key) {
                input[j + 1] = input[j];
                j = j - 1;
            }
            input[j + 1] = key;
        }
    }
    //TODO: A helper method for merging two sorted subarrays
    static void merge(int[] f, int p, int q, int r){
        int i = p; int j = q;
        int[] temp = new int[r-p]; int t = 0;
        while(i < q && j < r){
            if(f[i] <= f[j]){
                temp[t]=f[i];i++;
            }
            else{
                temp[t] = f[j]; j++;
            }
            t++;
        }
        while(i < q){ temp[t]=f[i];i++;t++;}
        while(j < r){ temp[t] = f[j]; j++; t++;}
        i = p; t = 0;
        while(t < temp.length){ f[i] = temp[t]; i++; t++;}
    }

}
