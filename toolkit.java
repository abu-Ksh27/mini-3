import java.util.*;

// ==============================
// Sorting Utility
// ==============================
class SortingUtil {

    /** Bubble Sort – O(n²) */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    /** Insertion Sort – O(n²) */
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /** Merge Sort – O(n log n) */
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) return;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) arr[k++] = left[i++];
            else arr[k++] = right[j++];
        }

        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    /** Quick Sort – Avg O(n log n) */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }

        int temp = arr[i + 1]; 
        arr[i + 1] = arr[high]; 
        arr[high] = temp;

        return i + 1;
    }
}


// ==============================
// Search Utility
// ==============================
class SearchUtil {

    /** Linear Search – O(n) */
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == key) return i;
        return -1;
    }

    /** Binary Search – O(log n) | Array must be sorted */
    public static int binarySearch(int[] arr, int key) {
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (arr[mid] == key) return mid;
            else if (arr[mid] < key) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }
}


// ==============================
// Generic Stack / Queue Utility
// ==============================
class CollectionUtil<T> {
    private LinkedList<T> list = new LinkedList<>();

    /** Push to stack */
    public void push(T val) { list.addLast(val); }

    /** Pop from stack */
    public T pop() { return list.isEmpty() ? null : list.removeLast(); }

    /** Enqueue (queue) */
    public void enqueue(T val) { list.addLast(val); }

    /** Dequeue (queue) */
    public T dequeue() { return list.isEmpty() ? null : list.removeFirst(); }

    /** Size */
    public int size() { return list.size(); }
}


// ==============================
// Analysis Utility (Timer)
// ==============================
class AnalysisUtil {

    /** Measure execution time of any algorithm */
    public static long measureTime(Runnable algo) {
        long start = System.nanoTime();
        algo.run();
        long end = System.nanoTime();
        return end - start;
    }
}


// ==============================
// MAIN CLASS (Test Cases)
// ==============================
public class AlgorithmToolkit {
    public static void main(String[] args) {

        System.out.println("=== Sorting Tests ===");
        int[] arr1 = {5, 3, 8, 1};
        SortingUtil.bubbleSort(arr1);
        System.out.println("Bubble Sort: " + Arrays.toString(arr1));

        int[] arr2 = {7, 2, 9, 4};
        SortingUtil.mergeSort(arr2);
        System.out.println("Merge Sort: " + Arrays.toString(arr2));

        System.out.println("\n=== Searching Tests ===");
        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("Linear Search (3): " + SearchUtil.linearSearch(arr3, 3));
        System.out.println("Binary Search (4): " + SearchUtil.binarySearch(arr3, 4));

        System.out.println("\n=== Collection Util Tests ===");
        CollectionUtil<Integer> util = new CollectionUtil<>();
        util.push(10);
        util.push(20);
        util.enqueue(30);

        System.out.println("Pop: " + util.pop());
        System.out.println("Dequeue: " + util.dequeue());

        System.out.println("\n=== Performance Test ===");
        long time = AnalysisUtil.measureTime(() -> SortingUtil.mergeSort(arr3));
        System.out.println("Merge Sort Time: " + time + " ns");
    }
}
