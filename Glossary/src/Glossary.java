import java.util.Arrays;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Class to generate HTML code for a Glossary from an input text file.
 *
 * @author Aarush Katta
 *
 */
public final class Glossary {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Glossary() {
    }

    /**
     * Returns a map of the term and definitions read from the
     * {@code inputFile}.
     *
     * @param inputFile
     *            file name of the input file
     * @return a map of the term and its definitions
     * @requires inputFile must exist
     * @ensures map contains all the terms and definitions from the input file
     */
    public static Map<String, String> processInput(String inputFile) {
        // Create SimpleReader to read input file
        SimpleReader in = new SimpleReader1L(inputFile);

        // Create a map containing the word and its definition
        Map<String, String> map = new Map1L<String, String>();

        while (!in.atEOS()) {
            // Read word
            String term = in.nextLine();

            // Read first line of definition
            String l = in.nextLine();
            String definition = l;

            // If more lines exist, add to definition

            while (!l.isEmpty() && !in.atEOS()) {
                l = in.nextLine();
                if (!l.isEmpty()) {
                    definition += " " + l;
                }
            }

            // add to map
            map.add(term, definition);
        }

        // Close SimpleReader
        in.close();

        // return map
        return map;
    }

    /**
     * Returns the {@code definition} with the intended links for each term that
     * exists in the definition.
     *
     * @param definition
     *            the definition for a term
     * @param map
     *            the map for all terms and definitions
     * @return definition with links for all the terms within the definition
     */
    public static String processDefinition(String definition, Map<String, String> map) {
        // Split definition into words
        String[] words = definition.split(" ");

        // Iterate through all the words
        for (int i = 0; i < words.length; i++) {
            String w = words[i];

            // Get last character and check if it is punctuation
            char last = w.charAt(w.length() - 1);
            boolean isPunctuation = last == '.' || last == ',' || last == '!'
                    || last == '?';

            // remove last letter if it's punctuation
            if (isPunctuation) {
                w = w.substring(0, w.length() - 1);
            }

            // if the word is a term in the glossary add a link to it's html
            if (map.hasKey(w)) {
                words[i] = String.format("<a href=\"%s.html\">%1$s</a>", w);

                // add the punctuation back to the word if it was removed
                if (isPunctuation) {
                    words[i] += last;
                }
            }
        }

        // Combine the words into the complete definition and return
        return String.join(" ", words);
    }

    /**
     * Outputs the glossary HTML code in the {@code outputFolder} for the
     * {@code term} containing its {@code definition}.
     *
     * @param term
     *            the glossary term
     * @param definition
     *            the glossary definition for the term
     * @param map
     *            the map of all terms and definitions
     * @param outputFolder
     *            the output folder where to make the HTML files
     * @requires outputFolder must exist
     * @ensures map = #map
     */
    public static void generateTermHTML(String term, String definition,
            Map<String, String> map, String outputFolder) {
        // Create SimpleWriter to output to file
        SimpleWriter out = new SimpleWriter1L(outputFolder + "/" + term + ".html");

        // Print HTML
        out.print(String.format(
                "<html>%n" + "<head>%n" + "<title>%s</title>%n" + "</head>%n" + "<body>%n"
                        + "<h2><b><i><font color=\"red\">%1$s</font></i></b></h2>%n"
                        + "<blockquote>%s</blockquote>%n" + "<hr />%n" + "  <main>%n"
                        + "<p>Return to <a href=\"index.html\">index</a>.</p>%n"
                        + "  </main>%n" + "</body>%n" + "</html>%n",
                term, processDefinition(definition, map)));

        // Close SimpleWriter
        out.close();
    }

    /**
     * Outputs the glossary HTML index.html file inside the {@code outputFolder}
     * containing all the terms in {@code map}.
     *
     * @param map
     *            map of all the terms and definitions
     * @param outputFolder
     *            the output folder where to make index.html
     * @requires outputFolder must exist
     * @ensures map = #map
     */
    public static void generateIndexHTML(Map<String, String> map, String outputFolder) {
        // Create SimpleWriter
        SimpleWriter out = new SimpleWriter1L(outputFolder + "/index.html");

        // Print Starting HTML
        out.print("<html>\n" + "<head>\n" + "<title>Glossary</title>\n" + "</head>\n"
                + "<body>\n" + "<h2>Glossary</h2>\n" + "<hr />\n" + "  <main>\n"
                + "<h3>Index</h3>\n" + "<ul>\n");

        // Get all terms and add to a String array
        int i = 0;
        String[] terms = new String[map.size()];

        for (Pair<String, String> p : map) {
            String term = p.key();
            terms[i++] = term;
        }

        // Sort in alphabetical order
        Arrays.sort(terms);

        // Print all term HTML
        for (String term : terms) {
            out.print(String.format("<li><a href=\"%s.html\">%1$s</a></li>%n", term));
        }

        // Print Ending HTML
        out.print("</ul>\n" + "  </main>\n" + "</body>\n" + "</html>\n");

        // Close SimpleWriter
        out.close();
    }

    /**
     * Given a file name (relative to the path where the application is running)
     * that contains a glossary, including terms and definitions which could be
     * written in several lines. This generates a website glossary including an
     * index.html with all the terms and links to another html file which
     * contains the term, its definition which then also contains links to any
     * term mentioned in the definition, all files are created in a folder that
     * is inputed.
     *
     * @param args
     *            the command line arguments: not used
     */
    public static void main(String[] args) {
        // Create SimpleReader and SimpleWriter
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // Get input file name
        out.print("Enter an input file name: ");
        String inputFile = in.nextLine();

        // Get output folder name
        out.print("Enter an output folder name: ");
        String outputFolder = in.nextLine();

        // Process inputFile into a map
        Map<String, String> map = processInput(inputFile);

        // Create index.html
        generateIndexHTML(map, outputFolder);

        // Create all term.html for all terms
        for (Pair<String, String> p : map) {
            String term = p.key();
            String definition = p.value();
            generateTermHTML(term, definition, map, outputFolder);
        }

        // Close SimpleReader and SimpleWriter
        in.close();
        out.close();
    }
}
