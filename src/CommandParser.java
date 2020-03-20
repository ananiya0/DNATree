import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *  The main class in terms of handling parsing
 *  and execution of commands based on the words
 *  parsed with parameters. 
 * @author Nebiyu
 * @author Ananiya 
 * @version 2/20/2020
 */
public class CommandParser {

    private DNAtree tree =
        new DNAtree< >();

    /**
     * 
     * @param filename
     *            is the name of the input file
     * @throws FileNotFoundException
     *             if input file does not exist
     */
    public void parse(String filename) throws FileNotFoundException {
        /*Scanner sc = new Scanner(new File(filename));
        while (sc.hasNextLine()) {
            String temp = sc.nextLine();
            String[] line = temp.trim().split("\\s+");

            switch (line[0]) {
                case "insert":
                    this.insert(line[1], Integer.parseInt(line[2]), Integer
                        .parseInt(line[3]), Integer.parseInt(line[4]),
                        Integer.parseInt(line[5]));
                    break;
                case "remove":
                    if (line.length > 2) {
                        this.remove(Integer.parseInt(line[1]), Integer
                            .parseInt(line[2]), Integer.parseInt(line[3]),
                            Integer.parseInt(line[4]));
                    }
                    else {
                        this.remove(line[1]);
                    }
                    break;
                case "print":
                    this.print();
                    break;
                case "print lengths":
                    this.print(Integer.parseInt(line[1]), Integer
                        .parseInt(line[2]), Integer.parseInt(line[3]),
                        Integer.parseInt(line[4]));
                    break;
                case "print stats":
                    this.print(Integer.parseInt(line[1]), Integer
                        .parseInt(line[2]), Integer.parseInt(line[3]),
                        Integer.parseInt(line[4]));
                    break;
                case "search":
                    this.search(line[1]);
                    break;
                
                default:
                    //do nothing
            }
        }
        sc.close();
        */
        /**
         * EDITING FOR NEW PROJECT, taken from Rectangle1
         */
    }


    /**
     * Attempts to insert the rectangle into the BST. Rectangle will be inserted
     * if it fits within the 1024 by 1024 grid.
     * 
     * @param name
     *            is the name
     * @param x
     *            is the x coordinate
     * @param y
     *            is the y
     * @param w
     *            is the width
     * @param h
     *            is the height
     * 
     * @return true if insert was successful
     */
    public boolean insert(String name, int x, int y, int w, int h) {
        if (w <= 0 || h <= 0 || x < 0 || y < 0 || x + w > 1024 || y
            + h > 1024 || !Character.isLetter(name.charAt(0))) {
            System.out.println("Rectangle rejected: (" + name + ", " + x + ", "
                + y + ", " + w + ", " + h + ")");
            return false;
        }
        else {
            Rectangle rec = new Rectangle(name, x, y, w, h);
            System.out.println("Rectangle accepted: (" + name + ", " + x + ", "
                + y + ", " + w + ", " + h + ")");
            tree.insert(rec);
        }
        return true;
    }


    /**
     * Removes the rectangle with the given name.
     * 
     * @param name
     *            is the name of the rectangle
     */
    public void remove(String name) {
        Iterator iter = new Iterator(tree.getRoot());
        boolean removed = false;
        Rectangle rec;
        while (iter.hasNext()) {
            rec = iter.next();
            if (rec.getName().equals(name)) {
                tree.remove(rec);
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Rectangle rejected " + name);
        }
    }


    /**
     * Removes the rectangle with the given dimensions.
     * 
     * @param x
     *            is the x coordinate
     * @param y
     *            is the y coordinate
     * @param w
     *            is the width
     * @param h
     *            is the height
     */
    public void remove(int x, int y, int w, int h) {
        int[] comp = new int[4];
        boolean removed = false;
        comp[0] = x;
        comp[1] = y;
        comp[2] = w;
        comp[3] = h;
        Iterator iter = new Iterator(tree.getRoot());
        while (iter.hasNext()) {
            Rectangle rec = iter.next();
            if (Arrays.equals(comp, rec.getCoords())) {
                tree.remove(rec);
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Rectangle rejected (" + x + ", " + y + ", " + w
                + ", " + h + ")");
        }
    }


    /**
     * Reports the rectangles that intersect the specified rectangular region.
     * 
     * @param x
     *            is the x coordinate of the region
     * @param y
     *            is the y coordinate
     * @param w
     *            is the width
     * @param h
     *            is the height
     */
    public void regionsearch(int x, int y, int w, int h) {
        if (w > 0 && h > 0) {
            Iterator iter = new Iterator(tree.getRoot());
            Rectangle rec;
            int[] region = new int[4];
            region[0] = x;
            region[1] = y;
            region[2] = w;
            region[3] = h;
            int count = 0;
            while (iter.hasNext()) {
                rec = iter.next();
                if (this.intersects(rec.getCoords(), region)) {
                    count++;
                    if (count == 1) {
                        System.out.println("Rectangles intersecting region (" 
                            + x + ", " + y + ", " + w + ", " + h + "):");
                    }
                    System.out.println("(" + rec.getName() + ", " + rec
                        .getCoords()[0] + ", " + rec.getCoords()[1] + ", " + rec
                            .getCoords()[2] + ", " + rec.getCoords()[3]
                        + ")");
                }
            }
        }
    }


    /**
     * Reports all intersecting pairs of rectangles in the BST.
     */
    public void intersections() {
        Iterator iter = new Iterator(tree.getRoot());
        Rectangle rec;
        int[] coord1;
        System.out.println("Intersection pairs:");
        int count = 0;
        while (iter.hasNext()) {
            rec = iter.next();
            coord1 = rec.getCoords();
            Rectangle rec2;
            int[] coord2;
            Iterator iter2 = new Iterator(tree.getRoot());
            count++;
            for (int i = 0; i < count; i++) {
                rec2 = iter2.next();
            }
            while (iter2.hasNext()) {
                rec2 = iter2.next();
                coord2 = rec2.getCoords();
                if (rec.equals(rec2)) {
                    continue;
                }
                else if (this.intersects(coord1, coord2)) {
                    System.out.println("(" + rec.getName() + ", " + coord1[0]
                        + ", " + coord1[1] + ", " + coord1[2] + ", " + coord1[3]
                        + ") : (" + rec2.getName() + ", " + coord2[0] + ", " + 
                        coord2[1] + ", "
                        + coord2[2] + ", " + coord2[3] + ")");
                }
            }
        }
    }


    /**
     * Finds and reports all rectangles in the BST with the given name.
     * 
     * @param name
     *            is the name
     */
    public void search(String name) {
        Rectangle rec;
        Iterator iter = new Iterator(tree.getRoot());
        int count = 0;
        while (iter.hasNext()) {
            rec = iter.next();
            if (rec.getName().equals(name)) {
                count++;
                String n = rec.getName();
                int x = rec.getCoords()[0];
                int y = rec.getCoords()[1];
                int w = rec.getCoords()[2];
                int h = rec.getCoords()[3];
                System.out.println("Rectangle found: (" + n + ", " + x + ", "
                    + y + ", " + w + ", " + h + ")");
            }
        }
        if (count == 0) {
            System.out.println("Rectangle not found: " + name);
        }
    }


    /**
     * Returns true if the rectangles intersect eachother
     * 
     * @param coord1
     *            are the coordinates of the first rectangle
     * @param coord2
     *            are the coordinates of the second rectangle
     * @return
     */
    private boolean intersects(int[] coord1, int[] coord2) {
        int x1 = coord1[0];
        int y1 = coord1[1];
        int w1 = coord1[2];
        int h1 = coord1[3];
        int x2 = coord2[0];
        int y2 = coord2[1];
        int w2 = coord2[2];
        int h2 = coord2[3];
        
        return (x2 < x1 + w1 && x1 < x2 + w2 && y2 < y1 + h1 
            && y1 < y2 + h2);
    }


    /**
     * Prints the depth and value of the rectangle in each node of the BST using
     * in order traversal, as well as the size of the BST.
     */
    public void dump() {

        System.out.println("BST dump:");

        Iterator iter = new Iterator(tree.getRoot());

        Rectangle rec; // RECHECK THIS

        String n;
        int x;
        int y;
        int w;
        int h;

        // int depthStart = 0;
        if (!iter.hasNext()) {
            System.out.println("Node has depth 0, Value (null)");
        }

        while (iter.hasNext()) {
            rec = iter.next();

            n = rec.getName();
            x = rec.getCoords()[0];
            y = rec.getCoords()[1];
            w = rec.getCoords()[2];
            h = rec.getCoords()[3];

            System.out.println("Node has depth " + tree.findLevel(rec)
                + ", Value (" + n + ", " + x + ", " + y + ", " + w + ", " + h
                + ")");
        }
        // String sizeString = tree.size.toString();

        int treeSize = tree.size();

        System.out.println("BST size is: " + treeSize);

    }
}