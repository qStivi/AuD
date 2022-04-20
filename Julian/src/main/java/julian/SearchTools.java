package julian;

import java.util.Random;

public class SearchTools {

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

    public static void insertionSort(int[] a) {

        for (int j = 1; j <= a.length - 1; j++) {

            int s = a[j];
            int i = j - 1;

            while (i >= 0 && a[i] > s) {
                a[i + 1] = a[i];
                i = i - 1;
            }

            a[i + 1] = s;
        }

    }

    public static <T extends Comparable<T>> void insertionSortGen(T[] a) {

        for (int j = 1; j <= a.length - 1; j++) {

            T s = a[j];
            int i = j - 1;

            while (i >= 0 && a[i].compareTo(s) > 0) { // > s
                a[i + 1] = a[i];
                i = i - 1;
            }

            a[i + 1] = s;
        }

    }

    public static void main(String... args) {

        final int RUNS = 10;

        long average1 = 0;
        long average2 = 0;
        long average3 = 0;
        long average4 = 0;
        long average5 = 0;

        for (int i = 0; i < RUNS; i++) {
            long start = System.nanoTime();
            insertionSort(createSequenceDec(100));
            long end = System.nanoTime();
            long time = end - start;
            average1 = average1 + time;
        }

        for (int i = 0; i < RUNS; i++) {
            long start = System.nanoTime();
            insertionSort(createSequenceDec(1000));
            long end = System.nanoTime();
            long time = end - start;
            average2 = average2 + time;
        }

        for (int i = 0; i < RUNS; i++) {
            long start = System.nanoTime();
            insertionSort(createSequenceDec(10000));
            long end = System.nanoTime();
            long time = end - start;
            average3 = average3 + time;
        }

        for (int i = 0; i < RUNS; i++) {
            long start = System.nanoTime();
            insertionSort(createSequenceDec(100000));
            long end = System.nanoTime();
            long time = end - start;
            average4 = average4 + time;
        }

        for (int i = 0; i < RUNS; i++) {
            long start = System.nanoTime();
            insertionSort(createSequenceDec(200000));
            long end = System.nanoTime();
            long time = end - start;
            average5 = average5 + time;
        }

        average1 = average1 / RUNS;
        System.out.println("Average 100: " + average1);

        average2 = average2 / RUNS;
        System.out.println("Average 1000: " + average2);

        average3 = average3 / RUNS;
        System.out.println("Average 10000: " + average3);

        average4 = average4 / RUNS;
        System.out.println("Average 100000: " + average4);

        average5 = average5 / RUNS;
        System.out.println("Average 200000: " + average5);

    }

}
