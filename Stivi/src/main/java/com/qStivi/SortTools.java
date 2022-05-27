package com.qStivi;


import java.util.Arrays;
import java.util.Random;

public class SortTools {

    public static int NUMBER_OF_ITERATIONS = 1000;


    //region"create" functions

    /**
     * Creates an array with alternating int values between 1 and 2 with size n (inclusive).<br>
     * Example (n = 10): [1, 2, 1, 2, 1, 2, 1, 2, 1, 2]
     *
     * @param n is the size of the returned array
     * @return an int array of the given size with alternating values
     */
    public static int[] createSequenceAlt(int n) {
        var arr = new int[n];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 2;
            }
        }
        return arr;
    }

    /**
     * Creates a descending sorted array with int values from n to 1 (inclusive).<br>
     * Example (n = 10): [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
     *
     * @param n is the size of the returned array
     * @return an int array of the given size with descending values
     */
    public static int[] createSequenceDec(int n) {
        var arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n - i;
        }
        return arr;
    }

    /**
     * Creates an ascending sorted array with int values from 1 to n (inclusive).<br>
     * Example (n = 10): [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
     *
     * @param n is the size of the returned array
     * @return an int array of the given size with incrementing values
     */
    public static int[] createSequenceInc(int n) {
        var arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    /**
     * Creates an array with random int values from 1 to n (inclusive).<br>
     * Example (n = 10): [2, 10, 6, 2, 1, 2, 2, 6, 2, 1]
     *
     * @param n is the size of the returned array
     * @return an int array of the given size with random values
     */
    public static int[] createSequenceRand(int n) {
        var arr = new int[n];
        var r = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(1, n + 1);
        }
        return arr;
    }

    //endregion

    //region insertion-sort


    /**
     * Sorts an int array in ascending order using insertion-sort.<br>
     * Worst-case performance: O(n^2) comparisons and swaps<br>
     * Best-case performance : O(n) comparisons and O(1) swaps<br>
     * Average performance: O(n^2) comparisons and swaps<br>
     *
     * @param a is the int array which is to be sorted
     */
    public static void insertionSort(int[] a) {
        for (int j = 1; j < a.length; j++) {
            var s = a[j];
            var i = j - 1;
            while (i >= 0 && a[i] > s) {
                a[i + 1] = a[i];
                i--;
            }
            a[i + 1] = s;
        }
    }


    /**
     * Sorts part of an int array from start to end using insertion-sort.<br>
     *
     * @param a     is the int array which a part of is to be sorted
     * @param start is the start index (inclusive)
     * @param end   ist the end index (inclusive)
     * @see SortTools#insertionSort(int[])
     */
    public static void insertionSort(int[] a, int start, int end) {

        for (int j = start + 1; j <= end; j++) {

            int s = a[j];
            int i = j - 1;

            while (i >= start && a[i] > s) {
                a[i + 1] = a[i];
                i = i - 1;
            }

            a[i + 1] = s;
        }

    }

    /**
     * This is the generic version of {@link SortTools#insertionSort(int[])}
     */
    public static <T extends Comparable<T>> void insertionSortGen(T[] a) {
        for (int j = 1; j < a.length; j++) {
            var s = a[j];
            var i = j - 1;
            while (i >= 0 && a[i].compareTo(s) > 0) {
                a[i + 1] = a[i];
                i--;
            }
            a[i + 1] = s;
        }
    }

    //endregion

    //region bubble-sort

    /**
     * Sorts an int array in ascending order using bubble-sort.<br>
     * Worst-case performance: O(n^2) comparisons and swaps<br>
     * Best-case performance : O(n) comparisons and O(1) swaps<br>
     * Average performance: O(n^2) comparisons and swaps<br>
     *
     * @param A is the int array which is to be sorted
     */
    public static void bubbleSort(int[] A) {
        for (int i = A.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (A[j] > A[j + 1]) {
                    swap(A, j, j + 1);
                }
            }
        }
    }

    /**
     * This is frankensteins monster.<br>
     * It uses bubble-sort in combination with insertion-sort to sort the array in a very inefficient way.
     *
     * @param A is the array which is to be sorted
     */
    public static void bubbleSortNew(int[] A) {
        var x = 10;
        var y = 10;
        if (A.length <= 10) {
            x = 0;
            y = A.length - 1;
        }
        for (int i = A.length - x; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (j + y >= A.length) {
                    y--;
                }
                insertionSort(A, j, j + y);
            }
        }
    }

    /**
     * This is the generic version of {@link SortTools#bubbleSort(int[])}
     */
    public static <T extends Comparable<T>> void bubbleSortGen(T[] A) {
        for (int i = A.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (A[j].compareTo(A[j + 1]) > 0) {
                    swap(A, j, j + 1);
                }
            }
        }
    }

    //endregion

    //region merge-sort

    private static void merge(int[] A, int i, int j, int k) {

        var n1 = (j - i) + 1;
        var n2 = k - j;

        // Create array for each side of merge (+1 for sentinel)
        var L = new int[n1 + 1];
        var R = new int[n2 + 1];

        // fill both arrays with corresponding values
        System.arraycopy(A, i, L, 0, n1);
        System.arraycopy(A, j + 1, R, 0, n2);

        // insert sentinels
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;

        // create indices to keep track of position in each side
        var l = 0;
        var r = 0;

        // Do the actual merging
        for (int m = i; m <= k; m++) { // Go through the whole original array
            if (L[l] <= R[r]) { // If current element of left array is smaller or equal than the current element of the right array
                A[m] = L[l]; // Replace current element of original array with current element of left array
                l++; // Make next element in left array the current element
            } else { // Analog to left side (but "<" instead of "<=")
                A[m] = R[r];
                r++;
            }
        }
    }

    private static void merge(int[] A, int i, int j, int o, int k) {

        var n1 = (j - i) + 1;
        var n2 = o - j;
        var n3 = k - o;

        // Create array for each side of merge (+1 for sentinel)
        var L = new int[n1 + 1];
        var M = new int[n2 + 1];
        var R = new int[n3 + 1];

        // fill both arrays with corresponding values
        System.arraycopy(A, i, L, 0, n1);
        System.arraycopy(A, j + 1, M, 0, n2);
        System.arraycopy(A, o + 1, R, 0, n3);

        // insert sentinels
        L[n1] = Integer.MAX_VALUE;
        M[n2] = Integer.MAX_VALUE;
        R[n3] = Integer.MAX_VALUE;

        // create indices to keep track of position in each side
        var l = 0;
        var m = 0;
        var r = 0;

        // Do the actual merging
        for (int p = i; p <= k; p++) {
            if (L[l] <= M[m] && L[l] <= R[r]) {
                A[p] = L[l];
                l++;
            } else if (M[m] <= R[r] && M[m] <= L[l]) {
                A[p] = M[m];
                m++;
            } else {
                A[p] = R[r];
                r++;
            }
        }
    }

    /**
     * Sorts an array in ascending order using merge-sort<br>
     * Worst-case performance: O(n*log n)<br>
     * Best-case performance : Ω(n*log n) typical, Ω(n) natural variant<br>
     * Average performance: Θ(n*log n)<br>
     *
     * @param A is the array which is to be sorted
     */
    public static void mergeSort(int[] A) {
        mergeSortRec(A, 0, A.length - 1);
    }

    private static void mergeSortRec(int[] A, int i, int k) {
        if (i < k) {
            var j = (i + k) / 2;
            mergeSortRec(A, i, j);
            mergeSortRec(A, j + 1, k);
            merge(A, i, j, k);
        }
    }

    /**
     * This is a variant of {@link SortTools#mergeSort(int[])} which spilts the array in 3 instead of 2
     *
     * @param A is the array which is to be sorted
     * @see SortTools#mergeSort(int[])
     */
    public static void mergeSortNew(int[] A) {
        SortTools.mergeSortNewRec(A, 0, A.length - 1);
    }

    private static void mergeSortNewRec(int[] A, int i, int k) {
        if (i < k) {
            var x = Math.round((k - i + 1) / 3f);
            var j = x - 1 + i;
            var o = j + x;
            mergeSortNewRec(A, i, j);
            mergeSortNewRec(A, j + 1, o);
            mergeSortNewRec(A, o + 1, k);
            merge(A, i, j, o, k);
        }
    }


    /**
     * This is the generic version of {@link SortTools#mergeSort(int[])}
     */
    public static <T extends Comparable<T>> void mergeSortGen(T[] a) {
        mergeSortGenHelp(a, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> void mergeSortGenHelp(T[] a, int p, int r) {

        if (p < r) {
            int q = (int) Math.floor((p + r) / 2.0);
            mergeSortGenHelp(a, p, q);
            mergeSortGenHelp(a, q + 1, r);
            mergeGen(a, p, q, r);
        }

    }

    public static <T extends Comparable<T>> void mergeGen(T[] a, int p, int q, int r) {

        int n1 = q - p + 1;
        int n2 = r - q;

        // 0, 1, 2, 3, 4, 5, 6, 7, 8

        var left = Arrays.copyOfRange(a, p, q + 1);
        var right = Arrays.copyOfRange(a, q + 1, r + 1);

        int i = 0;
        int j = 0;

        int k = p;

        while (i < left.length && j < right.length && k <= r) {
            if (left[i].compareTo(right[j]) <= 0) {
                a[k] = left[i];
                i++;
            } else {
                a[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left.length) {
            a[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            a[k] = right[j];
            j++;
            k++;
        }
    }

    //endregion

    // region quick-sort

    public static void quickSort(int[] arr) {
        quickSortHelp(arr, 0, arr.length - 1);
    }

    private static void quickSortHelp(int[] arr, int l, int r) {
        if (l < r) {
            int q = partition(arr, l, r);
            quickSortHelp(arr, l, q - 1);
            quickSortHelp(arr, q + 1, r);
        }
    }

    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[l];
        int i = r + 1;
        for (int j = r; j > l; j--) {
            if (arr[j] >= pivot) {
                i--;
                swap(arr, i, j);
            }
        }
        swap(arr, i - 1, l);
        return i - 1;
    }

    public static void quickSortRandom(int[] A) {
        quickSortRandomRec(A, 0, A.length - 1);
    }

    private static void quickSortRandomRec(int[] A, int l, int r) {
        if (l < r) {
            var q = partitionRandom(A, l, r);
            quickSortRandomRec(A, l, q - 1);
            quickSortRandomRec(A, q + 1, r);
        }
    }

    private static int partitionRandom(int[] A, int l, int r) {
        var rand = new Random().nextInt(l, r + 1);
        swap(A, rand, r);
        var x = A[r];
        var i = l - 1;
        for (int j = l; j < r; j++) {
            if (A[j] <= x) {
                i++;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, r);
        return i + 1;
    }

    public static void quickSortNewRandom(int[] A) {
        quickSortNewRandomRec(A, 0, A.length - 1);
    }

    private static void quickSortNewRandomRec(int[] A, int l, int r) {
        if (l < r) {
            var q = partitionNewRandom(A, l, r);
            quickSortNewRandomRec(A, l, q - 1);
            quickSortNewRandomRec(A, q + 1, r);
        }
    }

    private static int partitionNewRandom(int[] A, int l, int r) {
        var randomizer = new Random();
        var rand = getMiddleValue(randomizer.nextInt(l, r + 1), randomizer.nextInt(l, r + 1), randomizer.nextInt(l, r + 1));
        swap(A, rand, r);
        var x = A[r];
        var i = l - 1;
        for (int j = l; j < r; j++) {
            if (A[j] <= x) {
                i++;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, r);
        return i + 1;
    }

    public static int getMiddleValue(int v1, int v2, int v3) {
        if (isInRange(v1, v2, v3)) {
            return v1;
        } else if (isInRange(v2, v1, v3)) {
            return v2;
        } else {
            return v3;
        }
    }

    public static boolean isInRange(int inRangeValue, int Value1Inclusive, int Value2Inclusive) {
        return (inRangeValue >= Value1Inclusive && inRangeValue <= Value2Inclusive) || (inRangeValue >= Value2Inclusive && inRangeValue <= Value1Inclusive);
    }

    public static void quickSortTriRandom(int[] arr) {
        quickSortTriRandomHelp(arr, 0, arr.length - 1);
    }

    private static void quickSortTriRandomHelp(int[] arr, int l, int r) {
        if (l < r) {
            int[] q = partitionTriRandom(arr, l, r);
            quickSortTriRandomHelp(arr, l, q[0] - 1);
            quickSortTriRandomHelp(arr, q[0] + 1, q[1] - 1);
            quickSortTriRandomHelp(arr, q[1] + 1, r);
        }
    }

    private static int[] partitionTriRandom(int[] arr, int l, int r) {
        Random rand = new Random();
        int random = rand.nextInt(r - l) + l;
        swap(arr, random, r);
        int pivotr = arr[r];

        random = rand.nextInt(r - l) + l;
        swap(arr, random, l);
        int pivotl = arr[l];

        if (pivotl > pivotr) {
            swap(arr, l, r);
            pivotl = arr[l];
            pivotr = arr[r];
        }

        int i = l + 1;
        int g = r - 1;

        for (int j = l; j <= g; j++) {
            if (arr[j] < pivotl) {
                swap(arr, i, j);
                i++;
            } else if (arr[j] >= pivotr) {
                while (arr[g] > pivotr && j < g) {
                    g--;
                }
                swap(arr, j, g);
                g--;
                if (arr[j] < pivotl) {
                    swap(arr, j, i);
                    i++;
                }
            }
        }
        i--;
        g++;

        swap(arr, i, l);
        swap(arr, g, r);
        return new int[]{i, g};
    }

    // Wir wissen, dass es nicht geht :(
    // Es ist immer nur ein Index oder so falsch, aber wir verstehen nicht warum...
    public static void quickSortTriNewRandom(int[] A) {
        quickSortTriNewRandomRec(A, 0, A.length - 1);
    }

    public static void quickSortTriNewRandomRec(int[] A, int l, int r) {

        if (l < r) {
            int[] q = partitionTriNewRandom(A, l, r);
            quickSortTriNewRandomRec(A, l, q[0] - 1);
            quickSortTriNewRandomRec(A, q[0] + 1, q[1] - 1);
            quickSortTriNewRandomRec(A, q[1] + 1, r);
        }

    }

    public static int[] partitionTriNewRandom(int[] A, int l, int r) {

        Random random = new Random();

        int[] randElements = new int[5];

        for (int i = 0; i < 5; i++) {
            randElements[i] = A[random.nextInt(l, r + 1)];
        }

        quickSortTriRandom(randElements);

        int x1 = randElements[1];
        swap(A, SearchTools.linSearch(A, x1), l);

        int x2 = randElements[3];
        swap(A, SearchTools.linSearch(A, x2), r);

        if (x1 > x2) {
            swap(A, l, r);
            x1 = A[l];
            x2 = A[r];
        }

        int i = l + 1;
        int g = r - 1;

        for (int j = l; j <= g; j++) {
            if (A[j] < x1) {
                swap(A, i, j);
                i++;
            } else if (A[j] >= x2) {
                while (A[g] > x2 && j < g) {
                    g--;
                }
                swap(A, j, g);
                g--;
                if (A[j] < x1) {
                    swap(A, j, i);
                    i++;
                }
            }
        }
        i--;
        g++;

        swap(A, i, l);
        swap(A, g, r);
        return new int[]{i, g};
    }

    // endregion

    //region helper functions

    /**
     * This is the generic version of {@link SortTools#swap(int[], int, int)}
     */
    private static <T> void swap(T[] arr, int a, int b) {
        var temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * Swaps the places of two elements in an int array.
     *
     * @param arr is the int array where two elements are to be swapped
     * @param a   is the first element
     * @param b   is the second element
     */
    private static void swap(int[] arr, int a, int b) {
        var temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //endregion

    //region time measurements

    /* Bubble Sort
    Average runtimes (NUMBER_OF_ITERATIONS = 10)

    n = 100:    136174
    n = 1000:   616062
    n = 10000:  18298508
    n = 100000: 1936414066
    n = 200000: 7692864841
     */
    public static long getMeanTimeBubbleSort(int[] array) {
        var meanTime = 0L;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) { // do it x times

            var arr = Arrays.copyOf(array, array.length); // Create copy of array

            var startTimer = System.nanoTime(); // Get start time

            bubbleSort(arr); // Sort array

            var endTimer = System.nanoTime(); // Get end time

            long diff = endTimer - startTimer; // Calculate runtime

            meanTime += diff; // add difference to total
        }
        return meanTime / NUMBER_OF_ITERATIONS; // divide total by number of iterations
    }

    /* Bubble Sort New
    Average runtimes (NUMBER_OF_ITERATIONS = 10)

    n = 100:    232666
    n = 1000:   5665833
    n = 10000:  545436400
    n = 100000: 56007505679
    n = 200000: 223766426191
     */
    public static long getMeanTimeBubbleSortNew(int[] array) {
        var meanTime = 0L;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) { // do it x times

            var arr = Arrays.copyOf(array, array.length); // Create copy of array

            var startTimer = System.nanoTime(); // Get start time

            bubbleSortNew(arr); // Sort array

            var endTimer = System.nanoTime(); // Get end time

            long diff = endTimer - startTimer; // Calculate runtime

            meanTime += diff; // add difference to total
        }
        return meanTime / NUMBER_OF_ITERATIONS; // divide total by number of iterations
    }


    /* Insertion sort
    Average runtimes on an incremental sorted array (NUMBER_OF_ITERATIONS = 10)

    n = 100:    1053
    n = 1000:   3164
    n = 10000:  2182
    n = 100000: 20694
    n = 200000: 41401


    Average runtimes on a decremental sorted array (NUMBER_OF_ITERATIONS = 10)

    n = 100:    5937
    n = 1000:   329976
    n = 10000:  32567395
    n = 100000: 3292526041
    n = 200000: idk :( war nach 3Stunden immer noch nicht fertig?!
     */
    public static long getMeanTimeInsertionSort(int[] array) {
        var meanTime = 0L;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) { // do it x times

            var arr = Arrays.copyOf(array, array.length); // Create copy of array

            var startTimer = System.nanoTime(); // Get start time

            insertionSort(arr); // Sort array

            var endTimer = System.nanoTime(); // Get end time

            long diff = endTimer - startTimer; // Calculate runtime

            meanTime += diff; // add difference to total
        }
        return meanTime / NUMBER_OF_ITERATIONS; // divide total by number of iterations
    }


    /* Merge Sort
    Average runtimes on an incremental sorted array (NUMBER_OF_ITERATIONS = 10)

    n = 100:    4142
    n = 1000:   27437
    n = 10000:  283722
    n = 100000: 2908530
    n = 200000: 5858402


    Average runtimes on a decremental sorted array (NUMBER_OF_ITERATIONS = 10)

    n = 100:    2418
    n = 1000:   27215
    n = 10000:  249080
    n = 100000: 2841072
    n = 200000: 5774479
     */
    public static long getMeanTimeMergeSort(int[] array) {
        var meanTime = 0L;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) { // do it x times

            var arr = Arrays.copyOf(array, array.length); // Create copy of array

            var startTimer = System.nanoTime(); // Get start time

            mergeSort(arr); // Sort array

            var endTimer = System.nanoTime(); // Get end time

            long diff = endTimer - startTimer; // Calculate runtime

            meanTime += diff; // add difference to total
        }
        return meanTime / NUMBER_OF_ITERATIONS; // divide total by number of iterations
    }


    /* Merge Sort New
    Average runtimes on an incremental sorted array (NUMBER_OF_ITERATIONS = 10)

    n = 100:    3961
    n = 1000:   27297
    n = 10000:  259740
    n = 100000: 2986923
    n = 200000: 5655446


    Average runtimes on a decremental sorted array (NUMBER_OF_ITERATIONS = 10)

    n = 100:    1869
    n = 1000:   22492
    n = 10000:  253405
    n = 100000: 2883243
    n = 200000: 5393586
     */
    public static long getMeanTimeMergeSortNew(int[] array) {
        var meanTime = 0L;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) { // do it x times

            var arr = Arrays.copyOf(array, array.length); // Create copy of array

            var startTimer = System.nanoTime(); // Get start time

            mergeSortNew(arr); // Sort array

            var endTimer = System.nanoTime(); // Get end time

            long diff = endTimer - startTimer; // Calculate runtime

            meanTime += diff; // add difference to total
        }
        return meanTime / NUMBER_OF_ITERATIONS; // divide total by number of iterations
    }

    public static long getMeanTimeQuickSort(int[] array) {
        var meanTime = 0L;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) { // do it x times

            var arr = Arrays.copyOf(array, array.length); // Create copy of array

            var startTimer = System.nanoTime(); // Get start time

            quickSort(arr); // Sort array

            var endTimer = System.nanoTime(); // Get end time

            long diff = endTimer - startTimer; // Calculate runtime

            meanTime += diff; // add difference to total
        }
        return meanTime / NUMBER_OF_ITERATIONS; // divide total by number of iterations
    }

    public static long getMeanTimeQuickSortRandom(int[] array) {
        var meanTime = 0L;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) { // do it x times

            var arr = Arrays.copyOf(array, array.length); // Create copy of array

            var startTimer = System.nanoTime(); // Get start time

            quickSortRandom(arr); // Sort array

            var endTimer = System.nanoTime(); // Get end time

            long diff = endTimer - startTimer; // Calculate runtime

            meanTime += diff; // add difference to total
        }
        return meanTime / NUMBER_OF_ITERATIONS; // divide total by number of iterations
    }

    public static long getMeanTimeQuickSortNewRandom(int[] array) {
        var meanTime = 0L;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) { // do it x times

            var arr = Arrays.copyOf(array, array.length); // Create copy of array

            var startTimer = System.nanoTime(); // Get start time

            quickSortNewRandom(arr); // Sort array

            var endTimer = System.nanoTime(); // Get end time

            long diff = endTimer - startTimer; // Calculate runtime

            meanTime += diff; // add difference to total
        }
        return meanTime / NUMBER_OF_ITERATIONS; // divide total by number of iterations
    }

    //endregion


    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {
        try {
            NUMBER_OF_ITERATIONS = Integer.parseInt(args[0]);
        } catch (Exception ignored) {
        }

        var hundredDec = createSequenceDec(100);
        var thousandDec = createSequenceDec(1000);
        var tenThousandDec = createSequenceDec(10000);
        var hundredThousandDec = createSequenceDec(100000);
        var twoHundredThousandDec = createSequenceDec(200000);

        var hundredInc = createSequenceInc(100);
        var thousandInc = createSequenceInc(1000);
        var tenThousandInc = createSequenceInc(10000);
        var hundredThousandInc = createSequenceInc(100000);
        var twoHundredThousandInc = createSequenceInc(200000);

        var hundredRand = createSequenceRand(100);
        var thousandRand = createSequenceRand(1000);
        var tenThousandRand = createSequenceRand(10000);
        var hundredThousandRand = createSequenceRand(100000);
        var twoHundredThousandRand = createSequenceRand(200000);


        System.out.println("quickSortDec");
        System.out.println("100: " + getMeanTimeQuickSort(hundredDec));
        System.out.println("1.000: " + getMeanTimeQuickSort(thousandDec));
        System.out.println("10.000: " + getMeanTimeQuickSort(tenThousandDec));
        System.out.println("100.000: " + "StackOverflow");
        System.out.println("200.000: " + "StackOverflow" + System.lineSeparator());
        System.out.println("quickSortInc");
        System.out.println("100: " + getMeanTimeQuickSort(hundredInc));
        System.out.println("1.000: " + getMeanTimeQuickSort(thousandInc));
        System.out.println("10.000: " + getMeanTimeQuickSort(tenThousandInc));
        System.out.println("100.000: " + "StackOverflow");
        System.out.println("200.000: " + "StackOverflow" + System.lineSeparator());
        System.out.println("quickSortRand");
        System.out.println("100: " + getMeanTimeQuickSort(hundredRand));
        System.out.println("1.000: " + getMeanTimeQuickSort(thousandRand));
        System.out.println("10.000: " + getMeanTimeQuickSort(tenThousandRand));
        System.out.println("100.000: " + getMeanTimeQuickSort(hundredThousandRand));
        System.out.println("200.000: " + getMeanTimeQuickSort(twoHundredThousandRand) + System.lineSeparator());

        System.out.println("quickSortRandomDec");
        System.out.println("100: " + getMeanTimeQuickSortRandom(hundredDec));
        System.out.println("1.000: " + getMeanTimeQuickSortRandom(thousandDec));
        System.out.println("10.000: " + getMeanTimeQuickSortRandom(tenThousandDec));
        System.out.println("100.000: " + getMeanTimeQuickSortRandom(hundredThousandDec));
        System.out.println("200.000: " + getMeanTimeQuickSortRandom(twoHundredThousandDec) + System.lineSeparator());
        System.out.println("quickSortRandomInc");
        System.out.println("100: " + getMeanTimeQuickSortRandom(hundredInc));
        System.out.println("1.000: " + getMeanTimeQuickSortRandom(thousandInc));
        System.out.println("10.000: " + getMeanTimeQuickSortRandom(tenThousandInc));
        System.out.println("100.000: " + getMeanTimeQuickSortRandom(hundredThousandInc));
        System.out.println("200.000: " + getMeanTimeQuickSortRandom(twoHundredThousandInc) + System.lineSeparator());
        System.out.println("quickSortRandomRand");
        System.out.println("100: " + getMeanTimeQuickSortRandom(hundredRand));
        System.out.println("1.000: " + getMeanTimeQuickSortRandom(thousandRand));
        System.out.println("10.000: " + getMeanTimeQuickSortRandom(tenThousandRand));
        System.out.println("100.000: " + getMeanTimeQuickSortRandom(hundredThousandRand));
        System.out.println("200.000: " + getMeanTimeQuickSortRandom(twoHundredThousandRand) + System.lineSeparator());

        System.out.println("quickSortNewRandomDec");
        System.out.println("100: " + getMeanTimeQuickSortNewRandom(hundredDec));
        System.out.println("1.000: " + getMeanTimeQuickSortNewRandom(thousandDec));
        System.out.println("10.000: " + getMeanTimeQuickSortNewRandom(tenThousandDec));
        System.out.println("100.000: " + getMeanTimeQuickSortNewRandom(hundredThousandDec));
        System.out.println("200.000: " + getMeanTimeQuickSortNewRandom(twoHundredThousandDec) + System.lineSeparator());
        System.out.println("quickSortInc");
        System.out.println("100: " + getMeanTimeQuickSortNewRandom(hundredInc));
        System.out.println("1.000: " + getMeanTimeQuickSortNewRandom(thousandInc));
        System.out.println("10.000: " + getMeanTimeQuickSortNewRandom(tenThousandInc));
        System.out.println("100.000: " + getMeanTimeQuickSortNewRandom(hundredThousandInc));
        System.out.println("200.000: " + getMeanTimeQuickSortNewRandom(twoHundredThousandInc) + System.lineSeparator());
        System.out.println("quickSortRand");
        System.out.println("100: " + getMeanTimeQuickSortNewRandom(hundredRand));
        System.out.println("1.000: " + getMeanTimeQuickSortNewRandom(thousandRand));
        System.out.println("10.000: " + getMeanTimeQuickSortNewRandom(tenThousandRand));
        System.out.println("100.000: " + getMeanTimeQuickSortNewRandom(hundredThousandRand));
        System.out.println("200.000: " + getMeanTimeQuickSortNewRandom(twoHundredThousandRand) + System.lineSeparator());

        /*
        quickSortDec
        100: 6852
        1.000: 238152
        10.000: 21841233
        100.000: StackOverflow
        200.000: StackOverflow

        quickSortInc
        100: 3320
        1.000: 271339
        10.000: 26288631
        100.000: StackOverflow
        200.000: StackOverflow

        quickSortRand
        100: 710
        1.000: 9457
        10.000: 402488
        100.000: 5220725
        200.000: 11203428

        quickSortRandomDec
        100: 5935
        1.000: 39400
        10.000: 407915
        100.000: 4324878
        200.000: 8766202

        quickSortRandomInc
        100: 3392
        1.000: 35245
        10.000: 376787
        100.000: 4032247
        200.000: 8210054

        quickSortRandomRand
        100: 4365
        1.000: 55125
        10.000: 669480
        100.000: 7744353
        200.000: 15971181

        quickSortNewRandomDec
        100: 7216
        1.000: 49681
        10.000: 509625
        100.000: 5286046
        200.000: 10677780

        quickSortInc
        100: 4252
        1.000: 43221
        10.000: 452712
        100.000: 4668486
        200.000: 9455430

        quickSortRand
        100: 5467
        1.000: 65774
        10.000: 767794
        100.000: 9256185
        200.000: 18721439
         */
    }
}
