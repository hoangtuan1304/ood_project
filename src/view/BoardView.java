package view;

import model.*;

import java.awt.*;

import javax.swing.JPanel;

public class BoardView extends JPanel {
    private GameModel gameModel;
    public static final int TILE_SIZE = 80;
    public Color firstColor = new Color(183, 192, 216);
    public Color secondColor = new Color(232, 237, 249);
    private final Color HILIGHT_SQUARE = new Color(102, 103, 171, 190);
    private final Font FONT = new Font("Arial", Font.BOLD, 20);
    private final int MARGIN = 5;

    public BoardView(GameModel gameModel) {
        this.gameModel = gameModel;

        this.setPreferredSize(new Dimension(BoardModel.MAX_COL * TILE_SIZE,
                BoardModel.MAX_ROW * TILE_SIZE));
        this.setBackground(Color.GREEN);
        setPiece();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // paint board
        for (int i = 0; i < BoardModel.MAX_ROW; i++) {
            for (int j = 0; j < BoardModel.MAX_COL; j++) {
                g2d.setColor((i + j) % 2 == 0 ? firstColor : secondColor);
                g2d.fillRect(i * TILE_SIZE, j * TILE_SIZE,
                        TILE_SIZE, TILE_SIZE);
            }
        }

        // paint hilight
        if (gameModel.getSelectedPiece() != null) {
            for (int i = 0; i < BoardModel.MAX_ROW; i++) {
                for (int j = 0; j < BoardModel.MAX_COL; j++) {
                    if (gameModel.isValidMove(new Move(gameModel.getBoardModel(),
                            gameModel.getSelectedPiece(), j, i))) {
                        g2d.setColor(HILIGHT_SQUARE);
                        g2d.fillRect(j * TILE_SIZE, i * TILE_SIZE,
                                TILE_SIZE, TILE_SIZE);
                    }
                }
            }
        }

        // Piece num
        for (int i = 0; i < 8; i++) {
            if (i % 2 != 0)
                g2d.setColor(firstColor);
            else
                g2d.setColor(secondColor);

            g2d.setFont(FONT);
            g2d.drawString(String.valueOf(BoardModel.MAX_ROW - i), MARGIN, (TILE_SIZE * i + MARGIN * 4));
            if (i % 2 == 0)
                g2d.setColor(firstColor);
            else
                g2d.setColor(secondColor);
            g2d.drawString(String.valueOf((char) (i + 97)), (TILE_SIZE * (i + 1) - MARGIN * 3),
                    TILE_SIZE * (BoardModel.MAX_ROW - 1) + MARGIN * 4);
        }

        // paint pieces
        for (Piece piece : gameModel.getListPieces()) {
            piece.paint(g2d);
        }
    }

    public void setPiece() {
        gameModel.addPiece();
    }

    public void setColor(Color color1, Color color2) {
        firstColor = color1;
        secondColor = color2;
        repaint();
    }
}
