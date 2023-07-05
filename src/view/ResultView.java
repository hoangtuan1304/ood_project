package view;

import controller.PieceListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResultView extends JDialog {
    private final JPanel contentPanel = new JPanel();

    public ResultView(String winner) {
        setUndecorated(true);
        setBounds(100, 100, 461, 357);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        contentPanel.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        contentPanel.setLayout(null);

        JLabel header = new JLabel("");
        header.setIcon(new ImageIcon(ResultView.class.getResource("/res/lblResult.png")));
        header.setBounds(57, 0, 282, 147);
        contentPanel.add(header);

        JLabel result = new JLabel("");
        if(winner.equals("Player1"))
            result.setIcon(new ImageIcon(ResultView.class.getResource("/res/result1.png")));
        else
            result.setIcon(new ImageIcon(ResultView.class.getResource("/res/result2.png")));
        result.setBounds(105, 150, 200, 50);
        contentPanel.add(result);

        JLabel buttonCon = new JLabel("");
        buttonCon.setIcon(new ImageIcon(ResultView.class.getResource("/res/btnOk.png")));
        buttonCon.setBounds(135, 215, 154, 51);
        buttonCon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setVisible(false);
                PieceListener.playSound("soundClick.wav");
            }
        });
        contentPanel.add(buttonCon);

        JLabel bgWin = new JLabel("");
        bgWin.setIcon(new ImageIcon(ResultView.class.getResource("/res/resultWrapper.png")));
        bgWin.setBounds(45, 81, 310, 212);
        contentPanel.add(bgWin);
    }
}
