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

        ingame();

        showWin();

        sc.close();
    }

    private static void ingame() {
        boolean breakq = false;
        while (player2.getScore() < MAX_SCORE && !breakq)
        {
            if(displacement(player1, player2)) {
                displacement(player1, player2, ColorCase.WHITE);

                if(displacement(player2, player1)) {
                    displacement(player2, player1, ColorCase.WHITE);
                }
                else
                    breakq = true;
            }
            else
                breakq = true;
        }
    }

    private static String buildTextSectorInGame(Player currenPlayer, Player secondPlayer, ColorCase blockColor) {
        return new StringBuilder("Player ")
                .append(currenPlayer.getId())
                .append(" (Your Score ")
                .append(Integer.toString(currenPlayer.getScore()))
                .append(" to ")
                .append(Integer.toString(secondPlayer.getScore()))
                .append(") choose a displacement : (block ")
                .append(blockColor.toString())
                .append(")")
                .toString();
    }

    private static String buildTextSectorInGame(Player currenPlayer, Player secondPlayer) {
        return buildTextSectorInGame(currenPlayer, secondPlayer, currenPlayer.getColor());
    }

    private static boolean displacement(Player currenPlayer, Player secondPlayer) {
        displacement(currenPlayer, secondPlayer, currenPlayer.getColor());

        return currenPlayer.getScore() < MAX_SCORE;
    }

    private static void displacement(Player currenPlayer, Player secondPlayer, ColorCase color) {
        board.setCurrentColor(color);
        boolean good = false;

        System.out.println(board);

        System.out.println(currenPlayer.getPossibleDisplament(color));
        do {
            System.out.println(buildTextSectorInGame(currenPlayer, secondPlayer, color));

            good = currenPlayer.displacement(sc.nextLine(), color);
        }
        while (!good);
    }

    private static void showWin() {

        

        if (player1.getScore() >= MAX_SCORE) {
            if(player2.getScore() >= MIN_SCORE_TO_WIN) {
                System.out.println(buildWinText(player1, player2));
            }
            else {
                System.out.println(buildWinText(player2, player1));
            }
        }
        else {
            if(player1.getScore() >= MIN_SCORE_TO_WIN) {
                System.out.println(buildWinText(player1, player2));
            }
            else {
                System.out.println(buildWinText(player2, player1));
            }
        }

    }

    private static String buildWinText(Player playerWin, Player playerLose) {
        return new StringBuilder("Player ")
                    .append(playerWin.getId())
                    .append(" wins !")
                    .append("\n with ")
                    .append(playerWin.getScore())
                    .append(" to ")
                    .append(playerLose.getScore())
                    .toString();
    }

    static Board getBoard() {
        return board;
    }
}
