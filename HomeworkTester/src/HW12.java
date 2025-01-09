import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class HW12 {
    /**
     * Returns the number of digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures numberOfDigits = [number of digits of n]
     */
    private static int numberOfDigits(NaturalNumber n) {
        if (n.isZero()) {
            return 0;
        }
        n.divideBy10();

        return numberOfDigits(n) + 1;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static int sumOfDigits(NaturalNumber n) {
        if (n.isZero()) {
            return 0;
        }

        return n.divideBy10() + sumOfDigits(n);
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static NaturalNumber sumOfDigits2(NaturalNumber n) {
        if (n.isZero()) {
            return new NaturalNumber2(0);
        }
        NaturalNumber a = new NaturalNumber2(n.divideBy10());
        a.add(sumOfDigits2(n));
        return a;
    }

    /**
     * Divides {@code n} by 2.
     *
     * @param n
     *            {@code NaturalNumber} to be divided
     * @updates n
     * @ensures 2 * n <= #n < 2 * (n + 1)
     */
    private static void divideBy2(NaturalNumber n) {
        divideBy2Helper(n, 0);
    }

    private static void divideBy2Helper(NaturalNumber n1, int idx) {
        int[] past = new int[idx];
        for (int i = 0; i < idx; i++) {
            past[i] = n1.divideBy10();
        }
        boolean notZero = !n1.isZero();
        if (notZero) {

            int ones = n1.divideBy10();
            int tens = n1.divideBy10();

            // if tens is odd, add 10 to ones
            if (tens % 2 == 1) {
                ones += 10;
            }

            // if ones is odd, subtract one
            if (ones % 2 == 1) {
                ones -= 1;
            }

            ones /= 2;
            n1.multiplyBy10(tens);
            n1.multiplyBy10(ones);
        }
        for (int i = idx - 1; i >= 0; i--) {
            n1.multiplyBy10(past[i]);
        }

        if (notZero) {
            divideBy2Helper(n1, idx + 1);
        }
    }

    /**
     * Checks whether a {@code String} is a palindrome.
     *
     * @param s
     *            {@code String} to be checked
     * @return true if {@code s} is a palindrome, false otherwise
     * @ensures isPalindrome = (s = rev(s))
     */
    private static boolean isPalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }
        return s.substring(0, 1).equals(s.substring(s.length() - 1))
                && isPalindrome(s.substring(1, s.length() - 1));
    }

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        NaturalNumber t = new NaturalNumber2(6565656);
        // out.println(numberOfDigits(t));
        //out.println(sumOfDigits2(t));
        divideBy2(t);
        out.println(t);
        //out.println(isPalindrome("h2leh"));

        in.close();
        out.close();
    }
}
