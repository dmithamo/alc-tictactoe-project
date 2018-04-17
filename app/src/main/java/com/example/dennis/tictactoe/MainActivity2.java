package com.example.dennis.tictactoe;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static java.util.Arrays.copyOfRange;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_square);
        collectAllBoxes();
        playerOne();
    }

    public ImageView[] collectAllBoxes() {
        //    Collect all boxes into an array, set click listener on each
//        Row 1
        ImageView box11 = findViewById(R.id.box_11);
        ImageView box12 = findViewById(R.id.box_12);
        ImageView box13 = findViewById(R.id.box_13);
        ImageView box14 = findViewById(R.id.box_14);
        ImageView box15 = findViewById(R.id.box_15);

        //        Row 2
        ImageView box21 = findViewById(R.id.box_21);
        ImageView box22 = findViewById(R.id.box_22);
        ImageView box23 = findViewById(R.id.box_23);
        ImageView box24 = findViewById(R.id.box_24);
        ImageView box25 = findViewById(R.id.box_25);

        //        Row 3
        ImageView box31 = findViewById(R.id.box_31);
        ImageView box32 = findViewById(R.id.box_32);
        ImageView box33 = findViewById(R.id.box_33);
        ImageView box34 = findViewById(R.id.box_34);
        ImageView box35 = findViewById(R.id.box_35);

        //        Row 4
        ImageView box41 = findViewById(R.id.box_41);
        ImageView box42 = findViewById(R.id.box_42);
        ImageView box43 = findViewById(R.id.box_43);
        ImageView box44 = findViewById(R.id.box_44);
        ImageView box45 = findViewById(R.id.box_45);

        //        Row 5
        ImageView box51 = findViewById(R.id.box_51);
        ImageView box52 = findViewById(R.id.box_52);
        ImageView box53 = findViewById(R.id.box_53);
        ImageView box54 = findViewById(R.id.box_54);
        ImageView box55 = findViewById(R.id.box_55);

        ImageView[] allBoxes = {
                box11, box12, box13, box14, box15,
                box21, box22, box23, box24, box25,
                box31, box32, box33, box34, box35,
                box41, box42, box43, box44, box45,
                box51, box52, box53, box54, box55,
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
                    if (!checkGameOver()) {
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
                    if (!checkGameOver()) {
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
        boolean gameOver = false;

        //  Loop through array of winning combinations, check whether any has been satisfied
        for (ImageView[] line : winningCombinations()) {

            if (line[0].getDrawable() != null && line[1].getDrawable() != null
                    && line[2].getDrawable() != null && line[3].getDrawable() != null
                    && line[4].getDrawable() != null) {

                // Figure out if the game has been won, and by who
                if (line[0].getDrawable().getConstantState().equals(line[1].getDrawable().getConstantState())
                        && line[1].getDrawable().getConstantState().equals(line[2].getDrawable().getConstantState())
                        && line[2].getDrawable().getConstantState().equals(line[3].getDrawable().getConstantState())
                        && line[3].getDrawable().getConstantState().equals(line[4].getDrawable().getConstantState())) {
                    gameOver = true;
                    determineWhoWon(line[0]);
                }
            }
        }

        if (!gameOver) {
            gameOver = checkDraw();
        }

        // Make boxes un-clickable once game is over
        if (gameOver) {
            for (ImageView box : collectAllBoxes()) {
                box.setOnClickListener(null);
            }
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

            // Update x scores
            TextView xScores = findViewById(R.id.x_wins);
            int currentXScore = Integer.valueOf(xScores.getText().toString());
            xScores.setText(Integer.toString((currentXScore + 1)));

        } else if (box.getDrawable().getConstantState().equals(o.getConstantState())) {
            whoseMove.setText(R.string.o_won);

            // Update o scores
            TextView oScores = findViewById(R.id.o_wins);
            int currentOScore = Integer.valueOf(oScores.getText().toString());
            oScores.setText(Integer.toString((currentOScore + 1)));

        }
    }

    public boolean checkDraw() {
        // Boolean flag changes if there exists an empty box on the board
        boolean isADraw = true;

        // See if there is any empty box; if any is empty, game is not over yet
        for (ImageView box : collectAllBoxes()) {
            if (box.getDrawable() == null) {
                isADraw = false;
                break;
            }
        }
        if (isADraw) {
            TextView whoseMove = findViewById(R.id.whose_move);
            whoseMove.setText(R.string.is_a_draw);

            // Update draws
            TextView draws = findViewById(R.id.draws);
            int currentDraws = Integer.valueOf(draws.getText().toString());
            draws.setText(Integer.toString((currentDraws + 1)));
        }
        return isADraw;
    }

    public ImageView[][] winningCombinations() {
        // Collect rows
        ImageView[] row1 = copyOfRange(collectAllBoxes(), 0, 5);
        ImageView[] row2 = copyOfRange(collectAllBoxes(), 5, 10);
        ImageView[] row3 = copyOfRange(collectAllBoxes(), 10, 15);
        ImageView[] row4 = copyOfRange(collectAllBoxes(), 15, 20);
        ImageView[] row5 = copyOfRange(collectAllBoxes(), 20, 25);

        // Collect columns
        ImageView[] column1 = {
                collectAllBoxes()[0], collectAllBoxes()[5], collectAllBoxes()[10],
                collectAllBoxes()[15], collectAllBoxes()[20]
        };
        ImageView[] column2 = {
                collectAllBoxes()[1], collectAllBoxes()[6], collectAllBoxes()[11],
                collectAllBoxes()[16], collectAllBoxes()[21]
        };
        ImageView[] column3 = {
                collectAllBoxes()[2], collectAllBoxes()[7], collectAllBoxes()[12],
                collectAllBoxes()[17], collectAllBoxes()[22]
        };
        ImageView[] column4 = {
                collectAllBoxes()[3], collectAllBoxes()[8], collectAllBoxes()[13],
                collectAllBoxes()[18], collectAllBoxes()[23]
        };
        ImageView[] column5 = {
                collectAllBoxes()[4], collectAllBoxes()[9], collectAllBoxes()[14],
                collectAllBoxes()[19], collectAllBoxes()[24]
        };

        // Collect diagonals
        ImageView[] diagonalFromLeft = {
                collectAllBoxes()[0], collectAllBoxes()[6], collectAllBoxes()[12],
                collectAllBoxes()[18], collectAllBoxes()[24]
        };
        ImageView[] diagonalFromRight = {
                collectAllBoxes()[4], collectAllBoxes()[8], collectAllBoxes()[12],
                collectAllBoxes()[16], collectAllBoxes()[20]
        };

        // Set up array of winning combinations
        ImageView[][] winningLines = {
                row1, row2, row3, row4, row5,
                column1, column2, column3, column4, column5,
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

    //    Restart game on clicking RESET Button
    public void resetGame(View view) {
        Intent homepageIntent = new Intent(this, MainActivity.class);
        finish();
        startActivity(homepageIntent);
    }
}
