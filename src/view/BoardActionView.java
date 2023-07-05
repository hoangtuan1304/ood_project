package view;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardActionView extends JPanel {
    private final Font FONT_HISTORY = new Font("Arial Rounded MT Bold", Font.BOLD, 19);
    private final Color COLOR_BORDER_HISTORY = new Color(147, 154, 176, 147);
    public JTextArea txtHistoryMoved;
    public JLabel btnUndo;
    public JLabel btnGiveUp;
    public JLabel btnHome;
    public BoardActionView() {
        setBackground(BoardInfoView.BACKGROUND_COLOR);
        btnHome = new JLabel();
        btnHome.setIcon(new ImageIcon(BoardActionView.class.getResource("/res/btnHome.png")));
        btnHome.setBounds(400, 0, 60, 60);
        add(btnHome);

        txtHistoryMoved = new JTextArea();
        txtHistoryMoved.setBounds(40, 60, 400, 500);
        txtHistoryMoved.setBackground(BoardInfoView.BACKGROUND_COLOR);
        txtHistoryMoved.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_HISTORY, 20, true));
        txtHistoryMoved.setFont(FONT_HISTORY);
        txtHistoryMoved.setForeground(Color.WHITE);
        txtHistoryMoved.setPreferredSize(new Dimension(250, 400));
        txtHistoryMoved.setCaret(new DefaultCaret() {
            public void paint(java.awt.Graphics g) {
            }
        });

        btnUndo = new JLabel();
        btnUndo.setIcon(new ImageIcon(BoardActionView.class.getResource("/res/btnUndo.png")));
        btnUndo.setBounds(80, 500, 200, 200);

        btnGiveUp = new JLabel();
        btnGiveUp.setIcon(new ImageIcon(BoardActionView.class.getResource("/res/btnGiveUp.png")));
        btnGiveUp.setBounds(260, 500, 200, 200);
        JLabel lblHistory = new JLabel();
        lblHistory.setIcon(new ImageIcon(BoardActionView.class.getResource("/res/lblHistory.png")));
        add(lblHistory);

        setLayout(null);
        lblHistory.setBounds(40, 25, 140, 20);

        add(txtHistoryMoved);
        add(btnUndo);
        add(btnGiveUp);
    }
}