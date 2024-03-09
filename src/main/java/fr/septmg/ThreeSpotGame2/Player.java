package fr.septmg.ThreeSpotGame2;

public class Player {
    private ColorCase color;
    private boolean isPlaying;
    private int score;
    private int id;

    public Player(ColorCase color, int id) {
        this.color = color;
        this.isPlaying = false;
        this.score = 0;
        this.id = id;
    }

    public String getPossibleDisplament() {
        ThreeSpotGame.getBoard().setCurrentColor(color);
        isPlaying = true;
        return ThreeSpotGame.getBoard().getPlateauAllMovement();
    }

    public String getPossibleDisplamentWhite() {
        ThreeSpotGame.getBoard().setCurrentColor(ColorCase.WHITE);
        isPlaying = true;
        return ThreeSpotGame.getBoard().getPlateauAllMovement();
    }

    public boolean displacement(String id) {
        assert (isPlaying);
        int i = 0;
        
        try {
            i = Integer.parseInt(id);
        }
        catch (NumberFormatException e) {
            return false;
        }
        

        ThreeSpotGame.getBoard().setCurrentColor(color);

        try {
            score += ThreeSpotGame.getBoard().move(i);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        catch (NullPointerException e) {
            return false;
        }

        isPlaying = false;
        return true;
    }

    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }

    public ColorCase getColor() {
        return color;
    }
}
