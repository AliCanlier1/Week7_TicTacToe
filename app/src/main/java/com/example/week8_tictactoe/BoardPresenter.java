package com.example.week8_tictactoe;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoardPresenter implements BoardListener{
    BoardView boardView;
    Board board;
    List<CellClickListener> cellClickListenerList = new ArrayList<>();
    public void addCellClickListener(CellClickListener cellClickListener){
        cellClickListenerList.add(cellClickListener);
    }
    private void move(byte row, byte col){
        board.move(row,col);
    }

    public BoardPresenter(BoardView boardView) {
        this.boardView = boardView;
        board = new Board(this);
    }

    @Override
    public void playedAt(byte player, byte row, byte col) {
        if(player == BoardListener.PLAYER_1){
            boardView.putSymbol(BoardView.PLAYER_1_SYMBOL,row,col);
        }
        else if ( player == BoardListener.PLAYER_2){
            boardView.putSymbol(BoardView.PLAYER_2_SYMBOL,row,col);
        }

    }

    @Override
    public void gameEnded(byte winner) {
        switch (winner){
            case BoardListener.NO_ONE:
                boardView.gameEnded(BoardView.DRAW);
            case BoardListener.PLAYER_1:
                boardView.gameEnded(BoardView.PLAYER_1_WINNER);
            case BoardListener.PLAYER_2:
                boardView.gameEnded(BoardView.PLAYER_2_WINNER);
        }

    }

    @Override
    public void invalidPlay(byte row, byte col) {
        boardView.invalidPlay(row,col);

    }


    static class CellClickListener implements View.OnClickListener{
        byte row;
        byte col;
        BoardPresenter boardPresenter;

        public CellClickListener(byte row, byte col, BoardPresenter boardPresenter) {
            this.row = row;
            this.col = col;
            this.boardPresenter = boardPresenter;
        }

        @Override
        public void onClick(View view) {
            boardPresenter.move(row,col);

        }
    }




}
