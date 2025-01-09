import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class HW4 {
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        double distance = Math
                .sqrt((xCoord - 1) * (xCoord - 1) + (yCoord - 1) * (yCoord - 1));
        return distance <= 1;
    }

    private static int numberOfPointsInCircle(int n) {
        Random rnd = new Random1L();
        int count = 0;
        for (int i = 0; i < n; i++) {
            double x = rnd.nextDouble() * 2;
            double y = rnd.nextDouble() * 2;
            if (pointIsInCircle(x, y)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points: ");
        int n = input.nextInteger();
        /*
         * Declare counters and initialize them
         */
        int ptsInInterval = n, ptsInSubinterval = 0;
        /*
         * Create pseudo-random number generator
         */
        //Random rnd = new Random1L();
        /*
         * Generate points and count how many fall in [0.0,0.5) interval
         */
        ptsInSubinterval = numberOfPointsInCircle(n);
        /*
         * Estimate percentage of points generated in [0.0,1.0) interval that
         * fall in the [0.0,0.5) subinterval
         */
        double estimate = (100.0 * ptsInSubinterval) / ptsInInterval;
        double areaEstimate = estimate / 100 * 4;
        output.println("Estimate of percentage: " + estimate + "%");
        output.println("Estimate of area: " + areaEstimate);
        /*
         * Close input and output streams
         */
        input.close();
        output.close();
    }

}
