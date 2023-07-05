package model;

import view.BoardView;

import java.util.List;

public class CheckScanner {
    private BoardModel board;

    /**
     * @param board
     */
    public CheckScanner(BoardModel board) {
        super();
        this.board = board;
    }

    public boolean isKingChecked(Move move) {
        Piece king = board.findKing(move.isMovePieceIsWhite());

        int kingCol = king.getCol();
        int kingRow = king.getRow();

        if (board.getSelectedPiece() != null && board.getSelectedPieceName().equals("King")) {
            kingCol = move.getNewCol();
            kingRow = move.getNewRow();
        }

        return hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 0, 1) || // up
                hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 0, -1) || // down
                hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 1, 0) || // right
                hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, 0) || // left

                hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, -1) || // up left
                hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 1, -1) || // up right
                hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 1, 1) || // down right
                hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, 1) || // down left

                hitByKnight(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow)
                || hitByPawn(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow) || hitByKing(king, kingCol, kingRow);
    }


    public boolean isKingChecked(boolean color) {
        Piece king = board.findKing(color);

        List<Piece> listPieceOpposite = board.getPieceListOf(!color);

        int kingCol = king.getCol();
        int kingRow = king.getRow();

        for (Piece p : listPieceOpposite) {
            Move move = new Move(board, p, kingCol, kingRow);
            if (board.isValidMove(move)) {
                return true;
            }
        }
        return false;
    }

    private boolean hitByRook(int col, int row, Piece king, int kingCol, int kingRow, int colValue, int rowValue) {
        for (int i = 1; i < 8; i++) {
            if (kingCol + (i * colValue) == col && kingRow + (i * rowValue) == row) {
                break;
            }

            Piece piece = board.getPieceAt(kingCol + (i * colValue), kingRow + (i * rowValue));
            if (piece != null && piece != board.getSelectedPiece()) {
                if (!board.sameTeam(piece, king) && (piece.getName().equals("Rook") || piece.getName().equals("Queen"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean hitByBishop(int col, int row, Piece king, int kingCol, int kingRow, int colValue, int rowValue) {
        for (int i = 1; i < 8; i++) {
            if (kingCol - (i * colValue) == col && kingRow - (i * rowValue) == row) {
                break;
            }

            Piece piece = board.getPieceAt(kingCol - (i * colValue), kingRow - (i * rowValue));
            if (piece != null && piece != board.getSelectedPiece()) {
                if (!board.sameTeam(piece, king) && (piece.getName().equals("Bishop") || piece.getName().equals("Queen"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean hitByKnight(int col, int row, Piece king, int kingCol, int kingRow) {
        return checkKnight(board.getPieceAt(kingCol - 1, kingRow - 2), king, col, row)
                || checkKnight(board.getPieceAt(kingCol + 1, kingRow - 2), king, col, row)
                || checkKnight(board.getPieceAt(kingCol + 2, kingRow - 1), king, col, row)
                || checkKnight(board.getPieceAt(kingCol + 2, kingRow + 1), king, col, row)
                || checkKnight(board.getPieceAt(kingCol + 1, kingRow + 2), king, col, row)
                || checkKnight(board.getPieceAt(kingCol - 1, kingRow + 2), king, col, row)
                || checkKnight(board.getPieceAt(kingCol - 2, kingRow + 1), king, col, row)
                || checkKnight(board.getPieceAt(kingCol - 2, kingRow - 1), king, col, row);
    }

    private boolean checkKnight(Piece p, Piece k, int col, int row) {
        return p != null && !board.sameTeam(p, k) && p.getName().equals("Knight") && !(p.getRow() == row && p.getCol() == col);
    }

    private boolean hitByKing(Piece king, int kingCol, int kingRow) {
        return checkKing(board.getPieceAt(kingCol - 1, kingRow - 1), king)
                || checkKing(board.getPieceAt(kingCol, kingRow - 1), king)
                || checkKing(board.getPieceAt(kingCol + 1, kingRow - 1), king)
                || checkKing(board.getPieceAt(kingCol + 1, kingRow), king)
                || checkKing(board.getPieceAt(kingCol + 1, kingRow + 1), king)
                || checkKing(board.getPieceAt(kingCol, kingRow + 1), king)
                || checkKing(board.getPieceAt(kingCol - 1, kingRow + 1), king)
                || checkKing(board.getPieceAt(kingCol - 1, kingRow - 1), king);
    }

    private boolean checkKing(Piece p, Piece k) {
        return p != null && !board.sameTeam(p, k) && p.getName().equals("King");
    }

    private boolean hitByPawn(int col, int row, Piece king, int kingCol, int kingRow) {
        int colorIndex = king.isWhite() ? -1 : 1;
        return checkPawn(board.getPieceAt(kingCol + 1, kingRow + colorIndex), king, col, row)
                || checkPawn(board.getPieceAt(kingCol - 1, kingRow + colorIndex), king, col, row);
    }

    private boolean checkPawn(Piece p, Piece k, int col, int row) {
        return p != null && !board.sameTeam(p, k) && p.getName().equals("Pawn");
    }

    public boolean canMove(boolean colorTurn) {
        List<Piece> checkPieces = board.getPieceListOf(colorTurn);
        for (int i = 0; i < board.MAX_ROW; i++) {
            for (int j = 0; j < BoardModel.MAX_COL; j++) {
                for (Piece currentPiece : checkPieces) {
                    Move move = new Move(board, currentPiece, j, i);
                    if (board.isValidMove(move, colorTurn)) {
                        board.makeMove(move);
                        if (currentPiece.getName().equals("Pawn")) {
                            if (currentPiece.isWhite()) {
                                if (i == 6)
                                    currentPiece.setPieceFirstMove(true);
                                if (i == 0) {
                                    board.addToListPieces(currentPiece);
                                    Piece newQueen = board.getPieceAt(j, i);
                                    board.capture(newQueen);
                                }
                            } else {
                                if (i == 1)
                                    currentPiece.setPieceFirstMove(true);
                                if (i == 7) {
                                    board.addToListPieces(currentPiece);
                                    Piece newQueen = board.getPieceAt(j, i);
                                    board.capture(newQueen);
                                }
                            }
                        }
                        if (!isKingChecked(colorTurn)) {
                            currentPiece.setCol(move.getOldCol());
                            currentPiece.setRow(move.getOldRow());

                            currentPiece.setxPos(currentPiece.getCol() * BoardView.TILE_SIZE);
                            currentPiece.setyPos(currentPiece.getRow() * BoardView.TILE_SIZE);
                            if (move.getCapture() != null) {
                                board.addToListPieces(move.getCapture());
                            }
                            return true;
                        } else {
                            currentPiece.setCol(move.getOldCol());
                            currentPiece.setRow(move.getOldRow());

                            currentPiece.setxPos(currentPiece.getCol() * BoardView.TILE_SIZE);
                            currentPiece.setyPos(currentPiece.getRow() * BoardView.TILE_SIZE);
                            if (move.getCapture() != null) {
                                board.addToListPieces(move.getCapture());
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckMate(boolean colorTurn) {
        if (isKingChecked(colorTurn)) {
            if (!canMove(colorTurn))
                return true;
        }
        return false;
    }
}
