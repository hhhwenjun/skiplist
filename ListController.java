import java.awt.Rectangle;

/**
 * Controller for operation of the skip list
 * 
 * @author Wenjun Han
 * @version 4.26.22
 *
 */
public class ListController {

    private SkipList<String, RectangleInfo> list;
    // Use two list to act as hash map
    private LinkedList<String> nameList;
    private LinkedList<Rectangle> rectList;

    /**
     * Constructor of the controller
     */
    public ListController() {
        list = new SkipList<>();
        nameList = new LinkedList<>();
        rectList = new LinkedList<>();
    }


    /**
     * Check if the rectangle is a valid rectangle
     * 
     * @param name
     *            Name of the rectangle
     * 
     * @param x
     *            X location of rectangle
     * @param y
     *            Y location of rectangle
     * @param w
     *            Width of rectangle
     * @param h
     *            Height of rectangle
     * @param regionsearch
     *            True if this is region search, else if not
     * 
     * @return True if valid, False if not valid
     */
    public boolean checkRectangleValid(
        String name,
        String x,
        String y,
        String w,
        String h,
        boolean regionsearch) {

        int xInt = Integer.valueOf(x);
        int yInt = Integer.valueOf(y);
        int wInt = Integer.valueOf(w);
        int hInt = Integer.valueOf(h);

        if (regionsearch) {
            if (wInt <= 0 || hInt <= 0) {
                RectangleInfo rejectRect = new RectangleInfo(name, xInt, yInt,
                    wInt, hInt);
                System.out.println("Rectangle rejected: " + "(" + rejectRect
                    .printRectangleDim() + ")");
                return false;
            }
            return true;
        }
        // if not for region search situation
        if (xInt < 0 || yInt < 0 || wInt <= 0 || hInt <= 0 || yInt + hInt > 1024
            || xInt + wInt > 1024) {
            RectangleInfo rejectRect = new RectangleInfo(name, xInt, yInt, wInt,
                hInt);
            if (name.equals("")) {
                System.out.println("Rectangle rejected: " + "(" + rejectRect
                    .printRectangleDim() + ")");
            }
            else {
                System.out.println("Rectangle rejected: " + "(" + rejectRect
                    .printRectangle() + ")");
            }
            return false;
        }

        return true;
    }


    /**
     * Check if the rectangle exists
     * 
     * @param name
     *            Name of the rectangle
     * @param flag
     *            Positive if exist, negative if not
     * @param removedRect
     *            The rectangle supposed to be removed
     * @return True if the rectangle exist, false if not
     */
    public boolean checkRectangleExist(
        String name,
        int flag,
        Rectangle removedRect) {
        if (flag < 0) {
            if (removedRect instanceof Rectangle) {
                RectangleInfo notFoundRect = new RectangleInfo("", removedRect);
                System.out.println("Rectangle not removed: " + "("
                    + notFoundRect.printRectangleDim() + ")");
            }
            else {
                System.out.println("Rectangle not removed: " + name);
            }
            return false;
        }
        return true;
    }


    /**
     * Insert the rectangle into the list
     * 
     * @param name
     *            The name of the rectangle
     * @param x
     *            X of the rectangle
     * @param y
     *            Y of the rectangle
     * @param w
     *            Width of the rectangle
     * @param h
     *            Height of the rectangle
     */
    public void insert(String name, String x, String y, String w, String h) {
        if (checkRectangleValid(name, x, y, w, h, false)) {
            RectangleInfo rect = new RectangleInfo(name, Integer.valueOf(x),
                Integer.valueOf(y), Integer.valueOf(w), Integer.valueOf(h));
            list.insert(name, rect);
            System.out.println("Rectangle inserted: " + "(" + rect
                .printRectangle() + ")");
            nameList.moveToEnd();
            rectList.moveToEnd();
            nameList.append(name);
            rectList.append(rect.getRectangle());
        }
    }


    /**
     * Remove the node inside list
     * 
     * @param name
     *            Name of the rectangle
     */
    public void remove(String name) {
        int pos = nameList.search(name);
        if (checkRectangleExist(name, pos, null)) {
            RectangleInfo removed = list.remove(name);
            System.out.println("Rectangle removed: " + "(" + removed
                .printRectangle() + ")");
            nameList.moveToPos(pos);
            rectList.moveToPos(pos);
            nameList.remove();
            rectList.remove();
        }
    }


    /**
     * Remove the rectangle by its content
     * 
     * @param x
     *            X location of rectangle
     * @param y
     *            Y location of rectangle
     * @param w
     *            Width of rectangle
     * @param h
     *            Height of rectangle
     */
    public void removeByContent(String x, String y, String w, String h) {
        if (checkRectangleValid("", x, y, w, h, false)) {
            Rectangle target = new Rectangle(Integer.valueOf(x), Integer
                .valueOf(y), Integer.valueOf(w), Integer.valueOf(h));
            int pos = searchRectangle(target);
            if (checkRectangleExist("", pos, target)) {
                nameList.moveToPos(pos);
                String name = nameList.getValue();
                remove(name);
            }
        }

    }


    /**
     * Return the target position within the list
     * 
     * @param target
     *            The target rectangle to search
     * 
     * @return The position of the target
     */
    public int searchRectangle(Rectangle target) {

        int index = 0;
        rectList.moveToEnd();
        rectList.append(null);
        rectList.moveToStart();
        boolean found = false;
        while (!rectList.isAtEnd()) {
            Rectangle currRect = rectList.getValue();
            if (currRect.x == target.x && currRect.y == target.y
                && currRect.width == target.width
                && currRect.height == target.height) {
                found = true;
                break;
            }
            rectList.next();
            index++;
        }
        if (!found) {
            index = -1;
        }
        rectList.moveToEnd();
        rectList.remove();
        rectList.moveToStart();
        return index;
    }


    /**
     * Region search the rectangle by its dim
     * 
     * @param x
     *            X location of rectangle
     * @param y
     *            Y location of rectangle
     * @param w
     *            Width of rectangle
     * @param h
     *            Height of rectangle
     */
    public void regionSearch(String x, String y, String w, String h) {
        if (checkRectangleValid("", x, y, w, h, true)) {
            RectangleInfo target = new RectangleInfo("", Integer.valueOf(x),
                Integer.valueOf(y), Integer.valueOf(w), Integer.valueOf(h));
            System.out.println("Rectangles intersecting region (" + target
                .printRectangleDim() + "):");

            // add a dummy tail to the lists
            nameList.moveToEnd();
            nameList.append(null);
            rectList.moveToEnd();
            rectList.append(null);
            nameList.moveToStart();
            rectList.moveToStart();

            // loop through the skip list to find comparisons
            while (!rectList.isAtEnd()) {
                if (findIntersect(target.getRectangle(), rectList.getValue())) {
                    nameList.moveToPos(rectList.currPos());
                    RectangleInfo intersect = new RectangleInfo(nameList
                        .getValue(), rectList.getValue());
                    System.out.println("(" + intersect.printRectangle() + ")");
                }
                rectList.next();
            }

            // remove the last null dummy tail at the end
            nameList.moveToEnd();
            nameList.remove();
            nameList.moveToStart();

            rectList.moveToEnd();
            rectList.remove();
            rectList.moveToStart();

        }
    }


    /**
     * Find all intersections of the skip list
     */
    public void intersections() {
        System.out.println("Intersection pairs:");
        int length = nameList.length();
        if (length < 1) {
            return;
        }

        // add a dummy tail to the lists
        nameList.moveToEnd();
        nameList.append(null);
        rectList.moveToEnd();
        rectList.append(null);
        nameList.moveToStart();
        rectList.moveToStart();

        // loop through the list
        for (int i = 0; i < length; i++) {
            rectList.moveToPos(i);
            Rectangle curr = rectList.getValue();
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    continue;
                }
                rectList.moveToPos(j);
                Rectangle compare = rectList.getValue();
                if (findIntersect(curr, compare)) {
                    nameList.moveToPos(i);
                    String nameCurr = nameList.getValue();
                    nameList.moveToPos(j);
                    String nameCompare = nameList.getValue();
                    RectangleInfo nameCurrRect = new RectangleInfo(nameCurr,
                        curr);
                    RectangleInfo nameCompareRect = new RectangleInfo(
                        nameCompare, compare);
                    reportPair(nameCurrRect, nameCompareRect);
                }
            }
        }

        // remove the last null dummy tail at the end
        nameList.moveToEnd();
        nameList.remove();
        nameList.moveToStart();

        rectList.moveToEnd();
        rectList.remove();
        rectList.moveToStart();
    }


    /**
     * Print reported pairs of rectangles
     * 
     * @param r1
     *            Rectangle information 1
     * @param r2
     *            Rectangle information 2
     */
    public void reportPair(RectangleInfo r1, RectangleInfo r2) {
        System.out.println("(" + r1.printRectangle() + "| " + r2
            .printRectangle() + ")");
    }


    /**
     * Compare two rectangle to see if they have intersection
     * 
     * @param r1
     *            Rectangle 1
     * @param r2
     *            Rectangle 2
     * @return true if there is intersection, false is not
     */
    public boolean findIntersect(Rectangle r1, Rectangle r2) {
        
        return r1.intersects(r2);
    }


    /**
     * Search the record of rectangle in skip list
     * 
     * @param name
     *            The name of target rectangle
     */
    public void search(String name) {

        boolean found = false;
        // add a dummy tail to the lists
        nameList.moveToEnd();
        nameList.append(null);
        rectList.moveToEnd();
        rectList.append(null);
        nameList.moveToStart();
        rectList.moveToStart();

        while (!nameList.isAtEnd()) {
            if (nameList.getValue().equals(name)) {
                int pos = nameList.currPos();
                rectList.moveToPos(pos);
                RectangleInfo targetFull = new RectangleInfo(nameList
                    .getValue(), rectList.getValue());
                System.out.println("(" + targetFull.printRectangle() + ")");
                found = true;
            }
            nameList.next();
        }
        if (!found) {
            System.out.println("Rectangle not found: " + name);
        }
        // remove the last null dummy tail at the end
        nameList.moveToEnd();
        nameList.remove();
        nameList.moveToStart();

        rectList.moveToEnd();
        rectList.remove();
        rectList.moveToStart();
    }


    /**
     * Dump the skip list info
     */
    public void dump() {
        list.dump();
    }
}
