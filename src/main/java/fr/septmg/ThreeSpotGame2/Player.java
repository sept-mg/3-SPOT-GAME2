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
        return ThreeSpotGame.getBoard().getBoardAllMovement();
    }

    public String getPossibleDisplamentWhite() {
        ThreeSpotGame.getBoard().setCurrentColor(ColorCase.WHITE);
        isPlaying = true;
        return ThreeSpotGame.getBoard().getBoardAllMovement();
    }
    public boolean displacement(String id) {
        return displacement(id, color);
    }

    public boolean displacement(String id, ColorCase colorMove) {
        assert isPlaying;
        assert color == colorMove || color == ColorCase.WHITE;
        int i = 0;
        
        try {
            i = Integer.parseInt(id);
        }
        catch (NumberFormatException e) {
            return false;
        }

        boolean validId = i <= ThreeSpotGame.getBoard().getMaxMovement();
        assert validId;

        if(!validId){
            return false;
        } 

        int temp = ThreeSpotGame.getBoard().move(i);
        if(colorMove == color) {
            System.out.println("Score : " + temp);
            score += temp;
            System.out.println("Score : " + score);
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
