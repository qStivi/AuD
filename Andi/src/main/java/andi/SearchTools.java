package andi;

import java.util.Random;

public class SearchTools {

    public static void main(String[] args) {
        /*
        _________________________________________________________________________________
        LinSearch:
        47732
        361856
        49228703
        325654728
        _________________________________________________________________________________
        BinSearch:
        978
        917
        2100
        2122
        _________________________________________________________________________________
        BinSearchNew:
        1411
        926
        2197
        2398
         */

        int[] arr100000 = SortTools.createSequenceInc(100000);
        int[] arr1000000 = SortTools.createSequenceInc(1000000);
        int[] arr100000000 = SortTools.createSequenceInc(100000000);
        int[] arr685154321 = SortTools.createSequenceInc(685154321);

        long start = 0;
        long end = 0;
        long result = 0;
        System.out.println("_________________________________________________________________________________");
        System.out.println("LinSearch:");
        for (int i = 1; i <= 500; i++) {
            int r = new Random().nextInt(1, arr100000.length);
            start = System.nanoTime();
            linSearch(arr100000, r);
            linSearch(arr100000, -5);
            end = System.nanoTime();
            result += (end - start);
        }
        result /= 500;
        System.out.println(result);
        result = 0;

        for (int i = 1; i <= 500; i++) {
            int r = new Random().nextInt(1, arr1000000.length);
            start = System.nanoTime();
            linSearch(arr1000000, r);
            linSearch(arr1000000, -5);
            end = System.nanoTime();
            result += (end - start) ;
        }
        result /= 500;
        System.out.println(result);
        result = 0;

        for (int i = 1; i <= 500; i++) {
            start = System.nanoTime();
            linSearch(arr100000000, new Random().nextInt(1, arr100000000.length));
            linSearch(arr100000000, -5);
            end = System.nanoTime();
            result += (end - start);
        }
        result /= 500;
        System.out.println(result);
        result = 0;

        for (int i = 1; i <= 500; i++) {
            int r = new Random().nextInt(1, arr685154321.length);
            start = System.nanoTime();
            linSearch(arr685154321, r);
            linSearch(arr685154321, -5);
            end = System.nanoTime();
            result += (end - start);
        }
        result /= 500;
        System.out.println(result);
        result = 0;

        System.out.println("_________________________________________________________________________________");
        System.out.println("BinSearch:");

        for (int i = 1; i <= 500; i++) {
            int r = new Random().nextInt(1, arr100000.length);
            start = System.nanoTime();
            binSearch(arr100000, r, 0, arr100000.length - 1);
            binSearch(arr100000, -5, 0, arr100000.length - 1);
            end = System.nanoTime();
            result += (end - start);
        }
        result /= 500;
        System.out.println(result);
        result = 0;

        for (int i = 1; i <= 500; i++) {
            int r = new Random().nextInt(1, arr1000000.length);
            start = System.nanoTime();
            binSearch(arr1000000, r, 0, arr1000000.length - 1);
            binSearch(arr1000000, -5, 0, arr1000000.length - 1);
            end = System.nanoTime();
            result += (end - start);
        }
        result /= 500;
        System.out.println(result);
        result = 0;

        for (int i = 1; i <= 500; i++) {
            int r = new Random().nextInt(1, arr100000000.length);
            start = System.nanoTime();
            binSearch(arr100000000, r, 0, arr100000000.length - 1);
            binSearch(arr100000000, -5, 0, arr100000000.length - 1);
            end = System.nanoTime();
            result += (end - start);
        }
        result /= 500;
        System.out.println(result);
        result = 0;

        for (int i = 1; i <= 500; i++) {
            int r = new Random().nextInt(1, arr685154321.length);
            start = System.nanoTime();
            binSearch(arr685154321, r, 0, arr685154321.length - 1);
            binSearch(arr685154321, -5, 0, arr685154321.length - 1);
            end = System.nanoTime();
            result += (end - start);
        }
        result /= 500;
        System.out.println(result);
        result = 0;

        System.out.println("_________________________________________________________________________________");
        System.out.println("BinSearchNew:");

        for (int i = 1; i <= 500; i++) {
            int r = new Random().nextInt(1, arr100000.length);
            start = System.nanoTime();
            binSearchNew(arr100000, r, 0, arr100000.length - 1);
            binSearchNew(arr100000, -5, 0, arr100000.length - 1);
            end = System.nanoTime();
            result += (end - start);
        }
        result /= 500;
        System.out.println(result);
        result = 0;

        for (int i = 1; i <= 500; i++) {
            int r = new Random().nextInt(1, arr1000000.length);
            start = System.nanoTime();
            binSearchNew(arr1000000, r, 0, arr1000000.length - 1);
            binSearchNew(arr1000000, -5, 0, arr1000000.length - 1);
            end = System.nanoTime();
            result += (end - start);
        }
        result /= 500;
        System.out.println(result);
        result = 0;

        for (int i = 1; i <= 500; i++) {
            int r = new Random().nextInt(1, arr100000000.length);
            start = System.nanoTime();
            binSearchNew(arr100000000, r, 0, arr100000000.length - 1);
            binSearchNew(arr100000000, -5, 0, arr100000000.length - 1);
            end = System.nanoTime();
            result += (end - start);
        }
        result /= 500;
        System.out.println(result);
        result = 0;

        for (int i = 1; i <= 500; i++) {
            int r = new Random().nextInt(1, arr685154321.length);
            start = System.nanoTime();
            binSearchNew(arr685154321, r, 0, arr685154321.length - 1);
            binSearchNew(arr685154321, -5, 0, arr685154321.length - 1);
            end = System.nanoTime();
            result += (end - start);
        }
        result /= 500;
        System.out.println(result);
        result = 0;

    }

    public static int linSearch(int[] A, int x) {
        for (int i = 0; i < A.length; i++) {
            if (x == A[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int binSearch(int[] A, int x, int l, int r) {
        if (l < r) {
            int pivot = (l + r) / 2;
            if (x == A[pivot]) {
                return pivot;
            } else if (x < A[pivot]) {
                return binSearch(A, x, l, pivot);
            } else {
                return binSearch(A, x, pivot + 1, r);
            }
        }
        return -1;
    }

    public static int binSearchNew(int[] A, int x, int l, int r) {
        if (l < r) {
            int m = (int) Math.round((r - l + 1) / 3.0);
            int pivot1 = m - 1 + l;
            int pivot2 = pivot1 + m;

            if (x == A[pivot1]) {
                return pivot1;
            } else if (x == A[pivot2]) {
                return pivot2;
            } else if (x < A[pivot1]) {
                return binSearchNew(A, x, l, pivot1);
            } else if (x < A[pivot2]) {
                return binSearchNew(A, x, pivot1 + 1, pivot2);
            } else {
                return binSearchNew(A, x, pivot2 + 1, r);
            }
        }
        return -1;
    }
}
