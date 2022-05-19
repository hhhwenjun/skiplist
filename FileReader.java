import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reader for the text input file
 * 
 * @author Wenjun Han(hwenjun)
 * @version 4.22.22
 *
 */
public class FileReader {

    /** controller for reader **/
    private ListController controller;

    /**
     * Constructor of file reader
     */
    public FileReader() {
        controller = new ListController();
    }


    /**
     * Read the file and output the answer
     * 
     * @param fileName
     *            The name of text file
     * @throws FileNotFoundException
     *             Throw when the file is not found
     */
    public void readFile(String fileName) throws FileNotFoundException {
        File newFile = new File(fileName);
        Scanner file = new Scanner(newFile);

        while (file.hasNextLine()) {
            String line = file.nextLine();
            line = line.trim();
            if (!line.equals("")) {
                String cleanString = formatter(line);
                expressHandler(cleanString);
            }
        }
        file.close();
    }


    /**
     * Use controller to do the corresponding operation
     * 
     * @param inputs
     *            The input string array that is cleaned by formatter
     */
    public void expressHandler(String inputs) {
        String[] express = inputs.split(" ");
        String operator = express[0];

        if (operator.equals("insert")) {
            if (express.length == 6) {
                controller.insert(express[1], express[2], express[3],
                    express[4], express[5]);
            }
        }
        else if (operator.equals("remove")) {
            if (express.length == 5) {
                controller.removeByContent(express[1], express[2], express[3],
                    express[4]);
            }
            else if (express.length == 2) {
                controller.remove(express[1]);
            }
        }
        else if (operator.equals("regionsearch")) {
            if (express.length == 5) {
                controller.regionSearch(express[1], express[2], express[3],
                    express[4]);
            }
        }
        else if (operator.equals("intersections")) {
            controller.intersections();
        }
        else if (operator.equals("search")) {
            if (express.length == 2) {
                controller.search(express[1]);
            }
        }
        else if (operator.equals("dump")) {
            controller.dump();
        }
    }


    /**
     * Clean the data string and call cleanString method to clean 0's
     * 
     * @param line
     *            The raw line data
     * @return The cleaned string array data
     */
    public String formatter(String line) {
        line = line.trim();
        String[] rawData = line.split(" ");
        String strings = "";
        for (String string : rawData) {
            string = string.trim();
            if (!string.equals("")) {
                strings += string;
                strings += " ";
            }
        }
        strings = strings.trim();
        return strings;
    }

}
