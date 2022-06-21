package andi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class OpenHashmapForStrings {
    private final int m;
    private final String[] hashMap;

    public OpenHashmapForStrings(int m) {
        this.m = m;
        this.hashMap = new String[m];
    }

    public static void main(String[] args) throws Exception {
        OpenHashmapForStrings hashTable = new OpenHashmapForStrings(200000);

        FileReader fr1 = new FileReader("OfficialScrabbleWordListGerman.txt");
        BufferedReader br1 = new BufferedReader(fr1);

        String line1;

        long start = System.nanoTime();

        while((line1 = br1.readLine()) != null) {
            hashTable.insertLinear(line1);
        }
        br1.close();
        System.out.println("Zeit für Einfügen der Wörter: " + (System.nanoTime() - start));


        FileReader fr2 = new FileReader("AreMyFriendsCheating.txt");
        BufferedReader br2 = new BufferedReader(fr2);

        String line2;
        int counter = 0;
        start = System.nanoTime();
        while((line2 = br2.readLine()) != null) {
            if (hashTable.search(line2, "linear") != -1) {
                counter++;
            }
        }
        System.out.println("Zeit für Suchen der Wörter: " + (System.nanoTime() - start));
        br2.close();

        System.out.println("Nicht gefundene Wörter: " + counter);

    }

    public void insertLinear(String s) throws Exception {
        for (int i = 0; i < m; i++) {
            int j = linear(s, i);
            if (j == -1) {
                throw new Exception("Sondierung nicht gefunden.");
            }
            if (hashMap[j] == null || hashMap[j].equals("")) {
                hashMap[j] = s;
                break;
            } else {
                i++;
            }
        }
    }

    public int search(String s, String sondierung) throws Exception {
        for (int i = 0; i < m; i++) {
            int j = sondierung(sondierung, i); // Hashfunktion
            if (j == -1) {
                throw new Exception("Sondierung nicht gefunden.");
            }
            if (hashMap[j] == null) {
                return -1;
            }
            if (hashMap[j].equals(s)) {
                return j;
            }
        }
        return -1;
    }

    public void delete(String s, String sondierung) throws Exception {
        int j = search(s, sondierung);
        if (j != -1) {
            hashMap[j] = "";
        }
    }

    public int linear(String s, int i) {
        return (h(s) + i) % m; //Nochmal % m oder weglassen?
    }

    public int quadratisch(String s, int i) {
        return (h(s) + 1 / 2 * i + 1 / 2 * i * i) % m;
    }

    public int doppelt(String s, int i) {
        return (h(s) + i * h2(s)) % m;
    }


    private long stringToLong(String string) {
        return string.chars().asLongStream().reduce(0, Long::sum);
    }

    private int h(String key) {
        return Math.floorMod(stringToLong(key), m);
    }

    private int h2(String s) {
        return 1 + (Math.floorMod(stringToLong(s), m) % (m - 1));
    }

    private int sondierung(String s, int i) {
        return switch (s) {
            case "linear" -> linear(s, i);
            case "quadratisch" -> quadratisch(s, i);
            case "doppelt" -> doppelt(s, i);
            default -> -1;
        };
    }
}
