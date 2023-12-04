package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button start, answer;
    TextView questiontext, scoretext, lasttext;
    Spinner answertext;
    String[] countries = {"egypt", "england", "france", "germany", "palatine"};
    String[] capitals = {"cairo", "london", "paris", "hamburg", "kouds"};
    ArrayList<String> allcapitals;


        byte score, i;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.bustart);
        answer = findViewById(R.id.buanswer);
        scoretext = findViewById(R.id.scoretext);
        questiontext = findViewById(R.id.questiontext);
        answertext = findViewById(R.id.answertext);
        lasttext = findViewById(R.id.lasttext);
        allcapitals=new ArrayList<>();
        allcapitals.add("please select");
        allcapitals.add("cairo");
        allcapitals.add("kabul");
        allcapitals.add("hamburg");
        allcapitals.add("Algiers");
        allcapitals.add("Nigeria");
        allcapitals.add("kouds");
        allcapitals.add("paris");
        allcapitals.add("london");
        answertext.setEnabled(false);
        ArrayAdapter adapter =new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,allcapitals);
        answertext.setAdapter(adapter);
         MediaPlayer player = MediaPlayer.create(this, R.raw.truee);
        MediaPlayer player1 = MediaPlayer.create(this, R.raw.fales);
        MediaPlayer player2 = MediaPlayer.create(this, R.raw.win);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questiontext.setText("what is the capital of egypt " + countries[0]);
                scoretext.setText("");
                answertext.setEnabled(true);
                score = 0;
                i = 0;
                answer.setEnabled(true);


            }

        });
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setEnabled(false);
                if (answertext.getSelectedItem().toString().equals("please select")) {
                    Toast.makeText(MainActivity.this, "please select answer", Toast.LENGTH_SHORT).show();
                    return;
                }
                String ans = answertext.getSelectedItem().toString();
                if (ans.equals(capitals[i])) {
                    score++;
                    player.start();
                } else player1.start();
                i++;
                if (i < 4) {
                    questiontext.setText("what is the capital of " + countries[i]);
                } else {
                    if (score == 4) player2.start();
                    answer.setEnabled(false);
                    start.setEnabled(true);
                    scoretext.setText("score is " + score);


                }
                Collections.shuffle(allcapitals.subList(1,9));
                answertext.setSelection(0);
            }


        });

    }


}