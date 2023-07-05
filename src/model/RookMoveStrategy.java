package model;

public class RookMoveStrategy implements IMoveStrategy{
    @Override
    public boolean isValidMovement(Piece piece, int col, int row) {
        return piece.getCol() == col || piece.getRow() == row;
    }
}
