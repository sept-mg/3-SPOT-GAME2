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
        init();

    }

    public static void init() {
        Board board = new Board();
        System.out.println(board);
        board.setCurrentColor(ColorCase.RED);
        System.out.println(board.getPlateauAllMovement());
        board.move(1);
        System.out.println(board);

        System.out.println();

        board.setCurrentColor(ColorCase.WHITE);
        System.out.println(board.getPlateauAllMovement());
        
        board.move(3);

        System.out.println();

        System.out.println(board);
        System.out.println();


        board.setCurrentColor(ColorCase.RED);
        System.out.println(board.getPlateauAllMovement());
        
        board.move(3);

        System.out.println();

        System.out.println(board);
        System.out.println();

    }
}
