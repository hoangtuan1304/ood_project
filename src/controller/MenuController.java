package controller;

import model.BoardModel;
import model.GameModel;
import model.Piece;
import view.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuController {
    private HomeView menuView;
    private BoardView boardView;
    private BoardModel boardModel;
    private SettingView settingView;
    private GameView gameView;
    private GameModel gameModel;
    private BoardActionView boardAction;
    private ExitDialog exitDialog;
    private int timeGame = 10;
    private Color firstColor = new Color(183, 192, 216);
    private Color secondColor = new Color(232, 237, 249);
    private String filePath = "pieceTheme1.png";

    //Constructor
    public MenuController(HomeView menuView) {
        this.menuView = menuView;
        this.settingView = menuView.getSettingView();
        newGame();

       getGameView().getBoardActionView().btnHome.addMouseListener(backToMenuHome);
    }

    // Chơi ngay
    public MouseAdapter startGame = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            PieceListener.playSound("soundClick.wav");
            newGame();
            gameView.setVisible(true);
            menuView.setVisible(false);
            gameView.getPlayer1Info().getTimerModel().startTimer(gameView.getPlayer1Info().lblCountdownTime);
        }
    };

    // Mở cài đặt
    public MouseAdapter openSettingView = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            menuView.setVisible(false);
            menuView.getSettingView().setVisible(true);
            gameView.setVisible(false);
        }
    };


    //Chọn màu
    public ActionListener selectColorSetting = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ColorItem selectedItem = (ColorItem) settingView.selectColor.getSelectedItem();
            if (selectedItem != null) {
                settingView.selectColor.setSelectedItem(selectedItem.getName());
                firstColor = selectedItem.getFirstColor();
                secondColor = selectedItem.getSecondColor();
            }
            PieceListener.playSound("soundClick.wav");
        }
    };

    // trờ về menu chính
    public MouseAdapter backToMenuHome = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            menuView.getSettingView().setVisible(false);
            gameView.setVisible(false);
            menuView.setVisible(true);
            PieceListener.playSound("soundClick.wav");
        }
    };

    // chọn thời gian
    public ActionListener selectTimeSetting = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer selectedItem = (Integer) settingView.selectTime.getSelectedItem();
            if (selectedItem != null) {
                settingView.selectTime.setSelectedItem(selectedItem);
                timeGame = Integer.valueOf(String.valueOf(selectedItem));
            }
            PieceListener.playSound("soundClick.wav");
        }
    };

    public ActionListener selectThemeSetting = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ThemeItem selectedItem = (ThemeItem) settingView.selectPieceTheme.getSelectedItem();
            if (selectedItem != null) {
                settingView.selectPieceTheme.setSelectedItem(selectedItem.getName());
                filePath = selectedItem.getFilePath();
            }
            PieceListener.playSound("soundClick.wav");
        }
    };

    // Tạo mới tất cả
    public void newGame() {
        Piece.setChessFilePath(filePath);
        boardModel = new BoardModel();
        gameModel = new GameModel();
        gameModel.setTimeGame(timeGame);
        boardAction = new BoardActionView();
        boardView = new BoardView(gameModel);

        boardView.setColor(firstColor, secondColor);
        gameView = new GameView(gameModel, boardModel, boardView, boardAction);
    }

    public MouseAdapter exitGame = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            exitDialog = new ExitDialog();
            exitDialog.setVisible(true);
            PieceListener.playSound("soundClick.wav");
        }
    };

    public GameView getGameView() {
        return gameView;
    }
}
