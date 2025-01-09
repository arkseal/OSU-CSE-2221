import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @Test
    public void combination1() {
        String str1 = "test123";
        String str2 = "123woohoo";
        int overlap = 3;
        String expected = "test123woohoo";
        String actual = StringReassembly.combination(str1, str2, overlap);
        assertEquals(expected, actual);
    }

    @Test
    public void combination2() {
        String str1 = "this a test!";
        String str2 = "!tset a siht";
        int overlap = 1;
        String expected = "this a test!tset a siht";
        String actual = StringReassembly.combination(str1, str2, overlap);
        assertEquals(expected, actual);
    }

    @Test
    public void addToSetAvoidingSubstrings1() {
        String str1 = "est";
        Set<String> set = setUpStringSet();
        Set<String> expected = setUpStringSet();
        StringReassembly.addToSetAvoidingSubstrings(set, str1);
        assertEquals(expected, set);
    }

    @Test
    public void addToSetAvoidingSubstrings2() {
        String str1 = "RANDOM";
        Set<String> set = setUpStringSet();
        Set<String> expected = setUpStringSet();
        expected.add(str1);
        StringReassembly.addToSetAvoidingSubstrings(set, str1);
        assertEquals(expected, set);
    }

    @Test
    public void addToSetAvoidingSubstrings3() {
        String str1 = "testtest";
        String str2 = "test";
        Set<String> set = setUpStringSet();
        Set<String> expected = setUpStringSet();
        expected.add(str1);
        expected.remove(str2);
        StringReassembly.addToSetAvoidingSubstrings(set, str1);
        assertEquals(expected, set);
    }

    @Test
    public void linesFromInput1() {
        String[] strings = new String[] { "Bucks -- Beat", "Go Bucks", "o Bucks -- B",
                "Beat Mich", "Michigan~" };
        Set<String> expected = setUpStringSet(strings);

        SimpleReader reader = new SimpleReader1L("cheer-8-2.txt");
        Set<String> actual = StringReassembly.linesFromInput(reader);
        reader.close();

        assertEquals(expected, actual);
    }

    @Test
    public void printWithLineSeparators1() {
        String str1 = "Whoo~Hooo~:)";
        String[] expected = new String[] { "Whoo", "Hooo", ":)" };

        SimpleWriter out = new SimpleWriter1L("test.txt");
        StringReassembly.printWithLineSeparators(str1, out);
        out.close();

        SimpleReader in = new SimpleReader1L("test.txt");
        String[] actual = new String[] { in.nextLine(), in.nextLine(), in.nextLine() };

        assertEquals(true, in.atEOS());
        in.close();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void printWithLineSeparators2() {
        String str1 = "test";
        String expected = "test";

        SimpleWriter out = new SimpleWriter1L("test.txt");
        StringReassembly.printWithLineSeparators(str1, out);
        out.close();

        SimpleReader in = new SimpleReader1L("test.txt");
        String actual = in.nextLine();

        assertEquals(true, in.atEOS());
        in.close();
        assertEquals(expected, actual);
    }

    public static Set<String> setUpStringSet() {
        String[] strings = new String[] { "example", "test", "this is a string",
                "YIPPIEEE" };
        return setUpStringSet(strings);
    }

    public static Set<String> setUpStringSet(String[] strings) {
        Set<String> set = new Set1L<String>();
        for (String s : strings) {
            set.add(s);
        }

        return set;
    }
}
