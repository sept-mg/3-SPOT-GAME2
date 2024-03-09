package fr.septmg.ThreeSpotGame2;

import java.util.Scanner;

public class ThreeSpotGame
{
    private static Board board;

    private static Player player1, player2;
    private static Scanner sc;

    final static int MAX_SCORE = 12;
    final static int MIN_SCORE_TO_WIN = 6;
    
    public static void main( String[] args )
    {
        board = new Board();

        ColorCase red = ColorCase.RED;
        ColorCase blue = ColorCase.BLUE;
        String input = "";
        sc = new Scanner(System.in);

        do {
            System.out.println("Choose the color of your player (R or B): ");
            
            input = sc.nextLine();
        }
        while (!input.equals("R") && !input.equals("B"));

        if(input.equals("R"))
        {
            player1 = new Player(red, 1);
            player2 = new Player(blue, 2);
        }
        else
        {
            player1 = new Player(blue, 1);
            player2 = new Player(red, 2);
        }

        boolean breakq = false;
        while (player2.getScore() < MAX_SCORE && !breakq)
        {
            displacement(player1, player2);
            if(player1.getScore() < MAX_SCORE) {
                displacement(player2, player1);
            }
            else {
                breakq = true;
            }
        }

        if (player1.getScore() >= MAX_SCORE) {
            if(player2.getScore() >= MIN_SCORE_TO_WIN) {
                System.out.println("Player " + player1.getId() + " wins !");
            }
            else {
                System.out.println("Player " + player2.getId() + " wins !");
            }
        }
        else {
            if(player1.getScore() >= MIN_SCORE_TO_WIN) {
                System.out.println("Player " + player2.getId() + " wins !");
            }
            else {
                System.out.println("Player " + player1.getId() + " wins !");
            }
        }

        sc.close();
    }

    private static void displacement(Player currenPlayer, Player secondPlayer) {
        StringBuilder startStringBuilder = new StringBuilder("Player ")
                                                    .append(currenPlayer.getId())
                                                    .append(" (Your Score ");

        StringBuilder updatableStringBuilder = new StringBuilder(Integer.toString(currenPlayer.getScore()))
                                                    .append(" to ")
                                                    .append(Integer.toString(secondPlayer.getScore()))
                                                    .append(") choose a displacement : (block ");

        StringBuilder finishStringBuilder = new StringBuilder(currenPlayer.getColor().toString())
                                                    .append(")");

        StringBuilder totalStringBuilder = new StringBuilder(startStringBuilder)
                                                    .append(updatableStringBuilder)
                                                    .append(finishStringBuilder);
        boolean good = false;
        
        board.setCurrentColor(currenPlayer.getColor());

        System.out.println(board);
        
        System.out.println(currenPlayer.getPossibleDisplament());
        do {

            System.out.println(totalStringBuilder);

            good = currenPlayer.displacement(sc.nextLine());
        }
        while (!good);
        
        updatableStringBuilder = new StringBuilder(Integer.toString(currenPlayer.getScore()))
                                        .append(" to ")
                                        .append(Integer.toString(secondPlayer.getScore()))
                                        .append(") choose a displacement : (block ");

        finishStringBuilder = new StringBuilder("WHITE)");

        totalStringBuilder = new StringBuilder(startStringBuilder)
                                    .append(updatableStringBuilder)
                                    .append(finishStringBuilder);
        
        board.setCurrentColor(ColorCase.WHITE);
        System.out.println(board);

        System.out.println(currenPlayer.getPossibleDisplamentWhite());
        do {
            System.out.println(totalStringBuilder.toString());

            good = currenPlayer.displacement(sc.nextLine(), ColorCase.WHITE);
        }
        while (!good);
    }

    static Board getBoard() {
        return board;
    }
}
