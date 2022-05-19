import java.io.FileNotFoundException;
import java.io.IOException;
import student.TestCase;

/**
 * Test for the Rectangle1 class
 * 
 * @author Wenjun Han
 * @version 4.29.22
 *
 */
public class Rectangle1Test extends TestCase {

    private FileReader reader;

    /**
     * set up for tests
     */
    public void setUp() {
        reader = new FileReader();
    }


    /**
     * test given input file
     * 
     * @throws IOException
     *             Throws if anything wrong
     */
    public void testExampleFile() throws IOException {
        reader.readFile("SyntaxTest.txt");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle rejected: (inExistRec_0, 1, 1, -1, -2)"));
    }


    /**
     * test main of rectangle1
     */
    public void testMainInput() {
        try {
            Rectangle1.main(new String[] { "SyntaxTest.txt" });
            Rectangle1.main(new String[] { "1", "2" });
            // non-existing file
            Rectangle1.main(new String[] { "sample5.bin" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(contains(systemOut().getHistory(),
            "Rectangles intersecting region (-5, -5, 20, 20):"));
    }

}
