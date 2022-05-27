package andi;

public class Main {

    public static int maxWin(int[] a, int p, int r, int t1, int t2) {
        int result = 0;
        if (t1 < t2) {

        }
        return result;
    }

    public static void test(double a, double b) {
        while (true) {
            System.out.println((a + b) / 2.0);
            if (Math.pow((a + b) / 2.0, 2) <= 7) {
                a = (a + b) / 2.0;
            } else {
                b = (a + b) / 2.0;
            }
        }
    }

    public static void main(String[] args) {
        test(2.0, 3.0);
    }
}
