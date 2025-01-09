import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class HW11 {
    /**
     * Returns {@code n} to the power {@code p}.
     *
     * @param n
     *            the number to which we want to apply the power
     * @param p
     *            the power
     * @return the number to the power
     * @requires Integer.MIN_VALUE <= n ^ (p) <= Integer.MAX_VALUE and p >= 0
     * @ensures power = n ^ (p)
     */
    private static int power(int n, int p) {
        int a = 1;
        for (int i = 0; i < p; i++) {
            a *= n;
        }
        return a;
    }

    /**
     * Returns the {@code r}-th root of {@code n}.
     *
     * @param n
     *            the number to which we want to apply the root
     * @param r
     *            the root
     * @return the root of the number
     * @requires n >= 0 and r > 0
     * @ensures root ^ (r) <= n < (root + 1) ^ (r)
     */
    private static int root(int n, int r) {
        int lowEnough = 1, tooHigh = n;
        while (true) {
            // get middle => your guess
            int middleValue = (tooHigh + lowEnough) / 2;

            // lower bound
            int pwr1 = power(middleValue, r);

            // higher bound
            int pwr2 = power(middleValue + 1, r);

            if (pwr1 <= n && n < pwr2) {
                // inside lower and upper bound
                return middleValue;
            } else if (pwr1 > n) {
                // lower than lower bound, so you set guess to the next high
                // because the guess was too
                tooHigh = middleValue;
            } else if (pwr2 <= n) {
                // higher than upper bound, so you set the guess to the next low
                // because the guess was too low
                lowEnough = middleValue;
            }
        }
    }

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println(root(789, 3));

        in.close();
        out.close();
    }
}
