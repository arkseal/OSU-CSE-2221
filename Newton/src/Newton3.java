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
public final class Newton3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton3() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param epsilon
     *            the epsilon used to compute square root
     * @return estimate of square root
     */
    private static double sqrt(double x, double epsilon) {
        if (x == 0) {
            return 0;
        }
        double r = x;
        double error = epsilon;

        while (error >= epsilon * epsilon) {
            r = (r + x / r) / 2;
            error = Math.abs(r * r - x) / x;
        }

        return r;
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
        out.print("Please enter a value for epsilon: ");
        double epsilon = in.nextDouble();
        boolean more = true;
        while (more) {
            out.print("Do you wish to calculate a square root? (y/n): ");
            String resp = in.nextLine();
            more = resp.equals("y");
            if (more) {
                out.print("Enter a number: ");
                double x = in.nextDouble();
                double s = sqrt(x, epsilon);
                out.println("The square root of " + x + " is approximatly " + s + ".");
            }

        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
