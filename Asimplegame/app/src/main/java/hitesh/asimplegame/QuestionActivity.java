package hitesh.asimplegame;

/**
 * Created by H on 7/12/2015.
 */


import java.util.List;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;


public class QuestionActivity extends Activity {
    private static final String TAG = QuestionActivity.class.getSimpleName();

    private List<Question> questionList;
    private int score = 0;
    private int questionID = 0;

    private Question currentQ;
    private TextView txtQuestion, times, scored;
    private Button button1, button2, button3;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //버튼 추가
    //버튼 추가 아래
    private Button restart, mainback2;
    //버튼 추가 위
    //버튼 추가
    //버튼 추가
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Question incorrect;
    IncorrectDBOpenHelper incorrect_db = new IncorrectDBOpenHelper(this); // incorrect question bank class
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle bundle = getIntent().getExtras();
        int level = bundle.getInt("level");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuizDBOpenHelper db = new QuizDBOpenHelper(this,level);  // my question bank class
        questionList = db.getAllQuestions();  // this will fetch all quetonall questions
        currentQ = questionList.get(questionID); // the current question
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        // the textview in which the question will be displayed

        // the three buttons,
        // the idea is to set the text of three buttons with the options from question bank
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //버튼 추가
        //버튼 추가
        //버튼 추가 아래

        restart = (Button) findViewById(R.id.restart);
        mainback2 = (Button) findViewById(R.id.main_back2);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Soundclass.play(Soundclass.Click);

                Intent intent = new Intent(getApplicationContext(), InGameActivity.class);
                finish();
                startActivity(intent);
            }
        });

        mainback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Soundclass.play(Soundclass.Click);

                Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                finish();
                startActivity(intent);
            }
        });

        //버튼 추가 위
        //버튼 추가
        //버튼 추가
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // the textview in which score will be displayed
        scored = (TextView) findViewById(R.id.score);

        // the timer
        times = (TextView) findViewById(R.id.timers);


        // method which will set the things up for our game
        setQuestionView();
        times.setText("00:02:00");

        // A timer of 60 seconds to play for, with an interval of 1 second (1000 milliseconds)
        CounterClass timer = new CounterClass(60000, 1000);
        timer.start();

        // button click listeners
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // passing the button text to other method
                // to check whether the anser is correct or not
                // same for all three buttons
                Soundclass.play(Soundclass.Click);
                getAnswer(button1.getText().toString());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Soundclass.play(Soundclass.Click);
                getAnswer(button2.getText().toString());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Soundclass.play(Soundclass.Click);
                getAnswer(button3.getText().toString());
            }
        });
    }

    public void getAnswer(String AnswerString) {
        if (currentQ.getANSWER().equals(AnswerString)) {

            // if conditions matches increase the int (score) by 1
            // and set the text of the score view
            score++;
            scored.setText("Score : " + score);
        } else {
            // if unlucky start activity and finish the game
            Soundclass.play(Soundclass.Wow);
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            incorrect_db.addQuestion(currentQ.getQUESTION(), currentQ.getOPTA(), currentQ.getOPTB(), currentQ.getOPTC(), currentQ.getANSWER());
            Bundle incorrect = new Bundle();
            incorrect.putString("question",currentQ.getQUESTION());
            incorrect.putString("qopta",currentQ.getOPTA());
            incorrect.putString("qoptb",currentQ.getOPTB());
            incorrect.putString("qoptc",currentQ.getOPTC());
            incorrect.putString("answer",currentQ.getANSWER());
            intent.putExtras(incorrect);
            // passing the int value
            Bundle b = new Bundle();
            b.putInt("score", score); // Your score
            intent.putExtras(b); // Put your score to your next
            startActivity(intent);
            finish();
        }

        if (questionID < 20) {
            // if questions are not over then do this
            currentQ = questionList.get(questionID);
            setQuestionView();
        } else {
            // if over do this
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score); // Your score
            intent.putExtras(b); // Put your score to your next
            startActivity(intent);
            finish();
        }
    }


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer
    {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            times.setText("Time is up");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format( "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));

            Log.d(TAG, "current time: " + hms);
            times.setText(hms);
        }

    }

    private void setQuestionView() {
        // the method which will put all things together
        txtQuestion.setText(currentQ.getQUESTION());
        button1.setText(currentQ.getOPTA());
        button2.setText(currentQ.getOPTB());
        button3.setText(currentQ.getOPTC());

        questionID++;
    }


}
