import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The class containing the main method.
 *
 * @author Wenjun Han
 * @version 4.20.22
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class Rectangle1 {

    /**
     * @param args
     *            Command line parameters
     * @throws IOException
     *             Throw if anything wrong with I/O
     * @throws FileNotFoundException
     *             Throw if file not found
     */
    public static void main(String[] args)
        throws IOException,
        FileNotFoundException {
        if (args.length == 1) {
            FileReader reader = new FileReader();
            reader.readFile(args[0]);
        }
        else {
            FileReader reader = new FileReader();
            reader.readFile("SyntaxTest.txt");
        }
    }
}