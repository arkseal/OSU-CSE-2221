import java.util.Arrays;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author P. Bucci
 */
public final class CoinChange1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange1() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("What is the ammount you would like change for? (in cents): ");
        int cents = in.nextInteger();

        int[] denominations = { 100, 50, 25, 10, 5, 1 };
        int[] coins = new int[6];

        for (int i = 0; i < 6; i++) {
            coins[i] = cents / denominations[i];
            cents %= denominations[i];
        }

        out.println(Arrays.toString(coins));

        /*
         * out.println(String.format(
         * "Change: %d dollars, %d half dollars, %d quarters, %d dimes, %d nickels, %d pennies"
         * , dollars, halfdollars, quarters, dimes, nickels, pennies));
         */
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
