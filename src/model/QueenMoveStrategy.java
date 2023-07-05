package model;

public class QueenMoveStrategy implements IMoveStrategy{
    @Override
    public boolean isValidMovement(Piece piece, int col, int row) {
        return piece.getCol() == col || piece.getRow() == row
                || Math.abs(col - piece.col) == Math.abs(row - piece.getRow());
    }
}
