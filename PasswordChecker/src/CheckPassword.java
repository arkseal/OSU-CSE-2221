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
public final class CheckPassword {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CheckPassword() {
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an upper case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLowerCase(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an upper case letter, false otherwise
     */
    private static boolean containsDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param passwordCandidate
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String passwordCandidate, SimpleWriter out) {
        final int minimumLength = 8;

        int c = 0;
        if (!containsUpperCaseLetter(passwordCandidate)) {
            c++;
            out.println("Password does not have an upper case letter");
        }
        if (!containsLowerCaseLetter(passwordCandidate)) {
            c++;
            out.println("Password does not have a lower case letter");
        }
        if (!containsDigit(passwordCandidate)) {
            c++;
            out.println("Password does not have a digit");
        }
        boolean fail = false;
        if (passwordCandidate.length() < minimumLength) {
            out.println(String.format(
                    "The password is only %d characters long,"
                            + " it must be at least 8 characters long",
                    passwordCandidate.length()));
            fail = true;
        }
        if (c > 1) {
            out.println("The password doesn't have 2 of the following: "
                    + "uppercase letters, lowercase letters, digits");
            fail = true;
        }

        if (!fail) {
            out.println("The password passed the checks");
        }
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
        out.print("Enter a password: ");
        String pass = in.nextLine();
        checkPassword(pass, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
