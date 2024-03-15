package fr.septmg.ThreeSpotGame2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {

    @Test
    public void testConstructor() {
        // Test constructor with valid positions
        int[] positions = {0, 1, 2};
        Block block = new Block(ColorCase.RED, positions);
        assertEquals(ColorCase.RED, block.getColor());
        assertEquals(ColorCase.RED, block.getPosition(0));
        assertEquals(ColorCase.RED, block.getPosition(1));
        assertEquals(ColorCase.RED, block.getPosition(2));
        assertNotEquals(ColorCase.RED, block.getPosition(3));

        // Test constructor with empty color
        assertThrows(AssertionError.class, () -> new Block(ColorCase.EMPTY, positions));
        
        // Test constructor with null color
        assertThrows(AssertionError.class, () -> new Block(null, positions));

        // Test constructor with null positions
        assertThrows(AssertionError.class, () -> new Block(ColorCase.RED, null));
        
    }

    @Test
    public void testPositionListNotToLongOrToShort() {
        // Test with valid positions
        int[] validPositions = {0, 1, 2};
        assertTrue(Block.positionListNotToLongOrToShort(validPositions));

        // Test with null positions
        int[] nullPositions = null;
        assertFalse(Block.positionListNotToLongOrToShort(nullPositions));

        // Test with empty positions a null block
        int[] emptyPositions = {};
        assertTrue(Block.positionListNotToLongOrToShort(emptyPositions));

        // Test with positions exceeding max size
        int[] exceedingSizePositions = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; // Exceeds maximum size
        assertFalse(Block.positionListNotToLongOrToShort(exceedingSizePositions));
    }


    @Test
    public void testGetPositionOutOfRange() {
        int[] positions = {0, 1, 2};
        Block block = new Block(ColorCase.RED, positions);
        // Lambda is mandatory to verify the assertions
        assertThrows(AssertionError.class, () -> block.getPosition(-1));
        assertThrows(AssertionError.class, () -> block.getPosition(Board.SIZE));
    }


    @Test
    public void testGetColor() {
        // Creating a block with a specific color and positions
        int[] positions = {0, 1, 2};
        Block block = new Block(ColorCase.RED, positions);
        
        // Verifying that the color is correct
        assertEquals(ColorCase.RED, block.getColor());

        // Verifying that the color of the block is different from BLUE and WHITE
        assertNotEquals(ColorCase.BLUE, block.getColor());
        assertNotEquals(ColorCase.WHITE, block.getColor());
    }

    @Test
    public void testGetPosition() {
        // Creating a block with a specific color and positions
        int[] positions = {0, 1, 2};
        Block block = new Block(ColorCase.RED, positions);
        
        // Verifying that the color is correct at the specified positions
        assertEquals(ColorCase.RED, block.getPosition(0));
        assertEquals(ColorCase.RED, block.getPosition(1));
        assertEquals(ColorCase.RED, block.getPosition(2));
        
        // Verifying positions out of range throw exceptions
        assertThrows(AssertionError.class, () -> block.getPosition(-1));
        assertThrows(AssertionError.class, () -> block.getPosition(Board.SIZE));
    }


    @Test
    public void testGetPositionOrdinal() {
        int[] positions = {0, 1, 2};
        Block block = new Block(ColorCase.RED, positions);
        assertEquals(ColorCase.RED.ordinal(), block.getPositionOrdinal(0));
        assertEquals(ColorCase.RED.ordinal(), block.getPositionOrdinal(1));
        assertEquals(ColorCase.RED.ordinal(), block.getPositionOrdinal(2));
        assertNotEquals(ColorCase.RED.ordinal(), block.getPositionOrdinal(Board.SIZE-1));
        assertEquals(ColorCase.EMPTY.ordinal(), block.getPositionOrdinal(Board.SIZE-1));
    }

    @Test
    public void testIsInThisPosition() {
        int[] positions = {0, 1, 2};
        Block block = new Block(ColorCase.RED, positions);
        assertTrue(block.isInThisPosition(0));
        assertTrue(block.isInThisPosition(1));
        assertTrue(block.isInThisPosition(2));
        assertFalse(block.isInThisPosition(3));
    }

    @Test
    public void testUpdatePosition() {
        int[] positions = {0, 1, 2};
        Block block = new Block(ColorCase.RED, positions);
        block.updatePosition(3, 4, 5);
        assertEquals(ColorCase.RED, block.getPosition(3));
        assertEquals(ColorCase.RED, block.getPosition(4));
        assertEquals(ColorCase.RED, block.getPosition(5));
    }
}
