package com.example.dennis.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);
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

    //    Inflate 3 x 3 layout if selected
    public void loadThreeByThree(View view) {
        setContentView(R.layout.activity_three_square);
//        Make array of al boxes
        collectAllBoxes();

//        Call playerOne's method
        playerOneR();
    }


    //    Player One's move
    public void playerOneR() {
//        Refer to boxes array
        ImageView[] allBoxes = collectAllBoxes();
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

    //    Player Two's move
    public void playerTwo() {
//        Refer to boxes array
        ImageView[] allBoxes = collectAllBoxes();
        for (final ImageView box : allBoxes) {
            box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (box.getDrawable() == null) {
                        box.setImageResource(R.drawable.o);
                        playerOneR();
                    }
                }
            });
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
