package andi.Zettel1.Aufgabe1;

import java.util.Arrays;

public class SortTools {

    public static void main(String[] args) {
        /*final long timeStart = System.nanoTime();

        int[] arr1 = createSequenceDec(100);
        int[] arr2 = createSequenceDec(1000);
        int[] arr3 = createSequenceDec(10000);
        int[] arr4 = createSequenceDec(100000);
        int[] arr5 = createSequenceDec(200000);

        insertionSort(arr1);
        insertionSort(arr2);
        insertionSort(arr3);
        insertionSort(arr4);
        insertionSort(arr5);

        final long timeEnd = System.nanoTime();
        System.out.println((timeEnd - timeStart) + " ns");
        */

        int[] arr1 = createSequenceRand(100);
        int[] arr2 = createSequenceRand(100);
        bubbleSortNew(arr2);
        bubbleSort(arr1);

        for (int ele : arr2) {
            System.out.println(ele);
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
                System.arraycopy(a, j, temp, 0,  11);
                insertionSort(temp);
                System.arraycopy(temp, 0, a, j,  11);
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
