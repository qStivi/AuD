package julian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SortTools {

    public static int[] createSequenceInc(int n) {

        int[] result = new int[n];

        for (int i = 1; i <= n; i++) {
            result[i - 1] = i;
        }

        return result;
    }

    public static int[] createSequenceDec(int n) {

        int[] result = new int[n];

        for (int i = 1; i <= n; i++) {
            result[i - 1] = n + 1 - i;
        }

        return result;

    }

    public static int[] createSequenceRand(int n) {

        int[] result = new int[n];

        Random random = new Random();

        for (int i = 1; i <= n; i++) {
            result[i - 1] = random.nextInt(1, n + 1);
        }

        return result;

    }

    public static int[] createSequenceAlt(int n) {

        int[] result = new int[n];

        for (int i = 1; i <= n; i++) {

            if (i % 2 != 0) {
                result[i - 1] = 1;
            } else {
                result[i - 1] = 2;
            }

        }

        return result;
    }

    public static int count = 0;

    public static void insertionSort(int[] a) {

        for (int j = 1; j <= a.length - 1; j++) {

            int s = a[j];
            int i = j - 1;

            count++;
            while (i >= 0 && a[i] > s) {
                a[i + 1] = a[i];
                i = i - 1;
                count++;
            }

            a[i + 1] = s;
        }

    }

    public static void bubbleSort(int[] a) {
        for(int i = a.length - 1; i >= 1; i--) {
            for(int j = 0; j <= i - 1; j++) {
                if(a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }

    public static void insertionSort(int[] a, int start, int end) {

        for (int j = start + 1; j <= end; j++) {

            int s = a[j];
            int i = j - 1;

            count++;
            while (i >= start && a[i] > s) {
                a[i + 1] = a[i];
                i = i - 1;
                count++;
            }

            a[i + 1] = s;
        }

    }

    public static void bubbleSortNew(int[] a) {
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a.length - 1; j += 9) {
                insertionSort(a, j, j + 9);
            }
        }
    }

    private static void swap(int[] a, int pos1, int pos2) {
        int temp = a[pos2];
        a[pos2] = a[pos1];
        a[pos1] = temp;
    }

    private static <T> void swapGen(T[] a, int pos1, int pos2) {
        T temp = a[pos2];
        a[pos2] = a[pos1];
        a[pos1] = temp;
    }

    public static <T extends Comparable<T>> void insertionSortGen(T[] a) {

        for (int j = 1; j <= a.length - 1; j++) {

            T s = a[j];
            int i = j - 1;

            while (i >= 0 && a[i].compareTo(s) > 0) {
                a[i + 1] = a[i];
                i = i - 1;
            }

            a[i + 1] = s;
        }

    }

    public static <T extends Comparable<T>> void bubbleSortGen(T[] a) {

        for(int i = a.length - 1; i >= 1; i--) {
            for(int j = 0; j <= i - 1; j++) {
                if(a[j].compareTo(a[j + 1]) > 0) { //a[j].c
                    swapGen(a, j, j + 1);
                }
            }
        }

    }

    public static void mergeSort(int[] a) {
        mergeSortHelp(a, 0, a.length - 1);
    }

    public static void mergeSortHelp(int[] a, int p, int r) {

        if(p < r) {
             int q = (int) Math.floor((p + r) / 2.0);
             mergeSortHelp(a, p, q);
             mergeSortHelp(a, q + 1, r);
             merge(a, p, q, r);
        }

    }

    public static void merge(int[] a, int p, int q, int r) {

        int n1 = q - p + 1;
        int n2 = r - q;

        int[] left = new int[n1];
        int[] right = new int[n2];

        for(int i = 0; i < n1; i++) {
            left[i] = a[p + i];
        }

        for(int j = 0; j < n2; j++) {
            right[j] = a[q + j + 1];
        }

        int i = 0;
        int j = 0;

        int k = p;

        while(i < left.length && j < right.length && k <= r) {
            if(left[i] <= right[j]) {
                a[k] = left[i];
                i++;
            } else {
                a[k] = right[j];
                j++;
            }
            k++;
        }

        while(i < left.length) {
            a[k] = left[i];
            i++;
            k++;
        }

        while(j < right.length) {
            a[k] = right[j];
            j++;
            k++;
        }

    }

    public static void mergeSortNew(int[] a) {
        mergeSortNewHelper(a, 0, a.length - 1);
    }

    public static void mergeSortNewHelper(int[] a, int p, int r) { //p = StartIndex im Array, r = Endindex Im array

        if(p < r) {
            int x = (int) Math.round((r - p + 1) / 3.0);
            int q1 = x - 1 + p;
            int q2 = q1 + x;

            mergeSortNewHelper(a, p, q1);
            mergeSortNewHelper(a, q1 + 1, q2);
            mergeSortNewHelper(a, q2 + 1, r);

            mergeNew(a, p, q1, q2, r);
        }
    }

    public static void mergeNew(int[] a, int p, int q1, int q2, int r) { //Sollte funktionieren

        int n1 = q1 - p + 1;
        int n2 = q2 - q1;
        int n3 = r - q2;

        int[] left = new int[n1 + 1];
        int[] middle = new int[n2 + 1];
        int[] right = new int[n3 + 1];

        for(int i = 0; i < n1; i++) {
            left[i] = a[p + i];
        }

        left[left.length - 1] = Integer.MAX_VALUE;

        for(int i = 0; i < n2; i++) {
            middle[i] = a[q1 + 1 + i];
        }

        middle[middle.length - 1] = Integer.MAX_VALUE;

        for(int i = 0; i < n3; i++) {
            right[i] = a[q2 + 1 + i];
        }

        right[right.length - 1] = Integer.MAX_VALUE;

        int leftPointer = 0;
        int middlePointer = 0;
        int rightPointer = 0;

        for(int k = p; k <= r; k++) {
            if(left[leftPointer] <= middle[middlePointer] && left[leftPointer] <= right[rightPointer]) {
                a[k] = left[leftPointer++];
            } else if(middle[middlePointer] <= right[rightPointer] && middle[middlePointer] <= left[leftPointer]) {
                a[k] = middle[middlePointer++];
            } else {
                a[k] = right[rightPointer++];
            }
        }
    }

    public static <T extends Comparable<T>> void mergeSortGen(T[] a) {
        mergeSortGenHelp(a, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> void mergeSortGenHelp(T[] a, int p, int r) {

        if(p < r) {
            int q = (int) Math.floor((p + r) / 2.0);
            mergeSortGenHelp(a, p, q);
            mergeSortGenHelp(a, q + 1, r);
            mergeGen(a, p, q, r);
        }

    }

    public static <T extends Comparable<T>> void mergeGen(T[] a, int p, int q, int r) {

        int n1 = q - p + 1;
        int n2 = r - q;

        List<T> left = new ArrayList<>(n1);
        List<T> right = new ArrayList<>(n2);

        for(int i = 0; i < n1; i++) {
            left.add(i, a[p + i]);
        }

        for(int j = 0; j < n2; j++) {
            right.add(j, a[q + j + 1]);
        }

        int i = 0;
        int j = 0;

        int k = p;

        while(i < left.size() && j < right.size() && k <= r) {
            if(left.get(i).compareTo(right.get(j)) <= 0) {
                a[k] = left.get(i);
                i++;
            } else {
                a[k] = right.get(j);
                j++;
            }
            k++;
        }

        while(i < left.size()) {
            a[k] = left.get(i);
            i++;
            k++;
        }

        while(j < right.size()) {
            a[k] = right.get(j);
            j++;
            k++;
        }
    }

    public static long testSort(int[] a, String algorithm, int runs) { //array, welcher Sortieralgorithmus, wie oft ausführen -> gemittelte Zeit

        long sum = 0;

        for(int i = 1; i <= runs; i++) {

            int[] arrayCopy = Arrays.copyOf(a, a.length);

            if(algorithm.equalsIgnoreCase("insertionSort")) {
                long start = System.nanoTime();
                insertionSort(arrayCopy);
                long end = System.nanoTime();
                sum = sum + ((end - start) / runs);
            } else if(algorithm.equalsIgnoreCase("mergeSort")) {
                long start = System.nanoTime();
                mergeSort(arrayCopy);
                long end = System.nanoTime();
                sum = sum + ((end - start) / runs);
            } else if(algorithm.equalsIgnoreCase("mergeSortNew")) {
                long start = System.nanoTime();
                mergeSortNew(arrayCopy);
                long end = System.nanoTime();
                sum = sum + ((end - start) / runs);
            }
        }

        return sum;
    }

    private static int maxProfit1(int[] a) {
        int maxProfit = 0;
        for(int i = 0; i < a.length; i++) {
            for(int j = i + 1; j < a.length; j++) {
                if(a[j] - a[i] > maxProfit) {
                    maxProfit = a[j] - a[i];
                }
            }
        }
        return maxProfit;
    }

    private static int maxProfit2(int[] a) {
        return trade(a, 0, a.length - 1);
    }

    private static int trade(int[] a, int p, int r) {
        if(p == r) {
            return 0;
        } else {
            int q = (int) Math.floor((p + r) / 2.0);
            int t1 = trade(a, p, q); //maxLeft
            int t2 = trade(a, q + 1, r); //maxRight
            return maxWin(a, p, r, t1, t2); //maxBetween
        }
    }

    private static int maxWin(int[] a, int p, int r, int t1, int t2) {

        int q = (int) Math.floor((p + r) / 2.0);

        int minLeft = Integer.MAX_VALUE;

        for(int i = p; i <= q; i++) {
            if(a[i] < minLeft) {
                minLeft = a[i];
            }
        }

        int maxRight = Integer.MIN_VALUE;

        for(int j = q + 1; j <= r; j++) {
            if(a[j] > maxRight) {
                maxRight = a[j];
            }
        }

        int t3 = maxRight - minLeft;

        return Math.max(Math.max(t1, t2), t3);
    }


    public static void main(String... args) {

        int[] test1 = createSequenceRand(20);


        System.out.println(Arrays.toString(test1));
        //int[] test1 = new int[]{6, 6, 9, 9, 4, 7, 7, 7, 8, 7};

        System.out.println(maxProfit1(test1));
        System.out.println(maxProfit2(test1));

//        System.out.println(Arrays.toString(createSequenceRand(8)));
//
//        int[] arr100asc = createSequenceInc(100);
//        int[] arr1000asc = createSequenceInc(1000);
//        int[] arr10000asc = createSequenceInc(10000);
//        int[] arr100000asc = createSequenceInc(100000);
//        int[] arr200000asc = createSequenceInc(200000);
//
//        int[] arr100desc = createSequenceDec(100);
//        int[] arr1000desc = createSequenceDec(1000);
//        int[] arr10000desc = createSequenceDec(10000);
//        int[] arr100000desc = createSequenceDec(100000);
//        int[] arr200000desc = createSequenceDec(200000);
//
//        System.out.println("InsertionSort: ");
//
//        System.out.println("Ascending:");
//
//        System.out.println(testSort(arr100asc, "insertionSort", 10));
//        System.out.println(testSort(arr1000asc, "insertionSort", 10));
//        System.out.println(testSort(arr10000asc, "insertionSort", 10));
//        System.out.println(testSort(arr100000asc, "insertionSort", 10));
//        System.out.println(testSort(arr200000asc, "insertionSort", 10));
//
//        System.out.println("Descending:");
//
//        System.out.println(testSort(arr100desc, "insertionSort", 10));
//        System.out.println(testSort(arr1000desc, "insertionSort", 10));
//        System.out.println(testSort(arr10000desc, "insertionSort", 10));
//        System.out.println(testSort(arr100000desc, "insertionSort", 10));
//        System.out.println(testSort(arr200000desc, "insertionSort", 10));
//
//        System.out.println("-------------------------------");
//
//        System.out.println("MergeSort:");
//
//        System.out.println("Ascending:");
//
//        System.out.println(testSort(arr100asc, "mergeSort", 10));
//        System.out.println(testSort(arr1000asc, "mergeSort", 10));
//        System.out.println(testSort(arr10000asc, "mergeSort", 10));
//        System.out.println(testSort(arr100000asc, "mergeSort", 10));
//        System.out.println(testSort(arr200000asc, "mergeSort", 10));
//
//        System.out.println("Descending:");
//
//        System.out.println(testSort(arr100desc, "mergeSort", 10));
//        System.out.println(testSort(arr1000desc, "mergeSort", 10));
//        System.out.println(testSort(arr10000desc, "mergeSort", 10));
//        System.out.println(testSort(arr100000desc, "mergeSort", 10));
//        System.out.println(testSort(arr200000desc, "mergeSort", 10));
//
//        System.out.println("-----------------------------");
//
//        System.out.println("MergeSortNew:");
//
//        System.out.println("Ascending:");
//
//        System.out.println(testSort(arr100asc, "mergeSortNew", 10));
//        System.out.println(testSort(arr1000asc, "mergeSortNew", 10));
//        System.out.println(testSort(arr10000asc, "mergeSortNew", 10));
//        System.out.println(testSort(arr100000asc, "mergeSortNew", 10));
//        System.out.println(testSort(arr200000asc, "mergeSortNew", 10));
//
//        System.out.println("Descending:");
//
//        System.out.println(testSort(arr100desc, "mergeSortNew", 10));
//        System.out.println(testSort(arr1000desc, "mergeSortNew", 10));
//        System.out.println(testSort(arr10000desc, "mergeSortNew", 10));
//        System.out.println(testSort(arr100000desc, "mergeSortNew", 10));
//        System.out.println(testSort(arr200000desc, "mergeSortNew", 10));

        /*
        InsertionSort:
        Ascending:
        3616
        34629
        223148
        271995
        112095
        Descending:
        54963
        549447
        39341416
        3934231649
        16166463582
        -------------------------------
        MergeSort:
        Ascending:
        79521
        85879
        654991
        3621491
        6157021
        Descending:
        40788
        69725
        757384
        3809321
        7402262
        -----------------------------
        MergeSortNew:
        Ascending:
        49280
        62445
        661521
        3161738
        5962596
        Descending:
        2075
        23733
        271570
        3099974
        5767329
         */
    }

}
