package com.example.dennis.tictactoe;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static java.util.Arrays.copyOfRange;

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

        // Indicate whose move it is
        TextView whoseMove = findViewById(R.id.whose_move);
        whoseMove.setText(R.string.x_s_move);

        for (final ImageView box : collectAllBoxes()) {
            box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkGameOver()) {
                        // game is over
                    } else {
                        if (box.getDrawable() == null) {
                            box.setImageResource(R.drawable.x);
                            playerTwo();
                            checkGameOver();
                        }
                    }
                }

            });
        }
    }

    //    Player Two's move
    public void playerTwo() {

        // Indicate whose move it is
        TextView whoseMove = findViewById(R.id.whose_move);
        whoseMove.setText(R.string.o_s_move);

        for (final ImageView box : collectAllBoxes()) {
            box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkGameOver()) {
                        // game is over
                    } else {
                        if (box.getDrawable() == null) {
                            box.setImageResource(R.drawable.o);
                            playerOne();
                            checkGameOver();
                        }
                    }
                }
            });
        }

    }

    public boolean checkGameOver() {
        // Boolean flag depicting game status
        // Changes to true if game is over, stopping further play
        boolean gameOver = false;
        //  Loop through array of winning combinations, check whether any has been satisfied
        for (ImageView[] line : winningCombinations()) {

            if (line[0].getDrawable() != null && line[1].getDrawable() != null && line[2].getDrawable() != null) {
                // Figure out if the game has been won
                if (line[0].getDrawable().getConstantState().equals(line[1].getDrawable().getConstantState())
                        && line[1].getDrawable().getConstantState().equals(line[2].getDrawable().getConstantState())) {
                    gameOver = true;
                    determineWhoWon(line[0]);
                }
            }
        }

        if (!gameOver) {
            gameOver = checkDraw();
        }

        return gameOver;
    }

    public void determineWhoWon(ImageView box) {

        //  Reference x and o to use for checking who won
        Drawable x = ResourcesCompat.getDrawable(getResources(), R.drawable.x, null);
        Drawable o = ResourcesCompat.getDrawable(getResources(), R.drawable.o, null);

        // Reference the text view below board, and change the text depending on who won
        TextView whoseMove = findViewById(R.id.whose_move);

        //    Figure out who won the game
        if (box.getDrawable().getConstantState().equals(x.getConstantState())) {
            whoseMove.setText(R.string.x_won);
        } else if (box.getDrawable().getConstantState().equals(o.getConstantState())) {
            whoseMove.setText(R.string.x_won);
        } else if (checkDraw()) {
            whoseMove.setText(R.string.is_a_draw);
        }

    }

    public boolean checkDraw() {
        boolean isADraw = true; // Boolean flag changes if there exists an empty box on the board

        // See if there is any empty box; if any is empty, game is not over yet
        for (ImageView box : collectAllBoxes()) {
            if (box.getDrawable() == null) {
                isADraw = false;
                break;
            }
        }

        return isADraw;
    }

    public ImageView[][] winningCombinations() {
        // Collect rows
        ImageView[] row1 = copyOfRange(collectAllBoxes(), 0, 3);
        ImageView[] row2 = copyOfRange(collectAllBoxes(), 3, 6);
        ImageView[] row3 = copyOfRange(collectAllBoxes(), 6, 9);

        // Collect columns
        ImageView[] column1 = {collectAllBoxes()[0], collectAllBoxes()[3], collectAllBoxes()[6]};
        ImageView[] column2 = {collectAllBoxes()[1], collectAllBoxes()[4], collectAllBoxes()[7]};
        ImageView[] column3 = {collectAllBoxes()[2], collectAllBoxes()[5], collectAllBoxes()[8]};

        // Collect diagonals
        ImageView[] diagonalFromLeft = {collectAllBoxes()[0], collectAllBoxes()[4], collectAllBoxes()[8]};
        ImageView[] diagonalFromRight = {collectAllBoxes()[2], collectAllBoxes()[4], collectAllBoxes()[6]};

        // Set up array of winning combinations
        ImageView[][] winningLines = {
                row1, row2, row3,
                column1, column2, column3,
                diagonalFromLeft, diagonalFromRight
        };
        return winningLines;
    }

    public void restartGame(View view) {
        //        Wipe the board
        for (ImageView box : collectAllBoxes()) {
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
