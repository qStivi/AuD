package com.qStivi;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class SortToolsTest {

    @Test
    void createSequenceInc() {
        var arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        var arr2 = SortTools.createSequenceInc(10);
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
        var arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        var arr2 = SortTools.createSequenceInc(10);
        assertArrayEquals(arr, arr2);
    }

    @Test
    void insertionSort() {
        var arr = SortTools.createSequenceInc(10);
        SortTools.insertionSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, arr);
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
}
