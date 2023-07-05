package model;

public class BishopMoveStrategy implements IMoveStrategy{
    @Override
    public boolean isValidMovement(Piece piece, int col, int row) {
        return Math.abs(col - piece.getCol()) == Math.abs(row - piece.getRow());
    }
}
