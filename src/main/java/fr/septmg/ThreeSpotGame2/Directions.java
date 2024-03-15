package fr.septmg.ThreeSpotGame2;

/**
 * Utility class to manage directions in a game.
 */
public class Directions {

    private AdjDirection allOrientations[];

    /**
     * Constructs a new Directions object with the specified directions.
     *
     * @param directions The directions to initialize the object with.
     */
    public Directions(AdjDirection... directions) {
        allOrientations = new AdjDirection[AdjDirection.values().length];

        if (directions != null) {
            if (directions.length > 0) {
                for (int i = 0; i < directions.length; ++i) {
                    if (directions[i] != null && !has(directions[i])) {
                        allOrientations[directions[i].ordinal()] = directions[i];
                    }
                }
            }
        }
    }

    /**
     * Checks if the specified direction is present in the object.
     *
     * @param direction The direction to check.
     * @throws AssertionError If the direction is null.
     * @return True if the direction is present, false otherwise.
     */
    public boolean has(AdjDirection direction) {
        assert direction != null;
        return allOrientations[direction.ordinal()] != null;
    }

    /**
     * Adds a direction to the object.
     *
     * @param direction The direction to add.
     */
    public void add(AdjDirection direction) {
        if (!has(direction)) {
            allOrientations[direction.ordinal()] = direction;
        }
    }

    /**
     * Gets all the directions stored in the object.
     *
     * @return An array containing all the directions.
     */
    public AdjDirection[] getAllOrientations() {
        return allOrientations;
    }

    /**
     * Converts the directions to an integer value representing the count of directions present.
     *
     * @return The integer representation of the count of directions.
     */
    public int toInt() {
        int result = 0;

        for (int i = 0; i < allOrientations.length; ++i) {
            if (allOrientations[i] != null) {
                result += (allOrientations[i] != null) ? 1 : 0;
            }
        }

        return result;
    }
}

