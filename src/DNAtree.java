/**
 * Virginia Tech Honor Code Pledge:
 *
 * As a Hokie, I will conduct myself with honor
 * and integrity at all times.
 * I will not lie, cheat, or steal, nor will I
 * accept the actions of those who do.
 * -- Ananiya Admassu (ananiya0)
 */
import java.io.FileNotFoundException;

/**
 * Main file used to call the parser, into command parser class
 * 
 * @author Nebiyu nebiyue
 * @author Ananiya ananiya0
 * @version 3/20/2020
 */
public class DNAtree {

    /**
     * Compiler: jdk 1.8.0_162
     * 
     * Runs the DNAtree program, uses default filename if args is empty
     * 
     * @param args
     *            argument to be taken
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        CommandParser parser = new CommandParser();
        if (args.length >= 1) {
            parser.parse(args[0]);
        }
        else {
            parser.parse("test2.txt");
        }
    }
}
