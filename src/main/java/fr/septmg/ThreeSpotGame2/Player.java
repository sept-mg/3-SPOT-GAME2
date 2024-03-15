package fr.septmg.ThreeSpotGame2;

public class Player {
    private ColorCase color;
    private boolean isPlaying;
    private int score;
    private int id;

    public Player(ColorCase color, int id) {
        assert color != null && color != ColorCase.EMPTY && color != ColorCase.WHITE;
        assert id > 0;
        assert id <= ThreeSpotGame.NB_PLAYER;
        this.color = color;
        this.isPlaying = false;
        this.score = 0;
        this.id = id;
    }

    public String getPossibleDisplament(Board board) {
        return getPossibleDisplament(this.color, board);
    }

    public String getPossibleDisplament(ColorCase color, Board board) {
        assert color == this.color || color == ColorCase.WHITE;

        board.setCurrentColor(color);
        isPlaying = true;
        return board.getBoardAllMovement();
    }

    public boolean displacement(String id, Board board) {
        return displacement(id, color, board);
    }

    public boolean displacement(String id, ColorCase colorMove, Board board) {
        assert isPlaying;
        assert color == colorMove || color == ColorCase.WHITE;
        int i = 0;
        
        try {
            i = Integer.parseInt(id);
        }
        catch (NumberFormatException e) {
            return false;
        }

        boolean validId = i <= board.getMaxMovement();
        assert validId;

        if(!validId){
            return false;
        } 

        int temp = board.move(i);
        if(colorMove == color) {
            score += temp;
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
