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

    public static void quickSort(int[] A) {
        quickSortRec(A, 0, A.length - 1);
    }

    private static void quickSortRec(int[] A, int l, int r) {
        if (l < r) {
            var q = partition(A, l, r);
            quickSortRec(A, l, q - 1);
            quickSortRec(A, q + 1, r);
        }
    }

    private static int partition(int[] A, int l, int r) {
        var x = A[l];
        var i = r + 1;
        for (int j = r; j > l; j--) {
            if (A[j] >= x) {
                i--;
                swap(A, i, j);
            }
        }
        swap(A, i - 1, l);
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

//        System.out.println("BubbleSortInc");
//        System.out.println("100: " + getMeanTimeBubbleSort(hundredInc));
//        System.out.println("1.000: " + getMeanTimeBubbleSort(thousandInc));
//        System.out.println("10.000: " + getMeanTimeBubbleSort(tenThousandInc));
//        System.out.println("100.000: " + getMeanTimeBubbleSort(hundredThousandInc));
//        System.out.println("200.000: " + getMeanTimeBubbleSort(twoHundredThousandInc) + System.lineSeparator());
//
//        System.out.println("BubbleSortNewInc");
//        System.out.println("100: " + getMeanTimeBubbleSortNew(hundredInc));
//        System.out.println("1.000: " + getMeanTimeBubbleSortNew(thousandInc));
//        System.out.println("10.000: " + getMeanTimeBubbleSortNew(tenThousandInc));
//        System.out.println("100.000: " + getMeanTimeBubbleSortNew(hundredThousandInc));
//        System.out.println("200.000: " + getMeanTimeBubbleSortNew(twoHundredThousandInc) + System.lineSeparator());

        System.out.println("MergeSortInc");
        System.out.println("100: " + getMeanTimeMergeSort(hundredInc));
        System.out.println("1.000: " + getMeanTimeMergeSort(thousandInc));
        System.out.println("10.000: " + getMeanTimeMergeSort(tenThousandInc));
        System.out.println("100.000: " + getMeanTimeMergeSort(hundredThousandInc));
        System.out.println("200.000: " + getMeanTimeMergeSort(twoHundredThousandInc) + System.lineSeparator());

        System.out.println("MergeSortNewInc");
        System.out.println("100: " + getMeanTimeMergeSortNew(hundredInc));
        System.out.println("1.000: " + getMeanTimeMergeSortNew(thousandInc));
        System.out.println("10.000: " + getMeanTimeMergeSortNew(tenThousandInc));
        System.out.println("100.000: " + getMeanTimeMergeSortNew(hundredThousandInc));
        System.out.println("200.000: " + getMeanTimeMergeSortNew(twoHundredThousandInc) + System.lineSeparator() + System.lineSeparator());

        System.out.println("InsertionSortInc");
        System.out.println("100: " + getMeanTimeInsertionSort(hundredInc));
        System.out.println("1.000: " + getMeanTimeInsertionSort(thousandInc));
        System.out.println("10.000: " + getMeanTimeInsertionSort(tenThousandInc));
        System.out.println("100.000: " + getMeanTimeInsertionSort(hundredThousandInc));
        System.out.println("200.000: " + getMeanTimeInsertionSort(twoHundredThousandInc) + System.lineSeparator());


//        System.out.println("BubbleSortDec");
//        System.out.println("100: " + getMeanTimeBubbleSort(hundredDec));
//        System.out.println("1.000: " + getMeanTimeBubbleSort(thousandDec));
//        System.out.println("10.000: " + getMeanTimeBubbleSort(tenThousandDec));
//        System.out.println("100.000: " + getMeanTimeBubbleSort(hundredThousandDec));
//        System.out.println("200.000: " + getMeanTimeBubbleSort(twoHundredThousandDec) + System.lineSeparator());
//
//        System.out.println("BubbleSortNewDec");
//        System.out.println("100: " + getMeanTimeBubbleSortNew(hundredDec));
//        System.out.println("1.000: " + getMeanTimeBubbleSortNew(thousandDec));
//        System.out.println("10.000: " + getMeanTimeBubbleSortNew(tenThousandDec));
//        System.out.println("100.000: " + getMeanTimeBubbleSortNew(hundredThousandDec));
//        System.out.println("200.000: " + getMeanTimeBubbleSortNew(twoHundredThousandDec) + System.lineSeparator());

        System.out.println("MergeSortDec");
        System.out.println("100: " + getMeanTimeMergeSort(hundredDec));
        System.out.println("1.000: " + getMeanTimeMergeSort(thousandDec));
        System.out.println("10.000: " + getMeanTimeMergeSort(tenThousandDec));
        System.out.println("100.000: " + getMeanTimeMergeSort(hundredThousandDec));
        System.out.println("200.000: " + getMeanTimeMergeSort(twoHundredThousandDec) + System.lineSeparator());

        System.out.println("MergeSortNewDec");
        System.out.println("100: " + getMeanTimeMergeSortNew(hundredDec));
        System.out.println("1.000: " + getMeanTimeMergeSortNew(thousandDec));
        System.out.println("10.000: " + getMeanTimeMergeSortNew(tenThousandDec));
        System.out.println("100.000: " + getMeanTimeMergeSortNew(hundredThousandDec));
        System.out.println("200.000: " + getMeanTimeMergeSortNew(twoHundredThousandDec) + System.lineSeparator());

        System.out.println("InsertionSortDec");
        System.out.println("100: " + getMeanTimeInsertionSort(hundredDec));
        System.out.println("1.000: " + getMeanTimeInsertionSort(thousandDec));
        System.out.println("10.000: " + getMeanTimeInsertionSort(tenThousandDec));
        System.out.println("100.000: " + getMeanTimeInsertionSort(hundredThousandDec));
        System.out.println("200.000: " + getMeanTimeInsertionSort(twoHundredThousandDec) + System.lineSeparator());
    }
}
