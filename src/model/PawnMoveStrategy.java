package model;

public class PawnMoveStrategy implements IMoveStrategy{
    @Override
    public boolean isValidMovement(Piece piece, int col, int row) {
        int colorIndex = piece.isWhite() ? 1 : -1;

        // push pawn 1
        if(piece.getCol() == col && row == piece.getRow() - colorIndex && piece.board.getPieceAt(col, row) == null)
            return true;

        //pust pawn 2
        if(piece.isFirstMove && piece.getCol() == col && row == piece.getRow() - colorIndex * 2
                && piece.board.getPieceAt(col, row) == null && piece.board.getPieceAt(col, row + colorIndex) == null)
            return true;

        //capture left
        if(col == piece.getCol() - 1 && row == piece.getRow() - colorIndex && piece.board.getPieceAt(col, row) != null)
            return true;

        //capture right
        if(col == piece.getCol() + 1 && row == piece.getRow() - colorIndex && piece.board.getPieceAt(col, row) != null)
            return true;

        return false;
    }
}
