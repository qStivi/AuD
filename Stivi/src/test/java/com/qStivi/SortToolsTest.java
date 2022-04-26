package com.qStivi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortToolsTest {

    @Test
    void createSequenceInc() {
        var arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33};
        var arr2 = SortTools.createSequenceInc(33);
        assertArrayEquals(arr, arr2);
    }

    @Test
    void createSequenceDec() {
        var arr = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        var arr2 = SortTools.createSequenceDec(10);
        assertArrayEquals(arr, arr2);
    }

    @Test
    void createSequenceAlt() {
        var arr = new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1};
        var arr2 = SortTools.createSequenceAlt(11);
        assertArrayEquals(arr, arr2);
    }

    @Test
    void insertionSort() {
        var arr = SortTools.createSequenceDec(33);
        SortTools.insertionSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33}, arr);
    }

    @Test
    void insertionSortGen() {
        var arr = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        var arr2 = new Float[]{.9f, .8f, .7f, .6f, .5f, .4f, .3f, .2f, .1f};

        SortTools.insertionSortGen(arr);
        SortTools.insertionSortGen(arr2);

        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, arr);
        assertArrayEquals(new Float[]{.1f, .2f, .3f, .4f, .5f, .6f, .7f, .8f, .9f}, arr2);
    }

    @Test
    void bubbleSort() {
        var arr = SortTools.createSequenceDec(33);
        SortTools.bubbleSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33}, arr);
    }

    @Test
    void bubbleSortNew() {
        var arr = SortTools.createSequenceDec(33);
        SortTools.bubbleSortNew(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33}, arr);
    }

    @Test
    void bubbleSortGen() {
        var arr = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        var arr2 = new Float[]{.9f, .8f, .7f, .6f, .5f, .4f, .3f, .2f, .1f};

        SortTools.bubbleSortGen(arr);
        SortTools.bubbleSortGen(arr2);

        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, arr);
        assertArrayEquals(new Float[]{.1f, .2f, .3f, .4f, .5f, .6f, .7f, .8f, .9f}, arr2);
    }
}
