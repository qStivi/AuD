package andi;

public class Main {

    public static int maxWin(int[] a, int p, int r, int t1, int t2) {
       int result = 0;
       if (t1 < t2){

       }
        return result;
    }

    public static void test(double a, double b) {
       int counter = 1;
        while (true) {
            System.out.println((a+b) / 2.0);
            if (counter % 2 == 1) {
                a = (a+b) / 2.0;
            } else {
                b = (a+b) / 2.0;
            }
            counter++;
        }
    }

    public static void main(String[] args) {
        test(2.0,3.0);
    }
}
