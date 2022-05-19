import java.awt.Rectangle;

/**
 * Rectangle and its information class
 * 
 * @author Wenjun Han (hwenjun)
 * @version 4.22
 *
 */

public class RectangleInfo {

    private String name;
    private Rectangle rect;

    /**
     * Constructor of rectangle
     * 
     * @param name
     *            Name of the rectangle
     * @param x
     *            X location of the rectangle
     * @param y
     *            Y location of the rectangle
     * @param w
     *            Width of the rectangle
     * @param h
     *            Height of the rectangle
     */
    public RectangleInfo(String name, int x, int y, int w, int h) {
        this.name = name;
        rect = new Rectangle(x, y, w, h);
    }


    /**
     * Constructor of rectangle
     * 
     * @param name
     *            Name of the rectangle
     * @param rect
     *            The rectangle
     */
    public RectangleInfo(String name, Rectangle rect) {
        this.name = name;
        this.rect = rect;
    }


    /**
     * Get the full info of rectangle
     * 
     * @return Dimension string with the rectangle name
     */
    public String printRectangle() {

        return name + ", " + printRectangleDim();
    }


    /**
     * Get the rectangle
     * 
     * @return The rectangle name
     */
    public Rectangle getRectangle() {

        return rect;
    }


    /**
     * Get the rectangle name
     * 
     * @return The rectangle name
     */
    public String getName() {

        return name;
    }


    /**
     * Get the rectangle X
     * 
     * @return The X
     */
    public int getX() {

        return (int)rect.getMinX();
    }


    /**
     * Get the rectangle Y
     * 
     * @return The Y
     */
    public int getY() {

        return (int)rect.getMinY();
    }


    /**
     * Get the rectangle width
     * 
     * @return The rectangle width
     */
    public int getWidth() {

        return (int)rect.getWidth();
    }


    /**
     * Get the rectangle height
     * 
     * @return The rectangle height
     */
    public int getHeight() {

        return (int)rect.getHeight();
    }


    /**
     * Get the rectangle dimension info
     * 
     * @return Dimension string
     */
    public String printRectangleDim() {

        return (int)rect.getMinX() + ", " + (int)rect.getMinY() + ", "
            + (int)rect.getWidth() + ", " + (int)rect.getHeight();
    }

}
