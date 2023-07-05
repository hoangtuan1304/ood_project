package model;

import view.BoardView;

import java.util.List;
import java.util.Stack;

public class GameModel {

    private Stack<Move> moveHistory;
    private BoardModel boardModel;

    public static int timeGame;

    private Player[] players = new Player[2];

    public GameModel() {
        this.moveHistory = new Stack<>();
        this.boardModel = new BoardModel();
        players[0] = new Player("Player1", true);
        players[1] = new Player("Player2", false);
    }


    public GameModel(int timeGame) {
        this.timeGame = timeGame;
        this.moveHistory = new Stack<>();
        this.boardModel = new BoardModel();
        players[0] = new Player("Player1", true);
        players[1] = new Player("Player2", false);

    }

    public void addMove(Move move) {
        moveHistory.push(move);
    }

    public Stack<Move> getPastMoves() {
        return this.moveHistory;
    }

    public void undoMove() {
        if (getPastMoves().isEmpty())
            return;
        Move lastMove = getPastMoves().pop();
        Piece piece = lastMove.getPiece();
        Piece pieceInCapture = lastMove.getCapture();

        // Undo pawn's move
        if (piece.getName().equals("Pawn")) {
            if (piece.isWhite()) {
                if (lastMove.getOldRow() == 6)
                    piece.setPieceFirstMove(true);
                if (lastMove.getNewRow() == 0) {
                    boardModel.addToListPieces(piece);
                    Piece newQueen = boardModel.getPieceAt(lastMove.getNewCol(), lastMove.getNewRow());
                    boardModel.capture(newQueen);
                }
            } else {
                if (lastMove.getOldRow() == 1)
                    piece.setPieceFirstMove(true);
                if (lastMove.getNewRow() == 7) {
                    boardModel.addToListPieces(piece);
                    Piece newQueen = boardModel.getPieceAt(lastMove.getNewCol(), lastMove.getNewRow());
                    boardModel.capture(newQueen);
                }
            }
        }

        piece.setCol(lastMove.getOldCol());
        piece.setRow(lastMove.getOldRow());

        piece.setxPos(lastMove.getOldCol() * BoardView.TILE_SIZE);
        piece.setyPos(lastMove.getOldRow() * BoardView.TILE_SIZE);

        if (pieceInCapture != null) {
            boardModel.addToListPieces(pieceInCapture);
        }
        boardModel.flipTurn();
    }

    public String formatPastMovedText() {
        int i = 1;
        String text = "";
        for (Move moved : moveHistory) {
            if (moved.isMovePieceIsWhite()) {
                text += "              " + i++ + ". \t" + moved + "\t";
            } else {
                text += moved + "\n";
            }
        }
        return text;
    }

    public Player getWinner() {
        return (players[0].getColorPiece() == boardModel.getColorWin()) ? players[0] : players[1];
    }

    public boolean isGameEnd() {
        return boardModel.isGameEnd();
    }

    public Player getWinnerByGiveUp() {
        return (players[0].getColorPiece() == !boardModel.getColorWin()) ? players[0] : players[1];
    }

    public Player getWinnerByTimeOut() {
        if (players[0].isTimeOut())
            return players[1];
        if (players[1].isTimeOut())
            return players[0];
        return null;
    }

    public Player getPlayer1() {
        return players[0];
    }

    public Player getPlayer2() {
        return players[1];
    }

    public void setTimeGame(int minute) {
        timeGame = minute;
        getPlayer1().setTimer(timeGame);
        getPlayer2().setTimer(timeGame);
    }

    public Stack<Move> getMoveHistory() {
        return moveHistory;
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public Player[] getPlayers() {
        return players;
    }

    public List<Piece> getListPieces() {
        return boardModel.getListPieces();
    }

    public void addPiece() {
        boardModel.addPiece();
    }

    public Piece getSelectedPiece() {
        return boardModel.getSelectedPiece();
    }

    public boolean isValidMove(Move move) {
        return boardModel.isValidMove(move);
    }
}
