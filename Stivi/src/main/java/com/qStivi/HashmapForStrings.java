package com.qStivi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class HashmapForStrings {

    ArrayList<String>[] hashMap;

    public HashmapForStrings(int m) {
        this.hashMap = new ArrayList[m];
        Arrays.setAll(hashMap, i -> new ArrayList<>());
    }

    /*
    Insert size thousand:       204435208ns
    Insert size ten thousand:   199472000ns
    Number of Words not found:  1814
    Search size thousand:       9564333ns
    Number of Words not found:  1814
    Search size ten thousand:   5543666ns
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        var thousand = new HashmapForStrings(1000);
        var tenThousand = new HashmapForStrings(10000);

        var classloader = Thread.currentThread().getContextClassLoader();
        var wordListURL = classloader.getResource("OfficialScrabbleWordListGerman.txt");
        var cheatingURL = classloader.getResource("AreMyFriendsCheating.txt");
        if (wordListURL == null || cheatingURL == null) return;
        var wordList = new File(wordListURL.toURI());
        var cheating = new File(cheatingURL.toURI());

        try (BufferedReader br = new BufferedReader(new FileReader(wordList))) {
            var start = System.nanoTime();
            for (String line; (line = br.readLine()) != null; ) {
                thousand.insert(line);
            }
            System.out.println("Insert size thousand: " + (System.nanoTime() - start));
        }
        try (BufferedReader br = new BufferedReader(new FileReader(wordList))) {
            var start = System.nanoTime();
            for (String line; (line = br.readLine()) != null; ) {
                tenThousand.insert(line);
            }
            System.out.println("Insert size ten thousand: " + (System.nanoTime() - start));
        }

        try (BufferedReader br = new BufferedReader(new FileReader(cheating))) {
            var start = System.nanoTime();
            var count = 0L;
            for (String line; (line = br.readLine()) != null; ) {
                if (!thousand.search(line)) {
                    count++;
                }
            }
            System.out.println("Number of Words not found: " + count);

            System.out.println("Search size thousand: " + (System.nanoTime() - start));
        }
        try (BufferedReader br = new BufferedReader(new FileReader(cheating))) {
            var start = System.nanoTime();
            var count = 0L;
            for (String line; (line = br.readLine()) != null; ) {
                if (!tenThousand.search(line)) {
                    count++;
                }
            }
            System.out.println("Number of Words not found: " + count);

            System.out.println("Search size ten thousand: " + (System.nanoTime() - start));
        }


    }

    private static long stringToValue(String element) {
        char[] chars = element.toCharArray();

        long value = 1;
        int exponent = chars.length - 1;

        for (char letter : chars) {
            value = value + (int) (letter * Math.pow(128, exponent));
            exponent--;
        }

        return value;
    }

    public long stringToLong(String string) {
        return string.chars().asLongStream().reduce(0, Long::sum);
    }

    public int h(long n) {
        return Math.floorMod(n, hashMap.length);
    }

    public void insert(String string) {
        hashMap[h(stringToValue(string))].add(string);
    }

    public boolean search(String string) {
        return hashMap[h(stringToValue(string))].contains(string);
    }

    public void delete(String string) {
        hashMap[h(stringToValue(string))].remove(string);
    }
}
