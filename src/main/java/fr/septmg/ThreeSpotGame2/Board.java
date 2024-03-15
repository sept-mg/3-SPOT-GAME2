package fr.septmg.ThreeSpotGame2;

public class Board {
    private Block redBlock;
    private Block whiteBlock;
    private Block blueBlock;

    private int[] videCaseList;

    private ColorCase currentPlayedColor;
    private Movement[] currentMovements;
    private int maxMovement;

    static final int BLOCK_SIZE = 2;
    static final int SIZE = 9;
    static final int X_SIZE = 3;
    static final int Y_SIZE = 3;
    static final int NB_BLOCK = 3;

    private static final int CASE_STRING_SIZE = 7;
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

    public void setCurrentColor(ColorCase color) {
        assert color != ColorCase.EMPTY;
        this.currentPlayedColor = color;
    }

    private ColorCase getCase(int position) {
        if(redBlock.isInThisPosition(position)) {
            return ColorCase.RED;
        }
        else if(whiteBlock.isInThisPosition(position)) {
            return ColorCase.WHITE;
        }
        else if(blueBlock.isInThisPosition(position)) {
            return ColorCase.BLUE;
        }
        else {
            return ColorCase.EMPTY;
        }
    }

    // private int getCaseValue(int position) {
    //     return getCase(position).ordinal();
    // }

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

    public int getMaxMovement() {
        return maxMovement;
    }
    
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

    public ColorCase getCurrentColor() {
        return currentPlayedColor;
    }

    public boolean colorPeaked() {
        return currentPlayedColor != null;
    }
}
