package view;

import model.BoardModel;
import model.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ThemeItem {
    private String name;
    private String filePath;

    public ThemeItem(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }

    public ImageIcon getImage() {
        BufferedImage sheet = null;
        {
            try {
                InputStream inputStream = Piece.class.getClassLoader().getResourceAsStream("res/" + filePath);
                sheet = ImageIO.read(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Image img = sheet.getSubimage(30, 30, sheet.getWidth() / 6 , sheet.getWidth() / 6)
                .getScaledInstance(BoardView.TILE_SIZE / 2, BoardView.TILE_SIZE / 2, BufferedImage.SCALE_SMOOTH);
        return new ImageIcon(img);
    }



    static class ThemeIcon extends JLabel implements ListCellRenderer<ThemeItem> {
        public ThemeIcon() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends ThemeItem> list, ThemeItem value, int index, boolean isSelected, boolean cellHasFocus) {
            setFont(new Font("Arial", Font.BOLD, 18));
            setText(value.getName());
            setIcon(value.getImage());

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }
    }
}
