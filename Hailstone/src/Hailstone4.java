import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone4() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer, with its length, and maximum number.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int l = 0;
        int max = n;
        out.print(n);
        while (n != 1) {
            if (n > max) {
                max = n;
            }
            l++;
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
            out.print(" " + n);
        }
        out.println("\nLength: " + l);
        out.println("Max: " + max);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        boolean again = true;
        while (again) {
            out.print("Enter the starting number: ");
            int n = in.nextInteger();
            generateSeries(n, out);

            out.print("Do you want to generate a new series? (y/n): ");
            String s = in.nextLine();
            again = s.equals("y");
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
