package controller;

import model.BoardModel;
import model.GameModel;
import model.Move;
import model.Piece;
import view.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class PieceListener extends MouseAdapter {
    private BoardModel boardModel;
    private BoardView boardView;
    private GameView gameView;
    private GameModel gameModel;
    private BoardActionView boardActionView;
    private BoardInfoView player1Info;
    private BoardInfoView player2Info;
    private boolean isFirtMoveBlack = true;
    public PieceListener(GameView gameView) {
        super();
        this.gameView = gameView;
        this.gameModel = gameView.getGameModel();
        this.boardModel = gameModel.getBoardModel();
        this.boardView = gameView.getBoardView();
        this.boardActionView = gameView.getBoardActionView();
        this.player1Info = gameView.getPlayer1Info();
        this.player2Info = gameView.getPlayer2Info();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / BoardView.TILE_SIZE;
        int row = e.getY() / BoardView.TILE_SIZE;

        Piece pieceXY = boardModel.getPieceAt(col, row);
        if (pieceXY != null)
            boardModel.setSelectedPiece(pieceXY);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (boardModel.getSelectedPiece() != null) {
            boardModel.setSelectedPieceXPos(e.getX() - BoardView.TILE_SIZE / 2);
            boardModel.setSelectedPieceYPos(e.getY() - BoardView.TILE_SIZE / 2);
            boardView.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / BoardView.TILE_SIZE;
        int row = e.getY() / BoardView.TILE_SIZE;

        if (boardModel.getSelectedPiece() != null) {
            Move move = new Move(boardModel, boardModel.getSelectedPiece(), col, row);

            if (boardModel.isValidMove(move)) {
                boardModel.makeMove(move);
                if (boardModel.isCheckMate(!boardModel.isWhiteTurn())) {
                    boardModel.getColorWin();
                    boardModel.setIsEndGame();
                }

                gameModel.addMove(move);

                boardActionView.txtHistoryMoved.setText(gameModel.formatPastMovedText());
                boardActionView.txtHistoryMoved.repaint();

                if (boardModel.isWhiteTurn()) {
                    player1Info.getTimerModel().pauseTimer();
                    player2Info.getTimerModel().resumeTimer();
                } else {
                    player2Info.getTimerModel().pauseTimer();
                    player1Info.getTimerModel().resumeTimer();
                }

                if (gameModel.isGameEnd()) {
                    String winner = gameModel.getWinner().getName();
                    new ResultView(winner).setVisible(true);
                    player1Info.getTimerModel().pauseTimer();
                    player2Info.getTimerModel().pauseTimer();
                    boardActionView.btnGiveUp.setEnabled(false);
                    boardActionView.btnUndo.setEnabled(false);
                    playSound("soundResult.wav");
                }

                playSound("soundMove.wav");

                boardModel.flipTurn();

                if (isFirtMoveBlack && !boardModel.isWhiteTurn()) {
                    player2Info.getTimerModel().startTimer(player2Info.lblCountdownTime);
                    isFirtMoveBlack = false;
                }

            } else {
                boardModel.setSelectedPieceXPos(boardModel.getSelectedPieceCol() * BoardView.TILE_SIZE);
                boardModel.setSelectedPieceYPos(boardModel.getSelectedPieceRow() * BoardView.TILE_SIZE);
            }
        }

        boardModel.setSelectedPiece(null);

        boardView.repaint();
    }

    public void mouseClicked(MouseEvent e) {
        int col = e.getX() / BoardView.TILE_SIZE;
        int row = e.getY() / BoardView.TILE_SIZE;

        Piece pieceXY = boardModel.getPieceAt(col, row);
        if (pieceXY != null)
            boardModel.setSelectedPiece(pieceXY);
    }

    public static void playSound(String url) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            TestSound.class.getResourceAsStream("/res/" + url));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}


