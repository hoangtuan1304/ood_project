package model;

import view.BoardView;

import java.util.ArrayList;
import java.util.List;

public class BoardModel {
    public static final int MAX_COL = 8;
    public static final int MAX_ROW = 8;
    private Piece selectedPiece;
    private ArrayList<Piece> listPieces;
    private boolean whiteTurn = true;
    private CheckScanner checkScanner;
    private boolean isGameEnd = false;

    public BoardModel() {
        listPieces = new ArrayList<>();
        checkScanner = new CheckScanner(this);
    }

    public Piece getPieceAt(int col, int row) {
        for (Piece piece : listPieces) {
            if (piece.col == col && piece.row == row)
                return piece;
        }
        return null;
    }

    public void addPiece() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                if (i == 1)
                    listPieces.add(PieceFactory.createPiece("Pawn", this, j, i, false));
                if (i == 6)
                    listPieces.add(PieceFactory.createPiece("Pawn", this, j, i, true));
                if (i == 0) {
                    if (j == 0 || j == 7)
                        listPieces.add(PieceFactory.createPiece("Rook", this, j, i, false));
                    if (j == 1 || j == 6)
                        listPieces.add(PieceFactory.createPiece("Knight", this, j, i, false));
                    if (j == 2 || j == 5)
                        listPieces.add(PieceFactory.createPiece("Bishop", this, j, i, false));
                    if (j == 3)
                        listPieces.add(PieceFactory.createPiece("Queen", this, j, i, false));
                    if (j == 4)
                        listPieces.add(PieceFactory.createPiece("King", this, j, i, false));
                }
                if (i == 7) {
                    if (j == 0 || j == 7)
                        listPieces.add(PieceFactory.createPiece("Rook", this, j, i, true));
                    if (j == 1 || j == 6)
                        listPieces.add(PieceFactory.createPiece("Knight", this, j, i, true));
                    if (j == 2 || j == 5)
                        listPieces.add(PieceFactory.createPiece("Bishop", this, j, i, true));
                    if (j == 3)
                        listPieces.add(PieceFactory.createPiece("Queen", this, j, i, true));
                    if (j == 4)
                        listPieces.add(PieceFactory.createPiece("King", this, j, i, true));
                }
            }
        }

    }

    public Piece findKing(boolean isWhite) {
        for (Piece piece : listPieces) {
            if (piece.getName().equals("King") && isWhite == piece.isWhite())
                return piece;
        }
        return null;
    }

    public void makeMove(Move move) {
        if (move.getPieceName().equals("Pawn")) {
            movePawn(move);
        }

        move.setPieceCol(move.getNewCol());
        move.setPieceRow(move.getNewRow());
        move.setPieceXPos(move.getNewCol() * BoardView.TILE_SIZE);
        move.setPieceYPos(move.getNewRow() * BoardView.TILE_SIZE);

        move.setPieceIsFirstMove(false);

        capture(move.getCapture());
    }


    public void setIsEndGame() {
        isGameEnd = true;
    }

    public boolean getColorWin() {
        return isWhiteTurn();
    }

    private void movePawn(Move move) {
        int colorIndex = move.isMovePieceIsWhite() ? 0 : 7;

        move.setPieceCol(move.getNewCol());
        move.setPieceRow(move.getNewRow());
        move.setPieceXPos(move.getNewCol() * BoardView.TILE_SIZE);
        move.setPieceYPos(move.getNewRow() * BoardView.TILE_SIZE);

        move.setPieceIsFirstMove(false);

        if (move.getNewRow() == colorIndex) {
            promotePawn(move);
        }
    }

    public void promotePawn(Move move) {
        listPieces.add(PieceFactory.createPiece("Queen", this, move.getNewCol(), move.getNewRow(), move.isMovePieceIsWhite()));
        capture(move.getPiece());
    }

    public void capture(Piece piece) {
        listPieces.remove(piece);
    }

    public void flipTurn() {
        whiteTurn = !whiteTurn;
    }

    public boolean isValidMove(Move move) {
//        if (isGameEnd)
//            return false;

        if (move.isMovePieceIsWhite() != isWhiteTurn())
            return false;

        if (sameTeam(move.getPiece(), move.getCapture()))
            return false;

        if (!move.isPieceHasValidMovement(move.getNewCol(), move.getNewRow()))
            return false;

        if (move.isPieceCollided(move.getNewCol(), move.getNewRow()))
            return false;

        if (checkScanner.isKingChecked(move))
            return false;

        return true;
    }

    public boolean isValidMove(Move move, boolean turnColor) {
//        if(isGameEnd)
//            return false;

        if (move.isMovePieceIsWhite() != turnColor)
            return false;

        if (sameTeam(move.getPiece(), move.getCapture()))
            return false;

        if (!move.isPieceHasValidMovement(move.getNewCol(), move.getNewRow()))
            return false;

        if (move.isPieceCollided(move.getNewCol(), move.getNewRow()))
            return false;

        if (checkScanner.isKingChecked(move))
            return false;

        return true;
    }

    public boolean sameTeam(Piece piece1, Piece piece2) {
        if (piece1 == null || piece2 == null)
            return false;
        return piece1.isSameColor(piece2);
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public List<Piece> getPieceListOf(boolean isWhite) {

        List<Piece> list = new ArrayList<>();
        for (Piece p : listPieces) {
            if (p.isWhite == isWhite)
                list.add(p);
        }
        return list;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public ArrayList<Piece> getListPieces() {
        return listPieces;
    }

    public CheckScanner getCheckScanner() {
        return checkScanner;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public void addToListPieces(Piece piece) {
        getListPieces().add(piece);
    }

    public String getSelectedPieceName() {
        return getSelectedPiece().getName();
    }

    public void setSelectedPiece(Piece piece) {
        selectedPiece = piece;
    }

    public int getSelectedPieceCol() {
        return getSelectedPiece().getCol();
    }

    public int getSelectedPieceRow() {
        return getSelectedPiece().getRow();
    }

    public void setSelectedPieceXPos(int x) {
        getSelectedPiece().setxPos(x);
    }

    public void setSelectedPieceYPos(int y) {
        getSelectedPiece().setyPos(y);
    }

    public boolean isCheckMate(boolean color) {
        return checkScanner.isCheckMate(color);
    }
}
