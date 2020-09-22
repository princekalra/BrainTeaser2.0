package com.example.brainteaser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
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

Button goButton;
Button playAgainButton;
    int correctAnswer;
ArrayList<Integer>answers=new ArrayList<Integer>();
    Button button0;
    Button button1;
    Button button2;
    ConstraintLayout gameLayout;
    Button button3;
    TextView textView;
    public void start(View view)
    {
goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }
    int score=0;
    int numberOfQuestion=0;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;

    public void playAgain(View view)
    {
        score=0;
        resultTextView.setText("");
    numberOfQuestion=0;
        playAgainButton.setVisibility(View.INVISIBLE);
    timerTextView.setText("30s");
        newQuestion();
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        new CountDownTimer( 30100,1000)
        {
            @Override
            public void onTick(long l) {
                int a=(int)l/1000;
                if(a<10)
                {
                    timerTextView.setText("0"+Integer.toString((int)l/1000)+"s");
                }
                else
                {
                    timerTextView.setText(Integer.toString(a)+"s");

                }


            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void chooseAnswer(View view)
    {
        if(Integer.toString(correctAnswer).equals(view.getTag().toString()))
        {
            Log.i("msg","correct answer");
            score++;
            resultTextView.setText("Correct!! :)");
        }
        else
        {
            Log.i("msg","wrong answer");
            resultTextView.setText("Wrong :( ");
        }
        numberOfQuestion++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        Log.i("msg",view.getTag().toString());
        newQuestion();
    }
    public void newQuestion()
    {
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        textView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        correctAnswer=rand.nextInt(4);
answers.clear();
        for(int i=0;i<4;i++)
        {
            if(i==correctAnswer)
            {
                answers.add(a+b);
            }
            else
            {
                int wrongAnswer=rand.nextInt(41);
                while(wrongAnswer==(a+b))
                {
                    wrongAnswer=rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        textView = findViewById(R.id.questionTextView);
        goButton = findViewById(R.id.goButton);
        playAgainButton = findViewById(R.id.playAgainButton);
        timerTextView = findViewById(R.id.timerTextView);
        gameLayout=findViewById(R.id.gameLayout);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}