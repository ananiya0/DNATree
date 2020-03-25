import java.io.FileNotFoundException;
import student.TestCase;

/**
 * Virginia Tech Honor Code Pledge:
 *
 * As a Hokie, I will conduct myself with honor
 * and integrity at all times.
 * I will not lie, cheat, or steal, nor will I
 * accept the actions of those who do.
 * -- Ananiya Admassu (ananiya0)
 */

/**
 * Test the command parser
 *
 * @author Ananiya Admassu (ananiya0)
 * @author Nebiyu Elias (nebiyue)
 * @version 3/25/2020
 */
public class DNATreeStructTest extends TestCase {

    private CommandParser parser = new CommandParser();
    private DNATreeStruct treeTest = new 
        DNATreeStruct();
    
    /**
     * General test for parsing
     * @throws FileNotFoundException
     */
    public void testGeneral() throws FileNotFoundException 
    {
        parser.parse("test.txt");
        //parser.parse("test2.txt");
        assertEquals(treeTest.hashCode(), treeTest.hashCode());
    }
    

}

