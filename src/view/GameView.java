package view;

import controller.BoardController;
import controller.MenuController;
import controller.PieceListener;
import model.BoardModel;
import model.GameModel;
import model.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameView extends JFrame implements  Runnable{

    private GameModel gameModel;
    private BoardModel boardModel;
    private BoardView boardView;
    private BoardActionView boardActionView;
    private BoardInfoView player1Info;
    private BoardInfoView player2Info;
    private BoardController boardController;

    public GameView(GameModel gameModel, BoardModel boardModel, BoardView boardView, BoardActionView boardActionView) {
        this.gameModel = gameModel;
        this.boardView = boardView;
        this.boardActionView = boardActionView;
        this.boardModel = boardModel;
        this.player1Info = new BoardInfoView(this.gameModel, gameModel.getPlayer1());
        this.player2Info = new BoardInfoView(this.gameModel, gameModel.getPlayer2());
        this.boardController = new BoardController(this);


        setPreferredSize(new Dimension(1310, 1000));
        setBounds(100, 100, 1300, 1000);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.black);
        contentPane.setBounds(0, 0, 1300, 1000);

        setContentPane(contentPane);
        contentPane.setLayout(null);


        //set gradient background
        GradientBackground gradient = new GradientBackground(new Color(156, 89, 254), new Color(111, 83, 253));
        gradient.setBounds(0, 0, 1300, 1000);
        contentPane.add(gradient);
        gradient.setLayout(null);

        // add board View Panel
        boardView.setBounds(60, 75, BoardView.TILE_SIZE * BoardModel.MAX_COL, BoardView.TILE_SIZE * BoardModel.MAX_ROW);
        gradient.add(boardView);


        // add Board action view
        boardActionView.setBounds(760, 75, 480, 640);
        gradient.add(boardActionView);

        player1Info.setBounds(60, 730, 320, 60);
        player1Info.setBackground(BoardInfoView.BACKGROUND_COLOR);
        gradient.add(player1Info);

        player2Info.setBounds(60, 0, 320, 60);
        player2Info.setBackground(BoardInfoView.BACKGROUND_COLOR);
        gradient.add(player2Info);

        // add controller
        boardActionView.btnUndo.addMouseListener(boardController.undoMove);
        boardActionView.btnGiveUp.addMouseListener(boardController.giveUp);
        boardActionView.btnHome.addMouseListener(boardController.home);
        boardView.addMouseMotionListener(boardController.pieceListener);
        boardView.addMouseListener(boardController.pieceListener);

        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon(GameView.class.getResource("/res/miniLogo.png")));
        logo.setBounds(1028, 5, 300, 70);
        gradient.add(logo);

        // set up frame
        setTitle("Chess Game");
        pack();
        setResizable(false);
        setExtendedState(JFrame.NORMAL);
        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Thread thread = new Thread(this);
        thread.start();
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public BoardActionView getBoardActionView() {
        return boardActionView;
    }

    public BoardInfoView getPlayer1Info() {
        return player1Info;
    }

    public BoardInfoView getPlayer2Info() {
        return player2Info;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                Player winner = gameModel.getWinnerByTimeOut();
                if(winner != null) {
                    boardModel.setIsEndGame();
                    String name = winner.getName();
                    new ResultView(name).setVisible(true);
                    PieceListener.playSound("soundResult.wav");
                    player1Info.getTimerModel().pauseTimer();
                    player2Info.getTimerModel().pauseTimer();
                    boardActionView.btnGiveUp.setEnabled(false);
                    boardActionView.btnUndo.setEnabled(false);
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
