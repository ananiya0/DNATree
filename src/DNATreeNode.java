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
 * Base class for DNA node
 *
 * @author Ananiya Admassu (ananiya0)
 * @version 03/13/2020
 */
public class DNATreeNode {

    private int level;
    
    /**
     * Retrieve Level when called
     * @return the level of the node
     */
    public int getLevel()
    {
        return level;
    }
    
    /**
     * Set the new level to parameter "level"
     * @param level the level of the node
     * the level of the node
     */
    public void setLevel(int level)
    {
        this.level = level;
    }

}
