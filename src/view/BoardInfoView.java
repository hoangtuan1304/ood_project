package view;

import model.CountdownTimerModel;
import model.GameModel;
import model.Player;

import javax.swing.*;
import java.awt.*;

public class BoardInfoView extends JPanel {
    public static final Color BACKGROUND_COLOR = new Color(53, 61, 98);
    private final  Font FONT = new Font("Arial", Font.BOLD, 24);
    private GameModel gameModel;
    private Player player;
    public JLabel lblPlayerName;
    public JLabel lblCountdownTime;

    private CountdownTimerModel countdownTimerModel;
    public BoardInfoView(GameModel gameModel, Player player) {
        this.gameModel = gameModel;
        this.player = player;
        this.countdownTimerModel = player.getTimer();

        JPanel infoWrapper = new JPanel();
        infoWrapper.setLayout(new GridLayout(1, 2, 80, 50));

        lblCountdownTime = new JLabel(String.valueOf(gameModel.timeGame) + ":00", SwingConstants.CENTER);
        lblCountdownTime.setFont(FONT);
        lblCountdownTime.setForeground(Color.WHITE);

        lblPlayerName = new JLabel(player.getName());
        lblPlayerName.setFont(FONT);
        lblPlayerName.setForeground(Color.WHITE);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        namePanel.add(lblPlayerName);
        namePanel.setBackground(BACKGROUND_COLOR);


        JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new BorderLayout());

        JLabel iconClock = new JLabel();
        timerPanel.add(iconClock, BorderLayout.WEST);
        iconClock.setIcon(new ImageIcon(BoardInfoView.class.getResource("/res/iconClock.png")));

        timerPanel.add(lblCountdownTime, BorderLayout.CENTER);
        timerPanel.setBackground(BACKGROUND_COLOR);

        infoWrapper.add(namePanel);
        infoWrapper.add(timerPanel);
        infoWrapper.setBackground(BACKGROUND_COLOR);
        infoWrapper.setPreferredSize(new Dimension(320, 50));
        add(infoWrapper);
    }

    public CountdownTimerModel getTimerModel() {
        return countdownTimerModel;
    }
}
