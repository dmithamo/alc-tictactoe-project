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

    //    Inflate 5 x 5 layout if selected
    public void loadThreeByThree(View view) {
        setContentView(R.layout.activity_three_square);
    }

    //    Assign image resource to clicked image
    public void playerOne(View view) {
//    cast clicked box into an imageView
        ImageView theBox = (ImageView) view;
        //Draw an x inside clicked box if box is empty
        if (theBox.getDrawable() == null) {
            theBox.setImageResource(R.drawable.x);

//            Find subscript text and change it to "O's move"
            TextView whoseMove = findViewById(R.id.whose_move);
            whoseMove.setText(R.string.o_s_move);

        } else {
            Toast.makeText(this, "OccupiedO! Play somewhere else!",
                    Toast.LENGTH_SHORT).show();
        }

    }


    //    Player Two's move
    public void playerTwo() {

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
