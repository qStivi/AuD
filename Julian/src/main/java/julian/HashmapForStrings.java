package julian;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class HashmapForStrings {

    static HashmapForStrings scrabbleWordMap1000 = new HashmapForStrings(1000);
    static HashmapForStrings scrabbleWordMap10000 = new HashmapForStrings(10000);
    ArrayList<ArrayList<String>> hashTable;
    int size;

    public HashmapForStrings(int size) {
        hashTable = new ArrayList<>(size);
        this.size = size;

        for(int i = 0; i < size; i++) {
            hashTable.add(i, new ArrayList<>());
        }
    }

    public static void initScrabbleWordMaps(File file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        long time1000 = 0;
        long time10000 = 0;

        String word;



        while((word = br.readLine()) != null) {

            long startTime1000 = System.nanoTime();
            scrabbleWordMap1000.insert(word);
            long endTime1000 = System.nanoTime();
            long test = endTime1000 - startTime1000;
            time1000 = time1000 + (endTime1000 - startTime1000);

            long startTime10000 = System.nanoTime();
            scrabbleWordMap10000.insert(word);
            long endTime10000 = System.nanoTime();
            time10000 = time10000 + (endTime10000 - startTime10000);
        }

        System.out.println("InsertWords");
        System.out.println("Time1000: " + time1000 + "; Time10000: " + time10000);
    }

    public static void searchWordsInScrabbleWordMaps(File file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));

        int counter1000 = 0;
        int counter10000 = 0;
        long time1000 = 0;
        long time10000 = 0;

        String word;
        while((word = br.readLine()) != null) {

            long startTime1000 = System.nanoTime();
            boolean b1 = scrabbleWordMap1000.search(word);
            long endTime1000 = System.nanoTime();
            time1000 = time1000 + (endTime1000 - startTime1000);

            long startTime10000 = System.nanoTime();
            boolean b2 = scrabbleWordMap10000.search(word);
            long endTime10000 = System.nanoTime();
            time10000 = time10000 + (endTime10000 - startTime10000);

            if(!b1) {
                counter1000++;
            }
            if(!b2) {
                counter10000++;
            }
        }

        System.out.println("SearchWords: ");
        System.out.println("Time1000: " + time1000 + "; Time10000: " + time10000);
        System.out.println("Found1000: " + counter1000 + "; Found10000: " + counter10000);
    }

    public void insert(String element) {

        int insertPosition = (int) (stringToValue(element) % (long) this.size);
        ArrayList<String> collisionList = hashTable.get(insertPosition);
        collisionList.add(0, element);

    }

    public void delete(String element) {
        int deletePosition = (int) (stringToValue(element) % hashTable.size());
        ArrayList<String> collisionList = hashTable.get(deletePosition);

        for(int i = 0; i < collisionList.size(); i++) {
            if(collisionList.get(i).equals(element)) {
                collisionList.remove(i);
                break;
            }
        }

        throw new NoSuchElementException();
    }

    public boolean search(String element) {
        int searchPosition = (int) (stringToValue(element) % hashTable.size());
        ArrayList<String> collisionList = hashTable.get(searchPosition);

        for(String word : collisionList) {
            if(word.equals(element)) {
                return true;
            }
        }
        return false;
    }

    private static long stringToValue(String element) {
        char[] chars = element.toCharArray();

        long value = 1;
        int exponent = chars.length - 1;

        for(char letter : chars) {
            value = value + (int) (letter * Math.pow(128, exponent));
            exponent--;
        }

        return value;
    }

    public static void main(String[] args) throws IOException {

        initScrabbleWordMaps(new File("/Users/julian/Documents/AuD/Julian/src/resources/OfficialScrabbleWordListGerman.txt"));
        searchWordsInScrabbleWordMaps(new File("/Users/julian/Documents/AuD/Julian/src/resources/AreMyFriendsCheating.txt"));
    }

}
