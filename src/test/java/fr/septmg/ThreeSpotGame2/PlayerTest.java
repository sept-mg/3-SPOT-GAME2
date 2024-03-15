package fr.septmg.ThreeSpotGame2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void testConstructor() {

        // Test with valid color (RED) and ID
        assertDoesNotThrow(() -> new Player(ColorCase.RED, 1));

        // Test with valid color (BLUE) and ID
        assertDoesNotThrow(() -> new Player(ColorCase.BLUE, 2));

        // Test with invalid color (EMPTY)
        assertThrows(AssertionError.class, () -> new Player(ColorCase.EMPTY, 1));

         // Test with invalid color (NULL)
         assertThrows(AssertionError.class, () -> new Player(null, 1));

        // Test with invalid color (WHITE)
        assertThrows(AssertionError.class, () -> new Player(ColorCase.WHITE, 1));

        // Test with invalid ID (less than 1)
        assertThrows(AssertionError.class, () -> new Player(ColorCase.RED, 0));

        // Test with invalid ID (greater than 2)
        assertThrows(AssertionError.class, () -> new Player(ColorCase.RED, 3));

        Player player = new Player(ColorCase.RED, 1);

        assertEquals(ColorCase.RED, player.getColor());
        assertEquals(0, player.getScore());
        assertEquals(1, player.getId());

        Player playerB = new Player(ColorCase.BLUE, 2);

        assertEquals(ColorCase.BLUE, playerB.getColor());
        assertEquals(0, playerB.getScore());
        assertEquals(2, playerB.getId());

    }

    @Test
    public void testGetPossibleDisplament() {
        Board board = new Board();
        Player player = new Player(ColorCase.RED, 1);
        player.getPossibleDisplament(board);

        // board need string befor a displacement
        board.toString();

        assertEquals(player.getPossibleDisplament(board),  "* * * * * * * * * * * * *\n" + 
        "*       *       *       *\n" + 
        "*   1   *       *   O   *\n" + 
        "*       *       *       *\n" + 
        "* * * * * * * * * * * * *\n" + 
        "*       *       *       *\n" + 
        "*   2   *   W   *   W   *\n" + 
        "*       *       *       *\n" + 
        "* * * * * * * * * * * * *\n" + 
        "*       *       *       *\n" + 
        "*   3   *   B   *   B   *\n" + 
        "*       *       *       *\n" + 
        "* * * * * * * * * * * * *\n" + 
        "");

        Player playerB = new Player(ColorCase.BLUE, 2);
        board = new Board();
        // board need string befor a displacement
        board.toString();
        playerB.getPossibleDisplament(board);
        
        assertEquals(playerB.getPossibleDisplament(board), "* * * * * * * * * * * * *\n" + 
        "*       *       *       *\n" + 
        "*       *   R   *   R   *\n" + 
        "*       *       *       *\n" + 
        "* * * * * * * * * * * * *\n" + 
        "*       *       *       *\n" + 
        "*   1   *   W   *   W   *\n" + 
        "*       *       *       *\n" + 
        "* * * * * * * * * * * * *\n" + 
        "*       *       *       *\n" + 
        "* 2 - 3 *       *   O   *\n" + 
        "*       *       *       *\n" + 
        "* * * * * * * * * * * * *\n" + 
        "");
    }

    @Test
    public void testDisplacement() {
        Board board = new Board();
        board.toString();
        Player player = new Player(ColorCase.RED, 1);

        Board board1 = new Board();
        board1.setCurrentColor(ColorCase.RED);

        // board need string befor a displacement
        board1.toString();

        player.getPossibleDisplament(board1);
        // Assuming the first movement is valid
        assertTrue(player.displacement("1", board1));

        Board board2 = new Board();
        board2.setCurrentColor(ColorCase.RED);
        board2.toString();
        player.getPossibleDisplament(board2);
        // Assuming the second movement is invalid
        assertThrows(AssertionError.class, () -> player.displacement("100", board2));
        
        boolean good;
        try {
            good = player.displacement("100", board2);
        } catch (AssertionError e) {
            good = false;
        }

        assertFalse(good);

        Board board3 = new Board();
        board3.setCurrentColor(ColorCase.RED);
        board3.toString();
        player.getPossibleDisplament(board3);
        // Assuming the second movement is valid
        assertTrue(player.displacement("2", board3));

        Board board4 = new Board();
        board4.setCurrentColor(ColorCase.RED);
        board4.toString();
        player.getPossibleDisplament(board4);
        // Assuming the thierd movement is valid
        assertTrue(player.displacement("3", board4));

        Player player2 = new Player(ColorCase.BLUE, 1);
        Board board5 = new Board();
        board5.setCurrentColor(ColorCase.RED);
        board5.toString();
        player2.getPossibleDisplament(board5);
        // Assuming the thierd movement is valid
        assertTrue(player2.displacement("3", board5));
    }
}
