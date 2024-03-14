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

        System.out.println(winMessage(ingame()));

        sc.close();
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

    private static void displacement(Player currenPlayer, Player secondPlayer) {
        displacement(currenPlayer, secondPlayer, currenPlayer.getColor());
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

    private static Player ingame() {
        Player playerWasStopped = null;
        while (player2.getScore() < MAX_SCORE && playerWasStopped == null)
        {
            displacement(player1, player2);
            if(player1.getScore() < MAX_SCORE) {
                displacement(player1, player2, ColorCase.WHITE);

                displacement(player2, player1);
                if(player2.getScore() < MAX_SCORE) {
                    displacement(player2, player1, ColorCase.WHITE);
                }
                else
                    playerWasStopped = player2;
            }
            else
                playerWasStopped = player1;
        }

        return playerWasStopped;
    }

    private static String winMessage(Player playerWasStopped) {
        assert playerWasStopped != null;

        Player secondPlayer = playerWasStopped == player1 ? player2 : player1;

        return secondPlayer.getScore() < MIN_SCORE_TO_WIN ? buildWinText(secondPlayer, playerWasStopped) : buildWinText(playerWasStopped, secondPlayer);
    }

    private static String buildWinText(Player playerWin, Player playerLose) {
        return new StringBuilder("Player ")
                    .append(playerWin.getId())
                    .append(" wins !")
                    .append("\nWith ")
                    .append(playerWin.getScore())
                    .append(" points at ")
                    .append(playerLose.getScore())
                    .append(" points.")
                    .append(playerWin.getScore() < playerLose.getScore() ? new StringBuilder(" Player ").append(playerLose.getId()).append(" forgot the minimum score rule (the second player need a minimum above or equals to ").append(Integer.toString(MIN_SCORE_TO_WIN)).append(")") : "")
                    .toString();
    }

    static Board getBoard() {
        return board;
    }
}
