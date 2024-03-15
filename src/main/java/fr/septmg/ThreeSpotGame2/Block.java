package fr.septmg.ThreeSpotGame2;

public class Block {
    private ColorCase color;
    private ColorCase[] positions;
    // private HashMap<Integer, AdjOriention> possibleDisplament;

    public Block(ColorCase color, int[] positions) {
        assert color != null && color != ColorCase.EMPTY;
        assert positionListNotToLongOrToShort(positions);
        this.color = color;
        makePosition(positions);

    }

    private void makePosition(int[] position) {
        this.positions = new ColorCase[Board.SIZE];
        for (int i : position) {
            assert (i >= 0 && i < Board.SIZE);
            this.positions[i] = color;
        }
    }

    public static boolean positionListNotToLongOrToShort(int[] position) {
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
        assert positionListNotToLongOrToShort(positions);
        makePosition(positions);
    }


}
