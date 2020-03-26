import java.util.LinkedList;

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
    private LinkedList<String> list;
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
    public void search(String seq) {
        /*
         * 
         * //Initializing stuff to be used later
         * String without$ = seq;
         * int indicator = 0;
         * int withTeller = 0;
         * int i = 0;
         * int touched = 0;
         * char letter = '\n';
         * 
         * InternalNode intRoot = (InternalNode)root;
         * String matches = "";
         * 
         * //root instanceof EmptyNode
         * if (root.getClass() == emptyReference.getClass())
         * {
         * int numba = 1;
         * String noneFound = new String("no sequence found");
         * return noneFound + "\n" + "# of nodes visited: " + numba;
         * }
         * 
         * 
         * if (seq.charAt(seq.length() - 1) == '$')
         * {
         * without$ = seq.substring(0, seq.length() - 1);
         * seq = seq.substring(0, seq.length() - 1);
         * indicator = 1;
         * withTeller = 1;
         * }
         * else
         * {
         * withTeller = 0;
         * }
         * 
         * if (root instanceof LeafNode)
         * {
         * if (without$.equals(((LeafNode)root).getSequence()))
         * {
         * matches ="sequence: " + without$;
         * }
         * matches = "no sequence found";
         * 
         * }
         * else
         * {
         * //InternalNode intRoot = (InternalNode)root;
         * while (i < seq.length())
         * {
         * if (internalReference.getClass() !=
         * intRoot.getBranch(seq.charAt(i)).getClass())
         * {
         * break;
         * }
         * 
         * intRoot = (InternalNode)intRoot.getBranch(seq.charAt(i));
         * i = i + 1;
         * touched = touched + 1;
         * }
         * 
         * letter = seq.charAt(i);
         * DNATreeNode next = intRoot.getBranch(letter);
         * 
         * if (indicator == 1)
         * {
         * if (next.getClass() == leafReference.getClass())
         * {
         * if (((LeafNode)next).getSequence().substring(0,
         * seq.length()).equals(seq))
         * {
         * matches = matches + "sequence: " + ((LeafNode)next).getSequence();
         * }
         * }
         * else
         * {
         * matches = "No Sequence found";
         * }
         * 
         * touched++;
         * }
         * 
         * else
         * {
         * //if (letter == '$')
         * //{
         * 
         * //}
         * if (next.getClass() == leafReference.getClass()
         * && ((LeafNode)next).getSequence().equals(without$)
         * && letter == '$')
         * {
         * matches = matches + "sequence: " + ((LeafNode)next).getSequence();
         * touched = touched + 1;
         * }
         * else
         * {
         * matches = "No Sequence found";
         * touched = touched + 1;
         * }
         * }
         * }
         * 
         * return "# of nodes visited: " + touched + "\n" + matches + "\n";
         * 
         */

        boolean exact = seq.endsWith("$") ? true : false;
        if (root instanceof EmptyNode) {
            System.out.println("# of nodes visited: 0\n"
                + "no sequence found");
        }
        else if (root instanceof LeafNode) {
            String seq2 = ((LeafNode)root).getSequence();
            System.out.println("# of nodes visited: 1");
            if (exact) {
                if (seq2.equals(seq.substring(0, seq.length() - 1))) {
                    System.out.println("sequence: " + seq2);
                }
                else {
                    System.out.println("no sequence found");
                }
            }
            else {
                if (seq2.startsWith(seq)) {
                    System.out.println("sequence: " + seq2);
                }
                else {
                    System.out.println("no sequence found");
                }
            }
        }
        else {
            list = new LinkedList<String>();
            int visits = 1;
            if (exact) {
                seq = seq.substring(0, seq.length() - 1);
                visits = searchExact(seq, root, 0, 1);
            }
            else {
                visits = search(seq, root, 0, visits, false);
            }
            System.out.println("# of nodes visited: " + visits);
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("sequence: " + list.get(i));
                }
            }
            else {
                System.out.println("no sequence found");
            }
        }
    }

    /**
     * @param c
     */
    // public String print() {
    // TODO Auto-generated method stub

    // }


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


    /**
     * Searches the tree for the exact matching sequence, starting from internal
     * node
     * 
     * @param seq
     *            is the sequence
     * @param node
     *            is the internal node
     * @param level
     *            is the level in the tree
     * @param visits
     *            is the number of nodes visited
     * @return is visits
     */
    private int searchExact(
        String seq,
        DNATreeNode node,
        int level,
        int visits) {
        if (seq.length() == level) {
            if (((InternalNode)node).getBranch('$') instanceof EmptyNode) {
                // do nothing
            }
            DNATreeNode leaf = ((InternalNode)node).getBranch('$');
            if (((LeafNode)leaf).getSequence().equals(seq)) {
                list.add(seq);
            }
            return visits + 1;
        }
        else {
            if (((InternalNode)node).getBranch(seq.charAt(
                level)) instanceof EmptyNode) {
                // do nothing
            }
            else if (((InternalNode)node).getBranch(seq.charAt(
                level)) instanceof LeafNode) {
                DNATreeNode leaf = ((InternalNode)node).getBranch(seq.charAt(
                    level));
                if (((LeafNode)leaf).getSequence().equals(seq)) {
                    list.add(seq);
                }
            }
            else if (((InternalNode)node).getBranch(seq.charAt(
                level)) instanceof InternalNode) {
                return searchExact(seq, ((InternalNode)node).getBranch(seq
                    .charAt(level)), level + 1, visits + 1);
            }
        }
        return visits + 1;
    }


    /**
     * Searches the tree for sequences with the given prefix, starting from
     * internal node
     * 
     * @param seq
     *            is the sequence
     * @param node
     *            is the internal node
     * @param level
     *            is the level in the tree
     * @param visits
     *            is the number of nodes visited
     * @param flag
     *            is an indicator for when we reach the level of the tree where
     *            the sequence ends
     * @return is visits
     */
    private int search(
        String seq,
        DNATreeNode node,
        int level,
        int visits,
        boolean flag) {

        flag = level >= seq.length();

        if (flag) {
            String branches = "ACGT$";
            for (int i = 0; i < branches.length(); i++) {
                if (((InternalNode)node).getBranch(branches.charAt(
                    i)) instanceof EmptyNode) {
                    visits++;
                }
                else if (((InternalNode)node).getBranch(branches.charAt(
                    i)) instanceof LeafNode) {
                    DNATreeNode leaf = ((InternalNode)node).getBranch(branches
                        .charAt(i));
                    String seq2 = ((LeafNode)leaf).getSequence();
                    if (seq2.startsWith(seq)) {
                        list.add(seq2);
                    }
                    visits++;
                }
                else {
                    visits = search(seq, ((InternalNode)node).getBranch(branches
                        .charAt(i)), level + 1, visits + 1, flag);
                }
            }
        }

        else {
            if (((InternalNode)node).getBranch(seq.charAt(
                level)) instanceof EmptyNode) {
                visits++;
            }
            else if (((InternalNode)node).getBranch(seq.charAt(
                level)) instanceof LeafNode) {
                DNATreeNode leaf = ((InternalNode)node).getBranch(seq.charAt(
                    level));
                String seq2 = ((LeafNode)leaf).getSequence();
                if (seq2.startsWith(seq)) {
                    list.add(seq2);
                }
                visits++;
            }
            else {
                return search(seq, ((InternalNode)node).getBranch(seq.charAt(
                    level)), level + 1, visits + 1, flag);
            }
        }
        return visits;
    }

}
