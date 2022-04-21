package andi.Zettel1.Aufgabe1;

public class SortTools {

    /*
    Messungen:
    1. 8188034400 ns
    2. 8067841700 ns
    3. 8063164300 ns
    4. 8137629900 ns
    5. 8055539600 ns
    6. 8091639200 ns
    7. 8043909500 ns
    8. 8053188900 ns
    9. 8039996500 ns
    10. 8024955900 ns
     */

    public static void main(String[] args) {
        final long timeStart = System.nanoTime();

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
