package com.example.dennis.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Set up the Mode selector spinner
        Spinner modeSelector = findViewById(R.id.game_mode);
        ArrayAdapter<CharSequence> modeOptions = ArrayAdapter.createFromResource(this,
                R.array.modes, android.R.layout.simple_spinner_item);
        modeOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSelector.setAdapter(modeOptions);

//        set up Difficulty selector spinner
        Spinner difficultySelector = findViewById(R.id.game_difficulty);
        ArrayAdapter<CharSequence> difficultyOptions = ArrayAdapter.createFromResource(this,
                R.array.difficulty, android.R.layout.simple_spinner_item);
        difficultyOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySelector.setAdapter(difficultyOptions);
    }
}
