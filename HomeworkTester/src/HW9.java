import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class HW9 {
    private static void swap3(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber tmp = n1;
        n1 = n2;
        n2 = tmp;
    }

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        NaturalNumber m = new NaturalNumber1L(143);
        NaturalNumber n = new NaturalNumber1L(70);

        m.transferFrom(n);

        out.println(m + "____" + n);

        NaturalNumber x = new NaturalNumber2(41), y = new NaturalNumber2(78);
        swap3(x, y);
        out.println(x + " " + y);

        in.close();
        out.close();
    }
}
