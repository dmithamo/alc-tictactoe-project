package com.example.dennis.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);

    }

    public void loadThreeByThree(View view) {
        setContentView(R.layout.activity_three_square);
    }

    public void loadFiveByFive(View view) {
        setContentView(R.layout.activity_five_square);
        Toast.makeText(this, "This is as yet unhandled", Toast.LENGTH_LONG).show();
    }

    public void resetGame(View view) {
        Intent mIntent = getIntent();
        finish();
        startActivity(mIntent);
    }
}
