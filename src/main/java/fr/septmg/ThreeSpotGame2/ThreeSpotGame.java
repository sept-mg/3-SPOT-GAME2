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

        do {
            System.out.println("Choose the color of your player (R or B): ");
            sc = new Scanner(System.in);
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
            displacement(player1);
            if(player1.getScore() < MAX_SCORE) {
                displacement(player2);
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

    private static void displacement(Player currentPlayer) {
        StringBuilder sb = new StringBuilder();

        boolean good = false;
        
        sb.append("Player ");
        sb.append(currentPlayer.getId());
        sb.append(" choose a displacement : (block ");

        System.out.println(board);
        
        System.out.println(currentPlayer.getPossibleDisplament());
        do {
            System.out.println(sb.toString() + currentPlayer.getColor() + ")");

            sc = new Scanner(System.in);

            good = currentPlayer.displacement(sc.nextLine());
        }
        while (!good);
        
        System.out.println(board);

        System.out.println(currentPlayer.getPossibleDisplamentWhite());
        do {
            System.out.println(sb.toString() + "WHITE)");

            sc = new Scanner(System.in);

            good = currentPlayer.displacement(sc.nextLine());
        }
        while (!good);
    }

    static Board getBoard() {
        return board;
    }
}
