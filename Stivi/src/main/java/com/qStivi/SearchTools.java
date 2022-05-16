package com.qStivi;

import java.util.Random;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class SearchTools {

    private static final int numberOfIterations = 500;

    public static int linSearch(int[] A, int x) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public static int binSearch(int[] A, int x, int l, int r) {
        if (l < r) {
            var i = (l + r) / 2;
            if (x == A[i]) {
                return i;
            } else if (x < A[i]) {
                return binSearch(A, x, l, i);
            } else {
                return binSearch(A, x, i + 1, r);
            }
        }

        return -1;
    }

    // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    //    l     i        j     r
    public static int binSearchNew(int[] A, int x, int l, int r) {
        if (r >= 1) {
            var i = ((r - l + 1) / 3) + l;
            var j = i + (r - l + 1) / 3;
            if (x == A[i]) {
                return i;
            } else if (x == A[j]) {
                return j;
            } else if (x < A[i]) {
                return binSearchNew(A, x, l, i);
            } else if (x > A[i] && x < A[j]) {
                return binSearchNew(A, x, i, j);
            } else if (x > A[j]) {
                return binSearchNew(A, x, j + 1, r);
            }
        }

        return -1;
    }

    /*
    Die Werte sehen schon wieder so komisch aus :(

    Random Number

    Linear Search
        100.000: 19503
      1.000.000: 104305
    100.000.000: 10084235
    685.154.321: 67621127
    _______________________

    Binary Search
        100.000: 678
      1.000.000: 923
    100.000.000: 5424
    685.154.321: 1478
    _______________________

    Binary Search New
        100.000: 515
      1.000.000: 488
    100.000.000: 5379
    685.154.321: 1408
    _______________________

    Worst Case

    Linear Search
        100.000: 23378
      1.000.000: 182358
    100.000.000: 18752872
    685.154.321: 135180084
    _______________________

    Binary Search
        100.000: 733
      1.000.000: 181
    100.000.000: 254
    685.154.321: 190
    _______________________

    Binary Search New
        100.000: 557
      1.000.000: 209
    100.000.000: 293
    685.154.321: 188
    _______________________
    */
    public static void main(String[] args) {
        var hundredThousand = SortTools.createSequenceInc(100000);
        var million = SortTools.createSequenceInc(1000000);
        var hundredMillion = SortTools.createSequenceInc(100000000);
        var sixHundredEightyFiveMillionOneHundredAndFiftyFourThousandAndThreeHundredAndTwentyOne = SortTools.createSequenceInc(685154321);

        System.out.println("Random Number");
        System.out.println();

        System.out.println("Linear Search");
        System.out.println("    100.000: " + getAverageLinerSearchTimeRandom(hundredThousand));
        System.out.println("  1.000.000: " + getAverageLinerSearchTimeRandom(million));
        System.out.println("100.000.000: " + getAverageLinerSearchTimeRandom(hundredMillion));
        System.out.println("685.154.321: " + getAverageLinerSearchTimeRandom(sixHundredEightyFiveMillionOneHundredAndFiftyFourThousandAndThreeHundredAndTwentyOne));
        System.out.println("_______________________");
        System.out.println();

        System.out.println("Binary Search");
        System.out.println("    100.000: " + getAverageBinarySearchTimeRandom(hundredThousand));
        System.out.println("  1.000.000: " + getAverageBinarySearchTimeRandom(million));
        System.out.println("100.000.000: " + getAverageBinarySearchTimeRandom(hundredMillion));
        System.out.println("685.154.321: " + getAverageBinarySearchTimeRandom(sixHundredEightyFiveMillionOneHundredAndFiftyFourThousandAndThreeHundredAndTwentyOne));
        System.out.println("_______________________");
        System.out.println();

        System.out.println("Binary Search New");
        System.out.println("    100.000: " + getAverageBinarySearchNewTimeRandom(hundredThousand));
        System.out.println("  1.000.000: " + getAverageBinarySearchNewTimeRandom(million));
        System.out.println("100.000.000: " + getAverageBinarySearchNewTimeRandom(hundredMillion));
        System.out.println("685.154.321: " + getAverageBinarySearchNewTimeRandom(sixHundredEightyFiveMillionOneHundredAndFiftyFourThousandAndThreeHundredAndTwentyOne));
        System.out.println("_______________________");
        System.out.println();

        System.out.println("Worst Case");
        System.out.println();

        System.out.println("Linear Search");
        System.out.println("    100.000: " + getAverageLinerSearchTimeWorst(hundredThousand));
        System.out.println("  1.000.000: " + getAverageLinerSearchTimeWorst(million));
        System.out.println("100.000.000: " + getAverageLinerSearchTimeWorst(hundredMillion));
        System.out.println("685.154.321: " + getAverageLinerSearchTimeWorst(sixHundredEightyFiveMillionOneHundredAndFiftyFourThousandAndThreeHundredAndTwentyOne));
        System.out.println("_______________________");
        System.out.println();

        System.out.println("Binary Search");
        System.out.println("    100.000: " + getAverageBinarySearchTimeWorst(hundredThousand));
        System.out.println("  1.000.000: " + getAverageBinarySearchTimeWorst(million));
        System.out.println("100.000.000: " + getAverageBinarySearchTimeWorst(hundredMillion));
        System.out.println("685.154.321: " + getAverageBinarySearchTimeWorst(sixHundredEightyFiveMillionOneHundredAndFiftyFourThousandAndThreeHundredAndTwentyOne));
        System.out.println("_______________________");
        System.out.println();

        System.out.println("Binary Search New");
        System.out.println("    100.000: " + getAverageBinarySearchNewTimeWorst(hundredThousand));
        System.out.println("  1.000.000: " + getAverageBinarySearchNewTimeWorst(million));
        System.out.println("100.000.000: " + getAverageBinarySearchNewTimeWorst(hundredMillion));
        System.out.println("685.154.321: " + getAverageBinarySearchNewTimeWorst(sixHundredEightyFiveMillionOneHundredAndFiftyFourThousandAndThreeHundredAndTwentyOne));
        System.out.println("_______________________");
        System.out.println();
    }

    private static long getAverageLinerSearchTimeRandom(int[] A) {
        var avg = 0L;
        for (int i = 0; i < numberOfIterations; i++) {
            if (avg < 0) System.out.println("OVERFLOW!");
            var r = new Random().nextInt(1, A.length);
            var start = System.nanoTime();
            linSearch(A, r);
            avg += System.nanoTime() - start;
        }
        return avg / numberOfIterations;
    }

    private static long getAverageBinarySearchTimeRandom(int[] A) {
        var avg = 0L;
        for (int i = 0; i < numberOfIterations; i++) {
            if (avg < 0) System.out.println("OVERFLOW!");
            var r = new Random().nextInt(1, A.length);
            var start = System.nanoTime();
            binSearch(A, r, 0, A.length - 1);
            avg += System.nanoTime() - start;
        }
        return avg / numberOfIterations;
    }

    private static long getAverageBinarySearchNewTimeRandom(int[] A) {
        var avg = 0L;
        for (int i = 0; i < numberOfIterations; i++) {
            if (avg < 0) System.out.println("OVERFLOW!");
            var r = new Random().nextInt(1, A.length);
            var start = System.nanoTime();
            binSearchNew(A, r, 0, A.length - 1);
            avg += System.nanoTime() - start;
        }
        return avg / numberOfIterations;
    }

    private static long getAverageLinerSearchTimeWorst(int[] A) {
        var avg = 0L;
        for (int i = 0; i < numberOfIterations; i++) {
            if (avg < 0) System.out.println("OVERFLOW!");
            var r = new Random().nextInt(1, A.length);
            var start = System.nanoTime();
            linSearch(A, -5);
            avg += System.nanoTime() - start;
        }
        return avg / numberOfIterations;
    }

    private static long getAverageBinarySearchTimeWorst(int[] A) {
        var avg = 0L;
        for (int i = 0; i < numberOfIterations; i++) {
            if (avg < 0) System.out.println("OVERFLOW!");
            var r = new Random().nextInt(1, A.length);
            var start = System.nanoTime();
            binSearch(A, -5, 0, A.length - 1);
            avg += System.nanoTime() - start;
        }
        return avg / numberOfIterations;
    }

    private static long getAverageBinarySearchNewTimeWorst(int[] A) {
        var avg = 0L;
        for (int i = 0; i < numberOfIterations; i++) {
            if (avg < 0) System.out.println("OVERFLOW!");
            var r = new Random().nextInt(1, A.length);
            var start = System.nanoTime();
            binSearchNew(A, -5, 0, A.length - 1);
            avg += System.nanoTime() - start;
        }
        return avg / numberOfIterations;
    }

}
