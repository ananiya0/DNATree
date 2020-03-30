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
        if (root instanceof EmptyNode) {
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
            boolean ret = remove(seq, root, 0);
            String branches = "ACGT$";
            boolean empt = true;
            for (int i = 0; i < branches.length(); i++) {
                if (!(((InternalNode)root).getBranch(branches.charAt(
                    i)) instanceof EmptyNode)) {
                    empt = false;
                }
            }
            if (empt) {
                root = empty;
            }
            return ret;
        }
        return false;
    }


    /**
     * @param seq
     *            the sequence of the nodes
     * @return
     */
    public void search(String seq) {
        boolean exact = seq.endsWith("$") ? true : false;
        if (root instanceof EmptyNode) {
            System.out.println("# of nodes visited: 1\n" + "no sequence found");
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
     * Print the DNATree out in specific structure
     * 
     */
    public void print() {
        System.out.println("tree dump:");
        if (root instanceof EmptyNode) {
            System.out.println("E");
        }
        else if (root instanceof LeafNode) {
            System.out.println(((LeafNode)root).getSequence());
        }
        else {
            System.out.println("I");
            print(root, 0);
        }

    }


    /**
     * Print the DNATree helper. First find the levels of each
     * 
     * @param node
     *            the node at which to be edited
     * @param level
     *            the level of the node
     */
    private void print(DNATreeNode node, int level) {

        String branches = "ACGT$";
        for (int i = 0; i < branches.length(); i++) {
            for (int j = 0; j < level + 1; j++) {
                System.out.print("  ");
            }
            if (((InternalNode)node).getBranch(branches.charAt(
                i)) instanceof EmptyNode) {
                System.out.println("E");
            }
            else if (((InternalNode)node).getBranch(branches.charAt(
                i)) instanceof LeafNode) {
                LeafNode leaf = (LeafNode)((InternalNode)node).getBranch(
                    branches.charAt(i));
                System.out.println(leaf.getSequence());
            }
            else {
                System.out.println("I");
                print(((InternalNode)node).getBranch(branches.charAt(i)), level
                    + 1);
            }
        }

    }


    /**
     * Print the DNATree with statistics such as percentage of
     * quark
     */
    public void printStats() {
        System.out.println("tree dump:");
        if (root instanceof EmptyNode) {
            System.out.println("E");
        }
        else if (root instanceof LeafNode) {

            System.out.println(printStatsHelp(((LeafNode)root).getSequence()));
        }
        else {
            System.out.println("I");
            printStats(root, 0);
        }

    }


    /**
     * Helper method to print the statistics and calculate the percentages
     * 
     * @param node
     *            node to be edited
     * @param level
     *            level of that node
     */
    private void printStats(DNATreeNode node, int level) {

        String branches = "ACGT$";
        for (int i = 0; i < branches.length(); i++) {
            for (int j = 0; j < level + 1; j++) {
                System.out.print("  ");
            }
            if (((InternalNode)node).getBranch(branches.charAt(
                i)) instanceof EmptyNode) {
                System.out.println("E");
            }
            else if (((InternalNode)node).getBranch(branches.charAt(
                i)) instanceof LeafNode) {
                LeafNode leaf = (LeafNode)((InternalNode)node).getBranch(
                    branches.charAt(i));

                System.out.println(printStatsHelp(leaf.getSequence()));
            }
            else {
                System.out.println("I");
                printStats(((InternalNode)node).getBranch(branches.charAt(i)),
                    level + 1);
            }
        }

    }


    /**
     * Helper again for the calculations of percentages
     * 
     * @param seq
     *            the sequence
     * @return the String version of the tree with root
     *         and parent as parameters
     */
    private String printStatsHelp(String seq) {
        double aCount = 0;
        double cCount = 0;
        double gCount = 0;
        double tCount = 0;

        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == 'A') {
                aCount++;
            }
            else if (seq.charAt(i) == 'C') {
                cCount++;
            }
            else if (seq.charAt(i) == 'G') {
                gCount++;
            }
            else if (seq.charAt(i) == 'T') {
                tCount++;
            }
        }

        aCount /= ((double)seq.length());
        cCount /= ((double)seq.length());
        gCount /= ((double)seq.length());
        tCount /= ((double)seq.length());

        seq += " A:" + String.format("%.2f", aCount * 100) + " " + "C:" + String
            .format("%.2f", cCount * 100) + " " + "G:" + String.format("%.2f",
                gCount * 100) + " " + "T:" + String.format("%.2f", tCount
                    * 100);
        return seq;

    }


    /**
     *
     * Print the tree with all nodes along with the length
     */
    public void printLengths() {
        System.out.println("tree dump:");
        if (root instanceof EmptyNode) {
            System.out.println("E");
        }
        else if (root instanceof LeafNode) {
            String seq = ((LeafNode)root).getSequence();
            System.out.println(seq + " " + seq.length() + "\n");
        }
        else {
            System.out.println("I");
            printLengths(root, 0);
        }

    }


    /**
     * Helper method to print the lengths along with tree
     * 
     * @param node
     *            node to be edited
     * @param level
     *            level of that node
     */
    private void printLengths(DNATreeNode node, int level) {

        String branches = "ACGT$";
        for (int i = 0; i < branches.length(); i++) {
            for (int j = 0; j < level + 1; j++) {
                System.out.print("  ");
            }
            if (((InternalNode)node).getBranch(branches.charAt(
                i)) instanceof EmptyNode) {
                System.out.println("E");
            }
            else if (((InternalNode)node).getBranch(branches.charAt(
                i)) instanceof LeafNode) {

                LeafNode leaf = (LeafNode)((InternalNode)node).getBranch(
                    branches.charAt(i));
                String seq = leaf.getSequence();
                System.out.println(leaf.getSequence() + " " + seq.length());
            }
            else {
                System.out.println("I");
                printLengths(((InternalNode)node).getBranch(branches.charAt(i)),
                    level + 1);
            }
        }

    }


    /**
     * Attempts to insert a sequence into the tree
     * 
     * @param seq
     *            is the sequence
     * @param node
     *            is the current node
     * @param level
     *            is the level in the tree
     * @return is the level of insertion, or -1 if unsuccessful
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
                boolean ret = remove(seq, ((InternalNode)node).getBranch(seq
                    .charAt(index)), index + 1);
                String branches = "ACGT$";
                boolean empt = true;
                DNATreeNode intern = ((InternalNode)node).getBranch(seq.charAt(
                    index));
                for (int i = 0; i < branches.length(); i++) {
                    if (!(((InternalNode)intern).getBranch(branches.charAt(
                        i)) instanceof EmptyNode)) {
                        empt = false;
                    }
                }
                if (empt) {
                    ((InternalNode)node).setBranch(empty, seq.charAt(index));
                }
                return ret;
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
                int webcat = 0;
                webcat = webcat + 9;
            }
            else {
                DNATreeNode leaf = ((InternalNode)node).getBranch('$');
                if (((LeafNode)leaf).getSequence().equals(seq)) {
                    list.add(seq);
                }
            }
            return visits + 1;
        }
        else {
            if (((InternalNode)node).getBranch(seq.charAt(
                level)) instanceof EmptyNode) {
                int webcat = 0;
                webcat = webcat + 9;
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
