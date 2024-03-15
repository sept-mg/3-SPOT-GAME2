package fr.septmg.ThreeSpotGame2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovementTest {

    @Test
    public void testConstructor() {
        // Test constructor with valid index and direction
        int index = 3;
        AdjDirection direction = AdjDirection.RIGHT;
        Movement movement = new Movement(index, direction);
        assertEquals(index, movement.getPlacement());
        assertEquals(direction, movement.getDirection());


        // Test default constructor
        Movement movement2 = new Movement();
        assertEquals(0, movement2.getPlacement());
        assertEquals(AdjDirection.UP, movement2.getDirection());

        // Test constructor with invalid index
        assertThrows(AssertionError.class, () -> new Movement(-1, AdjDirection.UP));
        assertThrows(AssertionError.class, () -> new Movement(Board.SIZE, AdjDirection.UP));

        // Test constructor with null direction
        assertThrows(AssertionError.class, () -> new Movement(3, null));
    }

    @Test
    public void testSetPlacement() {
        // Test setPlacement method
        Movement movement = new Movement();
        int index = 4;
        movement.setPlacement(index);
        assertEquals(index, movement.getPlacement());

        // Test setPlacement method with invalid index
        Movement movement2 = new Movement();
        assertThrows(AssertionError.class, () -> movement2.setPlacement(-1));
        assertThrows(AssertionError.class, () -> movement2.setPlacement(Board.SIZE));
    }

    @Test
    public void testSetDirection() {
        // Test setDirection method
        Movement movement = new Movement();
        AdjDirection direction = AdjDirection.RIGHT;
        movement.setDirection(direction);
        assertEquals(direction, movement.getDirection());

        // Test setDirection method with null direction
        Movement movement2 = new Movement();
        assertThrows(AssertionError.class, () -> movement2.setDirection(null));
    }
}
