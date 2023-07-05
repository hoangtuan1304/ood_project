package model;

public class KnightMoveStrategy implements IMoveStrategy{
    @Override
    public boolean isValidMovement(Piece piece, int col, int row) {
        return Math.abs(col - piece.getCol()) * Math.abs(row - piece.getRow()) == 2;
    }
}
