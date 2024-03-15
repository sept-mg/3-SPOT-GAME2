package fr.septmg.ThreeSpotGame2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void testSetCurrentColor() {
        Board board = new Board();
        board.setCurrentColor(ColorCase.RED);
        assertEquals(ColorCase.RED, board.getCurrentColor());
    }

    @Test
    public void testGetCurrentColor() {
        Board board = new Board();
        assertNull(board.getCurrentColor());

        board.setCurrentColor(ColorCase.RED);
        assertEquals(ColorCase.RED, board.getCurrentColor());
    }

    @Test
    public void testColorPeaked() {
        Board board = new Board();
        assertFalse(board.colorPeaked());
        board.setCurrentColor(ColorCase.RED);
        assertTrue(board.colorPeaked());
    }

    @Test
    public void testMove() {
        Board board = new Board();
        String s = board.toString();
        board.setCurrentColor(ColorCase.RED);
        board.getBoardAllMovement();
        board.move(1);
        assertNotEquals(s, board.toString());

        assertEquals(board.toString(),  "* * * * * * * * * * * * *\n*       *       *       *\n*   R   *   R   *   O   *\n*       *       *       *\n* * * * * * * * * * * * *\n*       *       *       *\n*       *   W   *   W   *\n*       *       *       *\n* * * * * * * * * * * * *\n*       *       *       *\n*       *   B   *   B   *\n*       *       *       *\n* * * * * * * * * * * * *\n");
        
    }

    @Test
    public void testGetBoardAllMovement() {
        Board board = new Board();
        board.setCurrentColor(ColorCase.RED);
        //board must be tostringed one time before getBoardAllMovement
        board.toString();
        
        String boardString = board.getBoardAllMovement();
        assertEquals(boardString, "* * * * * * * * * * * * *\n" + 
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

        Board boardB = new Board();
        boardB.setCurrentColor(ColorCase.BLUE);
        //board must be tostringed one time before getBoardAllMovement
        boardB.toString();
        
        String boardStringB = boardB.getBoardAllMovement();
        assertEquals(boardStringB, "* * * * * * * * * * * * *\n" + 
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
}
