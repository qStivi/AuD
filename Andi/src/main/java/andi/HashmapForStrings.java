package andi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class HashmapForStrings {

    private int m;
    private LinkedList<String>[] hashMap;

    public HashmapForStrings(int m) {
        this.m = m;
        this.hashMap = new LinkedList[m];
        Arrays.setAll(hashMap, i -> new LinkedList<String>());
    }

    public static void main(String[] args) throws IOException {
        HashmapForStrings hashTable = new HashmapForStrings(5500);

        FileReader fr1 = new FileReader("OfficialScrabbleWordListGerman.txt");
        BufferedReader br1 = new BufferedReader(fr1);

        String line1;

        long start = System.nanoTime();
        while((line1 = br1.readLine()) != null) {
            hashTable.insert(line1);
        }
        br1.close();
        System.out.println("Zeit für Einfügen der Wörter: " + (System.nanoTime() - start));


        FileReader fr2 = new FileReader("AreMyFriendsCheating.txt");
        BufferedReader br2 = new BufferedReader(fr2);

        String line2;
        int counter = 0;
        start = System.nanoTime();
        while((line2 = br2.readLine()) != null) {
            if (!hashTable.search(line2)) {
                counter++;
            }
        }
        System.out.println("Zeit für Suchen der Wörter: " + (System.nanoTime() - start));
        br2.close();

        System.out.println("Nicht gefundene Wörter: " + counter);
    }

    public void insert (String key) {
        hashMap[h(key)].add(key);
    }

    public void delete (String key) {
        hashMap[h(key)].remove(key);
    }

    public boolean search (String key) {
       if(hashMap[h(key)].contains(key)) {
           return true;
       }
       return false;
    }
    private long stringToLong (String key) {
        return key.chars().asLongStream().reduce(0, Long::sum);
    }

    private int h(String key) {
        return Math.floorMod(stringToLong(key), m);
    }
}
