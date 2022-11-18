package com.example.week8_tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BoardView{
    BoardPresenter boardPresenter;
    TableLayout board;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boardPresenter = new BoardPresenter(this);
        board = findViewById(R.id.board);
        for(byte row = 0; row <3 ; row++){
            TableRow tableRow = (TableRow) board.getChildAt(row);
            for (byte col = 0; col <3 ; col++){
                Button button = (Button) tableRow.getChildAt(col);
                BoardPresenter.CellClickListener cellClickListener = new BoardPresenter.CellClickListener(row, col, boardPresenter);
                button.setOnClickListener(cellClickListener);
                boardPresenter.addCellClickListener(cellClickListener);
            }
        }
    }
    @Override
    public void newGame() {
        TableLayout boardView = findViewById(R.id.board);
        for(byte row = 0; row <3 ; row++){
            TableRow tableRow = (TableRow) boardView.getChildAt(row);
            for (byte col = 0; col <3 ; col++){
                Button button = (Button) tableRow.getChildAt(col);
                button.setText("");
                button.setEnabled(true);
            }
        }
    }

    @Override
    public void putSymbol(char symbol, byte row, byte col) {
        TableRow tableRow = (TableRow) board.getChildAt(row);
        Button button = (Button) tableRow.getChildAt(col);
        button.setText(Character.toString(symbol));


    }

    @Override
    public void gameEnded(byte winner) {
        for(byte row = 0; row <3 ; row++){
            TableRow tableRow = (TableRow) board.getChildAt(row);
            for (byte col = 0; col <3 ; col++){
                Button button = (Button) tableRow.getChildAt(col);
                button.setText("");
                button.setEnabled(false);
            }
        }
        switch(winner){
            case BoardView.DRAW:
                Toast.makeText(this, "Game is Draw", Toast.LENGTH_LONG).show();
                break;
            case BoardView.PLAYER_1_WINNER:
                Toast.makeText(this, "Player 1 Wins", Toast.LENGTH_LONG).show();
                break;
            case BoardView.PLAYER_2_WINNER:
                Toast.makeText(this, "Player 2 Wins", Toast.LENGTH_LONG).show();
                break;
        }

    }
    public void invalidPlay(byte row, byte col){
        Toast.makeText(this, "Invalid Move", Toast.LENGTH_LONG).show();
    }
}