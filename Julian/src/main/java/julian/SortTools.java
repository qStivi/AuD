package julian;

import java.util.*;
import java.util.List;

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

    public static void quicksort(int[] A) {
        quicksortHelp(A, 0, A.length - 1);
    }

    public static void quicksortRandom(int[] A) {
        quicksortRandomHelp(A, 0, A.length - 1);
    }

    public static void quicksortRandomNew(int[] A) {
        quicksortRandomNewHelp(A, 0, A.length - 1);
    }

    public static void quicksortHelp(int[] A, int l, int r) {
        if(l < r) {
            int q = partition(A, l, r);
            quicksortHelp(A, l, q - 1);
            quicksortHelp(A, q + 1, r);
        }
    }

    public static void quicksortRandomHelp(int[] A, int l, int r) {
        if(l < r) {
            int q = partitionRandom(A, l, r);
            quicksortRandomHelp(A, l, q - 1);
            quicksortRandomHelp(A, q + 1, r);
        }
    }

    public static void quicksortRandomNewHelp(int[] A, int l, int r) {
        if(l < r) {
            int q = partitionRandomNew(A, l, r);
            quicksortRandomNewHelp(A, l, q - 1);
            quicksortRandomNewHelp(A, q + 1, r);
        }
    }

    public static int partition(int[] A, int l, int r) {
        int x = A[l];

        int i = r + 1;

        for(int j = r; j > l; j--) {
            if(A[j] >= x) {
                i = i - 1;
                swap(A, i, j);
            }
        }
        swap(A, i - 1, l);
        return i - 1;
    }

    public static int partitionRandom(int[] A, int l, int r) {
        Random random = new Random();
        int pos = random.nextInt(l, r + 1);
        int x = A[pos];

        swap(A, pos, r);

        int i = l - 1;

        for(int j = l; j < r; j++) {
            if(A[j] <= x) {
                i = i + 1;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, r);
        return i + 1;
    }

    public static int selectMiddle(int x1, int x2, int x3) {

        if (x1 > x2) {
            if (x2 > x3) {
                return x2;
            } else return Math.min(x1, x3);
        } else {
            if (x1 > x3) {
                return x1;
            } else return Math.min(x2, x3);
        }

    }

    public static int partitionRandomNew(int[] A, int l, int r) {
        Random random = new Random();
        int pos1 = random.nextInt(l, r + 1);
        int pos2 = random.nextInt(l, r + 1);
        int pos3 = random.nextInt(l, r + 1);

        int x = selectMiddle(A[pos1], A[pos2], A[pos3]);
        int pos;

        if(x == A[pos1]) {
            pos = pos1;
        } else if(x == A[pos2]) {
            pos = pos2;
        } else {
            pos = pos3;
        }

        swap(A, pos, r);

        int i = l - 1;

        for(int j = l; j < r; j++) {
            if(A[j] <= x) {
                i = i + 1;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, r);
        return i + 1;
    }

    public static int[] partitionArray(int[] A, int q) { //x1 - x1 + q, x2 - x2 + q, x3 - x3 + q (xi bis xq)

        int countSequences = (int) Math.ceil((double) A.length / q);

        int[] startIndices = new int[countSequences];

        for(int i = 0; i < countSequences; i++) {
            startIndices[i] = i * q;
        }

        return startIndices;
    }

    public static int selection(int[] A, int k, int q) { // q = 3
        if(A.length <= q) {
            quicksort(A);
            return A[k - 1];
        }

        int[] startIndices = partitionArray(A, q);
        int[] medians = new int[startIndices.length];

        for(int i = 0; i < startIndices.length; i++) {

            if(i != startIndices.length - 1) {
                quicksortRandomHelp(A, startIndices[i], startIndices[i] + q - 1);

                medians[i] = A[startIndices[i] + (int) Math.floor((q / 2.0))]; //Floor oder ceil?

            } else {
                quicksortRandomHelp(A, startIndices[i], A.length - 1);

                medians[i] = A[startIndices[i] + (int) Math.floor(((A.length - startIndices[i]) / 2.0))];

            }
        }

        int m = selection(medians, (int) Math.ceil(medians.length / (2.0 * q)), q);

        List<Integer> A1 = new ArrayList<>();
        List<Integer> A2 = new ArrayList<>();
        List<Integer> A3 = new ArrayList<>();

        for(int i = 0; i < A.length; i++) {
            if(A[i] < m) {
                A1.add(A[i]);
            } else if(A[i] == m) {
                A2.add(A[i]);
            } else {
                A3.add(A[i]);
            }
        }

        if(A1.size() >= k) {

            int[] A1arr = new int[A1.size()];
            for(int i = 0; i < A1arr.length; i++) {
                A1arr[i] = A1.get(i);
            }
            return selection(A1arr, k, q);

        } else if(A1.size() + A2.size() >= k) {
            return m;
        } else {

            int[] A3arr = new int[A3.size()];
            for(int i = 0; i < A3arr.length; i++) {
                A3arr[i] = A3.get(i);
            }

            return selection(A3arr, k - (A1.size() + A2.size()), q);
        }


    }

    public static void quickSortTriRandom(int[] A, int l, int r) {

        if(r - l >= 1) {
            int[] q = partitionTriRandom(A, l, r);
            quickSortTriRandom(A, l, q[0] - 1);
            quickSortTriRandom(A, q[0] + 1, q[1] - 1);
            quickSortTriRandom(A, q[1] + 1, r);
        }

    }

    public static int partitionByX(int[] A, int x, int xIndex, int l, int r) {

        swap(A, xIndex, r);

        int i = l - 1;

        for(int j = l; j < r; j++) {
            if(A[j] <= x) {
                i = i + 1;
                swap(A, i, j);
            }
        }

        swap(A, i + 1, r);

        return i + 1;
    }

    public static int partitionByXNew(int[] A, int x, int l, int r) {

        List<Integer> smaller = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> bigger = new ArrayList<>();

        for(int i = l; i <= r; i++) {
            if(A[i] < x) {
                smaller.add(A[i]);
            } else if(A[i] > x) {
                bigger.add(A[i]);
            } else {
                equal.add(A[i]);
            }
        }

        //Alles vor l bleibt, alles nach r bleibt;

        int leftPointer = 0;
        int middlePointer = 0;
        int rightPointer = 0;
        int insertXAt = 0;

        for(int i = l; i <= r && leftPointer < smaller.size(); i++) {
            A[i] = smaller.get(leftPointer++);
        }

        for(int i = l + smaller.size(); middlePointer < equal.size() && i <= r; i++) {
            A[i] = equal.get(middlePointer++);
            insertXAt = i;
        }

        for(int i = l + smaller.size() + equal.size(); rightPointer < bigger.size() && i <= r; i++) {
            A[i] = bigger.get(rightPointer++);
        }

        return insertXAt;
    }


    public static int[] partitionTriRandom(int[] A, int l, int r) { //2 zufällige Elemente

        Random random = new Random();
        int[] pivots = new int[2];

        int first = random.nextInt(l, r + 1);
        int x1 = A[first];
        pivots[0] = partitionByX(A, x1, first, l, r);

        int second = random.nextInt(pivots[0], r + 1);
        int x2 = A[second];
        pivots[1] = partitionByX(A, x2, second, pivots[0], r);

        return pivots;
    }

    public static void quickSortTriNewRandom(int[] A, int l, int r) {

        if(r - l + 1 < 5) {
            quickSortTriRandom(A, l, r);
        } else {
            int[] q = partitionTriNewRandom(A, l, r);
            quickSortTriRandom(A, l, q[0] - 1);
            quickSortTriRandom(A, q[0] + 1, q[1] - 1);
            quickSortTriRandom(A, q[1] + 1, r);
        }

    }

    public static int[] partitionTriNewRandom(int[] A, int l, int r) {

        Random random = new Random();
        int[] pivots = new int[2];
        int[] randElements = new int[5];

        for(int i = 0; i < 5; i++) {
            int index = random.nextInt(l, r + 1);
            randElements[i] = A[index];
        }

        quickSortTriRandom(randElements, 0, 4);

        int x1 = randElements[1];
        pivots[0] = partitionByXNew(A, x1, l, r);

        int x2 = randElements[3];
        pivots[1] = partitionByXNew(A, x2, pivots[0], r);

        return pivots;
    }

    public static int[] getKBiggestElements(int[] A, int k) {

        int n = A.length;

        if(k > n) {
            k = n;
        }

        if(k < 1) {
            throw new IllegalArgumentException();
        }

        int[] output = new int[k];
        int j = 0;

        int x = selection(A, n - k + 1, 3);

        for(int i = 0; i < n; i++) {
            if(A[i] >= x) {
                output[j] = A[i];
                j++;
            }
        }

        Arrays.sort(output);
        return output;
    }

    public static int[] getKElementsLowestDistanceMedian(int[] A, int k) {

        int m = selection(A, (int) Math.ceil(A.length / 2.0), 3);

        Tuple[] distanceValue = new Tuple[A.length - 1];
        int[] distances = new int[A.length - 1];
        
        int mIndex = -1;

        for(int i = 0; i < A.length; i++) {
            if(A[i] == m) {
                mIndex = i;
            }
        }
        
        swap(A, mIndex, A.length - 1);
        
        int[] Anew = new int[A.length - 1];
        
        for(int i = 0; i < Anew.length; i++) {
            Anew[i] = A[i];
        }

        for(int i = 0; i < Anew.length; i++) {
            int abs = Math.abs(Anew[i] - m);
            distanceValue[i] = new Tuple(abs, Anew[i]);
            distances[i] = abs;
        }

        int bound = selection(distances, k, 3);

        int[] result = new int[k];
        int j = 0;

        for(int i = 0; i < distanceValue.length; i++) {
            if(j < k && distanceValue[i].getDistance() <= bound) {
                result[j++] = distanceValue[i].getValue();
            }
        }

        return result;

    }



    public static void main(String... args) {
    }

}
