package com.qStivi;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

@SuppressWarnings("all") // :(
public class OpenHashmapForStrings {

    private final static String nulls = "nulls";
    private final static String deleteds = "deleteds";
    private static final ArrayList<String> OfficialScrabbleWordListGerman = new ArrayList<>();
    private static final ArrayList<String> AreMyFriendsCheating = new ArrayList<>();

    String[] hashMap;

    private OpenHashmapForStrings(int size) throws URISyntaxException, IOException {

        var classloader = Thread.currentThread().getContextClassLoader();
        var wordListURL = classloader.getResource("OfficialScrabbleWordListGerman.txt");
        var cheatingURL = classloader.getResource("AreMyFriendsCheating.txt");
        if (wordListURL == null || cheatingURL == null) return;
        try (Stream<String> stream = Files.lines(Paths.get(wordListURL.toURI()))) {
            stream.forEach(OfficialScrabbleWordListGerman::add);
        }
        try (Stream<String> stream = Files.lines(Paths.get(cheatingURL.toURI()))) {
            stream.forEach(AreMyFriendsCheating::add);
        }

        hashMap = new String[size];
        Arrays.fill(hashMap, nulls);
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        var million = new OpenHashmapForStrings(2000000);
        var twoHundred = new OpenHashmapForStrings(200000000);


        System.out.println("#####################");
        System.out.println("200000 doubleInsert");
        var a = new OpenHashmapForStrings(200000);
        var time6 = new StopWatch();
        try {
            OfficialScrabbleWordListGerman.forEach(a::doubleInsert);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(time6.getTime() + "ns");
        System.out.println("#####################");

        // ################################################################

        System.out.println("200000 quadraticinsert");
        var b = new OpenHashmapForStrings(200000);
        var time5 = new StopWatch();
        try {
            OfficialScrabbleWordListGerman.forEach(b::quadraticInsert);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(time5.getTime() + "ns");
        System.out.println("#####################");

        // ################################################################

        System.out.println("200000 linearInsert");
        var c = new OpenHashmapForStrings(200000);
        var time4 = new StopWatch();
        try {
            OfficialScrabbleWordListGerman.forEach(c::linearInsert);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(time4.getTime() + "ns");
        System.out.println("#####################");

        // ################################################################

        System.out.println("1000000 doubleInsert");
        var d = new OpenHashmapForStrings(1000000);
        var time3 = new StopWatch();
        try {
            OfficialScrabbleWordListGerman.forEach(d::doubleInsert);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(time3.getTime() + "ns");
        System.out.println("#####################");

        // ################################################################

        System.out.println("1000000 quadraticinsert");
        var e = new OpenHashmapForStrings(1000000);
        var time2 = new StopWatch();
        try {
            OfficialScrabbleWordListGerman.forEach(e::quadraticInsert);
        } catch (RuntimeException E) {
            System.out.println(E.getMessage());
        }
        System.out.println(time2.getTime() + "ns");
        System.out.println("#####################");

        // ################################################################

        System.out.println("1000000 linearInsert");
        var f = new OpenHashmapForStrings(1000000);
        var time1 = new StopWatch();
        try {
            OfficialScrabbleWordListGerman.forEach(f::linearInsert);
        } catch (RuntimeException E) {
            System.out.println(E.getMessage());
        }
        System.out.println(time1.getTime() + "ns");
        System.out.println("#####################");
    }

    // region stringtolongs
    private long stringToLong(String string) {
        char[] chars = string.toCharArray();

        long value = 1;
        int exponent = chars.length - 1;

        for (char letter : chars) {
            value = value + (int) (letter * Math.pow(128, exponent));
            exponent--;
        }

        return value;
    }

    private long stringToLong2(String string) {
        return string.chars().asLongStream().reduce(0, Long::sum);
    }

    private long stringToLong3(String string) {
        return string.length();
    }

    private long stringToLong4(String string) {
        return string.hashCode();
    }

    // endregion

    // region inserts
    public int linearInsert(String string) {
        var i = 0;
        while (i < hashMap.length) {
            var h = (h(stringToLong(string)) + i) % hashMap.length;
            if (hashMap[h] == nulls || hashMap[h] == deleteds) {
                hashMap[h] = string;
                return h;
            } else {
                i++;
            }
        }
        var count = 0;
        for (String s : hashMap) {
            if (s == nulls) count++;
        }
        System.out.println("Still free: " + count);
        throw new RuntimeException("HashMap full!");
    }

    public int quadraticInsert(String string) {
        var i = 0;
        while (i < hashMap.length) {
            var h = (int) (h(stringToLong(string)) + ((1d / 2d) * i) + ((1d / 2d) * Math.pow(i, 2))) % hashMap.length;
            if (hashMap[h] == nulls || hashMap[h] == deleteds) {
                hashMap[h] = string;
                return h;
            } else {
                i++;
            }
        }
        var count = 0;
        for (String s : hashMap) {
            if (s == nulls) count++;
        }
        System.out.println("Still free: " + count);
        throw new RuntimeException("HashMap full!");
    }

    public int doubleInsert(String string) {
        var i = 0;
        while (i < hashMap.length) {
            var stringHash = stringToLong(string);
            var h2 = 1 + (stringHash % (hashMap.length - 1));
            var e = h(stringHash) + (i * h2);
            var h = Math.floorMod(e, hashMap.length);
            if (hashMap[h] == nulls || hashMap[h] == deleteds) {
                hashMap[h] = string;
                return h;
            } else {
                i++;
            }
        }
        var count = 0;
        for (String s : hashMap) {
            if (s == nulls) count++;
        }
        System.out.println("Still free: " + count);
        throw new RuntimeException("HashMap full!");
    }

    // endregion

    public Integer search(String string) {
        var i = 0;
        while (i < hashMap.length) {
            var j = (h(stringToLong(string)) + i) % hashMap.length;
            if (hashMap[j] == null) {
                return null;
            }
            if (hashMap[j].equals(string)) {
                return j;
            }
            i++;
        }
        return null;
    }

    public void delete(String string) {
        throw new RuntimeException("Not implemented yet!");
    }

    public int h(long n) {
        return Math.floorMod(n, hashMap.length);
    }
}
