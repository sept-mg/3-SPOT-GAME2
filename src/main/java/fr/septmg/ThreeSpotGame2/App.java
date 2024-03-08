package fr.septmg.ThreeSpotGame2;


/**
 * Hello world!
 *
 */
public class App
{
    static Board board;
    
    public static void main( String[] args )
    {
        Board board = new Board();
        System.out.println(board);
        board.setCurrentColor(ColorCase.RED);
        System.out.println(board.getPlateauAllMovement());

    }
}
