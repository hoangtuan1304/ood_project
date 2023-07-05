package model;

import view.BoardView;

import java.awt.image.BufferedImage;

public class Queen extends Piece {
    public Queen(BoardModel board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = BoardView.TILE_SIZE * col;
        this.yPos = BoardView.TILE_SIZE * row;

        this.isWhite = isWhite;
        this.name = "Queen";

        this.moveStrategy = new QueenMoveStrategy();

        this.sprite = sheet.getSubimage(1 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale)
                .getScaledInstance(BoardView.TILE_SIZE, BoardView.TILE_SIZE, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int col, int row) {
        return moveStrategy.isValidMovement(this, col, row);
    }

    public boolean moveCollidesWithPiece(int col, int row) {
        if (this.col == col || this.row == row) {
            if (this.col > col) {
                for (int i = this.col - 1; i > col; i--) {
                    if (board.getPieceAt(i, this.row) != null)
                        return true;
                }
            }

            if (this.col < col) {
                for (int i = this.col + 1; i < col; i++) {
                    if (board.getPieceAt(i, this.row) != null)
                        return true;
                }
            }

            if (this.row > row) {
                for (int i = this.row - 1; i > row; i--) {
                    if (board.getPieceAt(this.col, i) != null)
                        return true;
                }
            }

            if (this.row < row) {
                for (int i = this.row + 1; i < row; i++) {
                    if (board.getPieceAt(this.col, i) != null)
                        return true;
                }
            }
        } else {
            if (this.col > col && this.row > row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPieceAt(this.col - i, this.row - i) != null)
                        return true;
                }
            }

            if (this.col < col && this.row > row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPieceAt(this.col + i, this.row - i) != null)
                        return true;
                }
            }

            if (this.col > col && this.row < row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPieceAt(this.col - i, this.row + i) != null)
                        return true;
                }
            }

            if (this.col < col && this.row < row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPieceAt(this.col + i, this.row + i) != null)
                        return true;
                }
            }
        }

        return false;
    }
}
