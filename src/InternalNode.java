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
 * @version 03/13/2020
 */
public class InternalNode extends DNATreeNode {

    private DNATreeNode A,C,G,T,$;
    
    /**
     * Constructor
     * @param empty is the flyweight node
     */
    public InternalNode(DNATreeNode empty) {
        A = empty;
        C = empty;
        G = empty;
        T = empty;
        $ = empty;
    }
    
    /**
     * returns a branch of the internal node
     * @param branch is the sequence branch
     * @return is the node on the branch matching the character
     */
    public DNATreeNode getBranch(char branch) {
        switch(branch) {
            case 'A':
                return A;
            case 'C':
                return C;
            case 'G':
                return G;
            case 'T':
                return T;
            case '$':
                return $;
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
                A = node;
                return true;
            case 'C':
                C = node;
                return true;
            case 'G':
                G = node;
                return true;
            case 'T':
                T = node;
                return true;
            case '$':
                $ = node;
                return true;
            default:
                return false;
        }       
    }
}
