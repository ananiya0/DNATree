/**
 * Virginia Tech Honor Code Pledge:
 *
 * As a Hokie, I will conduct myself with honor
 * and integrity at all times.
 * I will not lie, cheat, or steal, nor will I
 * accept the actions of those who do.
 * -- Ananiya Admassu (ananiya0)
 * -- Nebiyu Elias nebiyue
 */

/**
 * DNA tree storing DNA sequences under different branches
 *
 * @author Ananiya Admassu (ananiya0)
 * @author Nebiyu Elias (nebiyue)
 * @version 03/18/2020
 */
public class DNATreeStruct {

    private DNATreeNode empty = new EmptyNode();
    private DNATreeNode root;
    private InternalNode internalReference;
    private EmptyNode emptyReference;
    private LeafNode leafReference;


    /**
     * Constructor
     */
    public DNATreeStruct() {
        root = empty;
    }


    /**
     * Attempts to insert the given sequence
     * 
     * @param seq
     *            is the sequence
     */
    public void insert(String seq) {
        if (root == empty) {
            root = new LeafNode(seq);
            System.out.println("sequence " + seq + " inserted at level 0");
        }
        else if (root instanceof LeafNode) {
            String seq2 = ((LeafNode)root).getSequence();
            if (!seq2.equals(seq)) {
                DNATreeNode newNode = new InternalNode(empty);
                ((InternalNode)newNode).setBranch(root, seq2.charAt(0));
                root = newNode;
                int level = insert(seq, root, 0);
                System.out.println("sequence " + seq + " inserted at level "
                    + level);
            }
            else {
                System.out.println("sequence " + seq + " already exists");
            }
        }
        else {
            int level = insert(seq, root, 0);
            if (level == -1) {
                System.out.println("sequence " + seq + " already exists");
            }
            else {
                System.out.println("sequence " + seq + " inserted at level "
                    + level);
            }
        }
    }


    /**
     * Attempts to remove the given sequence
     * 
     * @param seq
     *            is the sequence
     * @return is true if sequence was removed
     */
    public boolean remove(String seq) {
        if (root instanceof EmptyNode) {
            return false;
        }
        else if (root instanceof LeafNode) {
            if (((LeafNode)root).getSequence().equals(seq)) {
                root = empty;
                return true;
            }
        }
        else {
            return remove(seq, root, 0);
        }
        return false;
    }


    /**
     * @param seq
     * @return
     */
    public String search(String seq) {
        
        //Initializing stuff to be used later
        String without$ = seq;
        int indicator = 0;
        int withTeller = 0;
        int i = 0;
        int touched = 0;
        char letter = '\n';
        
        InternalNode intRoot = (InternalNode)root;
        String matches = "";
        
        //root instanceof EmptyNode
        if (root.getClass() == emptyReference.getClass())
        {
            int numba = 1;
            String noneFound = new String("no sequence found");
            return noneFound + "\n" + "# of nodes visited: " + numba;
        }
        
        
        if (seq.charAt(seq.length() - 1) == '$')
        {
            without$ = seq.substring(0, seq.length() - 1);
            seq = seq.substring(0, seq.length() - 1);
            indicator = 1;
            withTeller = 1;
        }
        else
        {
            withTeller = 0;
        }
        
        if (root instanceof LeafNode)
        {
            if (without$.equals(((LeafNode)root).getSequence()))
            {
                matches ="sequence: " + without$;
            }
            matches = "no sequence found";
            
        }
        else
        {
            //InternalNode intRoot = (InternalNode)root;
            while (i < seq.length())
            {
                if (internalReference.getClass() != 
                    intRoot.getBranch(seq.charAt(i)).getClass())
                {
                    break;
                }
                
                intRoot = (InternalNode)intRoot.getBranch(seq.charAt(i));
                i = i + 1;
                touched = touched + 1;              
            }
            
            letter = seq.charAt(i);
            DNATreeNode next = intRoot.getBranch(letter);
            
            if (indicator == 1) 
            {
                if (next.getClass() == leafReference.getClass())
                {
                    if (((LeafNode)next).getSequence().substring(0, seq.length()).equals(seq))
                    {
                        matches = matches + "sequence: " + ((LeafNode)next).getSequence();
                    }
                }
                else
                {
                    matches = "No Sequence found";
                }
                
                touched++;
            }
            
            else
            {
                //if (letter == '$')
                //{
                    
                //}
                if (next.getClass() == leafReference.getClass()
                    && ((LeafNode)next).getSequence().equals(without$)
                    && letter == '$')
                {
                    matches = matches + "sequence: " + ((LeafNode)next).getSequence();
                    touched = touched + 1;
                }
                else
                {
                    matches = "No Sequence found";
                    touched = touched + 1;
                }
            }
        }
        
        return "# of nodes visited: " + touched + "\n" + matches + "\n";
    }


    /**
     * @param c
     */
    //public String print() {
        // TODO Auto-generated method stub

    //}


    /**
     * Attempts to insert a sequence into the tree
     * 
     * @param seq
     *            is the sequence
     * @param node
     *            is the current node
     * @param level
     *            is the level in the tree
     * @return is the level of insertion, or -1 if unsuccesful
     */
    private int insert(String seq, DNATreeNode node, int level) {
        if (seq.length() == level) {
            if (((InternalNode)node).getBranch('$') instanceof EmptyNode) {
                DNATreeNode newNode = new LeafNode(seq);
                // newNode.setLevel(level + 1);
                ((InternalNode)node).setBranch(newNode, '$');
                return level + 1;
            }
            else {
                return -1;
            }
        }
        else {
            if (((InternalNode)node).getBranch(seq.charAt(
                level)) instanceof EmptyNode) {
                DNATreeNode newNode = new LeafNode(seq);
                // newNode.setLevel(level + 1);
                ((InternalNode)node).setBranch(newNode, seq.charAt(level));
                return level + 1;
            }

            else if (((InternalNode)node).getBranch(seq.charAt(
                level)) instanceof LeafNode) {
                DNATreeNode leaf = ((InternalNode)node).getBranch(seq.charAt(
                    level));
                String seq2 = ((LeafNode)leaf).getSequence();
                if (!seq2.equals(seq)) {
                    DNATreeNode newNode = new InternalNode(empty);
                    ((InternalNode)node).setBranch(newNode, seq.charAt(level));
                    insert(seq2, ((InternalNode)node).getBranch(seq.charAt(
                        level)), level + 1);
                    return insert(seq, ((InternalNode)node).getBranch(seq
                        .charAt(level)), level + 1);
                }
                else {
                    return -1;
                }
            }
            else {
                return insert(seq, ((InternalNode)node).getBranch(seq.charAt(
                    level)), level + 1);
            }
        }
    }


    /**
     * Attempts to remove a sequence from the tree
     * 
     * @param seq
     *            is the sequence to be removed
     * @param node
     *            is the current node
     * @param index
     * @return is true if sequence was removed
     */
    private boolean remove(String seq, DNATreeNode node, int index) {
        if (seq.length() == index) {
            if (((InternalNode)node).getBranch('$') instanceof EmptyNode) {
                return false;
            }
            DNATreeNode leaf = ((InternalNode)node).getBranch('$');
            if (((LeafNode)leaf).getSequence().equals(seq)) {
                ((InternalNode)node).setBranch(empty, '$');
                return true;
            }
            return false;
        }
        else {
            if (((InternalNode)node).getBranch(seq.charAt(
                index)) instanceof EmptyNode) {
                return false;
            }
            else if (((InternalNode)node).getBranch(seq.charAt(
                index)) instanceof LeafNode) {
                DNATreeNode leaf = ((InternalNode)node).getBranch(seq.charAt(
                    index));
                if (((LeafNode)leaf).getSequence().equals(seq)) {
                    ((InternalNode)node).setBranch(empty, seq.charAt(index));
                    return true;
                }
            }
            else if (((InternalNode)node).getBranch(seq.charAt(
                index)) instanceof InternalNode) {
                return remove(seq, ((InternalNode)node).getBranch(seq.charAt(
                    index)), index + 1);
            }
        }
        return false;
    }
}
