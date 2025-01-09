import java.util.Arrays;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class test {
    public static int fib(int n) {
        int a = 0;
        if (n == 1 || n == 2) {
            a = 1;
        } else {
            a = fib(n - 1) + fib(n - 2);
        }
        return a;
    }

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        String s = in.nextLine();
        int[] seq = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        int i = 0;
        while (i < seq.length - 1) {
            int n = seq[i];
            if (seq[i + 1] == n) {
                int j = i + 1;
                while (j < seq.length && seq[j] == n) {
                    j++;
                }
                i = j;
                out.print(n + " ");
            }
            i++;
        }

        in.close();
        out.close();
    }
}
