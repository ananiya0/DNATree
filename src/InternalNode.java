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
 * Internal branching node
 *
 * @author Ananiya Admassu (ananiya0)
 * @author Nebiyu Elias (nebiyue)
 * @version 03/13/2020
 */
public class InternalNode extends DNATreeNode {

    private DNATreeNode a;
    private DNATreeNode c;
    private DNATreeNode g;
    private DNATreeNode t;
    private DNATreeNode end;
    
    /**
     * Constructor
     * @param empty is the flyweight node
     */
    public InternalNode(DNATreeNode empty) {
        //setLevel(depth);
        a = empty;
        c = empty;
        g = empty;
        t = empty;
        end = empty;
        
    }
    
    /**
     * returns a branch of the internal node
     * @param branch is the sequence branch
     * @return is the node on the branch matching the character
     */
    public DNATreeNode getBranch(char branch) {
        switch(branch) {
            case 'A':
                return a;
            case 'C':
                return c;
            case 'G':
                return g;
            case 'T':
                return t;
            case '$':
                return end;
            default:
                return null;
        }       
    }
    
    /**
     * Sets the value of a branch of the internal node
     * @param node is the value to be stored
     * @param branch is the branch
     * @return is true if node was set successfully
     */
    public boolean setBranch(DNATreeNode node, char branch) {
        switch(branch) {
            case 'A':
                a = node;
                return true;
            case 'C':
                c = node;
                return true;
            case 'G':
                g = node;
                return true;
            case 'T':
                t = node;
                return true;
            case '$':
                end = node;
                return true;
            default:
                return false;
        }       
    }
}
