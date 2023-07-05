package model;

public class KingMoveStrategy implements IMoveStrategy{
    @Override
    public boolean isValidMovement(Piece piece, int col, int row) {
        return Math.abs((col - piece.getCol()) * (row - piece.getRow())) == 1
                || Math.abs(col - piece.getCol()) + Math.abs(row - piece.getRow()) == 1;
    }
}
