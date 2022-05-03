package andi.Zettel1.Aufgabe1;

import java.util.Arrays;

public class SortTools {

    public static void main(String[] args) {
    /*

    MergeSort:
    Ascending:
    100: 73100
    1000: 128830
    10000: 1300610
    100000: 8174150
    200000: 13997530
    Descending:
    100: 3560
    1000: 38540
    10000: 638300
    100000: 5211940
    200000: 13881870
    ________________________________________________________________________________________
    MergeSortNew:
    Ascending:
    100: 52820
    1000: 69700
    10000: 926990
    100000: 4896750
    200000: 8879630
    Descending:
    100: 3390
    1000: 35690
    10000: 418140
    100000: 4873380
    200000: 8997570
    ________________________________________________________________________________________
    InsertionSort:
    Ascending:
    100: 4030
    1000: 37480
    10000: 225750
    100000: 275030
    200000: 78710
    Descending:
    100: 46920
    1000: 532330
    10000: 39332440
    100000: 383023136
    200000: 1671065944

        int[] arr100Asc = createSequenceInc(100);
        int[] arr1000Asc = createSequenceInc(1000);
        int[] arr10000Asc = createSequenceInc(10000);
        int[] arr100000Asc = createSequenceInc(100000);
        int[] arr200000Asc = createSequenceInc(200000);

        int[] arr100Dec = createSequenceDec(100);
        int[] arr1000Dec = createSequenceDec(1000);
        int[] arr10000Dec = createSequenceDec(10000);
        int[] arr100000Dec = createSequenceDec(100000);
        int[] arr200000Dec = createSequenceDec(200000);

        System.out.println("MergeSort:");
        System.out.println("Ascending:");

        System.out.println("100: " + sortAlgo(arr100Asc, "mergeSort", 10));
        System.out.println("1000: " + sortAlgo(arr1000Asc, "mergeSort", 10));
        System.out.println("10000: " + sortAlgo(arr10000Asc, "mergeSort", 10));
        System.out.println("100000: " + sortAlgo(arr100000Asc, "mergeSort", 10));
        System.out.println("200000: " + sortAlgo(arr200000Asc, "mergeSort", 10));

        System.out.println("Descending:");
        System.out.println("100: " + sortAlgo(arr100Dec, "mergeSort", 10));
        System.out.println("1000: " + sortAlgo(arr1000Dec, "mergeSort", 10));
        System.out.println("10000: " + sortAlgo(arr10000Dec, "mergeSort", 10));
        System.out.println("100000: " + sortAlgo(arr100000Dec, "mergeSort", 10));
        System.out.println("200000: " + sortAlgo(arr200000Dec, "mergeSort", 10));

        System.out.println("________________________________________________________________________________________");

        System.out.println("MergeSortNew:");
        System.out.println("Ascending:");

        System.out.println("100: " + sortAlgo(arr100Asc, "mergeSortNew", 10));
        System.out.println("1000: " + sortAlgo(arr1000Asc, "mergeSortNew", 10));
        System.out.println("10000: " + sortAlgo(arr10000Asc, "mergeSortNew", 10));
        System.out.println("100000: " + sortAlgo(arr100000Asc, "mergeSortNew", 10));
        System.out.println("200000: " + sortAlgo(arr200000Asc, "mergeSortNew", 10));

        System.out.println("Descending:");
        System.out.println("100: " + sortAlgo(arr100Dec, "mergeSortNew", 10));
        System.out.println("1000: " + sortAlgo(arr1000Dec, "mergeSortNew", 10));
        System.out.println("10000: " + sortAlgo(arr10000Dec, "mergeSortNew", 10));
        System.out.println("100000: " + sortAlgo(arr100000Dec, "mergeSortNew", 10));
        System.out.println("200000: " + sortAlgo(arr200000Dec, "mergeSortNew", 10));

        System.out.println("________________________________________________________________________________________");

        System.out.println("InsertionSort:");
        System.out.println("Ascending:");

        System.out.println("100: " + sortAlgo(arr100Asc, "insertionSort", 10));
        System.out.println("1000: " + sortAlgo(arr1000Asc, "insertionSort", 10));
        System.out.println("10000: " + sortAlgo(arr10000Asc, "insertionSort", 10));
        System.out.println("100000: " + sortAlgo(arr100000Asc, "insertionSort", 10));
        System.out.println("200000: " + sortAlgo(arr200000Asc, "insertionSort", 10));

        System.out.println("Descending:");
        System.out.println("100: " + sortAlgo(arr100Dec, "insertionSort", 10));
        System.out.println("1000: " + sortAlgo(arr1000Dec, "insertionSort", 10));
        System.out.println("10000: " + sortAlgo(arr10000Dec, "insertionSort", 10));
        System.out.println("100000: " + sortAlgo(arr100000Dec, "insertionSort", 10));
        System.out.println("200000: " + sortAlgo(arr200000Dec, "insertionSort", 10));

    */
    }

    public static long sortAlgo(int[] arr, String algo, int x) {
        int result = 0;

        for (int i = 1; i <= x; i++) {
            int[] arrCopy = Arrays.copyOf(arr, arr.length);

            if (algo.equals("mergeSort")) {
                long start = System.nanoTime();
                mergeSort(arrCopy);
                long end = System.nanoTime();
                result += (end - start) / x;
            } else if (algo.equals("mergeSortNew")) {
                long start = System.nanoTime();
                mergeSortNew(arrCopy);
                long end = System.nanoTime();
                result += (end - start) / x;
            } else {
                long start = System.nanoTime();
                insertionSort(arrCopy);
                long end = System.nanoTime();
                result += (end - start) / x;
            }
        }

        return result;
    }

    public static void mergeSortNew(int[] a) {
        mergeSortNewHelp(a, 0, a.length - 1);
    }

    private static void mergeSortNewHelp(int[] a, int p, int r) {
        if (p < r) {
            int x = (int) Math.round((r - p + 1) / 3.0);
            int ql = x - 1 + p;
            int qr = ql + x;
            mergeSortNewHelp(a, p, ql);
            mergeSortNewHelp(a, ql + 1, qr);
            mergeSortNewHelp(a, qr + 1, r);
            mergeNew(a, p, ql, qr, r);
        }
    }

    private static void mergeNew(int[] a, int p, int ql, int qr, int r) {
        int n1 = ql - p + 1;
        int n2 = qr - ql;
        int n3 = r - qr;

        int[] L = new int[n1 + 1];
        int[] M = new int[n2 + 1];
        int[] R = new int[n3 + 1];

        for (int i = 0; i < n1; i++) {
            L[i] = a[p + i];
        }

        for (int j = 0; j < n2; j++) {
            M[j] = a[ql + j + 1];
        }

        for (int l = 0; l < n3; l++) {
            R[l] = a[qr + l + 1];
        }

        L[n1] = (int) Float.POSITIVE_INFINITY;
        M[n2] = (int) Float.POSITIVE_INFINITY;
        R[n3] = (int) Float.POSITIVE_INFINITY;
        int i = 0, j = 0, l = 0;
        for (int k = p; k <= r; k++) {
            if (L[i] <= R[l] && L[i] <= M[j]) {
                a[k] = L[i];
                i++;
            } else if (M[j] <= L[i] && M[j] <= R[l]) {
                a[k] = M[j];
                j++;
            } else {
                a[k] = R[l];
                l++;
            }
        }
    }

    public static void mergeSort(int[] a) {
        mergeSortHelp(a, 0, a.length - 1);
    }

    private static void mergeSortHelp(int[] a, int p, int r) {
        if (p < r) {
            int q = (int) Math.floor((p + r) / 2.0);
            mergeSortHelp(a, p, q);
            mergeSortHelp(a, q + 1, r);
            merge(a, p, q, r);
        }
    }

    private static void merge(int[] a, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        for (int i = 0; i < n1; i++) {
            L[i] = a[p + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = a[q + j + 1];
        }
        L[n1] = (int) Float.POSITIVE_INFINITY;
        R[n2] = (int) Float.POSITIVE_INFINITY;
        int i = 0, j = 0;
        for (int k = p; k <= r; k++) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
        }
    }

    public static void bubbleSort(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static void bubbleSortNew(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i - 9; j++) {
                int[] temp = new int[11];
                System.arraycopy(a, j, temp, 0, 11);
                insertionSort(temp);
                System.arraycopy(temp, 0, a, j, 11);
            }
        }
    }

    public static <T extends Comparable<T>> void bubbleSortGen(T[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j].compareTo(a[j + 1]) > 0) {
                    T temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void insertionSortGen(T[] a) {
        for (int j = 1; j < a.length; j++) {
            T s = a[j];
            int i = j - 1;
            while (i >= 0 && s.compareTo(a[i]) < 0) {
                a[i + 1] = a[i];
                i--;
            }
            a[i + 1] = s;
        }
    }

    public static void insertionSort(int[] a) {
        for (int j = 1; j < a.length; j++) {
            int s = a[j];
            int i = j - 1;
            while (i >= 0 && s < a[i]) {
                a[i + 1] = a[i];
                i--;
            }
            a[i + 1] = s;
        }
    }

    public static int[] createSequenceInc(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    public static int[] createSequenceDec(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = n - i;
        }
        return arr;
    }

    public static int[] createSequenceRand(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * n) + 1;
        }
        return arr;
    }

    public static int[] createSequenceAlt(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 2;
            }
        }
        return arr;
    }
}
