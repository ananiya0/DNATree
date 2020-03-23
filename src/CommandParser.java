import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * The main class in terms of handling parsing
 * and execution of commands based on the words
 * parsed with parameters.
 * 
 * @author Nebiyu
 * @author Ananiya
 * @version 3/20/2020
 */
public class CommandParser {

    private DNATreeStruct tree = new DNATreeStruct();


    /**
     * 
     * @param filename
     *            is the name of the input file
     * @throws FileNotFoundException
     *             if input file does not exist
     */
    public void parse(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNextLine()) {
            String temp = sc.nextLine();
            String[] line = temp.trim().split("\\s+");

            switch (line[0]) {
                case "insert":
                    this.insert(line[1]);
                    break;
                case "remove":
                    this.remove(line[1]);
                    break;
                case "print":
                    if (line.length == 1) {
                        tree.print('d');
                    }
                    else {
                        tree.print(line[1].charAt(0));
                    }
                    break;
                case "search":
                    this.search(line[1]);
                    break;

                default:
                    // do nothing
            }
        }
        sc.close();

    }


    /**
     * Attempts to insert the sequence into the tree
     * 
     * @param seq
     *            is the sequence
     */
    public void insert(String seq) {
        if (seq.matches("[ACGT]+")) {
            tree.insert(seq);
        }
        else {
            System.out.println("sequence " + seq + " is invalid");
        }

    }


    /**
     * Removes the sequence from the tree if it exists
     * 
     * @param seq
     *            is the sequence
     */
    public void remove(String seq) {
        if (seq.matches("[ACGT]+")) {
            boolean yes = tree.remove(seq);
            if (yes = true) {
                System.out.println("sequence " + seq + " removed");
            }
            else {
                System.out.println("sequence " + seq + " does not exist");
            }
        }
        else {
            System.out.println("sequence " + seq + " is invalid");
        }
        // print something
    }


    /**
     * Searches for and prints matching sequences
     * 
     * @param seq
     *            is the sequence to find
     */
    public void search(String seq) {
        if (seq.matches("[ACGT$]+")) {
            tree.search(seq);
        }
        else {
            System.out.println("sequence " + seq + " is invalid");
        }
    }

}
