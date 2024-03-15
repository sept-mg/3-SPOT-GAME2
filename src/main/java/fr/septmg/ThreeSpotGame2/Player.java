package fr.septmg.ThreeSpotGame2;

/**
 * Represents a player in the game.
 */
public class Player {
    private ColorCase color;
    private boolean isPlaying;
    private int score;
    private int id;

    /**
     * Constructs a new Player object with the specified color and ID.
     *
     * @param color The color assigned to the player.
     * @param id The unique identifier for the player.
     * @throws AssertionError If the color is null, empty, or WHITE, or if the ID is not positive or exceeds the maximum number of players.
     */
    public Player(ColorCase color, int id) {
        assert color != null && color != ColorCase.EMPTY && color != ColorCase.WHITE : "Invalid color for player.";
        assert id > 0 : "Invalid player ID.";
        assert id <= ThreeSpotGame.NB_PLAYER : "Player ID exceeds maximum number of players.";
        this.color = color;
        this.isPlaying = false;
        this.score = 0;
        this.id = id;
    }

    /**
     * Retrieves the possible displacements for the player on the given board.
     *
     * @param board The game board to analyze for possible movements.
     * @return A string representing the possible displacements for the player on the board.
     */
    public String getPossibleDisplament(Board board) {
        return getPossibleDisplament(this.color, board);
    }

    /**
     * Retrieves the possible displacements for the specified color on the given board.
     *
     * @param color The color for which to calculate possible displacements.
     * @param board The game board to analyze for possible movements.
     * @return A string representing the possible displacements for the specified color on the board.
     * @throws AssertionError If the specified color is not the player's color or WHITE.
     */
    public String getPossibleDisplament(ColorCase color, Board board) {
        assert color == this.color || color == ColorCase.WHITE : "Invalid color for player.";

        board.setCurrentColor(color);
        isPlaying = true;
        return board.getBoardAllMovement();
    }

    /**
     * Performs a displacement action based on the provided movement ID and updates the board and player's score accordingly.
     *
     * @param id The ID of the movement to execute.
     * @param board The game board on which to perform the movement.
     * @return True if the displacement was successful, false otherwise.
     * @throws AssertionError If the player is not currently playing, if the specified color is not the player's color or WHITE,
     *                        or if the provided movement ID is invalid.
     */
    public boolean displacement(String id, Board board) {
        return displacement(id, color, board);
    }

    /**
     * Performs a displacement action based on the provided movement ID and color, and updates the board and player's score accordingly.
     *
     * @param id The ID of the movement to execute.
     * @param colorMove The color associated with the movement.
     * @param board The game board on which to perform the movement.
     * @return True if the displacement was successful, false otherwise.
     * @throws AssertionError If the player is not currently playing, if the specified color is not the player's color or WHITE,
     *                        or if the provided movement ID is invalid.
     */
    public boolean displacement(String id, ColorCase colorMove, Board board) {
        assert isPlaying : "Player is not currently playing.";
        assert color == colorMove || color == ColorCase.WHITE : "Invalid color for player.";
        int i = 0;

        try {
            i = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return false;
        }

        boolean validId = i <= board.getMaxMovement();
        assert validId : "Invalid movement ID.";

        if (!validId) {
            return false;
        }

        int temp = board.move(i);
        if (colorMove == color) {
            score += temp;
        }

        isPlaying = false;
        return true;
    }

    /**
     * Retrieves the player's current score.
     *
     * @return The player's current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Retrieves the player's ID.
     *
     * @return The player's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the color associated with the player.
     *
     * @return The color associated with the player.
     */
    public ColorCase getColor() {
        return color;
    }
}
