package fr.septmg.ThreeSpotGame2;

public class Block {
    private ColorCase color;
    private ColorCase[] positions;
    // private HashMap<Integer, AdjOriention> possibleDisplament;

    public Block(ColorCase color, int[] positions) {
        assert positionListInRange(positions);
        this.color = color;

        this.positions = new ColorCase[Board.SIZE];
        makePosition(positions);

    }

    private void makePosition(int[] position) {
        
        for (int i : position) {
            assert (i >= 0 && i < Board.SIZE);
            this.positions[i] = color;
        }
    }

    public static boolean positionListInRange(int[] position) {
        return position != null && position.length < Board.SIZE;
    }

    public ColorCase getColor() {
        return color;
    }

    public ColorCase getPosition(int placement) {
        assert positionInRange(placement);
        return positions[placement] == null ? ColorCase.EMPTY : positions[placement];
    }

    public int getPositionOrdinal(int index) {
        return getPosition(index).ordinal();
    }

    public boolean isInThisPosition(int placement) {
        assert positionInRange(placement);
        return getPosition(placement) == color;
    }

    public boolean positionInRange(int placement) {
        return (placement >= 0 && placement < positions.length);
    }

    public void updatePosition(int... positions) {
        assert positionListInRange(positions);
        makePosition(positions);
    }


}
