package controller;

import model.BoardModel;
import model.GameModel;
import view.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardController {
    private BoardView boardView;
    private BoardActionView boardActionView;
    private BoardModel boardModel;
    private GameView gameView;
    private GameModel gameModel;
    private BoardInfoView player1Info;
    private BoardInfoView player2Info;
    public PieceListener pieceListener;

    public BoardController(GameView gameView) {
        this.gameView = gameView;
        this.gameModel = gameView.getGameModel();
        this.boardModel = gameModel.getBoardModel();
        this.boardView = gameView.getBoardView();
        this.boardActionView = gameView.getBoardActionView();
        this.player1Info = gameView.getPlayer1Info();
        this.player2Info = gameView.getPlayer2Info();
        this.pieceListener = new PieceListener(gameView);
    }

    public MouseAdapter undoMove = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            gameModel.undoMove();
            boardActionView.txtHistoryMoved.setText(gameModel.formatPastMovedText());
            if (boardModel.isWhiteTurn()) {
                player2Info.getTimerModel().pauseTimer();
                player1Info.getTimerModel().resumeTimer();
            } else {
                player1Info.getTimerModel().pauseTimer();
                player2Info.getTimerModel().resumeTimer();
            }
            boardView.repaint();
            PieceListener.playSound("soundClick.wav");
        }
    };

    public MouseAdapter giveUp = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            String winner = gameModel.getWinnerByGiveUp().getName();
            new ResultView(winner).setVisible(true);
            PieceListener.playSound("soundResult.wav");
            player1Info.getTimerModel().pauseTimer();
            player2Info.getTimerModel().pauseTimer();
            boardActionView.btnGiveUp.setEnabled(false);
            boardActionView.btnUndo.setEnabled(false);
        }
    };

    public MouseAdapter home = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            gameView.setVisible(false);
            new HomeView().setVisible(true);
            PieceListener.playSound("soundClick.wav");
        }
    };

}
