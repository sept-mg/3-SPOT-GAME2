package fr.septmg.ThreeSpotGame2;

import java.util.Scanner;

/**
 * Represents the Three Spot Game, a two-player game where players aim to score points by making strategic moves on a board.
 * The game ends when a player reaches the maximum score or when the other player fails to meet the minimum score requirement.
 */
public class ThreeSpotGame {
    private Board board;
    private Player player1, player2;
    private Scanner sc;

    /** The maximum score a player can achieve in the game. */
    final static int MAX_SCORE = 12;
    
    /** The minimum score required for a player to win the game. */
    final static int MIN_SCORE_TO_WIN = 6;
    
    /** The number of players in the game. */
    final static int NB_PLAYER = 2;
    
    /**
     * Constructs a new instance of the Three Spot Game and initializes the game components.
     * Players choose their colors, play the game, and the winner is determined.
     */
    public ThreeSpotGame() {
        board = new Board();
        playerPeekColor();
        System.out.println(winMessage(ingame()));
        sc.close();
    }

    /**
     * Prompts the players to choose their colors (red or blue).
     * Initializes player1 and player2 based on the chosen colors.
     */
    private void playerPeekColor() {
        String input = "";
        sc = new Scanner(System.in);

        do {
            System.out.println("Choose the color of your player (R or B): ");
            input = sc.nextLine();
        }
        while (!input.equals("R") && !input.equals("B"));

        if(input.equals("R")) {
            player1 = new Player(ColorCase.RED, 1);
            player2 = new Player(ColorCase.BLUE, 2);
        } else {
            player1 = new Player(ColorCase.BLUE, 1);
            player2 = new Player(ColorCase.RED, 2);
        }
    }

    /**
     * Builds the text indicating the current player's information and prompt for displacement.
     *
     * @param currentPlayer The current player.
     * @param secondPlayer The opponent player.
     * @param blockColor The color of the block involved in the displacement.
     * @return The constructed text.
     */
    private String buildTextSectorInGame(Player currenPlayer, Player secondPlayer, ColorCase blockColor) {
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
    /**
     * Initiates the displacement process for the current player.
     * This method automatically uses the color of the current player for the displacement.
     *
     * @param currentPlayer The current player initiating the displacement.
     * @param secondPlayer The opponent player.
     */
    private void displacement(Player currenPlayer, Player secondPlayer) {
        displacement(currenPlayer, secondPlayer, currenPlayer.getColor());
    }

    /**
     * Initiates the displacement process for the current player with the specified color.
     *
     * @param currentPlayer The current player initiating the displacement.
     * @param secondPlayer The opponent player.
     * @param color The color of the block involved in the displacement.
     */
    private void displacement(Player currenPlayer, Player secondPlayer, ColorCase color) {
        board.setCurrentColor(color);
        boolean good = false;

        System.out.println(board);

        System.out.println(currenPlayer.getPossibleDisplament(color, board));
        do {
            System.out.println(buildTextSectorInGame(currenPlayer, secondPlayer, color));

            good = currenPlayer.displacement(sc.nextLine(), color, board);
        }
        while (!good);
    }

    /**
     * Runs the main game loop until one of the players achieves the maximum score or the game ends.
     *
     * @return The player who reached the maximum score first or null if the game ends without a winner.
     */
    private Player ingame() {
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

    /**
     * Generates the win message based on the player who stopped the game.
     *
     * @param playerWasStopped The player who reached the maximum score.
     * @return The win message.
     * @throws AssertionError if playerWasStopped is null.
     */
    private String winMessage(Player playerWasStopped) {
        assert playerWasStopped != null;

        Player secondPlayer = playerWasStopped == player1 ? player2 : player1;

        return secondPlayer.getScore() < MIN_SCORE_TO_WIN ? buildWinText(secondPlayer, playerWasStopped) : buildWinText(playerWasStopped, secondPlayer);
    }

    /**
     * Constructs the win message based on the winning and losing players.
     *
     * @param playerWin The player who won the game.
     * @param playerLose The player who lost the game.
     * @return The constructed win message.
     */
    private String buildWinText(Player playerWin, Player playerLose) {
        return new StringBuilder("Player ")
                    .append(playerWin.getId())
                    .append(" wins !")
                    .append("\nWith ")
                    .append(playerWin.getScore())
                    .append(" points at ")
                    .append(playerLose.getScore())
                    .append(" points.")
                    .append(playerWin.getScore() < playerLose.getScore() ? new StringBuilder(" Player ")
                                                                                .append(playerLose.getId())
                                                                                .append(" forgot the minimum score rule (the second player need a minimum above or equals to ")
                                                                                .append(Integer.toString(MIN_SCORE_TO_WIN)).append(").") : "")
                    .toString();
    }
}
