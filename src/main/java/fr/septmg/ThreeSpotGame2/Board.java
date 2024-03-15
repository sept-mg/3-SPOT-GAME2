package fr.septmg.ThreeSpotGame2;

/**
 * Represents the game board for the ThreeSpot game.
 */
public class Board {
    private Block redBlock;
    private Block whiteBlock;
    private Block blueBlock;

    private int[] videCaseList;

    private ColorCase currentPlayedColor;
    private Movement[] currentMovements;
    private int maxMovement;

    /**
     * The size of a block on the board.
     */
    static final int BLOCK_SIZE = 2;

    /**
     * The total size of the board.
     */
    static final int SIZE = 9;

    /**
     * The number of columns on the board.
     */
    static final int X_SIZE = 3;

    /**
     * The number of rows on the board.
     */
    static final int Y_SIZE = 3;

    /**
     * The number of blocks on the board.
     */
    static final int NB_BLOCK = 3;

    private static final int CASE_STRING_SIZE = 7;

    /**
     * Constructs a new Board object with default configurations.
     */
    public Board() {
        int x_milieu = (X_SIZE-1)/2, y_milieu = (Y_SIZE-1)/2;
        int tempRed[] = new int[BLOCK_SIZE], tempWhite[] = new int[BLOCK_SIZE], tempBlue[] = new int[BLOCK_SIZE];

        for(int i = 0; i < BLOCK_SIZE; ++i) {
            tempRed[i] = i + x_milieu;
            tempWhite[i] = i + x_milieu + y_milieu * X_SIZE;
            tempBlue[i] = i + x_milieu + (Y_SIZE - 1) * X_SIZE;
        }
        this.redBlock = new Block(ColorCase.RED, tempRed);
        this.whiteBlock = new Block(ColorCase.WHITE, tempWhite);
        this.blueBlock = new Block(ColorCase.BLUE, tempBlue);

        videCaseList = new int[X_SIZE * Y_SIZE - BLOCK_SIZE * NB_BLOCK];
        currentMovements = new Movement[videCaseList.length * AdjDirection.values().length];
    }

    /**
     * Sets the current played color.
     *
     * @param color The color to set as the current played color.
     * @throws AssertionError If the color is empty.
     */
    public void setCurrentColor(ColorCase color) {
        assert color != ColorCase.EMPTY;
        this.currentPlayedColor = color;
    }

     /**
     * Gets the color case at the specified position on the board.
     *
     * @param position The position on the board to check.
     * @return The color case at the specified position.
     */
    private ColorCase getCase(int position) {
        if (redBlock.isInThisPosition(position)) {
            return ColorCase.RED;
        } else if (whiteBlock.isInThisPosition(position)) {
            return ColorCase.WHITE;
        } else if (blueBlock.isInThisPosition(position)) {
            return ColorCase.BLUE;
        } else {
            return ColorCase.EMPTY;
        }
    }

    // private int getCaseValue(int position) {
    //     return getCase(position).ordinal();
    // }

     /**
     * Determines the directions in which the current player can move from the specified position.
     *
     * @param position The position on the board to check for moveable directions.
     * @return A Directions object representing the moveable directions from the specified position.
     */
    public Directions moveable(int position) {
        Directions direction = new Directions();

        // up direction test
        if(position >= Y_SIZE) {
            ColorCase temp = getCase(position - Y_SIZE);

            if(temp == ColorCase.EMPTY) {
                direction.add(AdjDirection.UP);
            }
            else if (temp == currentPlayedColor && getCase(position) == ColorCase.EMPTY) {
                direction.add(AdjDirection.UP);
            }
        }
        
        // right direction test
        if(position % X_SIZE < X_SIZE && (position + 1) % Y_SIZE != 0) {
            ColorCase temp = getCase(position + 1);

            if(temp == ColorCase.EMPTY) {
                direction.add(AdjDirection.RIGHT);

            } else if(temp == currentPlayedColor && getCase(position) == ColorCase.EMPTY) {
                direction.add(AdjDirection.RIGHT);
            }
        }

        return direction;
    }

    /**
     * Returns a string representation of the board. Use Before getALlMovements !
     * 
     *
     * @return A string representing the current state of the board.
     */
    public String toString() {
        ColorCase currCase;
        int videCaseListIndex = 0;
        StringBuilder sb = new StringBuilder("* * * * * * * * * * * * *\n*       *       *       *\n");

        for (int i = 0; i < SIZE; ++i) {
            if(i % X_SIZE == 0 && i != 0) {
                sb.append("*\n*       *       *       *\n* * * * * * * * * * * * *\n*       *       *       *\n");
            }

            sb.append("*");
            currCase = getCase(i);

            if(currCase == ColorCase.EMPTY) {
                sb.append(i % Y_SIZE == X_SIZE - 1 ? "   O   " : "       ");
                videCaseList[videCaseListIndex++] = i;
            }
            else 
                sb.append("   ").append(currCase.toString().charAt(0)).append("   ");
        }
        return sb.append("*\n*       *       *       *\n* * * * * * * * * * * * *\n").toString();
    }


    /**
     * Generates a string representation of the board including all possible movements for the current player.
     * Each empty cell is annotated with a number representing a possible movement, and occupied cells are labeled
     * with the corresponding player color. Use After String the board !
     *
     * @return A string representing the current state of the board with all possible movements annotated.
     * @throws AssertionError If the list of empty cells is empty or if the current played color is null or empty.
     */
    public String getBoardAllMovement() {
        assert videCaseList.length > 0;
        assert currentPlayedColor != null && currentPlayedColor != ColorCase.EMPTY;
        
        ColorCase currCase;
        int videCaseListIndex = 0, accDirection = 0;

        StringBuilder sb = new StringBuilder("* * * * * * * * * * * * *\n*       *       *       *\n");

        for(int i = 0; i < SIZE; ++i) {
            if(i % X_SIZE == 0 && i != 0) {
                sb.append("*\n*       *       *       *\n* * * * * * * * * * * * *\n*       *       *       *\n");
            }
            sb.append("*");
            currCase = getCase(i);

            if(videCaseListIndex < videCaseList.length && i == videCaseList[videCaseListIndex] || currCase == currentPlayedColor) {
                Directions moveable = moveable(i);
                int moveableInt = moveable.toInt();
                StringBuilder sd = new StringBuilder();
                
                for(int j = 0; j < moveable.toInt(); ++j) {
                    if(j > 0) {
                        sd.append(" - ");
                    }

                    sd.append(++accDirection);
                    
                    AdjDirection[] allowedOrientations = moveable.getAllOrientations();
                    
                    while(allowedOrientations[j] == null && j < moveableInt) {
                        ++j;
                    }

                    currentMovements[accDirection - 1] = new Movement(i, allowedOrientations[j]);
                }
                if(currCase == ColorCase.EMPTY)
                    ++videCaseListIndex;

                if(moveableInt == 0)
                    sb.append(i % Y_SIZE == X_SIZE - 1 ? "   O   " : "       ");
                else{
                    for (int j = 0; j < (CASE_STRING_SIZE - sd.length())/2 + 1; ++j) {
                        sb.append(" ");
                        sd.append(" ");
                    }

                    sb.append(sd.toString());
                }
            }
            else {
                

                if(currCase == ColorCase.EMPTY || currCase == currentPlayedColor) {
                    sb.append(i % Y_SIZE == X_SIZE - 1 ? "   O   " : "       ");
                }
                else
                    sb.append("   ").append(currCase.toString().charAt(0)).append("   ");
            }

        }
        maxMovement = accDirection;
        return sb.append("*\n*       *       *       *\n* * * * * * * * * * * * *\n").toString();
    }

    /**
     * Retrieves the maximum number of movements available on the board.
     *
     * @return The maximum number of movements available.
     */
    public int getMaxMovement() {
        return maxMovement;
    }
    
    /**
     * Moves the current player's piece according to the specified movement ID and updates the board accordingly. Use After getAllMovement !
     *
     * @param idMovement The ID of the movement to execute.
     * @return The points gained from the movement.
     * @throws AssertionError If the movement ID is invalid, or if the current played color is null or empty.
     */
    public int move(int idMovement) {
        assert idMovement <= maxMovement && idMovement > 0;
        assert currentPlayedColor != null && currentPlayedColor != ColorCase.EMPTY;

        --idMovement;
        int point;
        switch (currentPlayedColor) {
            case RED:
                point = updateblock(redBlock, currentMovements[idMovement]);
                break;
            
            case WHITE:
                point = updateblock(whiteBlock, currentMovements[idMovement]);
                break;

            case BLUE:
                point = updateblock(blueBlock, currentMovements[idMovement]);
                break;
            
            default:
                throw new IllegalStateException("Unexpected value: " + currentPlayedColor);
        }

        currentPlayedColor = null;
        return point;
    }

    /**
     * Updates the specified block's position based on the given movement and returns the points gained.
     *
     * @param block The block to update.
     * @param movement The movement to execute.
     * @return The points gained from the movement.
     */
    private int updateblock(Block block, Movement movement) {
        int point = 0;
        int currentPlacement = movement.getPlacement();
        if(movement.getDirection() == AdjDirection.UP) {
            block.updatePosition(currentPlacement, currentPlacement - Y_SIZE);
            
            if(currentPlacement % Y_SIZE == X_SIZE-1)
                point = BLOCK_SIZE;
        }
        else if(movement.getDirection() == AdjDirection.RIGHT) {
            block.updatePosition(currentPlacement, ++currentPlacement);

            if(currentPlacement % Y_SIZE == X_SIZE-1)
                point = 1;
        }

        return point;
    }

    /**
     * Retrieves the current color being played.
     *
     * @return The current color being played, or null if no color is currently being played.
     */
    public ColorCase getCurrentColor() {
        return currentPlayedColor;
    }

    /**
     * Checks if a color has been selected for the current turn.
     *
     * @return True if a color has been selected, otherwise false.
     */
    public boolean colorPeaked() {
        return currentPlayedColor != null;
    }

}
