package model;

import view.BoardView;

import java.awt.image.BufferedImage;

public class Knight extends Piece{
    public Knight(BoardModel board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = BoardView.TILE_SIZE * col;
        this.yPos = BoardView.TILE_SIZE * row;

        this.isWhite = isWhite;
        this.name = "Knight";

        this.moveStrategy = new KnightMoveStrategy();

        this.sprite = sheet.getSubimage(3 * sheetScale , isWhite ? 0 : sheetScale , sheetScale, sheetScale)
                .getScaledInstance(BoardView.TILE_SIZE, BoardView.TILE_SIZE, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int col, int row) {
        return moveStrategy.isValidMovement(this, col, row);
    }
}
