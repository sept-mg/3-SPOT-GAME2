package fr.septmg.ThreeSpotGame2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DirectionsTest {

    @Test
    public void testConstructorWithDirections() {
        // Test constructor with specified directions
        Directions directions1 = new Directions(AdjDirection.UP, AdjDirection.RIGHT);
        AdjDirection[] allOrientations1 = directions1.getAllOrientations();

        // Verify that the specified directions are added correctly
        assertTrue(directions1.has(AdjDirection.UP));
        assertTrue(directions1.has(AdjDirection.RIGHT));

        // Verify that other orientations remain null
        for (AdjDirection direction : allOrientations1) {
            if (direction != AdjDirection.UP && direction != AdjDirection.RIGHT) {
                assertNull(direction);
            }
        }

        // Test constructor with null directions
        Directions directions2 = new Directions(null, null);
        AdjDirection[] allOrientations2 = directions2.getAllOrientations();

        Directions directions3 = new Directions(null);
        AdjDirection[] allOrientations3 = directions3.getAllOrientations();

        // Verify that all orientations are initialized to null
        for (AdjDirection direction : allOrientations2) {
            assertNull(direction);
        }

        for (AdjDirection direction : allOrientations3) {
            assertNull(direction);
        }


        Directions directions4 = new Directions(AdjDirection.UP);
        assertTrue(directions4.has(AdjDirection.UP));
        assertFalse(directions4.has(AdjDirection.RIGHT));


        Directions directions5 = new Directions(AdjDirection.UP, AdjDirection.UP);
        assertTrue(directions5.has(AdjDirection.UP));
        assertFalse(directions5.has(AdjDirection.RIGHT));

        Directions directions6 = new Directions(AdjDirection.RIGHT, AdjDirection.RIGHT, AdjDirection.UP, AdjDirection.UP, AdjDirection.RIGHT);
        assertTrue(directions6.has(AdjDirection.UP));
        assertTrue(directions6.has(AdjDirection.RIGHT));
    }

    @Test
    public void testHasMethod() {
        // Test has method with existing direction
        Directions directions1 = new Directions(AdjDirection.UP);
        assertTrue(directions1.has(AdjDirection.UP));

        // Test has method with non-existing direction
        Directions directions2 = new Directions();
        assertFalse(directions2.has(AdjDirection.RIGHT));

        // Test has method with null direction
        Directions directions3 = new Directions();
        assertThrows(AssertionError.class, () -> directions3.has(null));

        // Test has method with multiple directions
        Directions directions4 = new Directions(AdjDirection.UP, AdjDirection.RIGHT);
        assertTrue(directions4.has(AdjDirection.UP));
        assertTrue(directions4.has(AdjDirection.RIGHT));
    }

    @Test
    public void testAddMethod() {
        // Test add method with existing direction
        Directions directions1 = new Directions(AdjDirection.UP);
        directions1.add(AdjDirection.UP); // Adding the same direction
        assertTrue(directions1.has(AdjDirection.UP)); // Should remain true

        // Test add method with non-existing direction
        Directions directions2 = new Directions();
        directions2.add(AdjDirection.RIGHT);
        assertTrue(directions2.has(AdjDirection.RIGHT)); // Should be added

        // Test add method with null direction
        Directions directions3 = new Directions();
        assertThrows(AssertionError.class, () -> directions3.add(null)); // Adding null direction
        assertThrows(AssertionError.class, () -> directions3.has(null)); // Should not be added

        // Test add method with multiple directions
        Directions directions4 = new Directions();
        directions4.add(AdjDirection.UP);
        directions4.add(AdjDirection.RIGHT);
        assertTrue(directions4.has(AdjDirection.UP)); // Should be added
        assertTrue(directions4.has(AdjDirection.RIGHT)); // Should be added
    }

    @Test
    public void testToIntMethod() {
        // Test toInt method with no directions
        Directions directions1 = new Directions();
        assertEquals(0, directions1.toInt()); // Should return 0

        // Test toInt method with single direction
        Directions directions2 = new Directions(AdjDirection.UP);
        assertEquals(1, directions2.toInt()); // Should return 1

        // Test toInt method with multiple directions
        Directions directions3 = new Directions(AdjDirection.UP, AdjDirection.RIGHT);
        assertEquals(2, directions3.toInt()); // Should return 2

        // Test toInt method with null direction
        Directions directions4 = new Directions(null);
        assertEquals(0, directions4.toInt()); // Should return 0
    }
}
