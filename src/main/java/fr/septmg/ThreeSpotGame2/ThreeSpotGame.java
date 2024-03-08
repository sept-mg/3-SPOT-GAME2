package fr.septmg.ThreeSpotGame2;


/**
 * Hello world!
 *
 */
public class ThreeSpotGame
{
    static Board board;
    
    public static void main( String[] args )
    {
        Board board = new Board();
        System.out.println(board);
        board.setCurrentColor(ColorCase.RED);
        System.out.println(board.getPlateauAllMovement());
        board.move(1);
        System.out.println(board);

        System.out.println();

        board.setCurrentColor(ColorCase.WHITE);
        System.out.println(board.getPlateauAllMovement());
        
        board.move(1);

        System.out.println();

        System.out.println(board);
        System.out.println();
        board.setCurrentColor(ColorCase.BLUE);
        System.out.println(board.getPlateauAllMovement());
        System.out.println();

        board.move(1);

        System.out.println(board);

    }
}
