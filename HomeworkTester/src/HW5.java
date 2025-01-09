
public class HW5 {
    public static void main(String[] args) {
        /*
         * int i = 2; double n = 1.0 / 2.0; System.out.println("i " + i +
         * " - n " + n); while (i <= 5) { System.out.println("i " + i + " - n "
         * + n); n = n + 1.0 / i; System.out.println("i " + i + " - n " + n); i
         * = i + 1; System.out.println("i " + i + " - n " + n); }
         * System.out.println("i " + i + " - n " + n);
         * System.out.println("\n\n\n\n"); double x = 1.0; double y = 1.0; while
         * (x < 1.8) { y = y / 2.0; x = x + y; } System.out.println(x + " " +
         * y);
         *
         * x = 3; y = 4; while (y > 0) { x = x + 1; y = y - 1; }
         * System.out.println(x + " " + y);
         *
         * int num = 432677; int s = 0; for (int n1 = num; n1 > 0; n1 /= 100) {
         * s += n1 % 10; System.out.println(n1 + " " + s); }
         * System.out.println(s);
         */

        int num = 432677;
        int s = 0;
        for (int n1 = Integer.toString(num).length() - 1; n1 >= 0; n1 -= 2) {
            System.out.println(n1);
            s += num / Math.pow(10, n1) % 10;
        }
        System.out.println(s);
    }
}
