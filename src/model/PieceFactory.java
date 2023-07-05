package model;

public class PieceFactory {
    public static final Piece createPiece(String type, BoardModel boardModel, int col, int row, boolean isWhite) {
        switch (type) {
            case "Pawn":
                return new Pawn(boardModel, col, row, isWhite);
            case "Knight":
                return new Knight(boardModel, col, row, isWhite);
            case "Rook":
                return new Rook(boardModel, col, row, isWhite);
            case "King":
                return new King(boardModel, col, row, isWhite);
            case "Queen":
                return new Queen(boardModel, col, row, isWhite);
            case "Bishop":
                return new Bishop(boardModel, col, row, isWhite);
            default:
                throw new IllegalArgumentException("");
        }
    }
}
