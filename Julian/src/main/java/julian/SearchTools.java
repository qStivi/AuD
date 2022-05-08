package julian;

import java.util.Random;

public class SearchTools {

    public static int linSearch(int[] A, int x) {
        for(int i = 0; i < A.length; i++) {
            if(A[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public static int binSearch(int[] A, int x) {
        return binSearch(A, x, 0, A.length - 1);
    }

    public static int binSearch(int[] A, int x, int l, int r) {

        int i = l + (r - l) / 2;

        if(l > r) {
            return -1;
        }

        if(l == r) {
            if(A[l] == x) {
                return l;
            } else {
                return -1;
            }
        }

        if(A[i] == x) {
            return i;
        } else if(x < A[i]) {
            return binSearch(A, x, l, i - 1);
        } else {
            return binSearch(A, x, i + 1, r);
        }
    }

    public static int binSearchNew(int[] A, int x) {
        return binSearchNew(A, x, 0, A.length - 1);
    }

    public static int binSearchNew(int[] A, int x, int l, int r) {

        int h = (int) Math.round((r - l + 1) / 3.0);
        int q1 = h - 1 + l;
        int q2 = q1 + h;

        //if q1 == q2
        if(l > r) {
            return -1;
        }

        if(l == r) {
            if(A[l] == x) {
                return l;
            } else {
                return -1;
            }
        }

        if(x == A[q1]) {
            return q1;
        }

        if(x == A[q2]) {
            return q2;
        }

        if(x > A[q2]) { //Dann in A3
            return binSearchNew(A, x, q2 + 1, r);
        } else if(x > A[q1]) { //Dann in A2
            return binSearchNew(A, x, q1 + 1, q2);
        } else { //Ansonsten in A1
            return binSearchNew(A, x, l, q1);
        }
    }

    public static void measureTime100000() {

        System.out.println("#############################");
        System.out.println("#############################");

        int[] arr = SortTools.createSequenceInc(100000);
        Random random = new Random();

        long time1 = 0;
        long time2 = 0;
        long time3 = 0;

        for(int i = 1; i <= 500; i++) {
            int searchFor = random.nextInt(1, 100001);

            long start1 = System.nanoTime();
            linSearch(arr, searchFor);
            long end1 = System.nanoTime();
            time1 = time1 + (end1 - start1);

            long start2 = System.nanoTime();
            binSearch(arr, searchFor);
            long end2 = System.nanoTime();
            time2 = time2 + (end2 - start2);

            long start3 = System.nanoTime();
            binSearchNew(arr, searchFor);
            long end3 = System.nanoTime();
            time3 = time3 + (end3 - start3);
        }

        System.out.println("Linear Search 100000: " + time1 + "ns");
        System.out.println("Binary Search 100000: " + time2 + "ns");
        System.out.println("Binary Search New 100000: " + time3 + "ns");

        time1 = 0;
        time2 = 0;
        time3 = 0;

        for(int i = 1; i <= 500; i++) {
            int searchFor = -5;

            long start1 = System.nanoTime();
            linSearch(arr, searchFor);
            long end1 = System.nanoTime();
            time1 = time1 + (end1 - start1);

            long start2 = System.nanoTime();
            binSearch(arr, searchFor);
            long end2 = System.nanoTime();
            time2 = time2 + (end2 - start2);

            long start3 = System.nanoTime();
            binSearchNew(arr, searchFor);
            long end3 = System.nanoTime();
            time3 = time3 + (end3 - start3);
        }

        System.out.println("--------------------------------");
        System.out.println("Search For element outside of range");
        System.out.println("Linear Search 100000: " + time1 + "ns");
        System.out.println("Binary Search 100000: " + time2 + "ns");
        System.out.println("Binary Search New 100000: " + time3 + "ns");
    }

    public static void measureTime1000000() {

        System.out.println("#############################");
        System.out.println("#############################");

        int[] arr = SortTools.createSequenceInc(1000000);
        Random random = new Random();

        long time1 = 0;
        long time2 = 0;
        long time3 = 0;

        for(int i = 1; i <= 500; i++) {
            int searchFor = random.nextInt(1, 1000001);

            long start1 = System.nanoTime();
            linSearch(arr, searchFor);
            long end1 = System.nanoTime();
            time1 = time1 + (end1 - start1);

            long start2 = System.nanoTime();
            binSearch(arr, searchFor);
            long end2 = System.nanoTime();
            time2 = time2 + (end2 - start2);

            long start3 = System.nanoTime();
            binSearchNew(arr, searchFor);
            long end3 = System.nanoTime();
            time3 = time3 + (end3 - start3);
        }

        System.out.println("Linear Search 1000000: " + time1 + "ns");
        System.out.println("Binary Search 1000000: " + time2 + "ns");
        System.out.println("Binary Search New 1000000: " + time3 + "ns");

        time1 = 0;
        time2 = 0;
        time3 = 0;

        for(int i = 1; i <= 500; i++) {
            int searchFor = -5;

            long start1 = System.nanoTime();
            linSearch(arr, searchFor);
            long end1 = System.nanoTime();
            time1 = time1 + (end1 - start1);

            long start2 = System.nanoTime();
            binSearch(arr, searchFor);
            long end2 = System.nanoTime();
            time2 = time2 + (end2 - start2);

            long start3 = System.nanoTime();
            binSearchNew(arr, searchFor);
            long end3 = System.nanoTime();
            time3 = time3 + (end3 - start3);
        }

        System.out.println("--------------------------------");
        System.out.println("Search For element outside of range");
        System.out.println("Linear Search 1000000: " + time1 + "ns");
        System.out.println("Binary Search 1000000: " + time2 + "ns");
        System.out.println("Binary Search New 1000000: " + time3 + "ns");
    }

    public static void measureTime100000000() {

        System.out.println("#############################");
        System.out.println("#############################");

        int[] arr = SortTools.createSequenceInc(10000000);
        Random random = new Random();

        long time1 = 0;
        long time2 = 0;
        long time3 = 0;

        for(int i = 1; i <= 500; i++) {
            int searchFor = random.nextInt(1, 10000001);

            long start1 = System.nanoTime();
            linSearch(arr, searchFor);
            long end1 = System.nanoTime();
            time1 = time1 + (end1 - start1);

            long start2 = System.nanoTime();
            binSearch(arr, searchFor);
            long end2 = System.nanoTime();
            time2 = time2 + (end2 - start2);

            long start3 = System.nanoTime();
            binSearchNew(arr, searchFor);
            long end3 = System.nanoTime();
            time3 = time3 + (end3 - start3);
        }

        System.out.println("Linear Search 100000000: " + time1 + "ns");
        System.out.println("Binary Search 100000000: " + time2 + "ns");
        System.out.println("Binary Search New 100000000: " + time3 + "ns");

        time1 = 0;
        time2 = 0;
        time3 = 0;

        for(int i = 1; i <= 500; i++) {
            int searchFor = -5;

            long start1 = System.nanoTime();
            linSearch(arr, searchFor);
            long end1 = System.nanoTime();
            time1 = time1 + (end1 - start1);

            long start2 = System.nanoTime();
            binSearch(arr, searchFor);
            long end2 = System.nanoTime();
            time2 = time2 + (end2 - start2);

            long start3 = System.nanoTime();
            binSearchNew(arr, searchFor);
            long end3 = System.nanoTime();
            time3 = time3 + (end3 - start3);
        }

        System.out.println("--------------------------------");
        System.out.println("Search For element outside of range");
        System.out.println("Linear Search 100000000: " + time1 + "ns");
        System.out.println("Binary Search 100000000: " + time2 + "ns");
        System.out.println("Binary Search New 100000000: " + time3 + "ns");
    }

    public static void measureTime685154321() {

        System.out.println("#############################");
        System.out.println("#############################");

        int[] arr = SortTools.createSequenceInc(685154321);
        Random random = new Random();

        long time1 = 0;
        long time2 = 0;
        long time3 = 0;

        for(int i = 1; i <= 500; i++) {
            int searchFor = random.nextInt(1, 685154322);

            long start1 = System.nanoTime();
            linSearch(arr, searchFor);
            long end1 = System.nanoTime();
            time1 = time1 + (end1 - start1);

            long start2 = System.nanoTime();
            binSearch(arr, searchFor);
            long end2 = System.nanoTime();
            time2 = time2 + (end2 - start2);

            long start3 = System.nanoTime();
            binSearchNew(arr, searchFor);
            long end3 = System.nanoTime();
            time3 = time3 + (end3 - start3);
        }

        System.out.println("Linear Search 685154321: " + time1 + "ns");
        System.out.println("Binary Search 685154321: " + time2 + "ns");
        System.out.println("Binary Search New 685154321: " + time3 + "ns");

        time1 = 0;
        time2 = 0;
        time3 = 0;

        for(int i = 1; i <= 500; i++) {
            int searchFor = -5;

            long start1 = System.nanoTime();
            linSearch(arr, searchFor);
            long end1 = System.nanoTime();
            time1 = time1 + (end1 - start1);

            long start2 = System.nanoTime();
            binSearch(arr, searchFor);
            long end2 = System.nanoTime();
            time2 = time2 + (end2 - start2);

            long start3 = System.nanoTime();
            binSearchNew(arr, searchFor);
            long end3 = System.nanoTime();
            time3 = time3 + (end3 - start3);
        }

        System.out.println("--------------------------------");
        System.out.println("Search For element outside of range");
        System.out.println("Linear Search 685154321: " + time1 + "ns");
        System.out.println("Binary Search 685154321: " + time2 + "ns");
        System.out.println("Binary Search New 685154321: " + time3 + "ns");
    }

    public static void main(String[] args) {

        measureTime100000();
        measureTime1000000();
        measureTime100000000();
        measureTime685154321();

        /*
        #############################
        #############################
        Linear Search 100000: 7527423ns
        Binary Search 100000: 158910ns
        Binary Search New 100000: 280101ns
        --------------------------------
        Search For element outside of range
        Linear Search 100000: 15319501ns
        Binary Search 100000: 128457ns
        Binary Search New 100000: 142578ns
        #############################
        #############################
        Linear Search 1000000: 50347677ns
        Binary Search 1000000: 143975ns
        Binary Search New 1000000: 181027ns
        --------------------------------
        Search For element outside of range
        Linear Search 1000000: 96818839ns
        Binary Search 1000000: 78534ns
        Binary Search New 1000000: 128710ns
        #############################
        #############################
        Linear Search 100000000: 481820660ns
        Binary Search 100000000: 535388ns
        Binary Search New 100000000: 361259ns
        --------------------------------
        Search For element outside of range
        Linear Search 100000000: 971102532ns
        Binary Search 100000000: 689606ns
        Binary Search New 100000000: 332750ns
        #############################
        #############################
        Linear Search 685154321: 32177478534ns
        Binary Search 685154321: 1356381ns
        Binary Search New 685154321: 874613ns
        --------------------------------
        Search For element outside of range
        Linear Search 685154321: 66565567290ns
        Binary Search 685154321: 1031333ns
        Binary Search New 685154321: 864906ns
         */
    }

}
