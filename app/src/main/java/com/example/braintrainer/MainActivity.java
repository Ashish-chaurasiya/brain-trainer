package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    TextView sumTextView,correctTextView,timerTextView;
    int wrongAnswer;
    int locationOfCorrectAnswer;
    TextView scoreTextView;
    Button button1 ;
    Button button2 ;
    Button button3 ;
    Button button4 ;
    Button playAgin;
    int score = 0;
    int numberOfQ = 0;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        sumTextView = findViewById(R.id.sumTextView);
        correctTextView = findViewById(R.id.correctTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgin = findViewById(R.id.playAgin);
        playAgin.setVisibility(View.INVISIBLE);
        playAgains(findViewById(R.id.timerTextView));

    }


    public void playAgains( View view ) {
        score = 0;
        numberOfQ = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQ));
        newAnswer();
        playAgin.setVisibility(View.INVISIBLE);
        correctTextView.setText("");
        new CountDownTimer(30100,1000){

            @Override
            public void onTick( long l ) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override


            public void onFinish() {
                correctTextView.setText("Done!");
                playAgin.setVisibility(View.VISIBLE);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                correctTextView.setText("Your Score: "+Integer.toString(score)+"/"+Integer.toString(numberOfQ));
            }
        }.start();
    }
    public void newAnswer(){
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b)+" =");
        locationOfCorrectAnswer = rand.nextInt(4);
        answer.clear();
        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answer.add(a+b);
            }else{
                wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answer.add(wrongAnswer );
            }
        }
        button1.setText(Integer.toString(answer.get(0)));
        button2.setText(Integer.toString(answer.get(1)));
        button3.setText(Integer.toString(answer.get(2)));
        button4.setText(Integer.toString(answer.get(3)));
    }
    public void chooseAnswer( View view ) {
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            Log.i("correct","you got this");
            correctTextView.setText("Correct!");
            score++;
        }else{
            Log.i("wrong",":/");
            correctTextView.setText("Wrong :/");
        }
        numberOfQ++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQ));
        newAnswer();
    }


}