import java.util.Arrays;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class HW7 {
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * String vowels = "AEIOUaeiou"; String b = in.nextLine(); for (int i =
         * 0; i < b.length(); i++) { if (vowels.contains(b.substring(i, i + 1)))
         * { out.print("_"); } else { out.print(b.charAt(i)); } }
         */

        int[] a = { 1, 2, 3, 4, 5, 4, 3, 2, 1, 0 };

        int i = 1;
        while (i < 5) {
            a[i] = a[9 - i];
            i++;
        }

        out.println(Arrays.toString(a));

        in.close();
        out.close();
    }
}
