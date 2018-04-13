package com.example.dennis.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);
    }


    //    Inflate 3 x 3 layout if selected
    public void loadThreeByThree(View view) {
        setContentView(R.layout.activity_three_square);
//        Make array of al boxes
        collectAllBoxes();

//        Call playerOne's method
        playerOne();
    }


    public ImageView[] collectAllBoxes() {
        //    Collect all boxes into an array, set click listener on each
//        Row 1
        ImageView box11 = findViewById(R.id.box_11);
        ImageView box12 = findViewById(R.id.box_12);
        ImageView box13 = findViewById(R.id.box_13);

        //        Row 2
        ImageView box21 = findViewById(R.id.box_21);
        ImageView box22 = findViewById(R.id.box_22);
        ImageView box23 = findViewById(R.id.box_23);

        //        Row 3
        ImageView box31 = findViewById(R.id.box_31);
        ImageView box32 = findViewById(R.id.box_32);
        ImageView box33 = findViewById(R.id.box_33);

        ImageView[] allBoxes = {
                box11, box12, box13,
                box21, box22, box23,
                box31, box32, box33,
        };
        return allBoxes;
    }


    //    Player One's move
    public void playerOne() {
//        Indicate whose move it is
        TextView whoseMove = findViewById(R.id.whose_move);
        whoseMove.setText(R.string.x_s_move);
//        Check if the Game is over
        boolean gameOver = checkGameOver();
//        Refer to boxes array
        ImageView[] allBoxes = collectAllBoxes();

        if (gameOver) {


        } else {
            for (final ImageView box : allBoxes) {
                box.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (box.getDrawable() == null) {
                            box.setImageResource(R.drawable.x);
                            playerTwo();
                        }
                    }
                });
            }

        }

    }

    //    Player Two's move
    public void playerTwo() {
//        Indicate whose move it is
        TextView whoseMove = findViewById(R.id.whose_move);
        whoseMove.setText(R.string.o_s_move);
//        Check if game is over
        boolean gameOver = checkGameOver();

        if (gameOver) {

        } else {
            //        Refer to boxes array
            ImageView[] allBoxes = collectAllBoxes();
            for (final ImageView box : allBoxes) {
                box.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (box.getDrawable() == null) {
                            box.setImageResource(R.drawable.o);
                            playerOne();
                        }
                    }
                });
            }

        }

    }

    public boolean checkGameOver() {

        //        check whether playerOne has won
        boolean playerOneWin = checkPlayerOneWin();

        //        check whether playerOne has won
        boolean playerTwoWin = checkPlayerTwoWin();

        //        Check stalemate (draw)
        boolean draw = checkDraw();

        if (playerOneWin || playerTwoWin || draw) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPlayerOneWin() {
        return false;
    }

    public boolean checkPlayerTwoWin() {
        return false;
    }

    public boolean checkDraw() {
//        ImageView[] allBoxes = collectAllBoxes();
        boolean isADraw = false;
//        for(final ImageView box : allBoxes){
//
//            // If any box is as yet unfilled, the game is not yet over
//            if(box.getDrawable() == null){
//                isADraw = false;
//            }
//            // If there are no empty boxes
//            else {
//                isADraw = true;
//            }
//        }
        return isADraw;
    }

    public void restartGame(View view) {
        //        Wipe the board
        ImageView[] allBoxes = collectAllBoxes();
        for (final ImageView box : allBoxes) {
            box.setImageDrawable(null);
            playerOne();
        }
    }

    //    Inflate 5 x 5 layout if selected
    public void loadFiveByFive(View view) {
        setContentView(R.layout.activity_five_square);
        Toast.makeText(this, "This is as yet unhandled", Toast.LENGTH_LONG).show();
    }

    //    Restart game on clicking RESET Button
    public void resetGame(View view) {
        Intent mIntent = getIntent();
        finish();
        startActivity(mIntent);
    }


}
