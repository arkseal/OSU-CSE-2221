import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class HW2 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SimpleWriter out = new SimpleWriter1L();

        int i = 0, j = 10, n = 0;
        while (i != j) {
            i = i + 2;
            j = j - 2;
            n++;
            out.println(i + " " + j + " " + n);
        }
        out.close();
    }

}
