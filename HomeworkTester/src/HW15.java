import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class HW15 {
    /**
     * Reports the smallest integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return the smallest integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * min is in entries(q) and
     * for all x: integer
     *     where (x is in entries(q))
     *   (min <= x)
     * </pre>
     */
    private static int min(Queue<Integer> q) {
        int min = q.dequeue();
        int i;
        while (q.length() > 0) {
            i = q.dequeue();
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     * for all x: integer
     *     where (x in in entries(q))
     *   (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMax(Queue<Integer> q) {
        int min = q.dequeue();
        int max = min;
        int i, j;
        while (q.length() >= 2) {
            i = q.dequeue();
            j = q.dequeue();
            if (i > j) {
                if (j < min) {
                    min = j;
                }
                if (i > max) {
                    max = i;
                }
            } else {
                if (i < min) {
                    min = i;
                }
                if (j > max) {
                    max = j;
                }
            }
        }
        return new int[] { min, max };
    }

    private static int[] minAndMax2(Queue<Integer> q) {
        int min = q.dequeue();
        int max = min;
        for (int i : q) {
            if (i < min) {
                min = i;
            }
            if (i > max) {
                max = i;
            }
        }
        return new int[] { min, max };
    }

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        in.close();
        out.close();
    }
}
