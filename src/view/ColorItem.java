package view;

import javax.swing.*;
import java.awt.*;

public class ColorItem {
    private String name;
    private Color firstColor;
    private Color secondColor;

    public ColorItem(String name, Color firstColor, Color secondColor) {
        this.name = name;
        this.firstColor = firstColor;
        this.secondColor = secondColor;
    }

    public String getName() {
        return name;
    }

    public Color getFirstColor() {
        return firstColor;
    }

    public Color getSecondColor() {
        return secondColor;
    }

    static class DualColorItemRenderer extends JPanel implements ListCellRenderer<ColorItem> {
        public DualColorItemRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends ColorItem> list, ColorItem value, int index, boolean isSelected, boolean cellHasFocus) {
            removeAll();
            JLabel label = new JLabel();
            label.setText(value.getName());
            label.setFont(new Font("Arial", Font.BOLD, 18));
            add(label);

            setBackground(Color.WHITE);
            setLayout(new GridLayout(1, 2));
            JPanel temp = new JPanel();
            temp.setLayout(new GridLayout(1, 2));

            JPanel colorPanel1 = new JPanel();
            colorPanel1.setBackground(value.getFirstColor());
            colorPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            temp.add(colorPanel1);


            JPanel colorPanel2 = new JPanel();
            colorPanel2.setBackground(value.getSecondColor());
            colorPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            temp.add(colorPanel2);
            temp.setBackground(new Color(222, 226, 230));
            add(temp);

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
