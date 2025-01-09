import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Aarush Katta
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        String n = "NOT NUMBER";
        while (!FormatChecker.canParseDouble(n) || Double.parseDouble(n) <= 0) {
            out.print("Please enter a positive real number: ");
            n = in.nextLine();
        }
        return Double.parseDouble(n);
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in, SimpleWriter out) {
        String n = "NOT NUMBER";
        while (!FormatChecker.canParseDouble(n) || Double.parseDouble(n) <= 0
                || Double.parseDouble(n) == 1) {
            out.print("Please enter a positive real number not equal to 1: ");
            n = in.nextLine();
        }
        return Double.parseDouble(n);
    }

    /**
     * Checks if the new estimate is better than the current best estimate.
     *
     * @param mu
     *            the value being estimated
     * @param estimate
     *            the new estimate
     * @param bestEstimate
     *            the current best estimate
     * @return a boolean whether the new estimate is better
     */
    private static boolean isBetterEstimate(double mu, double estimate,
            double bestEstimate) {
        return Math.abs(mu - estimate) < Math.abs(mu - bestEstimate);
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
        final double[] numbers = { -5, -4, -3, -2, -1, -1.0 / 2, -1.0 / 3, -1.0 / 4, 0,
                1.0 / 4, 1.0 / 3, 1.0 / 2, 1, 2, 3, 4, 5 };

        double mu = getPositiveDouble(in, out);

        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        double a = 0, b = 0, c = 0, d = 0;
        double estimate = 0, bestEstimate = 0;

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                for (int k = 0; k < numbers.length; k++) {
                    for (int l = 0; l < numbers.length; l++) {
                        estimate = Math.pow(w, numbers[i]) * Math.pow(x, numbers[j])
                                * Math.pow(y, numbers[k]) * Math.pow(z, numbers[l]);
                        if (isBetterEstimate(mu, estimate, bestEstimate)) {
                            bestEstimate = estimate;
                            a = numbers[i];
                            b = numbers[j];
                            c = numbers[k];
                            d = numbers[l];
                        }
                    }
                }
            }
        }

        final int convertToPercent = 100;
        out.println("The closest estimate to nu = " + mu + " is " + bestEstimate + ".");
        out.println(w + "^" + a + " * " + x + "^" + b + " * " + y + "^" + c + " * " + z
                + "^" + d);
        out.println(String.format("The relative error is about: %.2f%%",
                Math.abs((bestEstimate - mu) / mu) * convertToPercent));
        in.close();
        out.close();
    }

}
