package com.qStivi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchToolsTest {

    @Test
    void linSearch() {
        var arr = SortTools.createSequenceDec(100);
        assertEquals(95, SearchTools.linSearch(arr, 5));
    }

    @Test
    void binSearch() {
        var arr = SortTools.createSequenceDec(100);
        assertEquals(95, SearchTools.binSearch(arr, 5, 0, arr.length - 1));
    }
}
