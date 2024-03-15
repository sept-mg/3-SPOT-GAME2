package fr.septmg.ThreeSpotGame2;

/**
 * Represents a block in the ThreeSpot game.
 */
public class Block {
    private ColorCase color;
    private ColorCase[] positions;

    /**
     * Constructs a new Block with the specified color and positions.
     *
     * @param color The color of the block.
     * @param positions The positions of the block on the board.
     * @throws AssertionError If the color is null or empty, or if the positions list is too long or too short.
     */
    public Block(ColorCase color, int[] positions) {
        assert color != null && color != ColorCase.EMPTY;
        assert positionListNotToLongOrToShort(positions);
        this.color = color;
        makePosition(positions);
    }

    /**
     * Creates positions array for the block based on the given positions.
     *
     * @param position The positions of the block on the board.
     * @throws AssertionError If the positions is out of range.
     */
    private void makePosition(int[] position) {
        this.positions = new ColorCase[Board.SIZE];
        for (int i : position) {
            assert (i >= 0 && i < Board.SIZE);
            this.positions[i] = color;
        }
    }

    /**
     * Checks if the given positions list is not too long or too short.
     *
     * @param position The positions list to check.
     * @return True if the positions list is not too long or too short, false otherwise.
     */
    public static boolean positionListNotToLongOrToShort(int[] position) {
        return position != null && position.length < Board.SIZE;
    }

    /**
     * Gets the color of the block.
     *
     * @return The color of the block.
     */
    public ColorCase getColor() {
        return color;
    }

    /**
     * Gets the color at the specified position.
     *
     * @param placement The position to check.
     * @throws AssertionError If the placement is out of bounds.
     * @return The color at the specified position.
     */
    public ColorCase getPosition(int placement) {
        assert positionInRange(placement);
        return positions[placement] == null ? ColorCase.EMPTY : positions[placement];
    }

    /**
     * Gets the ordinal value of the color at the specified position.
     *
     * @param index The position index.
     * @return The ordinal value of the color at the specified position.
     */
    public int getPositionOrdinal(int index) {
        return getPosition(index).ordinal();
    }

    /**
     * Checks if the block is in the specified position.
     *
     * @param placement The position to check.
     * @throws AssertionError If the placement is out of bounds.
     * @return True if the block is in the specified position, false otherwise.
     */
    public boolean isInThisPosition(int placement) {
        assert positionInRange(placement);
        return getPosition(placement) == color;
    }

    /**
     * Checks if the given position is within the range of the positions array.
     *
     * @param placement The position to check.
     * @return True if the position is within the range, false otherwise.
     */
    public boolean positionInRange(int placement) {
        return (placement >= 0 && placement < positions.length);
    }

    /**
     * Updates the positions of the block.
     *
     * @param positions The new positions of the block on the board.
     * @throws AssertionError If the positions list is too long or too short.
     */
    public void updatePosition(int... positions) {
        assert positionListNotToLongOrToShort(positions);
        makePosition(positions);
    }

    // You may include JavaDoc for private methods if deemed necessary for clarity or future maintenance.
}
