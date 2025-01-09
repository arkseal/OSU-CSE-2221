import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author P. Bucci
 */
public final class CoinChange2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange2() {
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

        int dollars = cents / 100;
        cents %= 100;

        int halfdollars = cents / 50;
        cents %= 50;

        int quarters = cents / 25;
        cents %= 25;

        int dimes = cents / 10;
        cents %= 10;

        int nickels = cents / 5;
        cents %= 5;

        int pennies = cents;

        out.print(String.format(
                "Change: %d dollars, %d half dollars, %d quarters, %d dimes, %d nickels, %d pennies",
                dollars, halfdollars, quarters, dimes, nickels, pennies));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
