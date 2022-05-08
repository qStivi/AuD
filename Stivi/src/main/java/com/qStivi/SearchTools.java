package com.qStivi;

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
        SortTools.mergeSort(A);

        var i = (l + r) / 2;
        if (x == A[i]) {
            return i;
        } else if (x < A[i]) {
            binSearch(A, x, l, i);
        } else if (x > A[i]) {
            binSearch(A, x, i + 1, r);
        }

        return -1;
    }

}
