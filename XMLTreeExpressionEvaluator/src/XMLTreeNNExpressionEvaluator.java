import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires * [exp is a subtree of a well-formed XML arithmetic expression]
     *           and [the label of the root of exp is not "expression"]
     *
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        if (exp.label().equals("number")) {
            return new NaturalNumber2(exp.attributeValue("value"));
        }
        NaturalNumber a = evaluate(exp.child(0));
        NaturalNumber b = evaluate(exp.child(1));

        String operation = exp.label();
        if (operation.equals("plus")) {
            a.add(b);
        } else if (operation.equals("minus")) {
            if (a.compareTo(b) < 0) {
                Reporter.fatalErrorToConsole(
                        "Subtraction results in negative number error");
            }
            a.subtract(b);
        } else if (operation.equals("times")) {
            a.multiply(b);
        } else if (operation.equals("divide")) {
            if (b.isZero()) {
                Reporter.fatalErrorToConsole("Divide by 0 error");
            }
            a.divide(b);
        }
        return a;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
