package model;

public class Move {
    private int oldRow;
    private int oldCol;
    private int newRow;
    private int newCol;
    private Piece piece;
    private Piece capture;

    /**
     * @param newRow
     * @param newCol
     * @param piece
     */
    public Move(BoardModel board, Piece piece, int newCol, int newRow) {
        super();
        this.oldRow = piece.row;
        this.oldCol = piece.col;
        this.newRow = newRow;
        this.newCol = newCol;
        this.piece = piece;
        this.capture = board.getPieceAt(newCol, newRow);
    }

    @Override
    public String toString() {
        String pieceInCapture = (capture == null) ? "" : ("x");
        String pieceName = piece.getName().equals("Knight") ? "N" : String.valueOf(piece.getName().charAt(0));
        return pieceName + "" + pieceInCapture + (char)(newCol + 97) + newRow;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getPieceCol() {
        return piece.getCol();
    }

    public int getPieceRow() {
        return piece.getRow();
    }

    public String getPieceName() {
        return piece.getName();
    }

    public int getOldRow() {
        return oldRow;
    }

    public int getOldCol() {
        return oldCol;
    }

    public int getNewRow() {
        return newRow;
    }

    public int getNewCol() {
        return newCol;
    }

    public Piece getCapture() {
        return capture;
    }

    public boolean isMovePieceIsWhite() {
        return getPiece().isWhite();
    }

    public boolean isPieceHasValidMovement(int col, int row) {
        return getPiece().isValidMovement(col, row);
    }

    public boolean isPieceCollided(int col, int row) {
        return getPiece().moveCollidesWithPiece(col, row);
    }

    public void setPieceCol(int col) {
        this.piece.setCol(col);
    }

    public void setPieceRow(int row) {
        this.piece.setRow(row);
    }

    public boolean isPieceHasFirstMove() {
        return this.piece.isFirstMove();
    }

    public void setPieceIsFirstMove(boolean option) {
        this.piece.setPieceFirstMove(option);
    }

    public void setPieceColorIsWhite(boolean option) {
        this.piece.setColorIsWhite(option);
    }

    public void setPieceXPos(int x) {
        this.piece.setxPos(x);
    }

    public void setPieceYPos(int y) {
        this.piece.setyPos(y);
    }
}
