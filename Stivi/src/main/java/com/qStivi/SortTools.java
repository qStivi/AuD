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
            arr[i] = r.nextInt(n + 1);
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

    public static int insertionSortCount(int[] a) {
        var count = 0;
        for (int j = 1; j < a.length; j++) {
            var s = a[j];
            var i = j - 1;
            while (i >= 0 && a[i] > s) {
                count++;
                a[i + 1] = a[i];
                i--;
            }
            a[i + 1] = s;
        }
        return count;
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

//        logger.info(String.valueOf(getMeanTimeInsertionSort(hundred)));
//        logger.info(String.valueOf(getMeanTimeInsertionSort(thousand)));
//        logger.info(String.valueOf(getMeanTimeInsertionSort(tenThousand)));
//        logger.info(String.valueOf(getMeanTimeInsertionSort(hundredThousand)));
//        logger.info(String.valueOf(getMeanTimeInsertionSort(twoHundredThousand)));

        var arr = new int[]{1, 9, 1, 10, 1, 11, 1, 12, 1, 13, 1, 14, 1, 15, 1, 16};
        logger.info(String.valueOf(insertionSortCount(arr)));
    }

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
}
