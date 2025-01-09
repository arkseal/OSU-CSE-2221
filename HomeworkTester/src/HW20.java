import components.queue.Queue;
import components.queue.Queue1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.stack.Stack1L;

public class HW20 {
    /**
     * Reverses ("flips") {@code this}.
     *
     * @param <T>
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    public static <T> void flip(Stack<T> stack) {
        Queue<T> q = new Queue1L<T>();
        while (stack.length() > 0) {
            q.enqueue(stack.pop());
        }
        while (q.length() > 0) {
            stack.push(q.dequeue());
        }
    }

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    public static <T> void flip(Sequence<T> seq) {
        int left = 0;
        int right = seq.length() - 1;

        if (left < right) {
            T r = seq.remove(right);
            T l = seq.remove(left);

            flip(seq);

            seq.add(left, r);
            seq.add(right, l);
        }
    }

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        Stack<String> s = new Stack1L<String>();
        s.push("1");
        s.push("2");
        s.push("3");

        out.println(s);
        flip(s);
        out.println(s);

        Sequence<String> seq = new Sequence1L<String>();
        seq.add(0, "1");
        seq.add(1, "2");
        seq.add(2, "3");
        out.println(seq);
        flip(seq);
        out.println(seq);

        in.close();
        out.close();
    }
}
