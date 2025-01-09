import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.stack.Stack1L;

public class HW17 {
    /**
     * Reverses ("flips") {@code this}.
     *
     * @param <T>
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    public <T> void flip() {
        Stack<T> s = new Stack1L<T>();
        while (this.length() > 0) {
            s.push(this.dequeue());
        }
        while (s.length() > 0) {
            this.enqueue(s.pop());
        }
    }

    /**
     * Reverses ("flips") {@code q}.
     *
     * @param <T>
     * @param Queue<T>
     *            q
     *
     * @updates q
     * @ensures q = rev(#q)
     */
    public static <T> void flip(Queue<T> q) {
        Stack<T> s = new Stack1L<T>();
        while (q.length() > 0) {
            s.push(q.dequeue());
        }
        while (s.length() > 0) {
            q.enqueue(s.pop());
        }
    }

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        in.close();
        out.close();
    }
}
