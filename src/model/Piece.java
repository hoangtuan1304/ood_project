package model;

import view.BoardView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class Piece {
    protected int col;
    protected int row;

    protected int xPos;
    protected int yPos;

    protected boolean isWhite;
    protected String name;
    protected int value;

    protected boolean isFirstMove = true;

    private static String chessFilePath = "pieceTheme2.png";

    protected BufferedImage sheet;

    {
        try {
            InputStream inputStream = Piece.class.getClassLoader().getResourceAsStream("res/" + chessFilePath);
            sheet = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Image sprite;
    protected BoardModel board;
    protected IMoveStrategy moveStrategy;

    protected int sheetScale = sheet.getWidth() / 6;

    public Piece(BoardModel board) {
        this.board = board;
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(sprite, xPos, yPos, null);
    }

    public boolean isValidMovement(int col, int row) {
        return true;
    }

    public boolean moveCollidesWithPiece(int col, int row) {
        return false;
    }

    public boolean isSameColor(Piece other) {
        return this.isWhite() == other.isWhite();
    }

    @Override
    public String toString() {
        return name + "";
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public String getName() {
        return name;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setPieceFirstMove(boolean option) {
        isFirstMove = option;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setColorIsWhite(boolean option) {
        isWhite = option;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }
    public static void setChessFilePath(String pathName) {
        chessFilePath = pathName;
    }

}
