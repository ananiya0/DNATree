/**
 * Virginia Tech Honor Code Pledge:
 *
 * As a Hokie, I will conduct myself with honor
 * and integrity at all times.
 * I will not lie, cheat, or steal, nor will I
 * accept the actions of those who do.
 * -- Ananiya Admassu (ananiya0)
 * -- Nebiyu Elias (nebiyue)
 */

/**
 * Leaf Node storing DNA sequences
 *
 * @author Ananiya Admassu (ananiya0)
 * @author Nebiyu Elias (nebiyue)
 * @version 03/13/2020
 */
public class LeafNode extends DNATreeNode {

    private String sequence;
    
    /**
     * Constructor
     * @param seq is the DNA sequence
     */
    public LeafNode(String seq) {
        sequence = seq;
    }
    
    /**
     * @return is the node's DNA sequence
     */
    public String getSequence() {
        return sequence;
    }
}
