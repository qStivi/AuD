package julian;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class OpenHashmapForStrings {

    String[] hashtable;
    boolean[] deleted;

    boolean useLinearProbing = false;
    boolean useQuadraticProbing = false;
    boolean useDoubleHashing = false;

    public OpenHashmapForStrings(int size, String handleConflicts) {
        hashtable = new String[size];
        deleted = new boolean[size];

        if(handleConflicts.equalsIgnoreCase("Linear Probing")) {
            useLinearProbing = true;
        } else if(handleConflicts.equalsIgnoreCase("Quadratic Probing")) {
            useQuadraticProbing = true;
        } else if(handleConflicts.equalsIgnoreCase("Double Hashing")) {
            useDoubleHashing = true;
        }
    }

    public static OpenHashmapForStrings linearProbing200000 = new OpenHashmapForStrings(200000, "Linear Probing");
    public static OpenHashmapForStrings linearProbing1000000 = new OpenHashmapForStrings(1000000, "Linear Probing");
    public static OpenHashmapForStrings quadraticProbing200000 = new OpenHashmapForStrings(200000, "Quadratic Probing");
    public static OpenHashmapForStrings quadraticProbing1000000 = new OpenHashmapForStrings(1000000, "Quadratic Probing");
    public static OpenHashmapForStrings doubleHashing200000 = new OpenHashmapForStrings(200000, "Double Hashing");
    public static OpenHashmapForStrings doubleHashing1000000 = new OpenHashmapForStrings(1000000, "Double Hashing");

    public static void initScrabbleWordMaps(File file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String word;

        while((word = br.readLine()) != null) {

            System.out.println(word);

//            linearProbing200000.insert(word);
//            System.out.println("Linear Probing 200000 OK");
//            linearProbing1000000.insert(word);
//            System.out.println("Linear Probing 1000000 OK");
//            quadraticProbing200000.insert(word);
//            System.out.println("Quadratic Probing 200000 OK");
//            quadraticProbing1000000.insert(word);
//            System.out.println("Quadratic Probing 1000000 OK");
            doubleHashing200000.insert(word);
            System.out.println("Double Hashing 200000 OK");
//            doubleHashing1000000.insert(word);
//            System.out.println("Double Hashing 1000000 OK");
        }
    }

    public static void searchWordsInScrabbleWordMaps(File file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        int counterLinearProbing200000 = 0;
        int counterLinearProbing1000000 = 0;
        int counterQuadraticProbing200000 = 0;
        int counterQuadraticProbing1000000 = 0;
        int counterDoubleHashing200000 = 0;
        int counterDoubleHashing1000000 = 0;
        String word;

        while((word = br.readLine()) != null) {

            int i1 = linearProbing200000.search(word);
            int i2 = linearProbing1000000.search(word);
            int i3 = quadraticProbing200000.search(word);
            int i4 = quadraticProbing1000000.search(word);
            int i5 = doubleHashing200000.search(word);
            int i6 = doubleHashing1000000.search(word);

            if(i1 == -1) {
                counterLinearProbing200000++;
            }
            if(i2 == -1) {
                counterLinearProbing1000000++;
            }
            if(i3 == -1) {
                counterQuadraticProbing200000++;
            }
            if(i4 == -1) {
                counterQuadraticProbing1000000++;
            }
            if(i5 == -1) {
                counterDoubleHashing200000++;
            }
            if(i6 == -1) {
                counterDoubleHashing1000000++;
            }

        }
        System.out.println("Linear Probing: " + counterLinearProbing200000 + "; " + counterLinearProbing1000000);
        System.out.println("Quadratic Probing: " + counterQuadraticProbing200000 + "; " + counterQuadraticProbing1000000);
        System.out.println("Double Hashing: " + counterDoubleHashing200000 + "; " + counterDoubleHashing1000000);
    }

    private int helperFunction(String key) {
        return (int) (stringToValue(key) % (long) hashtable.length);
    }

    private int helperFunction2(String key) {

        //System.out.println("HelperFunction2: " + (1 + (int) (stringToValue(key) % (long) (hashtable.length - 1))));

        return (int) (1 + (stringToValue(key) % ((long) hashtable.length - 1)));
    }

    private int linearProbing(String key, int i) {
        return (int) ((helperFunction(key) + i) % (long) hashtable.length);
    }

    private int quadraticProbing(String key, int i) {
        double c1 = 0.5;
        double c2 = 0.5;
        return (int) ((helperFunction(key) + c1 * i + c2 * i * i) % (long) hashtable.length);
    }

    private int doubleHashing(String key, int i) {
        return (int) ((helperFunction(key) + ((long) i * helperFunction2(key))) % (long) hashtable.length);
    }

    private int hash(String key, int i) {
        if(useLinearProbing) {
            return linearProbing(key, i);
        } else if(useQuadraticProbing) {
            return quadraticProbing(key, i);
        } else {
            return doubleHashing(key, i);
        }
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

    public int insert(String key) {
        int i = 0;
        while(i < hashtable.length) {
            int j = hash(key, i);
            if(hashtable[j] == null) {
                hashtable[j] = key;
                deleted[j] = false;
                return j;
            } else {
                i = i + 1;
            }
        }
        throw new IllegalStateException("Hashtable is full");
    }

    public boolean delete(String key) {
        int j = search(key);
        if(hashtable[j] != null) {
            deleted[j] = true;
            hashtable[j] = null;
            return true;
        }
        return false;
    }

    public int search(String key) throws NoSuchElementException {
        int i = 0;
        while(i < hashtable.length) {
            int j = hash(key, i);
            if(hashtable[j] == null && !deleted[j]) { //Sobald einmal null und nicht gelöscht gefunden abbrechen
                return -1;
            } else if(hashtable[j] != null && hashtable[j].equals(key)){
                return j;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(hashtable);
    }

    public static void main(String[] args) throws IOException {
        initScrabbleWordMaps(new File("/Users/julian/Documents/AuD/Julian/src/resources/OfficialScrabbleWordListGerman.txt"));
        searchWordsInScrabbleWordMaps(new File("/Users/julian/Documents/AuD/Julian/src/resources/AreMyFriendsCheating.txt"));

    }


}
