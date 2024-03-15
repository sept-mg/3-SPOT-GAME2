package fr.septmg.ThreeSpotGame2;

public class Directions {

    private AdjDirection allOrientations[];

    public Directions(AdjDirection... directions) {
        allOrientations = new AdjDirection[AdjDirection.values().length];
        
        if(directions != null){

            if(directions.length > 0) {
                for (int i = 0; i < directions.length; ++i) {
                    if(directions[i] != null && !has(directions[i])) {
                        allOrientations[directions[i].ordinal()] = directions[i];
                    }
                }
            }
        }
    }

    public boolean has(AdjDirection direction) {
        assert direction != null;
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
