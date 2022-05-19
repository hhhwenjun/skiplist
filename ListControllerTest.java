import student.TestCase;

/**
 * Test for the list controller
 * 
 * @author Wenjun Han
 * @version 4.29.22
 *
 */
public class ListControllerTest extends TestCase {

    private ListController controller;

    /**
     * set up the list controller
     */
    public void setUp() {
        controller = new ListController();
    }


    /**
     * test insert()
     */
    public void testInsert() {
        controller.insert("arec", "0", "0", "15", "10");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle inserted: (arec, 0, 0, 15, 10)"));
        controller.insert("a", "-2", "0", "15", "10");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle rejected: (a, -2, 0, 15, 10)"));
        controller.insert("a", "0", "0", "15", "-10");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle rejected: (a, 0, 0, 15, -10)"));
    }


    /**
     * test remove() and search()
     */
    public void testRemoveSearch() {
        controller.insert("arec", "0", "0", "15", "10");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle inserted: (arec, 0, 0, 15, 10)"));
        controller.insert("arec", "0", "0", "10", "10");
        controller.insert("brec", "10", "0", "15", "10");
        controller.insert("crec", "5", "5", "15", "20");
        controller.search("arec");
        assertTrue(contains(systemOut().getHistory(), "(arec, 0, 0, 15, 10)"));
        assertTrue(contains(systemOut().getHistory(), "(arec, 0, 0, 10, 10)"));
        controller.remove("brec");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle removed: (brec, 10, 0, 15, 10)"));
        controller.search("brec");
        controller.remove("brec");
        controller.remove("drec");
        controller.removeByContent("1029", "10", "10", "10");
        controller.removeByContent("1020", "10", "-10", "10");
        controller.removeByContent("20", "10", "10", "10");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle not removed: (20, 10, 10, 10)"));
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle rejected: (1029, 10, 10, 10)"));
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle rejected: (1020, 10, -10, 10)"));
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle not removed: drec"));
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle not removed: brec"));
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle not found: brec"));
        controller.removeByContent("5", "5", "15", "20");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle removed: (crec, 5, 5, 15, 20)"));
        controller.search("crec");
        controller.insert("drec", "5", "5", "15", "20");
        controller.dump();
        controller.remove("arec");
        controller.dump();
    }


    /**
     * test region search()
     */
    public void testRegionSearchIntersection() {
        System.out.println("******** region search & intersection *********");
        controller.insert("arec", "0", "0", "15", "10");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle inserted: (arec, 0, 0, 15, 10)"));
        controller.insert("arec", "0", "0", "10", "10");
        controller.insert("brec", "10", "0", "15", "10");
        controller.insert("crec", "5", "5", "15", "20");
        controller.intersections();
        assertTrue(contains(systemOut().getHistory(),
            "(arec, 0, 0, 15, 10| arec, 0, 0, 10, 10)"));
        controller.regionSearch("100", "100", "10", "10");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangles intersecting region (100, 100, 10, 10):"));
        controller.regionSearch("100", "1025", "-10", "10");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle rejected: (100, 1025, -10, 10)"));
        controller.regionSearch("0", "0", "10", "10");
        assertTrue(contains(systemOut().getHistory(), "(crec, 5, 5, 15, 20)"));
    }


    /**
     * test dump()
     */
    public void testdump() {
        controller.insert("arec", "0", "0", "15", "10");
        assertTrue(contains(systemOut().getHistory(),
            "Rectangle inserted: (arec, 0, 0, 15, 10)"));
        controller.insert("arec", "0", "0", "10", "10");
        controller.insert("brec", "10", "0", "15", "10");
        controller.insert("crec", "5", "5", "15", "20");
        controller.dump();
        assertTrue(contains(systemOut().getHistory(),
            "Value (arec, 0, 0, 15, 10)"));
        assertTrue(contains(systemOut().getHistory(), "Value (null)"));
    }

}
