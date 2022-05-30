package com.qStivi;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SortToolsTest {

    // region sequence generators

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

    // endregion

    // region insertion-sort

    @Test
    void insertionSort() {
        var arr = SortTools.createSequenceDec(33);
        SortTools.insertionSort(arr);
        assertArrayEquals(SortTools.createSequenceInc(33), arr);
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

    // endregion

    // region bubble-sort

    @Test
    void bubbleSort() {
        var arr = SortTools.createSequenceDec(33);
        SortTools.bubbleSort(arr);
        assertArrayEquals(SortTools.createSequenceInc(33), arr);
    }

    @Test
    void bubbleSortNew() {
        var arr = SortTools.createSequenceDec(33);
        SortTools.bubbleSortNew(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33}, arr);
        var arr2 = SortTools.createSequenceDec(10);
        SortTools.bubbleSortNew(arr2);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, arr2);
        var arr3 = SortTools.createSequenceDec(3);
        SortTools.bubbleSortNew(arr3);
        assertArrayEquals(new int[]{1, 2, 3}, arr3);
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

    // endregion

    // region merge-sort

    @Test
    void mergeSort() {
        var arr = SortTools.createSequenceDec(33);
        SortTools.mergeSort(arr);
        assertArrayEquals(SortTools.createSequenceInc(33), arr);
    }

    @Test
    void mergeSortNew() {
        var arr = SortTools.createSequenceDec(33);
        SortTools.mergeSortNew(arr);
        assertArrayEquals(SortTools.createSequenceInc(33), arr);
    }

    @Test
    void mergeSortGen() {
        var arr = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        var arr2 = new Float[]{.9f, .8f, .7f, .6f, .5f, .4f, .3f, .2f, .1f};

        SortTools.mergeSortGen(arr);
        SortTools.mergeSortGen(arr2);

        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, arr);
        assertArrayEquals(new Float[]{.1f, .2f, .3f, .4f, .5f, .6f, .7f, .8f, .9f}, arr2);
    }

    // endregion

    // region quick-sort

    @Test
    void quickSort() {
        var arr = SortTools.createSequenceDec(33);
        SortTools.quickSort(arr);
        assertArrayEquals(SortTools.createSequenceInc(33), arr);
    }

    @RepeatedTest(100)
    void quickSortRandom() {
        var arr = SortTools.createSequenceDec(33);
        SortTools.quickSortRandom(arr);
        assertArrayEquals(SortTools.createSequenceInc(33), arr);
    }

    @RepeatedTest(100)
    void quickSortNewRandom() {
        var arr = SortTools.createSequenceDec(33);
        SortTools.quickSortNewRandom(arr);
        assertArrayEquals(SortTools.createSequenceInc(33), arr);
    }

    @RepeatedTest(100)
    void quickSortTriRandom() {
        var arr = SortTools.createSequenceDec(33);
        SortTools.quickSortTriRandom(arr);
        assertArrayEquals(SortTools.createSequenceInc(33), arr);
    }

    @RepeatedTest(100)
    void quickSortTriNewRandom() {
        var arr = SortTools.createSequenceDec(33);
        SortTools.quickSortTriNewRandom(arr);
        assertArrayEquals(SortTools.createSequenceInc(33), arr);
    }

    // endregion

    @Test
    void getMiddleValueTest() {
        assertEquals(2, SortTools.getMiddleValue(1, 2, 3));
        assertEquals(2, SortTools.getMiddleValue(-1, 2, 3));

        assertEquals(2, SortTools.getMiddleValue(2, 2, 3));
        assertEquals(2, SortTools.getMiddleValue(2, 3, 2));
        assertEquals(2, SortTools.getMiddleValue(3, 2, 2));
        assertEquals(2, SortTools.getMiddleValue(1, 2, 2));
        assertEquals(2, SortTools.getMiddleValue(2, 1, 2));
        assertEquals(2, SortTools.getMiddleValue(2, 2, 1));

        assertEquals(2, SortTools.getMiddleValue(2, 1, 3));
        assertEquals(2, SortTools.getMiddleValue(2, 3, 1));
        assertEquals(2, SortTools.getMiddleValue(3, 1, 2));
        assertEquals(2, SortTools.getMiddleValue(1, 3, 2));
    }
}
