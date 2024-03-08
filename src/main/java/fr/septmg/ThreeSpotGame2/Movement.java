package fr.septmg.ThreeSpotGame2;

public class Movement {
    private int placement;
    private AdjDirection direction;

    public Movement(int index, AdjDirection direction) {
        assert index >= 0 && index < Board.SIZE;
        this.placement = index;
        this.direction = direction;
    }

    public Movement() {
        this(0, AdjDirection.UP);
    }

    public int getPlacement() {
        return placement;
    }

    public void setPlacement(int index) {
        assert index >= 0 && index < Board.SIZE;
        this.placement = index;
    }

    public AdjDirection getDirection() {
        return direction;
    }

    public void setDirection(AdjDirection direction) {
        assert direction != null;
        this.direction = direction;
    }
}
