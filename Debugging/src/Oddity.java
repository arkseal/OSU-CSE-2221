import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Oddity {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Oddity() {
    }

    /**
     * Returns a modulo b using "clock arithmetic".
     *
     * @param a
     *            the first operand
     * @param b
     *            the modulus
     * @return a modulo b
     * @requires b > 0
     * @ensures mod = a mod b
     */
    private static int mod(int a, int b) {
        int s = a;
        if (s >= 0) {
            while (s > b) {
                s -= b;
            }
        } else {
            while (s < b) {
                s += b;
            }
            s = b - s;
        }
        return s;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        final int[] values = { 8, -4, 3, 0, -5 };
        int i = 0;
        while (i < values.length) {
            int remainder = values[i] % 2;
            if (remainder == 1 || remainder == -1) {
                out.println("odd");
            } else {
                out.println("even");
            }
            i = i + 1;
        }
        out.println();

        //
        final double x = 456.0;
        final double y = 100.0 * 4.56;
        final double err = 1e-3;
        if (Math.abs(x - y) < err) {
            out.println("equal");
        } else {
            out.println("not equal");
        }

        out.println();

        //
        final double microsPerDay = 24.0 * 60 * 60 * 1000 * 1000;
        final double millisPerDay = 24.0 * 60 * 60 * 1000;
        out.println(microsPerDay / millisPerDay);
        out.println();

        //
        final int n = 12345 + 54321;
        out.println(n);
        out.println();

        //
        final int a = -67;
        final int b = 24;
        out.println(mod(a, b));
        out.close();
    }

}
