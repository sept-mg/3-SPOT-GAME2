package fr.septmg.ThreeSpotGame2;

/**
 * Represents a movement in the game.
 */
public class Movement {
    private int placement;
    private AdjDirection direction;

    /**
     * Constructs a new Movement object with the specified index and direction.
     *
     * @param index The index of the movement.
     * @param direction The direction of the movement.
     * @throws AssertionError If the index is out of bounds or if the direction is null.
     */
    public Movement(int index, AdjDirection direction) {
        assert index >= 0 && index < Board.SIZE;
        assert direction != null;

        this.placement = index;
        this.direction = direction;
    }

    /**
     * Constructs a new Movement object with default values.
     */
    public Movement() {
        this(0, AdjDirection.UP);
    }

    /**
     * Gets the index of the movement.
     *
     * @return The index of the movement.
     */
    public int getPlacement() {
        return placement;
    }

    /**
     * Sets the index of the movement.
     *
     * @param index The new index of the movement.
     * @throws AssertionError If the index is out of bounds.
     */
    public void setPlacement(int index) {
        assert index >= 0 && index < Board.SIZE;
        this.placement = index;
    }

    /**
     * Gets the direction of the movement.
     *
     * @return The direction of the movement.
     */
    public AdjDirection getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the movement.
     *
     * @param direction The new direction of the movement.
     * @throws AssertionError If the direction is null.
     */
    public void setDirection(AdjDirection direction) {
        assert direction != null;
        this.direction = direction;
    }
}

