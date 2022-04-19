package com.qStivi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class SortTools {

    public static final int NUMBER_OF_ITERATIONS = 10000000;
    private static final Logger logger = LoggerFactory.getLogger(SortTools.class);

    public static int[] createSequenceInc(int n) {
        var arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
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
            while (i >= 0 && a[i] >= s) {
                a[i + 1] = a[i];
                i--;
            }
            a[i + 1] = s;
        }
    }

    public static void main(String[] args) {
        var hundred = createSequenceDec(100);
        var thousand = createSequenceDec(1000);
        var tenThousand = createSequenceDec(10000);
        var hundredThousand = createSequenceDec(100000);
        var twoHundredThousand = createSequenceDec(200000);

        logger.info(String.valueOf(getMeanTimeInsertionSort(hundred)));
        logger.info(String.valueOf(getMeanTimeInsertionSort(thousand)));
        logger.info(String.valueOf(getMeanTimeInsertionSort(tenThousand)));
        logger.info(String.valueOf(getMeanTimeInsertionSort(hundredThousand)));
        logger.info(String.valueOf(getMeanTimeInsertionSort(twoHundredThousand)));
    }

    public static long getMeanTimeInsertionSort(int[] array) {
        var meanTime = 0L;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            var startTimer = System.nanoTime();
            insertionSort(array);
            var endTimer = System.nanoTime();
            meanTime += endTimer - startTimer;
        }
        return meanTime / NUMBER_OF_ITERATIONS;
    }
}
