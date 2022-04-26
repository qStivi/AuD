package julian;

import java.util.Arrays;
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

    public static long doSomething(int[] a) {
        long x = -1;
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a.length; j++) {
                if(i != j) {
                    if((long) a[i] * a[j] > x) {
                        x = (long) a[i] * a[j];
                    }
                }
            }
        }
        return x;
    }

    public static long doSomething2(int[] a) {
        long x = -1;
        int xIndex = -1;
        long y = -1;

        for(int i = 0; i < a.length; i++) {
            if(a[i] > x) {
                x = a[i];
                xIndex = i;
            }
        }

        for(int j = 0; j < a.length; j++) {
            if(a[j] > y && j != xIndex) {
                y = a[j];
            }
        }

        return x * y;
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

    public static void main(String... args) {

        final int runs = 10;

        int arr100[] = createSequenceRand(100);
        int arr1000[] = createSequenceRand(1000);
        int arr10000[] = createSequenceRand(10000);
        int arr100000[] = createSequenceRand(100000);

        long time100averageInsertionSort = 0;
        long time1000averageInsertionSort = 0;
        long time10000averageInsertionSort = 0;
        long time100000averageInsertionSort = 0;

        for(int i = 0; i < runs; i++) {
            int[] toSort = Arrays.copyOf(arr100, arr100.length);
            long startTime = System.nanoTime();
            insertionSort(toSort);
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            time100averageInsertionSort = time100averageInsertionSort + (time / runs);
        }

        for(int i = 0; i < runs; i++) {
            int[] toSort = Arrays.copyOf(arr1000, arr1000.length);
            long startTime = System.nanoTime();
            insertionSort(toSort);
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            time1000averageInsertionSort = time1000averageInsertionSort + (time / runs);
        }

        for(int i = 0; i < runs; i++) {
            int[] toSort = Arrays.copyOf(arr10000, arr10000.length);
            long startTime = System.nanoTime();
            insertionSort(toSort);
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            time10000averageInsertionSort = time10000averageInsertionSort + (time / runs);
        }

        for(int i = 0; i < runs; i++) {
            int[] toSort = Arrays.copyOf(arr100000, arr100000.length);
            long startTime = System.nanoTime();
            insertionSort(toSort);
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            time100000averageInsertionSort = time100000averageInsertionSort + (time / runs);
        }

        System.out.println("InsertionSort 100 items: " + time100averageInsertionSort);
        System.out.println("InsertionSort 1000 items: " + time1000averageInsertionSort);
        System.out.println("InsertionSort 10000 items: " + time10000averageInsertionSort);
        System.out.println("InsertionSort 100000 items: " + time100000averageInsertionSort);

        long time100averageBubbleSort = 0;
        long time1000averageBubbleSort = 0;
        long time10000averageBubbleSort = 0;
        long time100000averageBubbleSort = 0;

        for(int i = 0; i < runs; i++) {
            int[] toSort = Arrays.copyOf(arr100, arr100.length);
            long startTime = System.nanoTime();
            bubbleSort(toSort);
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            time100averageBubbleSort = time100averageBubbleSort + (time / runs);
        }

        for(int i = 0; i < runs; i++) {
            int[] toSort = Arrays.copyOf(arr1000, arr1000.length);
            long startTime = System.nanoTime();
            bubbleSort(toSort);
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            time1000averageBubbleSort = time1000averageBubbleSort + (time / runs);
        }

        for(int i = 0; i < runs; i++) {
            int[] toSort = Arrays.copyOf(arr10000, arr10000.length);
            long startTime = System.nanoTime();
            bubbleSort(toSort);
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            time10000averageBubbleSort = time10000averageBubbleSort + (time / runs);
        }

        for(int i = 0; i < runs; i++) {
            int[] toSort = Arrays.copyOf(arr100000, arr100000.length);
            long startTime = System.nanoTime();
            bubbleSort(toSort);
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            time100000averageBubbleSort = time100000averageBubbleSort + (time / runs);
        }

        System.out.println("BubbleSort 100 items: " + time100averageBubbleSort);
        System.out.println("BubbleSort 1000 items: " + time1000averageBubbleSort);
        System.out.println("BubbleSort 10000 items: " + time10000averageBubbleSort);
        System.out.println("BubbleSort 100000 items: " + time100000averageBubbleSort);

        long time100averageBubbleSortNew = 0;
        long time1000averageBubbleSortNew = 0;
        long time10000averageBubbleSortNew = 0;
        long time100000averageBubbleSortNew = 0;

        for(int i = 0; i < runs; i++) {
            int[] toSort = Arrays.copyOf(arr100, arr100.length);
            long startTime = System.nanoTime();
            bubbleSortNew(toSort);
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            time100averageBubbleSortNew = time100averageBubbleSortNew + (time / runs);
        }

        for(int i = 0; i < runs; i++) {
            int[] toSort = Arrays.copyOf(arr1000, arr1000.length);
            long startTime = System.nanoTime();
            bubbleSortNew(toSort);
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            time1000averageBubbleSortNew = time1000averageBubbleSortNew + (time / runs);
        }

        for(int i = 0; i < runs; i++) {
            int[] toSort = Arrays.copyOf(arr10000, arr10000.length);
            long startTime = System.nanoTime();
            bubbleSortNew(toSort);
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            time10000averageBubbleSortNew = time10000averageBubbleSortNew + (time / runs);
        }

        for(int i = 0; i < runs; i++) {
            int[] toSort = Arrays.copyOf(arr100000, arr100000.length);
            long startTime = System.nanoTime();
            bubbleSortNew(toSort);
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            time100000averageBubbleSortNew = time100000averageBubbleSortNew + (time / runs);
        }

        System.out.println("BubbleSortNew 100 items: " + time100averageBubbleSortNew);
        System.out.println("BubbleSortNew 1000 items: " + time1000averageBubbleSortNew);
        System.out.println("BubbleSortNew 10000 items: " + time10000averageBubbleSortNew);
        System.out.println("BubbleSortNew 100000 items: " + time100000averageBubbleSortNew);

        /*
        InsertionSort 100 items: 116028
        InsertionSort 1000 items: 720958
        InsertionSort 10000 items: 9176985
        InsertionSort 100000 items: 840375545

        BubbleSort 100 items: 120585
        BubbleSort 1000 items: 791184
        BubbleSort 10000 items: 45706255
        BubbleSort 100000 items: 11362504164

        BubbleSortNew 100 items: 89461
        BubbleSortNew 1000 items: 1787162
        BubbleSortNew 10000 items: 154655212
        BubbleSortNew 100000 items: 16362683363
         */

    }

}
