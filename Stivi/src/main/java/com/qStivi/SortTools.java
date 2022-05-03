package com.qStivi;


import java.util.Arrays;
import java.util.Random;

public class SortTools {

    public static int NUMBER_OF_ITERATIONS = 10;


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

    private static void merge(int[] A, int p, int q, int r) {
        var n1 = q - p + 1;
        var n2 = r - q;

        var L = new int[n1 + 1];
        var R = new int[n2 + 1];

        if (n1 - 1 >= 0) System.arraycopy(A, p + 1 - 1, L, 1, n1 - 1);
        if (n2 - 1 >= 0) System.arraycopy(A, q + 1, R, 1, n2 - 1);

        L[n1 + 1] = Integer.MAX_VALUE;
        L[n2 + 1] = Integer.MAX_VALUE;

        var i = 1;
        var j = 1;
        for (int k = p; k < r; k++) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
        }
    }

    public static void mergeSort(int[] A) {
        mergeSortRec(A, A[A.length - 1], A[0]);
    }

    private static void mergeSortRec(int[] A, int p, int r) {
        if (p < r) {
            var q = (p + r) / 2;
            mergeSortRec(A, p, q);
            mergeSortRec(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    //endregion

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

    /*
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

    /*
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


    /*
    Average runtimes (NUMBER_OF_ITERATIONS = 10)

    n = 100:    69962
    n = 1000:   723237
    n = 10000:  11882270
    n = 100000: 1071934204
    n = 200000: 4252841279
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

    //endregion


    public static void main(String[] args) {
        try {
            NUMBER_OF_ITERATIONS = Integer.parseInt(args[0]);
        } catch (Exception ignored) {
        }

        var hundred = createSequenceDec(100);
        var thousand = createSequenceDec(1000);
        var tenThousand = createSequenceDec(10000);
        var hundredThousand = createSequenceDec(100000);
        var twoHundredThousand = createSequenceDec(200000);

        System.out.println("InsertionSort");
        System.out.println("100: " + getMeanTimeInsertionSort(hundred));
        System.out.println("1.000: " + getMeanTimeInsertionSort(thousand));
        System.out.println("10.000: " + getMeanTimeInsertionSort(tenThousand));
        System.out.println("100.000: " + getMeanTimeInsertionSort(hundredThousand));
        System.out.println("200.000: " + getMeanTimeInsertionSort(twoHundredThousand) + System.lineSeparator());

        System.out.println("BubbleSort");
        System.out.println("100: " + getMeanTimeBubbleSort(hundred));
        System.out.println("1.000: " + getMeanTimeBubbleSort(thousand));
        System.out.println("10.000: " + getMeanTimeBubbleSort(tenThousand));
        System.out.println("100.000: " + getMeanTimeBubbleSort(hundredThousand));
        System.out.println("200.000: " + getMeanTimeBubbleSort(twoHundredThousand) + System.lineSeparator());

        System.out.println("BubbleSortNew");
        System.out.println("100: " + getMeanTimeBubbleSortNew(hundred));
        System.out.println("1.000: " + getMeanTimeBubbleSortNew(thousand));
        System.out.println("10.000: " + getMeanTimeBubbleSortNew(tenThousand));
        System.out.println("100.000: " + getMeanTimeBubbleSortNew(hundredThousand));
        System.out.println("200.000: " + getMeanTimeBubbleSortNew(twoHundredThousand) + System.lineSeparator());
    }
}
