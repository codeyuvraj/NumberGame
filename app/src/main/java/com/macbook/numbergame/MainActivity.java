package com.macbook.numbergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView question , correctText, scoreText, timerText, playAgain;
    Random random;
    int a , b;
    int i;
    Button goButton , button0 , button1 , button2, button3;
    int correctPosition;
    int score , totalQuestions;
    ConstraintLayout cl1 , cl2;
    ArrayList<Integer> arrayList;

    public void timer(){
        new CountDownTimer(30100 , 1000){
            @Override
            public void onTick(long l) {
                timerText.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                correctText.setText("Finished");
                question.setVisibility(View.INVISIBLE);
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                playAgain.setVisibility(View.VISIBLE);

            }
        }.start();

    }

    public void play(View view){
        question();
        answer();
        cl2.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);
       timer();

    }
    public void playAgain(View view){
        arrayList.clear();
        score = 0;
        totalQuestions = 0;
        scoreText.setText(Integer.toString(score) + "/" +  Integer.toString(totalQuestions));
        question.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        correctText.setVisibility(View.INVISIBLE);
        question();
        answer();
        timer();
    }
    public void question(){
        a = random.nextInt(41);
        b = random.nextInt(41);
        question.setText(Integer.toString(a) + " + " + Integer.toString(b));
        correctPosition = random.nextInt(4);
    }

    public void answer(){
        for(i = 0; i < 4; i++){
            if(i==correctPosition){
                arrayList.add(a+b);
            }else {
                int answer = random.nextInt(81);
                while(answer == (a+b)) {
                    answer = random.nextInt(81);
                }
                arrayList.add(answer);
            }
        }
        button0.setText(Integer.toString(arrayList.get(0)));
        button1.setText(Integer.toString(arrayList.get(1)));
        button2.setText(Integer.toString(arrayList.get(2)));
        button3.setText(Integer.toString(arrayList.get(3)));
    }

    public void choose(View view){
        String s = String.valueOf(view.getTag());
        totalQuestions++;
        correctText.setVisibility(View.VISIBLE);
        if(s.equals(Integer.toString(correctPosition))){
            score++;
            correctText.setText("Correct");
        }
        else{
            correctText.setText("Wrong");
        }
        scoreText.setText(Integer.toString(score) + "/" +  Integer.toString(totalQuestions));
        arrayList.clear();
        question();
        answer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cl1 = findViewById(R.id.constraintLayout1);
        cl2 = findViewById(R.id.constraintLayout2);
        question = findViewById(R.id.questionTextView);
        goButton = findViewById(R.id.goButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        correctText = findViewById(R.id.correctTextView);
        scoreText = findViewById(R.id.scoreTextView);
        timerText = findViewById(R.id.timerTextView);
        playAgain = findViewById(R.id.playAgainTextView);
        arrayList = new ArrayList<Integer>();
        random = new Random();
        correctPosition = random.nextInt(4);
        goButton.setVisibility(View.VISIBLE);
        cl2.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);

    }
}