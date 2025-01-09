import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Tests class Glossary.java.
 */
public class GlossaryTest {

    /**
     * Setups up textInput.txt and testOutput.
     */
    @Before
    public void setup() {
        // Setup input file
        // create simplewriter
        SimpleWriter out = new SimpleWriter1L("testInput.txt");

        // print content
        out.println("sample");
        out.println("this is a definition");
        out.println();

        out.println("definition");
        out.println("the meaning of");
        out.print("a word");

        // close simplewriter
        out.close();

        // Setup output folder
        File outputFolder = new File("testOutput");
        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }
    }

    /**
     * Tests method processInput.
     */
    @Test
    public void testProcessInput() {
        Map<String, String> result = Glossary.processInput("testInput.txt");

        // Check size
        assertEquals(2, result.size());

        // Check sample
        assertTrue(result.hasKey("sample"));
        assertEquals("this is a definition", result.value("sample"));

        // check definition
        assertTrue(result.hasKey("definition"));
        assertEquals("the meaning of a word", result.value("definition"));
    }

    /**
     * Tests method processDefinition using a normal example.
     */
    @Test
    public void testProcessDefinition1() {
        Map<String, String> map = setupMap();

        String processed = Glossary.processDefinition("sample is a test term", map);
        assertEquals("<a href=\"sample.html\">sample</a> is a test term", processed);
    }

    /**
     * Tests method processDefinition using an exmaple with punctuation.
     */
    @Test
    public void testProcessDefinition2() {
        // WITH PUNCTUATION
        Map<String, String> map = setupMap();

        String processed = Glossary.processDefinition("sample, is a test term", map);
        assertEquals("<a href=\"sample.html\">sample</a>, is a test term", processed);
    }

    /**
     * Tests method processDefinition using an example not requiring any change.
     */
    @Test
    public void testProcessDefinition3() {
        Map<String, String> map = setupMap();

        String processed = Glossary.processDefinition("this is a test term", map);
        assertEquals("this is a test term", processed);
    }

    /**
     * Tests method generateTermHTML.
     */
    @Test
    public void testGenerateTermHTML() {
        Map<String, String> map = setupMap();

        Glossary.generateTermHTML("sample", "this is a definition", map, "testOutput");

        // check if file exists
        File generatedFile = new File("testOutput/sample.html");
        assertTrue(generatedFile.exists());

        // read file contents
        SimpleReader in = new SimpleReader1L("testOutput/sample.html");
        String content = "";
        while (!in.atEOS()) {
            content += in.nextLine() + "\n";
        }
        in.close();

        // check if term, definition, and link to index exists
        assertTrue(content.contains("<title>sample</title>"));
        assertTrue(content.contains("<font color=\"red\">sample</font>"));
        assertTrue(
                content.contains("this is a <a href=\"definition.html\">definition</a>"));
        assertTrue(content.contains("<a href=\"index.html\">index</a>"));
    }

    /**
     * Tests method generateIndexHTML.
     */
    @Test
    public void testGenerateIndexHTML() {
        Map<String, String> map = setupMap();

        Glossary.generateIndexHTML(map, "testOutput");

        // check if file exists
        File indexFile = new File("testOutput/index.html");
        assertTrue(indexFile.exists());

        // read file contents
        SimpleReader in = new SimpleReader1L("testOutput/index.html");
        String content = "";
        while (!in.atEOS()) {
            content += in.nextLine() + "\n";
        }
        in.close();

        // check if glossary and all terms exists
        assertTrue(content.contains("<title>Glossary</title>"));
        assertTrue(content.contains("<li><a href=\"sample.html\">sample</a></li>"));
        assertTrue(
                content.contains("<li><a href=\"definition.html\">definition</a></li>"));
    }

    /**
     * Returns a map to use for testing.
     *
     * @return map of the example terms and example definitions
     */
    private static Map<String, String> setupMap() {
        // Create map
        Map<String, String> map = new Map1L<String, String>();

        // add 2 terms and definitions
        map.add("sample", "this is a definition");
        map.add("definition", "the meaning of a word");

        return map;
    }

    /**
     * Clean up testing files.
     */
    @After
    public void cleanup() {
        // Cleanup input file
        new File("testInput.txt").delete();

        // Cleanup output folder and files
        File folder = new File("testOutput");
        if (folder.exists()) {
            for (File file : folder.listFiles()) {
                file.delete();
            }
            folder.delete();
        }
    }
}
