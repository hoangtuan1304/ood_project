package model;

public class Player {
    private String name;
    private boolean colorPiece;

    private CountdownTimerModel timer;

    public Player(String name, boolean colorPiece) {
        this.name = name;
        this.colorPiece = colorPiece;
        this.timer = new CountdownTimerModel(GameModel.timeGame);
    }

    public String getName() {
        return name;
    }

    public boolean getColorPiece() {
        return colorPiece;
    }

    public CountdownTimerModel getTimer() {
        return timer;
    }

    public boolean isTimeOut() {
        return timer.isTimeOut();
    }

    public void setTimer(int timeGame) {
        this.timer = new CountdownTimerModel(timeGame);
    }


}
