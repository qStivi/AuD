package com.qStivi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

public class SortTools {

    private static final Logger logger = LoggerFactory.getLogger(SortTools.class);
    public static int NUMBER_OF_ITERATIONS = 10;

    public static int[] createSequenceInc(int n) {
        var arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    public static int[] createSequenceDec(int n) {
        var arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n - i;
        }
        return arr;
    }

    public static int[] createSequenceRand(int n) {
        var arr = new int[n];
        var r = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(1, n + 1);
        }
        return arr;
    }

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

    public static void bubbleSort(int[] A) {
        for (int i = A.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (A[j] > A[j + 1]) {
                    swap(A, j, j + 1);
                }
            }
        }
    }

    public static void mergeSort(int[] A) {
        mergeSortRec(A, A[0], A[A.length - 1]);
    }

    private static void mergeSortRec(int[] A, int p, int r) {
        if (p < r) {
            var q = (p + r) / 2;
            mergeSortRec(A, p, q);
            mergeSortRec(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    private static void merge(int[] a, int p, int q, int r) {

    }

    public static <T extends Comparable<T>> void bubbleSortGen(T[] A) {
        for (int i = A.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (A[j].compareTo(A[j + 1]) > 0) {
                    swap(A, j, j + 1);
                }
            }
        }
    }

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

    private static <T> void swap(T[] arr, int a, int b) {
        var temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void swap(int[] arr, int a, int b) {
        var temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

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

        logger.info("InsertionSort");
        logger.info("100: " + getMeanTimeInsertionSort(hundred));
        logger.info("1.000: " + getMeanTimeInsertionSort(thousand));
        logger.info("10.000: " + getMeanTimeInsertionSort(tenThousand));
        logger.info("100.000: " + getMeanTimeInsertionSort(hundredThousand));
        logger.info("200.000: " + getMeanTimeInsertionSort(twoHundredThousand) + System.lineSeparator());

        logger.info("BubbleSort");
        logger.info("100: " + getMeanTimeBubbleSort(hundred));
        logger.info("1.000: " + getMeanTimeBubbleSort(thousand));
        logger.info("10.000: " + getMeanTimeBubbleSort(tenThousand));
        logger.info("100.000: " + getMeanTimeBubbleSort(hundredThousand));
        logger.info("200.000: " + getMeanTimeBubbleSort(twoHundredThousand) + System.lineSeparator());

        logger.info("BubbleSortNew");
        logger.info("100: " + getMeanTimeBubbleSortNew(hundred));
        logger.info("1.000: " + getMeanTimeBubbleSortNew(thousand));
        logger.info("10.000: " + getMeanTimeBubbleSortNew(tenThousand));
        logger.info("100.000: " + getMeanTimeBubbleSortNew(hundredThousand));
        logger.info("200.000: " + getMeanTimeBubbleSortNew(twoHundredThousand) + System.lineSeparator());
    }

    // TODO Why do the average times get smaller when I increase NUMBER_OF_ITERATIONS????!!!
    /*
    Average runtimes (NUMBER_OF_ITERATIONS = 10)

    n=100: 71.174
    n=1000: 59.8825
    n=10000: 12.900.924
    n=100000: 125.237.8954
    n=200000: 4.870.728.483
     */
    public static long getMeanTimeInsertionSort(int[] array) {
        var meanTime = 0L;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) { // do it x times

            var arr = Arrays.copyOf(array, array.length); // Create copy of array

            var startTimer = System.nanoTime(); // Get start time

            insertionSort(arr); // Sort array

            var endTimer = System.nanoTime(); // Get end time

            long diff = endTimer - startTimer; // Calculate runtime

//             logger.info(String.valueOf(diff)); // TODO Why do get random sporadic high values when NUMBER_OF_ITERATIONS is a large value?

            meanTime += diff; // add difference to total
        }
        return meanTime / NUMBER_OF_ITERATIONS; // divide total by number of iterations
    }

    /*
    Average runtimes (NUMBER_OF_ITERATIONS = 10)

    n=100: 138.271
    n=1000: 608.595
    n=10000: 18.664.937
    n=100000: 1.971.782.800
    n=200000: 7.878.594.274
     */
    public static long getMeanTimeBubbleSort(int[] array) {
        var meanTime = 0L;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) { // do it x times

            var arr = Arrays.copyOf(array, array.length); // Create copy of array

            var startTimer = System.nanoTime(); // Get start time

            bubbleSort(arr); // Sort array

            var endTimer = System.nanoTime(); // Get end time

            long diff = endTimer - startTimer; // Calculate runtime

            // logger.info(String.valueOf(diff)); // TODO Why do get random sporadic high values when NUMBER_OF_ITERATIONS is a large value?

            meanTime += diff; // add difference to total
        }
        return meanTime / NUMBER_OF_ITERATIONS; // divide total by number of iterations
    }


    /*
    Average runtimes (NUMBER_OF_ITERATIONS = 10)

    n=100: 1.080.304
    n=1000: 9.785.691
    n=10000: 808.150.183
    n=100000: 79.717.190.346
    n=200000: 318.326.093.637
     */
    public static long getMeanTimeBubbleSortNew(int[] array) {
        var meanTime = 0L;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) { // do it x times

            var arr = Arrays.copyOf(array, array.length); // Create copy of array

            var startTimer = System.nanoTime(); // Get start time

            bubbleSortNew(arr); // Sort array

            var endTimer = System.nanoTime(); // Get end time

            long diff = endTimer - startTimer; // Calculate runtime

            // logger.info(String.valueOf(diff)); // TODO Why do get random sporadic high values when NUMBER_OF_ITERATIONS is a large value?

            meanTime += diff; // add difference to total
        }
        return meanTime / NUMBER_OF_ITERATIONS; // divide total by number of iterations
    }
}
