package model;

import view.BoardView;

import java.awt.image.BufferedImage;

public class King extends Piece{
    public King(BoardModel board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = BoardView.TILE_SIZE * col;
        this.yPos = BoardView.TILE_SIZE * row;

        this.isWhite = isWhite;
        this.name = "King";

        this.moveStrategy = new KingMoveStrategy();

        this.sprite = sheet.getSubimage(0 * sheetScale, isWhite ? 1 : sheetScale, sheetScale, sheetScale)
                .getScaledInstance(BoardView.TILE_SIZE, BoardView.TILE_SIZE, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int col, int row) {
        return moveStrategy.isValidMovement(this, col, row);
    }
}
