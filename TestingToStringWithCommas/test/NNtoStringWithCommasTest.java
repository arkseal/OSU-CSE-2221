import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

public class NNtoStringWithCommasTest {

    @Test
    public void test1() {
        NaturalNumber n = new NaturalNumber2("1234567");
        NaturalNumber a = new NaturalNumber2();
        a.copyFrom(n);

        String s = redirectToMethodUnderTest(n);
        System.out.println(s);

        assertEquals(s, "1,234,567");
        assertEquals(a, n);
    }

    @Test
    public void test2() {
        NaturalNumber n = new NaturalNumber2(0);
        String s = redirectToMethodUnderTest(n);
        System.out.println(s);
        assertEquals("0", s);
    }

    @Test
    public void test3() {
        NaturalNumber n = new NaturalNumber2("1000000");
        String s = redirectToMethodUnderTest(n);
        System.out.println(s);
        assertEquals("1,000,000", s);
    }

    @Test
    public void test4() {
        NaturalNumber n = new NaturalNumber2("9999");
        String s = redirectToMethodUnderTest(n);
        System.out.println(s);
        assertEquals("9,999", s);
    }

    @Test
    public void test5() {
        NaturalNumber n = new NaturalNumber2("123456");
        String s = redirectToMethodUnderTest(n);
        System.out.println(s);
        assertEquals("123,456", s);
    }

    @Test
    public void test6() {
        NaturalNumber n = new NaturalNumber2("812009000060000");
        String s = redirectToMethodUnderTest(n);
        System.out.println(s);
        assertEquals("812,009,000,060,000", s);
    }

    @Test
    public void test7() {
        NaturalNumber n = new NaturalNumber2("999999999999999999999999");
        String s = redirectToMethodUnderTest(n);
        System.out.println(s);
        assertEquals("999,999,999,999,999,999,999,999", s);
    }

    @Test
    public void test8() {
        NaturalNumber n = new NaturalNumber2("100000000000000000000000");
        String s = redirectToMethodUnderTest(n);
        System.out.println(s);
        assertEquals("100,000,000,000,000,000,000,000", s);
    }

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas5.toStringWithCommas(n);
    }

}
