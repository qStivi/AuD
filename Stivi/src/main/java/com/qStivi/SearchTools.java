package com.qStivi;

import java.util.Random;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class SearchTools {

    public static int linSearch(int[] A, int x) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public static int binSearch(int[] A, int x, int l, int r) {
        var i = (l + r) / 2;
        if (r >= 1) {
            if (x == A[i]) {
                return i;
            } else if (x < A[i]) {
                return binSearch(A, x, l, i);
            } else if (x > A[i]) {
                return binSearch(A, x, i + 1, r);
            }
        }

        return -1;
    }

    // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    //    l     i        j     r
    public static int binSearchNew(int[] A, int x, int l, int r) {
        var i = (l + r) / 3;
        var j = ((l + r) / 3) * 2;
        if (r >= 1) {
            if (x == A[i]) {
                return i;
            } else if (x < A[i]) {
                return binSearch(A, x, l, i);
            } else if (x > A[i] && x < A[j]) {
                return binSearch(A, x, i, j);
            } else if (x > A[j]) {
                return binSearch(A, x, j + 1, r);
            }
        }

        return -1;
    }

    /*
        Linear Search
            100.000: 36084
          1.000.000: 293939
        100.000.000: 28030546
        685.154.321: 192841299
        _______________________

        Binary Search
            100.000: 1014
          1.000.000: 13599
        100.000.000: 41169
        685.154.321: 1999
        _______________________

        Binary Search New
            100.000: 391
          1.000.000: 511
        100.000.000: 26370
        685.154.321: 2778
        _______________________
     */
    public static void main(String[] args) {
        var hundredThousand = SortTools.createSequenceInc(100000);
        var million = SortTools.createSequenceInc(1000000);
        var hundredMillion = SortTools.createSequenceInc(100000000);
        var sixHundredEightyFiveMillionOneHundredAndFiftyFourThousandAndThreeHundredAndTwentyOne = SortTools.createSequenceInc(685154321);

        System.out.println("Linear Search");
        System.out.println("    100.000: " + getAverageLinerSearchTime(hundredThousand));
        System.out.println("  1.000.000: " + getAverageLinerSearchTime(million));
        System.out.println("100.000.000: " + getAverageLinerSearchTime(hundredMillion));
        System.out.println("685.154.321: " + getAverageLinerSearchTime(sixHundredEightyFiveMillionOneHundredAndFiftyFourThousandAndThreeHundredAndTwentyOne));
        System.out.println("_______________________");
        System.out.println();

        System.out.println("Binary Search");
        System.out.println("    100.000: " + getAverageBinarySearchTime(hundredThousand));
        System.out.println("  1.000.000: " + getAverageBinarySearchTime(million));
        System.out.println("100.000.000: " + getAverageBinarySearchTime(hundredMillion));
        System.out.println("685.154.321: " + getAverageBinarySearchTime(sixHundredEightyFiveMillionOneHundredAndFiftyFourThousandAndThreeHundredAndTwentyOne));
        System.out.println("_______________________");
        System.out.println();

        System.out.println("Binary Search New");
        System.out.println("    100.000: " + getAverageBinarySearchNewTime(hundredThousand));
        System.out.println("  1.000.000: " + getAverageBinarySearchNewTime(million));
        System.out.println("100.000.000: " + getAverageBinarySearchNewTime(hundredMillion));
        System.out.println("685.154.321: " + getAverageBinarySearchNewTime(sixHundredEightyFiveMillionOneHundredAndFiftyFourThousandAndThreeHundredAndTwentyOne));
        System.out.println("_______________________");
        System.out.println();
    }

    private static long getAverageLinerSearchTime(int[] A) {
        var avg = 0L;
        for (int i = 0; i < 500; i++) {
            var r = new Random().nextInt(1, A.length);
            var start = System.nanoTime();
            linSearch(A, r);
            linSearch(A, -5);
            avg += System.nanoTime() - start;
        }
        return avg / 500;
    }

    private static long getAverageBinarySearchTime(int[] A) {
        var avg = 0L;
        for (int i = 0; i < 500; i++) {
            var r = new Random().nextInt(1, A.length);
            var start = System.nanoTime();
            binSearch(A, r, 0, A.length - 1);
            binSearch(A, -5, 0, A.length - 1);
            avg += System.nanoTime() - start;
        }
        return avg / 500;
    }

    private static long getAverageBinarySearchNewTime(int[] A) {
        var avg = 0L;
        for (int i = 0; i < 500; i++) {
            var r = new Random().nextInt(1, A.length);
            var start = System.nanoTime();
            binSearchNew(A, r, 0, A.length - 1);
            binSearchNew(A, -5, 0, A.length - 1);
            avg += System.nanoTime() - start;
        }
        return avg / 500;
    }

}
