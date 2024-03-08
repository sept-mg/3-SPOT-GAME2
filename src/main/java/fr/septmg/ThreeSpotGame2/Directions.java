package fr.septmg.ThreeSpotGame2;

public class Directions {

    private AdjDirection allOrientations[];

    public Directions() {
        allOrientations = new AdjDirection[AdjDirection.values().length];
    }

    public Directions(AdjDirection... directions) {
        this();

        assert directions.length <= allOrientations.length;
        assert directions.length > 0;

        for (int i = 0; i < directions.length; ++i) {
            if(directions[i] != null && !has(directions[i])) {
                allOrientations[directions[i].ordinal()] = directions[i];
            }
        }
    }

    public boolean has(AdjDirection direction) {
        return allOrientations[direction.ordinal()] != null;
    }
    
    public void add(AdjDirection direction) {
        if(!has(direction)) {
            allOrientations[direction.ordinal()] = direction;
        }
    }

    public AdjDirection[] getAllOrientations() {
        return allOrientations;
    }

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
