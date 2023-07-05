package model;

public interface IMoveStrategy {
    public boolean isValidMovement(Piece piece, int col, int row);
}
